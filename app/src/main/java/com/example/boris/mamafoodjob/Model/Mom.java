package com.example.boris.mamafoodjob.Model;

import android.graphics.Bitmap;

public class Mom {
    private String name;
    private String description;
    private String date;
    private String image;
    private String passport;
    private String email;

    public Mom(String name, String description, String date, String image, String passport, String email) {
        this.name = name;
        this.description = description;
        this.date = date;
        this.image = image;
        this.passport = passport;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }

    public String getImage() {
        return image;
    }

    public String getPassport() {
        return passport;
    }
}
