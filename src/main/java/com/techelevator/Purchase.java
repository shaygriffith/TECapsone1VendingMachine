package com.techelevator;

import com.techelevator.view.Menu;
import com.techelevator.view.VendingMachine;

import java.io.FileWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Purchase extends Menu {
    private PrintWriter out;
    private Scanner in;
    private String userInput;
    private Double balance;

    public Purchase(InputStream input, OutputStream output) {  //input & output classes shared with feedMoney && Transaction
        super(input, output);
        this.out = new PrintWriter(output);
        this.in = new Scanner(input);
        this.balance = 0.0;
    }

    public void selectItem(VendingMachine vendingMachine, FeedMoney feedMoney) {
        balance = feedMoney.getBalance();
        boolean isInvalid = true;
        while (isInvalid) {
            System.out.println("Select position of the item");  //selecting product
            String userInput = in.nextLine();

            try (FileWriter writer = new FileWriter("log.txt", true);   //generates the purchase into log.txt
                 PrintWriter print = new PrintWriter(writer)) {

                //audit entry in format
                Date date = new Date();
                SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss a");

                //if they choose an invalid selection
                if (vendingMachine.getVendingMachine().get(userInput) == null) {
                    System.out.println("Invalid selection");

                    //if the balance is zero, prompts to enter money
                } else if (balance == 0) {
                    System.out.println("Feed money to make selection");
                    break;
                }
                //if what they are selecting is more than their balance, prompts user to enter more money
                if (vendingMachine.getVendingMachine().get(userInput).getPrice() > balance) {
                    System.out.println("Need feed money to continue");
                    break;
                }
                // updating balance
                balance = balance - vendingMachine.getVendingMachine().get(userInput).getPrice();
                feedMoney.updateBalance(vendingMachine.getVendingMachine().get(userInput).getPrice());
                System.out.println("Your remaining balance is: " + balance);
                isInvalid = false;

            } catch (Exception e){
                e.printStackTrace();
            }
        }


    }
    public Double getBalance(){
        return balance;
    }
    public String getUserInput() {
        return userInput;
    }

    public void setUserInput(String userInput) {
        this.userInput = userInput;
    }

}
