package com.techelevator;

import java.io.FileWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FinishTransaction extends Transaction {

    private Double balance;
    private Double quarter = 0.25;
    private Double dime = 0.10;
    private Double nickle = 0.05;
    private int numberOfQuarters;
    private int numberOfDimes;
    private int numberOfNickels;



    public FinishTransaction(InputStream input, OutputStream output) {
        super(input, output);
        this.balance = 0.0;
    }

    public void makeChange(Purchase purchase) {
        balance = purchase.getBalance();
        Double initialBalance = purchase.getBalance();
        try (FileWriter writer = new FileWriter("log.txt", true);
             PrintWriter print = new PrintWriter(writer) {

                 Date date = new Date();
                 SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss a");  //audit entry in format

                 if(balance >=quarter){
                     numberOfQuarters++;
                     balance -= quarter;
                 }
                 if(balance >=dime){
                     numberOfDimes++;
                     balance -= dime;
                 }
                 if(balance >=nickle){
                     numberOfNickels++;
                     balance -= nickle;
                 }
                 System.out.println("Your change is: "+numberOfDimes +" dimes"+numberOfNickels +"  nickles"+numberOfQuarters +" quarters");
             }

             } catch(Exception e){
        e.printStackTrace();
    }


    public Double getBalance() {
        return balance;
    }
    public int getNumberOfQuarters() {
        return numberOfQuarters;
    }

    public int getNumberOfDimes() {
        return numberOfDimes;
    }

    public int getNumberOfNickels() {
        return numberOfNickels;
    }


}
