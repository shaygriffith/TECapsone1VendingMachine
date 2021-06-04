package com.techelevator;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Candy extends Item{
    private String sound;

    public Candy(String name, double price, String position) {
        super(name, price, position);
    }

    public String getSound(){
        return "Munch Munch, yum!";
    }
}



 //   public String makeSound() {
 //       FileReader file = new FileReader();
  //     File inputFile = file.setInputFile();
  //      List<String> outputList = new ArrayList<String>();
 //       outputList = file.createVendingMachineList(inputFile);
  //      for (int i=0, i<outputList.size(); i++) {
 //           String [] splitLine = outputList.get(i).split("//|");
 //           if(splitLine[0].equals(getPosition()) && splitLine[3].equals("Candy")){
 //               sound = "Munch Much, yum!";
 //           }
 //       }
 //       return sound;
 //   }

 //   @Override
 //   public String getSound() {
  //      return sound;
  //  }


