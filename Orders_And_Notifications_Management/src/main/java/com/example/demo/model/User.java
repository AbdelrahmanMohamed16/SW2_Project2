package com.example.demo.model;

import com.example.demo.Repo.inMemory;
import com.fasterxml.jackson.annotation.JsonProperty;

public class User {
    @JsonProperty("ID")
    private String ID;

    @JsonProperty("Name")
    private String Name;

    @JsonProperty("Balance")
    private double Balance;

    @JsonProperty("Email")
    private String Email;

    @JsonProperty("mobileNumber")
    private String mobileNumber;

    @JsonProperty("Language")
    private String Language;

    @JsonProperty("Password")
    private String Password;

    @JsonProperty("Longitude")
    private double Longitude;

    @JsonProperty("Latitude")
    private double Latitude;

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

    public boolean checkPassword(String id , String password){
        return inMemory.persons.get(id).Password.equals(password);
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

    private String getPassword() {
        return Password;
    }

}
