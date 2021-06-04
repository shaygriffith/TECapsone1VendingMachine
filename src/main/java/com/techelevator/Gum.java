package com.techelevator;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Gum extends Item{
    private String sound;

    public Gum(String name, double price, String position) {
        super(name, price, position);
    }

    public String getSound(){
        return "Glug Glug, yum!";
    }


}