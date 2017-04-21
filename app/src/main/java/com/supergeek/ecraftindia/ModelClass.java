package com.supergeek.ecraftindia;

/**
 * Created by Junejas on 4/21/2017.
 */

public class ModelClass {
    String name;
    int price;
    String image;
    String decription;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDecription() {
        return decription;
    }

    public void setDecription(String decription) {
        this.decription = decription;
    }

    public ModelClass(String name, int price, String image, String decription) {

        this.name = name;
        this.price = price;
        this.image = image;
        this.decription = decription;
    }
}
