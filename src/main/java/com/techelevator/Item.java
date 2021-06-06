package com.techelevator;

public abstract class Item {
    private double price;
    private String name;
    private String position;
    private int numInMachine = 5;

    public Item(String position, String name ,double price){
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

    public String getPosition() {
        return position;}
    public int getNumInMachine() {
        return numInMachine;
    }

}
