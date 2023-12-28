package com.example.demo.model;

import com.example.demo.Repo.inMemory;
import com.fasterxml.jackson.annotation.JsonProperty;

public class User {
//    @JsonProperty("ID")
//    public String ID;

    @JsonProperty("Name")
    public String Name;

    @JsonProperty("Balance")
    public double Balance;

    @JsonProperty("Email")
    public String Email;

    @JsonProperty("mobileNumber")
    public String mobileNumber;

    @JsonProperty("Language")
    public String Language;

    @JsonProperty("Password")
    private String Password;

    @JsonProperty("Longitude")
    public double Longitude;

    @JsonProperty("Latitude")
    public double Latitude;

    public boolean isLoggedUser = false;
    public User(){

    }
    public User(String name, double balance, String email, String mobileNumber, String language, double longitude, double latitude) {
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

//    public void setID(String ID) {
//        this.ID = ID;
//    }

    public void setName(String name) {
        Name = name;
    }

    public void setBalance(double balance) {
        Balance = balance;
    }


    public void setEmail(String email) {
        Email = email;
    }


    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }


    public void setLanguage(String language) {
        Language = language;
    }


    public void setLongitude(double longitude) {
        Longitude = longitude;
    }

    public void setLatitude(double latitude) {
        Latitude = latitude;
    }

    private String getPassword() {
        return Password;
    }

}
