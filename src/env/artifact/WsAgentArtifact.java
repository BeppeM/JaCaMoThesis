package artifact;

import java.net.InetSocketAddress;

import cartago.Artifact;
import cartago.INTERNAL_OPERATION;
import cartago.OPERATION;
import websocket.WebSocketChannel;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

public class WsAgentArtifact extends Artifact implements WsArtifactInterface {

    private WebSocketChannel server;
    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();

    void init() {
        String host = "localhost";
        int port = 8887; // Choose a port number
        InetSocketAddress address = new InetSocketAddress(host, port);
        server = new WebSocketChannel(address);
        server.setWsServerArtifact(this);
        server.start();
        System.out.println("WebSocket server started on port: " + server.getPort());
    }

    @OPERATION
    @Override
    public void sendMessageToUnity(String msg) {
        server.sendMessage(msg);        
    }

    public void signalNewMessageToJaCaMo(String message) {
        // Trigger an internal operation to handle the signal
        execInternalOp("signalAgent", message);
    }

    @INTERNAL_OPERATION
    public void signalAgent(String message) {
        lock.lock();
        try{
            signal("received_message", message);
        }finally{
            lock.unlock();
        }
    }
}
