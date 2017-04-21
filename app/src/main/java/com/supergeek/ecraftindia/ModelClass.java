package com.supergeek.ecraftindia;

/**
 * Created by Junejas on 4/21/2017.
 */

public class ModelClass {
    String name;
    int price;
    String image;

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

    public ModelClass(String name, int price, String image) {

        this.name = name;
        this.price = price;
        this.image = image;
    }
}
