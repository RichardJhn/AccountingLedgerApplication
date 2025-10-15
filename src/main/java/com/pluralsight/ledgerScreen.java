package com.pluralsight;

import java.io.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.Scanner;
import java.time.*;
public class ledgerScreen {
    Scanner scanner = new Scanner(System.in);

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
                    entries.add(new LedgerEntry(date, time, item, vendor, amount));
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
                case "A":
                    showAll();
                    break;
                case "D":
                    showDeposits();
                    break;
                case "P":
                    showPayments();
                    break;
                case "R":
                    showReports(); //WIP
                    break;
                case "H":
                    System.out.println("Returning to Home Screen...");
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    private void showAll() {
        System.out.println("All Entries: ");
        for (LedgerEntry entry : entries) {
            System.out.println(entry);
        }
    }

    private void showDeposits() {
        for (LedgerEntry entry : entries) {
            if (entry.getAmount() > 0) {
                System.out.println(entry);
            }
        }
    }

    private void showPayments() {
        System.out.println("Payments: ");
        for (LedgerEntry entry : entries) {
            if (entry.getAmount() < 0) {
                System.out.println(entry);
            }
        }
    }

    private void showReports() {
        String choice = "";
        while (!choice.equalsIgnoreCase("6")) {
            System.out.println("""
                    
                    
                    === Reports Menu ===
                    1) Month to date
                    2) Previous Month
                    3) Year to Date
                    4) Previous Year
                    5) Search by vendor
                    6) Return to Ledger menu \n""");


            System.out.println("Enter your choice: ");

            choice = scanner.nextLine().trim();
            if(choice.equalsIgnoreCase("6")) {
                System.out.println("returning to Ledger Menu");
                break;
            }


            try (BufferedReader reader = new BufferedReader(new FileReader("information.csv"))) {
                String line;
                DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");// skip header
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split("\\|");
                    if (parts.length == 5) {
                        String date = parts[0];
                        //String item = parts[2];
                        //String time = parts[1];
                        //String vendor = parts[3];
                        //double amount = Double.parseDouble(parts[4]);
                        LocalDate now = LocalDate.now();
                        LocalDate entryDate = LocalDate.parse(date, dateFormat);
                        LocalDate startOfMonth = LocalDate.now().withDayOfMonth(1);
                        LocalDate firstDayLastMonth = now.minusMonths(1);
                        LocalDate startOfYear = LocalDate.ofYearDay(2025,1);


                        if (choice.equals("1")) {
                            if (!entryDate.isBefore(startOfMonth)) {
                                System.out.println(date);
                            }
                        }
                        if (choice.equals("2")){
                            if(!entryDate.isBefore(firstDayLastMonth) && entryDate.isBefore(startOfMonth)){
                                System.out.println(date);
                            }
                        }
                        if (choice.equals("3")){
                            if(!entryDate.isBefore(startOfYear)){
                            System.out.println(date);
                        }

                    }
                    }


                }
            } catch (IOException e) {
                System.out.println("file not found");
            }

        }
    }
}