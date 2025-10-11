package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class HomeScreen {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        try {
            FileReader fileReader = new FileReader("information.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            //sculpting the main menu
            System.out.println("What would you like to do?: ");
            System.out.println("""
                    D) Add Deposit
                    P)Make Payment(Debit)
                    L)Ledger
                    X)Exit""");
            //Using scanner
            String choice = scanner.nextLine();
            if (choice == D) {
                //placeholder
            }else if (choice == P){
                //placeholder
            }else if (choice == L){
                //placeholder
            }else if (choice == X){
                //placeholder
            }
            else{
                System.out.println("Closing screen");
            }


        }
        catch (IOException e ){
        }


    }
}

