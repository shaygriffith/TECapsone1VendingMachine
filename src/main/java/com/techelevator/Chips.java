package com.techelevator;


public class Chips extends Item {
    private final String sound = "Crunch Crucnh, yum!";

    public Chips( String position,String name,  double price) {
        super(position, name, price);
    }



        public String getSound(){

        return sound;
        }
    }

