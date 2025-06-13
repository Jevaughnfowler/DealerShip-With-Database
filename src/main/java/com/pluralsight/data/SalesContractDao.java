package com.pluralsight.data;


import com.pluralsight.models.SalesContract;

import java.util.List;

public interface SalesContractDao {
    List<SalesContract> getAllSales();
    SalesContract getSaleById(int contractId);
    void addSale(SalesContract sale);
    void removeSale(int contractId);
}