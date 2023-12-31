package com.example.demo.controller;

import com.example.demo.Notification_Handler.Notification;
import com.example.demo.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Queue;

@RestController
@RequestMapping("/api")
public class notificationController {
    @Autowired
    NotificationService service ;
    @GetMapping(value = "/mostUsedEmail")
    public String getMostUsedEmail()
    {
        return service.getMostUsedEmail();
    }
    @GetMapping(value = "/mostUsedSMS")
    public String getMostUsedSMS()
    {
        return service.getMostUsedSMS();
    }
    @GetMapping(value = "/mostUsedEmailandSMS")
    public String getMostUsedSMSandEmail()
    {
        return service.getMostUsedSMSandEmail();
    }
    @GetMapping("/notifications")
    public Queue getAllNotifications(){
        return service.getAllNotifications();
    }
    @GetMapping("/MostUsedTemplate")
    public String MostUsedTemplate(){
        return service.getMostUsedTemplate();
    }
}
