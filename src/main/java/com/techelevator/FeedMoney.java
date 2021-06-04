package com.techelevator;

import java.io.FileWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.io.PrintWriter;

public class FeedMoney extends Transaction {

    private Double balance;
    private Scanner in;
    private String choice;
    private Double oneDollar = 1.0;
    private Double fiveDollars = 5.0;
    private Double tenDollars = 10.0;

    public FeedMoney(InputStream input, OutputStream) {
        super(input, output);
        this.balance = balance;
        this.in = new Scanner input;
    }

    public Double getBalance() {
        return balance;
    }

    public void feedMoney(String choice) {
        try (FileWriter writer = new FileWriter("log.txt", true);
             PrintWriter print = new PrintWriter(writer)) {


            Date date = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss a");

            if (choice.equals("Feed $1")) {
                balance += oneDollar;
                System.out.println("Your balance is: " + balance);
            } else if (choice.equals("Feed $5")) {
                balance += fiveDollars;
                System.out.println("Your balance is: " + balance);
            } else if (choice.equals("Feed $10")) {
                balance += tenDollars;
                System.out.println("Your balance is: " + balance);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Double updateBalance(Double purchase) {
        balance -= purchase;
        return balance;
    }
}
