package com.techelevator;

public class Drink extends Item {
        private final String sound= "Glug Glug, yum!";

        public Drink( String position,String name,  double price) {
                super(position, name, price);
        }


        public String getSound(){
                return sound;
        }



}





