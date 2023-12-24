package com.example.demo.model;

public class User {
       private String ID ;
       private String Name ;
       private double Balance ;
       private String Email ;
       private String mobileNumber ;
       private String Language ;

       private double Longitude ;
       private double Latitude ;

    public User(){

    }
    public User(String ID, String name, double balance, String email, String mobileNumber, String language, double longitude, double latitude) {
        this.ID = ID;
        Name = name;
        Balance = balance;
        Email = email;
        this.mobileNumber = mobileNumber;
        Language = language;
        Longitude = longitude;
        Latitude = latitude;
    }
    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public double getBalance() {
        return Balance;
    }

    public void setBalance(double balance) {
        Balance = balance;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getLanguage() {
        return Language;
    }

    public void setLanguage(String language) {
        Language = language;
    }

    public double getLongitude() {
        return Longitude;
    }

    public void setLongitude(double longitude) {
        Longitude = longitude;
    }

    public double getLatitude() {
        return Latitude;
    }

    public void setLatitude(double latitude) {
        Latitude = latitude;
    }
}
