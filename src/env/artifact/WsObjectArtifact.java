package artifact;

import java.net.InetSocketAddress;

import cartago.Artifact;
import websocket.WebSocketChannel;

public class WsObjectArtifact extends Artifact implements WsArtifactInterface {

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

    @Override
    public void signalNewMessageToJaCaMo(String message) {
        // TODO Auto-generated method stub
    }

    @Override
    public void sendMessageToUnity(String msg) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'sendMessageToUnity'");
    }

}
