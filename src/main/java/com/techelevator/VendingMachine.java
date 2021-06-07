package com.techelevator;

import com.techelevator.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class VendingMachine {
    private Double balance = 0.00;
    private Scanner in;
    private String choice;
    private static final Double ONE_DOLLAR = 1.0;
    private static final Double FIVE_DOLLARS = 5.0;
    private static final Double TEN_DOLLARS = 10.0;
    private VendingMachineCLI vendingMachineCLI;


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
                String position = splitLine[0];
                String name = splitLine[1];
                double price = Double.parseDouble(splitLine[2]);
                if (splitLine[3].equals("Chip")) {
                    Chips c = new Chips(splitLine[0], splitLine[1], price);
                  //  Chips c = new Chips(position, name, price);
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
            System.err.print("Couldn't find file");
        }
    }



    public Double getBalance() {
        return balance;
    }

    public void feedMoney(Double choice) {
        balance += choice;
    }

    public Double updateBalance(Double purchase) {
        balance -= purchase;
        return balance;
    }
    public List<Item> getItemOptions(){
        return itemOptions;
 }
}


