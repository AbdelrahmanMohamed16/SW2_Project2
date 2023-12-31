package com.example.demo.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import com.example.demo.Repo.inMemory;

import java.util.Queue;

@Service
public class NotificationService {
    public String getMostUsedEmail()
    {
        int mx = -1 ;
        String email="";
        for(String key : inMemory.mostUsedPhoneAndEmail.keySet())
        {
            int value = inMemory.mostUsedPhoneAndEmail.get(key) ;
            if( value > mx && key.contains("@"))
            {
                mx = value;
                email = key;
            }
        }
        return email ;


    }
    public String getMostUsedSMS()
    {
        int mx = -1 ;
        String number="";
        for(String key : inMemory.mostUsedPhoneAndEmail.keySet())
        {
            int value = inMemory.mostUsedPhoneAndEmail.get(key) ;
            if( value > mx && !key.contains("@"))
            {
                mx = value;
                number = key;
            }
        }
        return number ;
    }
    public String getMostUsedSMSandEmail()
    {
        int mx = -1 ;
        String email_or_number="";
        for(String key : inMemory.mostUsedPhoneAndEmail.keySet())
        {
            int value = inMemory.mostUsedPhoneAndEmail.get(key) ;
            if( value > mx)
            {
                mx = value;
                email_or_number = key;
            }
        }
        return email_or_number ;

    }
    public Queue getAllNotifications(){
        return inMemory.Notifications;
    }
    @Scheduled(fixedRate = 30000) // Run every 30 seconds
    public void notificationsSimulations(){
        // simulate it Send
        if(inMemory.Notifications.size() != 0){
            inMemory.Notifications.remove();

        }
    }
    public String getMostUsedTemplate(){
        int mx = -1 ;
        String temp="";
        for(String key : inMemory.MostUsedTemplate.keySet())
        {
            int value = inMemory.MostUsedTemplate.get(key) ;
            if( value > mx)
            {
                mx = value;
                temp = key;
            }
        }
        return temp ;
    }
}
