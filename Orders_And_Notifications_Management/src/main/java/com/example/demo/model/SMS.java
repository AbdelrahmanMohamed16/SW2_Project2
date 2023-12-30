package com.example.demo.model;

public class SMS implements Channel{
    private String receiver;

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    @Override
    public String sendNotification(String subject , String content) {
        return "from: SMS    To: " +getReceiver()+"    subject: "+subject+"     content"+content;
    }
}