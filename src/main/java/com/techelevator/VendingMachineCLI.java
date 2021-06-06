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

      //  VendingMachine vendingMachine = new VendingMachine();
     //   vendingMachine.makeVendingMachine();

        while (true) {
          //  boolean runAgain = true;
            String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);
            if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
                vendingMachine.getItemOptions(); // display vending machine items from VendingMachine class
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
                      //  FinishTransaction finish = new FinishTransaction(System.in, System.out);

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

            Date date = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss a");  //audit entry in format

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
            System.out.println("Your change is: " + numberOfDimes + " dimes" + numberOfNickels + "  nickles" + numberOfQuarters + " quarters");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void selectItem() {
            //      balance = feedMoney.getBalance();
            boolean isInvalid = true;
            while (isInvalid) {
                //    System.out.println("Select position of the item");  //selecting product
                //        String userInput = in.nextLine();

                try (FileWriter writer = new FileWriter("log.txt", true);   //generates the purchase into log.txt
                     PrintWriter print = new PrintWriter(writer)) {

                    List<Item> items = vendingMachine.getItemOptions();
                    for (Item item : items) {       //looping through the list to display options
                        System.out.println(item);
                    }
                    //audit entry in format
                    Date date = new Date();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss a");

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


                        } else if (selectedItem == item.getPosition()) {
                            selectedItem= item.getPosition();
                            System.out.println(item.getSound() + item.getName() + item.getPrice());
                        }
                   //         vendingMachine.getItemOptions(selectedItem + item.getSound());


                        //if selected item == null, print invalid selection **
                        //else if numinmachine == 0, out of stock message **
                        //else if vendingmachine.getbalance < getPrice println insufficient funds **
                        //else vendingmachine.puchase, and pass through the selected item && getSound

                        System.out.println("Your remaining balance is: " + vendingMachine.getBalance());

                        isInvalid = false;

                    }
                    }catch(Exception e){
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
//feedMoney method
//      public void purchaseItem() {
//          try (FileWriter writer = new FileWriter("log.txt", true);
//              PrintWriter print = new PrintWriter(writer)) {

//             if (vendingMachine.feedMoney().equals("Feed $1")) {
//                 vendingMachine.getBalance() += ONE_DOLLAR;
//                System.out.println("Your balance is: " + balance);
//            } else if (choice.equals("Feed $5")) {
//                 balance += FIVE_DOLLARS;
//                 System.out.println("Your balance is: " + balance);
//             } else if (choice.equals("Feed $10")) {
//                 balance += TEN_DOLLARS;
//                 System.out.println("Your balance is: " + balance);
//            }

//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }


//       Scanner scanner = null;
//       try {
//           scanner = new Scanner(new File("vendingmachine.csv"));
//           while (scanner.hasNext()) {
//              System.out.println(scanner.nextLine());
//get string, split it, get "new item",

//FileReader file = new FileReader();
//File vendingMachineFile = file.setVendingMachineFile();

//}
//     } catch (FileNotFoundException e) {
//        e.printStackTrace();
//    } finally {
//        if (scanner != null) {
//           scanner.close();
//       }
//  }
//	public void selectProduct(int product) {
//		this.selectProduct = product;
//	}

//	Interface[] interface = new Interface[] {beverages, candy, chips, gum};

//	public void selectProduct(){
//		if (product < 1){
//			System.out.println("SOLD OUT");


//	for (Product product : Product.price()) {
//		if (!product.Empty.equals(product)) {
//			System.out.println(product.getPosition() + " " + product.getName() + " ""-price: " + product.getPrice());


//public void enterAmountGiven() {
//		VendingMachineCLI




