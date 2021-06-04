package com.techelevator.view;

import com.techelevator.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VendingMachine {

    private List<Item> itemOptions = new ArrayList<Item>();


    public VendingMachine() {
        loadItemsFromCSV();
    }
    private void loadItemsFromCSV() {
        File inputFile = new File("vendingmachine.csv");
        try (Scanner inputScanner = new Scanner(inputFile)) {
            while (inputScanner.hasNextLine()) {
                String line = inputScanner.nextLine();
                String[] splitLine = line.split("\\|");
                double price = Double.parseDouble(splitLine[2]);
                if (splitLine[3].equals("Chip")) {
                    Chips c = new Chips(splitLine[0], splitLine[1], price);
                    itemOptions.add(c);
                } else if (splitLine[3].equals("Candy")) {
                    Candy c = new Candy(splitLine[0], splitLine[1], price);
                    itemOptions.add(c);
                } else if (splitLine[3].equals("Drink")) {
                    Drink d = new Drink(splitLine[0], splitLine[1], price);
                    itemOptions.add(d);
                } else {
                    Gum g = new Gum(splitLine[0], splitLine[1], price);
                    itemOptions.add(g);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.printLn("Couldn't find file");
        }
    }



    public List<Item> getItemOptions() {
        return itemOptions;
    }


}
























        }
