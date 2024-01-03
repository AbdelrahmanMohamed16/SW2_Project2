package com.example.demo.model;

public class Email implements Channel{
    private String receiver;
    public String getReceiver() {
        return receiver;
    }
    @Override
    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    @Override
    public String sendNotification(String subject , String content) {
        return "from: Email    To: " +getReceiver()+"    subject: "+subject+"     content"+content;
    }
}
