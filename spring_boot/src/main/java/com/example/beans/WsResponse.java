package com.example.beans;

/**
 * Created by zhougb on 2016/8/16.
 */
public class WsResponse {
    private String responseMessage;

    public WsResponse(String msg){
        responseMessage = msg;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }
}
