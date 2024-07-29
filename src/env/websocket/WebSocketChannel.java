package websocket;

// Websockets
import java.net.InetSocketAddress;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import example.WsServerArtifact;

public class WebSocketChannel extends WebSocketServer{

    private WebSocket webSocketClients;
    private WsServerArtifact wsServerArtifact;

    public WebSocketChannel(InetSocketAddress address){
        super(address);
    }

    @Override // Open connection and send welcome message to the clients that are listening on the channel
    public void onOpen(WebSocket conn, ClientHandshake handshake){
        // Save connection info
        webSocketClients = conn;
        // Send welcome to the client
        conn.send("I'm the client!");
    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote){
        System.out.println("closed " + conn.getRemoteSocketAddress() + " with exit code " + code + " additional info " + reason);
    }

    @Override // Received message to the client
	public void onMessage(WebSocket conn, String message) {
        wsServerArtifact.signalNewMessage(message);                
	}

	@Override
	public void onError(WebSocket conn, Exception ex) {
		System.err.println("an error occurred on connection " + conn.getRemoteSocketAddress()  + ":" + ex);
	}
	
	@Override
	public void onStart() {
		System.out.println("server started successfully");
	}

    public void sendMessage(String msg){
        System.out.println("Sending message");
        webSocketClients.send(msg);
    }

    public void setWsServerArtifact(WsServerArtifact wsServerArtifact){
        this.wsServerArtifact = wsServerArtifact;
    }

}
