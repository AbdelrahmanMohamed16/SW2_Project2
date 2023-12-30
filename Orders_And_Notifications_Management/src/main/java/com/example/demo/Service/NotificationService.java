package com.example.demo.Service;

import com.example.demo.Repo.inMemory;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    public String getMostUsedEmail()
    {
        int mx = 0 ;
        String user="";
        for(String key : inMemory.mostUsedEmail.keySet())
        {
            if(inMemory.mostUsedEmail.get(key)>=mx)
            {
                mx = inMemory.mostUsedEmail.get(key);
                user = key;
            }
        }
        return user ;


    }
    public String getMostUsedSMS()
    {
        int mx = 0 ;
        String user="";
        for(String key : inMemory.mostUsedPhone.keySet())
        {
            if(inMemory.mostUsedPhone.get(key)>=mx)
            {
                mx = inMemory.mostUsedPhone.get(key);
                user = key;
            }
        }
        return user ;


    }
    public String getMostUsedSMSandEmail()
    {
        int mx = 0 ;
        String user="";
        for(String key : inMemory.mostUsedPhoneAndEmail.keySet())
        {
            if(inMemory.mostUsedPhoneAndEmail.get(key)>=mx)
            {
                mx = inMemory.mostUsedPhoneAndEmail.get(key);
                user = key;
            }
        }
        return user ;


    }
}
