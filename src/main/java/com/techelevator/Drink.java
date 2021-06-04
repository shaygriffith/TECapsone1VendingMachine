package com.techelevator;

public class Drink extends Item {

        public Drink(String name, double price, String position) {
                super(name, price, position);
        }


        public String getSound(){
                return "Glug Glug, yum!";
        }



}





