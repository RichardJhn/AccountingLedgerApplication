package com.pluralsight;

import java.io.*;
import java.util.Scanner;
import java.time.LocalDate;

public class HomeScreen {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        LocalDate displayTime = LocalDate.now();
        //---------------- Main Menu Here -----------------------
        try {
            FileReader fileReader = new FileReader("information.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            //--------------Showing the Main Menu----------------
            double totalAmountInAccount = 0.00;
            String choice = "";
            while (!choice.equalsIgnoreCase("X")) {
                System.out.println("""
                     ====== HomeScreen ======
                      D)Add Deposit         
                      P)Make Payment(Debit) 
                      L)Ledger              
                      X)Exit                
                     ========================\n""");
                System.out.println("enter your choice: ");
                choice = scanner.nextLine().trim();
                //Using scanner
                if (choice.equalsIgnoreCase("d")) {
                    showDeposit();
                    System.out.println("You have made a new deposit into the system");
                } else if (choice.equalsIgnoreCase("p")) {
                    System.out.println("Please enter your transaction information : ");
                    transaction();
                    System.out.println("You have added a new transaction into the system");
                } else if (choice.equalsIgnoreCase("l")) {
                   new ledgerScreen();
                    //placeholder
                } else if (choice.equalsIgnoreCase("X")) {
                    System.out.println("Exiting Home-Screen");
                    //placeholder
                } else {
                    System.out.println("Invalid output!");
                }
            }

            }
        catch(IOException e ){
                System.out.println("error reading file");
            }

        }
        //------------- Deposit and Transaction deposit screens------------------

    public static void showDeposit(){
        try {
            Scanner scanner = new Scanner(System.in);
            FileWriter myWriter = new FileWriter("information.csv", true);
            System.out.println("What is the date?");
            String date = scanner.nextLine();
            System.out.println("What time is it?");
            String time = scanner.nextLine();
            System.out.println("Whats the decription of your item?");
            String description = scanner.nextLine();
            System.out.println("Who is the Vendor?");
            String vendor = scanner.nextLine();
            System.out.println("How much are you selling this item for?");
            double price = scanner.nextDouble();
            scanner.nextLine();


            depositEntry deposit = new depositEntry(date, time, description, vendor, price);
            myWriter.write(String.format("\n%s|%s|%s|%s|%.2f", date, time, description, vendor, price));
            myWriter.close();

    }catch (IOException e){
        System.out.println("an error occured");
    }
        //-------------making the transaction screen--------------
    }
    public static void transaction() {
        try {
            Scanner scanner = new Scanner(System.in);
            FileWriter myWriter = new FileWriter("information.csv", true);
            System.out.println("What is the date?");
            String date = scanner.nextLine();
            System.out.println("What time is it?");
            String time = scanner.nextLine();
            System.out.println("Whats the decription of your item?");
            String description = scanner.nextLine();
            System.out.println("Who is the Vendor?");
            String vendor = scanner.nextLine();
            System.out.println("How much is this item?");
            double price = scanner.nextDouble();
            scanner.nextLine();


            depositEntry deposit = new depositEntry(date, time, description, vendor, price);
            myWriter.write(String.format("\n%s|%s|%s|%s|%.2f", date, time, description, vendor, price));
            myWriter.close();

        }catch (IOException e){
            System.out.println("an error occured");

        }
    }
}


