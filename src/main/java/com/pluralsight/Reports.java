package com.pluralsight;

import java.util.Scanner;

public class Reports {
    Scanner scanner = new Scanner(System.in);

    userInput input = new userInput();

    private String date;
    private String item;
    private String vendor;
    private String amount;

    public Reports(String Date, String item, String vendor, String amount){

        this.date = date;
        this.item = item;
        this.amount = amount;
        this.vendor = vendor;

    }
    public String getDate(){
        return date;
    }

    public String getItem(){
        return item;
    }

    public String getVendor(){
        return vendor;
    }
    public String getAmount(){
        return amount;
    }






}
