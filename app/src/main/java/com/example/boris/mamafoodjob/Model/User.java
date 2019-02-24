package com.example.boris.mamafoodjob.Model;

public class User {
    String name;
    String description;
    String image, date;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public String getAdress() {
        return adress;
    }
    public String getDate() {
        return date;
    }

    public User(String name, String description, String image, String adress, String date) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.adress = adress;
        this.date = date;
    }

    String adress;

}
