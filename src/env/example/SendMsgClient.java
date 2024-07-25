package example;

import feign.Headers;
import feign.RequestLine;
import feign.Response;

public interface SendMsgClient {

    @RequestLine("POST /")
    @Headers("Content-Type: application/json")
    Response sendMessage(String msg);
    
}
