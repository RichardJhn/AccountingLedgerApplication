package com.pluralsight;

import java.io.*;
import java.util.*;

public class ledgerScreen {

    private List<LedgerEntry> entries = new ArrayList<>();

    public ledgerScreen() {
        loadEntries();
        showMenu();
    }

    private void loadEntries() {
        try (BufferedReader reader = new BufferedReader(new FileReader("information.csv"))) {
            //still not sure why it is not reading from my information file
            String line;
            reader.readLine(); // skip header
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 5) {
                    String date = parts[0];
                    String item = parts[2];
                    String time = parts[1];
                    String vendor = parts[3];
                    double amount = Double.parseDouble(parts[4]);
                    entries.add(new LedgerEntry(date,time, item, vendor, amount));
                }
            }

            //use this to swap elements
            Collections.reverse(entries);

        } catch (IOException e) {
            System.out.println("Error loading ledger entries.");
        }
    }

    private void showMenu() {
        Scanner scanner = new Scanner(System.in);
        String choice = "";

        while (!choice.equalsIgnoreCase("H")) {
            System.out.println("""
                    
                    === Ledger Menu ===
                    A) All Entries
                    D) Deposits Only
                    P) Payments Only
                    R) Reports
                    H) Home
                    """);
            System.out.print("Enter your choice: ");
            choice = scanner.nextLine().trim();

            switch (choice.toUpperCase()) {
                case "A" : showAll();
                break;
                case "D" : showDeposits();
                break;
                case "P" : showPayments();
                break;
                //case "R" -> showReports(); //WIP
                //break;
                case "H" : System.out.println("Returning to Home Screen...");
                break;
                default : System.out.println("Invalid option. Try again.");
            }
        }
    }
    private void showAll(){
        System.out.println("All Entries: ");
        for(LedgerEntry entry : entries) {
            System.out.println(entry);
        }
    }
    private void showDeposits(){
        for (LedgerEntry entry : entries){
            if(entry.getAmount() > 0){
                System.out.println(entry);
            }
        }
    }
    private void showPayments(){
        System.out.println("Payments: ");
        for (LedgerEntry entry : entries){
            if(entry.getAmount() < 0 ){
                System.out.println(entry);
            }
        }
    }




}