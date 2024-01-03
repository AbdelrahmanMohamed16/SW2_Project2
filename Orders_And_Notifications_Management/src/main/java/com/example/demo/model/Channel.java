package com.example.demo.model;

public interface Channel {
    public String sendNotification(String subject , String content);
    public void setReceiver(String receiver);

}
