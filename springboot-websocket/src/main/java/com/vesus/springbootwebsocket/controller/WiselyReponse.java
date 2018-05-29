package com.vesus.springbootwebsocket.controller;

/**
 * @Description:
 * @Author: vesus
 * @CreateDate: 2018/5/29 下午7:08
 * @Version: 1.0
 */
public class WiselyReponse {

    private String responseMessage;

    public WiselyReponse(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }
}
