package artifact;

import java.net.InetSocketAddress;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import cartago.Artifact;
import cartago.INTERNAL_OPERATION;
import websocket.WebSocketChannel;

public class WsObjectArtifact extends Artifact implements WsArtifactInterface {

    private WebSocketChannel server;
    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();

    void init() {
        String host = "localhost";
        int port = 8888; // Choose a port number
        InetSocketAddress address = new InetSocketAddress(host, port);
        server = new WebSocketChannel(address);
        server.setWsServerArtifact(this);
        server.start();
        System.out.println("WebSocket server started on port: " + server.getPort());
    }

    @Override
    public void signalNewMessageToJaCaMo(String message) {
        System.out.println("Messaggio arrivato: " + message);
        if (message.equals("pedana_pressed")) {
            execInternalOp("signalAgent", "box_pressed");
        }
    }

    @INTERNAL_OPERATION
    public void signalAgent(String message) {
        lock.lock();
        try {
            signal("event_happened", message);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void sendMessageToUnity(String msg) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'sendMessageToUnity'");
    }

}
