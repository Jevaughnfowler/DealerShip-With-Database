package com.pluralsight.data;

import com.pluralsight.models.SalesContract;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MySqlSaleContractDao implements SalesContractDao {

    @Override
    public List<SalesContract> getAllSales() {
        List<SalesContract> contracts = new ArrayList<>();
        String sql = "SELECT * FROM sales_contracts";

        try (Connection conn = DatabaseManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                SalesContract sc = new SalesContract(
                        rs.getInt("id"),
                        rs.getInt("dealership_id"),
                        rs.getString("vin"),
                        rs.getString("customer_name"),
                        rs.getDate("sale_date").toLocalDate(),
                        rs.getDouble("sale_price")
                );
                contracts.add(sc);
            }

        } catch (SQLException e) {
            System.out.println("DB error (getAllSales): " + e.getMessage());
        }

        return contracts;
    }

    @Override
    public SalesContract getSaleById(int contractId) {
        String sql = "SELECT * FROM sales_contracts WHERE id = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, contractId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new SalesContract(
                        rs.getInt("id"),
                        rs.getInt("dealership_id"),
                        rs.getString("vin"),
                        rs.getString("customer_name"),
                        rs.getDate("sale_date").toLocalDate(),
                        rs.getDouble("sale_price")
                );
            }

        } catch (SQLException e) {
            System.out.println("DB error (getSaleById): " + e.getMessage());
        }

        return null;
    }

    @Override
    public void addSale(SalesContract sale) {
        String sql = "INSERT INTO sales_contracts (dealership_id, vin, customer_name, sale_date, sale_price) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, sale.getDealershipId());
            stmt.setString(2, sale.getVin());
            stmt.setString(3, sale.getCustomerName());
            stmt.setDate(4, Date.valueOf(sale.getSaleDate()));
            stmt.setDouble(5, sale.getSalePrice());

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("DB error (addSale): " + e.getMessage());
        }
    }

    @Override
    public void removeSale(int contractId) {
        String sql = "DELETE FROM sales_contracts WHERE id = ?";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, contractId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("DB error (removeSale): " + e.getMessage());
        }
    }
}
