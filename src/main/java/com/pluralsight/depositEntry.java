package com.pluralsight;

public class depositEntry {
    private String date;
    private String time;
    private String description;
    private String vendor;
    private double amount;

    public depositEntry(String date, String time, String description, String vendor, double amount) {
        this.date = date;
        this.time = time;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }
    public String getTime(){
        return time;
    }

    public String getDescription() {
        return description;
    }

    public String getVendor() {
        return vendor;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return String.format("%-12s %s %-15s %-15s $%.2f", date, time, description, vendor, amount);
    }
}
