package com.techelevator;

import com.techelevator.view.Menu;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class VendingMachineCLI {

    private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
    private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
    private static final String MAIN_MENU_OPTION_EXIT = "Exit";
    private static final String[] MAIN_MENU_OPTIONS = {
            MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT};

    private static final String PURCHASE_MENU_FEED_MONEY = "Feed Money";
    private static final String PURCHASE_MENU_PURCHASE = "Purchase";
    private static final String PURCHASE_MENU_FINISH_TRANSACTION = "Finish transaction";
    private static final String[] PURCHASE_MENU = {PURCHASE_MENU_FEED_MONEY, PURCHASE_MENU_PURCHASE, PURCHASE_MENU_FINISH_TRANSACTION};


    private static final String FEED_MONEY_OPTION_1 = "Add $1";
    private static final String FEED_MONEY_OPTION_2 = "Add $5";
    private static final String FEED_MONEY_OPTION_3 = "Add $10";
    private static final String[] FEED_MENU_OPTIONS = {FEED_MONEY_OPTION_1, FEED_MONEY_OPTION_2, FEED_MONEY_OPTION_3};


    private Menu menu;
    private VendingMachine vendingMachine;
    private Scanner userInput = new Scanner(System.in);
    private Item item;
    private Double quarter = 0.25;
    private Double dime = 0.10;
    private Double nickle = 0.05;
    private int numberOfQuarters;
    private int numberOfDimes;
    private int numberOfNickels;


    public VendingMachineCLI() {
        this.menu = new Menu(System.in, System.out);
        this.vendingMachine = new VendingMachine();
    }


    public void run() {

        while (true) {
            //  boolean runAgain = true;
            String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);
            if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
                List<Item> inventory = vendingMachine.getItemOptions();

                for (Item item : inventory) {
                    System.out.println(item.getPosition() + "|" + item.getName() + "|" + item.getPrice() + "|" + item.getNumInMachine());

                }
                // display vending machine items from VendingMachine class
            } else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
                boolean isDone = false;
                while (!isDone) {
                    choice = (String) menu.getChoiceFromOptions(PURCHASE_MENU);
                    if (choice.equals(PURCHASE_MENU_FEED_MONEY)) {
                        choice = (String) menu.getChoiceFromOptions(FEED_MENU_OPTIONS);
                        if (choice.equals(FEED_MONEY_OPTION_1)) {
                            vendingMachine.feedMoney(1.0);
                        } else if (choice.equals(FEED_MONEY_OPTION_2)) {
                            vendingMachine.feedMoney(5.0);
                        } else if (choice.equals(FEED_MONEY_OPTION_3)) {
                            vendingMachine.feedMoney(10.0);
                        }
                        System.out.println("Your balance is: " + vendingMachine.getBalance());
                    } else if (choice.equals(PURCHASE_MENU_PURCHASE)) {
                        selectItem();
                    } else if (choice.equals(PURCHASE_MENU_FINISH_TRANSACTION)) {

                        makeChange();
                        isDone = true;
                    }
                }
            }
        }
    }

    public void makeChange() {
        Double vendingMachineBalance = vendingMachine.getBalance();
        try (FileWriter writer = new FileWriter("log.txt", true);
             PrintWriter print = new PrintWriter(writer)) {

            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss a");  //audit entry in format

            print.println(dateFormat.format(new Date()) + "GIVE CHANGE" + vendingMachineBalance + "0.00");

            while (vendingMachineBalance >= quarter) {
                numberOfQuarters++;
                vendingMachineBalance -= quarter;
            }
            while (vendingMachineBalance >= dime) {
                numberOfDimes++;
                vendingMachineBalance -= dime;
            }
            while (vendingMachineBalance >= nickle) {
                numberOfNickels++;
                vendingMachineBalance -= nickle;
            }
            System.out.println("Your change is: " + numberOfDimes + " dimes " + numberOfNickels + " nickles " + numberOfQuarters + " quarters");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void selectItem() {
        boolean isInvalid = true;
        while (isInvalid) {

            try (FileWriter writer = new FileWriter("log.txt", true);   //generates the purchase into log.txt
                 PrintWriter print = new PrintWriter(writer)) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss a");  //audit entry in format

                List<Item> items = vendingMachine.getItemOptions();
                for (Item item : items) {
                    System.out.println(item.getPosition() + "|" + item.getName() + "|" + item.getPrice() + "|" + item.getNumInMachine());

                }

                System.out.println("Enter selection");

                //use scanner to get user selection/input
                String selectedItem = userInput.nextLine();

                //declare Item call it selected item set to null: Item item == null
                //  if (Item selectedItem == null;
                // loop through the list again and set selected item to the current item if the positions are equal
                for (Item item : items) {
                    if (selectedItem == null) {
                        System.out.println("Invalid selection");
                    } else if (item.getNumInMachine() == 0) {
                        System.out.println("Out Of Stock");
                    } else if (vendingMachine.getBalance() < item.getPrice()) {
                        System.out.println("Insufficient Funds");


                    } else if (selectedItem.equalsIgnoreCase(item.getPosition())) {
                        selectedItem = item.getPosition();
                        System.out.println(item.getSound() + " " + item.getName() + " " + item.getPrice());
                        System.out.println("Your remaining balance is: " + vendingMachine.updateBalance(item.getPrice()));
                        item.updateNumInMachine();

                    }
                    //         vendingMachine.getItemOptions(selectedItem + item.getSound());


                    //if selected item == null, print invalid selection **
                    //else if numinmachine == 0, out of stock message **
                    //else if vendingmachine.getbalance < getPrice println insufficient funds **
                    //else vendingmachine.puchase, and pass through the selected item && getSound
                }
                isInvalid = false;
            } catch (Exception e) {
                e.printStackTrace();
            }


        }
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


    public static void main(String[] args) {
        VendingMachineCLI cli = new VendingMachineCLI();
        cli.run();
    }

}




