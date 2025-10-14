package com.pluralsight;

public class userInput {
    private double Deposit;
    private double Payment;
    private String Ledger;


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
}
