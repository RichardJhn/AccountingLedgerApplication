package com.pluralsight;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;

public class HomeScreen {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        //to do
        //remove some redundant code and finalize report
        //fix deposit and make payment screen

        LocalDate displayTime = LocalDate.now();


        try {
            FileReader fileReader = new FileReader("information.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            //sculpting the main menu
            double totalAmountInAccount = 0.00;
            String choice = "";
            while (!choice.equalsIgnoreCase("X")) {
                System.out.println("=== HomeScreen ===");
                System.out.println("""
                        D)Add Deposit
                        P)Make Payment(Debit)
                        L)Ledger
                        X)Exit \n""");
                System.out.println("enter your choice: ");
                choice = scanner.nextLine().trim();
                //Using scanner
                if (choice.equalsIgnoreCase("d")) {
                    System.out.println("How much would you like to deposit?: ");
                    double depositAmount = scanner.nextDouble();
                    scanner.nextLine();
                    //to do: find a way to combine deposit to tal ammount
                    //have a predefined ammount and transfer all information to csv
                    totalAmountInAccount += depositAmount;
                    System.out.printf("You have deposited $%.2f into your bank account.", depositAmount);
                    System.out.printf("you now have $%.2f in your account. ", totalAmountInAccount);
                    //placeholder
                } else if (choice.equalsIgnoreCase("p")) {
                    System.out.println("Please enter your Debit Card information : ");
                    //to do:
                    //find a way to have java read numbers in between space
                    //double debitCardInfo = scanner.nextDouble();
                    //System.out.printf("you have saved the card %.0f to your banking information.", debitCardInfo);
                    //placeholder
                } else if (choice.equalsIgnoreCase("l")) {
                   new ledgerScreen();
                    //placeholder
                } else if (choice.equalsIgnoreCase("X")) {
                    System.out.println("Exiting Home-Screen");
                    //placeholder
                } else {
                    System.out.println("Closing screen");
                }
            }

            }
        catch(IOException e ){
                System.out.println("error reading file");
            }

        }





    public void showDeposit(){
    }
}

