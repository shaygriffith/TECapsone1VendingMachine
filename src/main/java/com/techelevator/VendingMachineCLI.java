package com.techelevator;

import com.techelevator.view.Menu;
import com.techelevator.view.PurchaseMenu;
import com.techelevator.view.VendingMachine;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;

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
    private PurchaseMenu purchaseMenu;
    private VendingMachine vendingMachine;
    private FeedMoney feedMoney;
    private Purchase purchase;
    private FinishTransaction finishTransaction;

    //  private Scanner scanner = new Scanner(System.in);


    public VendingMachineCLI(Menu menu, PurchaseMenu purchaseMenu, FeedMoney feedMoney, Purchase purchase) {
        this.menu = menu;
        this.purchaseMenu = purchaseMenu;
        this.feedMoney = feedMoney;
        this.purchase = purchase;
    }


    //DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
    // Date date = new Date();

    public void run() {
        VendingMachine vendingMachine = new VendingMachine();
        vendingMachine.makeVendingMachine();

        while (true) {
            boolean runAgain = true;
            String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);
            if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
                vendingMachine.displayVendingMachine(); // display vending machine items
            } else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
                boolean isDone = false;
                while (!isDone) {
                    choice = (String) purchaseMenu.getChoiceFromOptions(PURCHASE_MENU);
                    if (choice.equals(PURCHASE_MENU_FEED_MONEY)) {
                        choice = (String) purchaseMenu.getChoiceFromOptions(FEED_MENU_OPTIONS);
                        if (choice.equals(FEED_MONEY_OPTION_1)) {
                            feedMoney.feedMoney(choice);
                        } else if (choice.equals(FEED_MONEY_OPTION_2)) {
                            feedMoney.feedMoney(choice);
                        } else if (choice.equals(FEED_MONEY_OPTION_3)) {
                            feedMoney.feedMoney(choice);
                        }
                        runAgain = true;
                    } else if (choice.equals(PURCHASE_MENU_PURCHASE)) {
                        vendingMachine.displayVendingMachine();
                        purchase.selectItem(vendingMachine, feedMoney);
                        runAgain = false;
                    } else if (choice.equals(PURCHASE_MENU_FINISH_TRANSACTION)) {
                        FinishTrasaction finish = new FinishTransaction(System.in, System.out);
                        finish.makeChange(purchase);
                        isDone = true;
                    }
                }
            }
        }
    }

    
    public static void main(String[] args) {
        Menu menu = new Menu(System.in, System.out);
        PurchaseMenu purchaseMenu = new PurchaseMenu(System.in, System.out);
        FeedMoney feedMoney = new FeedMoney(System.in, System.out);
        Purchase purchase = new Purchase(System.in, System.out);
        VendingMachineCLI cli = new VendingMachineCLI(menu);
        cli.run();
    }

}

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

//	Beverages beverages = new Beverages();
//	beverages.isInStock(true);

//	Candy candy = new Candy();
//	candy.isInStock(true);

//	Chips chips = new Chips();
//	chips.isInStock(true);

//	Gum gum = new Gum();
//	gum.isInStock(true);

//	Interface[] interface = new Interface[] {beverages, candy, chips, gum};

//	public void selectProduct(){
//		if (product < 1){
//			System.out.println("SOLD OUT");


//	for (Product product : Product.price()) {
//		if (!product.Empty.equals(product)) {
//			System.out.println(product.getPosition() + " " + product.getName() + " ""-price: " + product.getPrice());


//public void enterAmountGiven() {
//		VendingMachineCLI




