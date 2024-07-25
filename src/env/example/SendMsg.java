// CArtAgO artifact code for project my1st_app

package example;

import cartago.*;
import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;

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
            sendMsgClient.sendMessage("move");
        } catch (Exception e) {
            e.printStackTrace();
            failed("Errore: " + e.getMessage());
        }
    }

}
