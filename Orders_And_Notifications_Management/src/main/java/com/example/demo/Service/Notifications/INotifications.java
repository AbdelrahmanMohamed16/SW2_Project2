package com.example.demo.service.Notifications;

import com.example.demo.Repo.inMemory;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Queue;

public interface INotifications {
    public String getMostUsedEmail();
    public String getMostUsedSMS();
    public String getMostUsedSMSandEmail();
    public Queue getAllNotifications();
    public void notificationsSimulations();
    public String getMostUsedTemplate();
}
