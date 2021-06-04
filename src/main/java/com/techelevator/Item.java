package com.techelevator;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public abstract class Item {
    private double price;
    private String name;
    private String position;
    private int numInMachine = 5;

    public Item(String name, double price, String position){
        this.name = name;
        this.price = price;
        this.position = position;

    }




    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public abstract String getSound();

    public int getPosition() {
        return position;}

}
