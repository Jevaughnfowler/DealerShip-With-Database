package com.pluralsight.models;

import java.time.LocalDate;

public class SalesContract {
    private int id;
    private String customerName;
    private String customerEmail;
    private String vin;
    private LocalDate saleDate;
    private double salePrice;
    private double taxAmount;
    private double processingFee;
    private double totalPrice;
    private boolean finance;

    public SalesContract() {
    }

    public SalesContract(String customerName, String customerEmail, String vin,
                         LocalDate saleDate, double salePrice, double taxAmount,
                         double processingFee, double totalPrice, boolean finance) {
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.vin = vin;
        this.saleDate = saleDate;
        this.salePrice = salePrice;
        this.taxAmount = taxAmount;
        this.processingFee = processingFee;
        this.totalPrice = totalPrice;
        this.finance = finance;
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

    public LocalDate getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(LocalDate saleDate) {
        this.saleDate = saleDate;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    public double getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(double taxAmount) {
        this.taxAmount = taxAmount;
    }

    public double getProcessingFee() {
        return processingFee;
    }

    public void setProcessingFee(double processingFee) {
        this.processingFee = processingFee;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public boolean isFinance() {
        return finance;
    }

    public void setFinance(boolean finance) {
        this.finance = finance;
    }
}