package com.example.demo.Notification_Handler;

import com.example.demo.model.Channel;
import com.example.demo.model.Order;
import com.example.demo.model.User;

import java.util.ArrayList;

public abstract class Notification {
    private Channel channel;
    private String subject;
    private String content;
    private String language;

    public Notification(Channel channel,String language) {
        this.channel = channel;
        this.language = language;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Channel getChannel() {
        return channel;
    }
    public void setChannel(Channel channel){
        this.channel = channel;
    }


    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String send(){
        return channel.sendNotification(getSubject() , getContent());
    }

    public abstract void generateContent(User user, Order order);
}
