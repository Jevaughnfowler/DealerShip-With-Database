package com.pluralsight.models;


import java.time.LocalDate;

public class LeaseContract {
    private int id;
    private String customerName;
    private String customerEmail;
    private String vin;
    private LocalDate leaseDate;
    private double leasePrice;
    private double taxAmount;
    private double expectedEndValue;
    private double leaseFee;
    private double totalPrice;
    private double monthlyPayment;

    public LeaseContract() {
    }

    public LeaseContract(String customerName, String customerEmail, String vin,
                         LocalDate leaseDate, double leasePrice, double taxAmount,
                         double expectedEndValue, double leaseFee, double totalPrice,
                         double monthlyPayment) {
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.vin = vin;
        this.leaseDate = leaseDate;
        this.leasePrice = leasePrice;
        this.taxAmount = taxAmount;
        this.expectedEndValue = expectedEndValue;
        this.leaseFee = leaseFee;
        this.totalPrice = totalPrice;
        this.monthlyPayment = monthlyPayment;
    }

    // Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public LocalDate getLeaseDate() {
        return leaseDate;
    }

    public void setLeaseDate(LocalDate leaseDate) {
        this.leaseDate = leaseDate;
    }

    public double getLeasePrice() {
        return leasePrice;
    }

    public void setLeasePrice(double leasePrice) {
        this.leasePrice = leasePrice;
    }

    public double getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(double taxAmount) {
        this.taxAmount = taxAmount;
    }

    public double getExpectedEndValue() {
        return expectedEndValue;
    }

    public void setExpectedEndValue(double expectedEndValue) {
        this.expectedEndValue = expectedEndValue;
    }

    public double getLeaseFee() {
        return leaseFee;
    }

    public void setLeaseFee(double leaseFee) {
        this.leaseFee = leaseFee;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double getMonthlyPayment() {
        return monthlyPayment;
    }

    public void setMonthlyPayment(double monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }
}