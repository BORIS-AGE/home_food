package com.example.boris.mamafoodjob.Model;

import android.graphics.Bitmap;

public class Mom {
    private String name;
    private String description;
    private String date;
    private Bitmap image;
    private Bitmap passport;

    public Mom(String name, String description, String date, Bitmap image, Bitmap passport) {
        this.name = name;
        this.description = description;
        this.date = date;
        this.image = image;
        this.passport = passport;
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

    public Bitmap getImage() {
        return image;
    }

    public Bitmap getPassport() {
        return passport;
    }
}
