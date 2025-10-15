package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.EOFException;
import java.io.IOException;

public class userInput {
    private double Deposit;
    private double Payment;
    private String Ledger;

//this was a test placeholder and will be moved
    public double getDeposit() {
        return Deposit;
    }
    public void setDeposit(double deposit){
        this.Deposit = deposit;
    }
    public double getPayment(){
        return Payment;
    }
    public void setPayment(double payment){
        this.Payment = payment;
    }
    public String getLedger(){
        return Ledger;
    }
    public void setLedger(String ledger){
        this.Ledger = ledger;
    }

    public void loadVendor(){
        try (BufferedReader reader = new BufferedReader(new FileReader("information.csv"))){
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts =  line.split("\\|");
                if (parts.length == 5){
                    System.out.printf("""
                            Searching by vendor %s""", parts[3]);
                }
            }

        } catch (IOException e) {
            System.out.println("error reading file");
        }
    }
}
