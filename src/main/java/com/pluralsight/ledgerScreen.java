package com.pluralsight;

import java.io.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.Scanner;
import java.time.*;
public class ledgerScreen {
    Scanner scanner = new Scanner(System.in);

    private List<depositEntry> entries = new ArrayList<>();

    public ledgerScreen() {
        loadEntries();
        showMenu();
    }
    //----------------------------loading entries----------------------------------------------------

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
                    entries.add(new depositEntry(date, time, item, vendor, amount));
                }
            }
            //use this to swap elements
            Collections.reverse(entries);

        } catch (IOException e) {
            System.out.println("Error loading ledger entries.");
        }
    }
    //---------------showing ledger menu---------------

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
            System.out.print("Enter your choice: \n");
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
        for (depositEntry entry : entries) {
            System.out.println(entry);
        }
    }

    private void showDeposits() {
        for (depositEntry entry : entries) {
            if (entry.getAmount() > 0) {
                System.out.println(entry);
            }
        }
    }

    private void showPayments() {
        System.out.println("Payments: ");
        for (depositEntry entry : entries) {
            if (entry.getAmount() < 0) {
                System.out.println(entry);
            }
        }
    }
    //----------------showing reports screen-----------

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
            if (choice.equalsIgnoreCase("6")) {
                System.out.println("returning to Ledger Menu");
                break;
            }
            try (BufferedReader reader = new BufferedReader(new FileReader("information.csv"))) {
                String line;

                while ((line = reader.readLine()) != null) {
                    DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    String[] parts = line.split("\\|");
                    if (parts.length == 5) {
                        String date = parts[0];
                        String item = parts[2];
                        String time = parts[1];
                        String vendor = parts[3];
                        double amount = Double.parseDouble(parts[4]);

                        //--------setting the dates--------------------------
                        LocalDate now = LocalDate.now();
                        LocalDate entryDate = LocalDate.parse(date, dateFormat);
                        LocalDate startOfMonth = LocalDate.now().withDayOfMonth(1);
                        LocalDate firstDayLastMonth = now.minusMonths(1).withDayOfMonth(1);
                        LocalDate startOfYear = LocalDate.of(now.getYear(), 1, 1);
                        LocalDate startOfLastYear = LocalDate.of(now.getYear() - 1, 1, 1);
                        LocalDate startOfNextMonth = startOfMonth.plusMonths(1);
                        LocalDate endOfLastMonth = startOfMonth.withDayOfMonth(1).minusDays(1);

                        //--------------making the choices----------------


                        if (choice.equals("1")) {
                            for (depositEntry entry : entries) {
                                if (!entryDate.isBefore(startOfMonth) && entryDate.isBefore(startOfNextMonth)) {
                                    System.out.println(line);
                                }
                                break;
                            }
                        }
                        if (choice.equals("2")) {
                            for (depositEntry entry : entries) {
                                if (entryDate.isAfter(firstDayLastMonth) && entryDate.isBefore(endOfLastMonth)) {
                                    System.out.println(line);
                                }
                                break;
                            }
                        }
                        if (choice.equals("3")) {
                            for (depositEntry entry : entries){
                            if (!entryDate.isBefore(startOfYear) && entryDate.isBefore(LocalDate.now())) {
                                System.out.println(line);
                            }
                            break;
                        }
                        }
                        if (choice.equals("4")) {

                            if (entryDate.isBefore(startOfYear) && entryDate.isBefore(LocalDate.now())) {
                                System.out.println(line);
                            }
                        }
                        if (choice.equals("5")){
                            System.out.println("Which vendor would you like to search?");
                            String vendorSearch = scanner.nextLine();
                            for (depositEntry entry : entries){
                            if(entry.getVendor().equalsIgnoreCase(vendorSearch)){
                                System.out.println(entry);

                            }

                            }
                            System.out.println("""
                                        Would you like to return to the Reports screen?
                                        1) Yes
                                        If Not:
                                         ----------------------------------------------""");
                            if(vendorSearch.equals("1")){
                                System.out.println("Returning to Reports");
                                break;
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