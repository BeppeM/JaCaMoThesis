package example;

import java.net.InetSocketAddress;

import cartago.Artifact;
import cartago.INTERNAL_OPERATION;
import cartago.OPERATION;
import websocket.WebSocketChannel;

public class WsServerArtifact extends Artifact {

    private WebSocketChannel server;

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
    void sendMessage(String msg) {
        server.sendMessage(msg);
    }

    public void signalNewMessage(String message) {
        // Trigger an internal operation to handle the signal
        execInternalOp("signalAgent", message);
    }

    @INTERNAL_OPERATION
    public void signalAgent(String message) {
        signal("received_message", message);
    }
}
