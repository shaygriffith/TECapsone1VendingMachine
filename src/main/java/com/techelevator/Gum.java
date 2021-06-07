package com.techelevator;



public class Gum extends Item{
    private String sound = "Chew Chew yum!";

    public Gum( String position,String name,  double price) {
        super(position, name, price);
    }

    public String getSound(){
        return sound;
    }


}
