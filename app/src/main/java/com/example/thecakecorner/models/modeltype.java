package com.example.thecakecorner.models;

public class modeltype {

private byte[] image;

    private String name;

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public modeltype(String name, byte[] image) {

        this.image = image;
        this.name = name;
    }





    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
