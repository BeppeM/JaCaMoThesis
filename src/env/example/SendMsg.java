// CArtAgO artifact code for project my1st_app

package example;

import cartago.*;
import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.Response;

public class SendMsg extends Artifact {

    private SendMsgClient sendMsgClient;

    void init(int initialValue) {
        sendMsgClient = Feign.builder()
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .target(SendMsgClient.class, "http://127.0.0.1:8080/");
    }

    @OPERATION
    void sendMessage(String msg) {
        try {
            Response response = sendMsgClient.sendMessage("move");

            if (response.status() == 200) {
                String responseBody = response.body().toString();
                System.out.println("Ritornata la risposta correttamente!!");
                // Notify the agent with the response body
                signal("responseReceived", responseBody);
            } else {
                failed("Failed to send message: " + response.status());
            }
        } catch (Exception e) {
            e.printStackTrace();
            failed("Errore: " + e.getMessage());
        }
    }

}
