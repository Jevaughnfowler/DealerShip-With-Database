package com.pluralsight.data;

import com.pluralsight.models.LeaseContract;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlLeaseContractDao implements LeaseContractDao {

    @Override
    public List<LeaseContract> getAllLeases() {
        List<LeaseContract> leases = new ArrayList<>();
        String sql = "SELECT * FROM lease_contracts";

        try (Connection conn = DatabaseManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                LeaseContract lease = new LeaseContract(
                        rs.getString("customer_name"),
                        rs.getString("customer_email"),
                        rs.getString("vin"),
                        rs.getDate("lease_date").toLocalDate(),
                        rs.getDouble("lease_price"),
                        rs.getDouble("tax_amount"),
                        rs.getDouble("expected_end_value"),
                        rs.getDouble("lease_fee"),
                        rs.getDouble("total_price"),
                        rs.getDouble("monthly_payment")
                );
                lease.setId(rs.getInt("id"));
                leases.add(lease);
            }

        } catch (SQLException e) {
            System.out.println("DB error (getAllLeases): " + e.getMessage());
        }

        return leases;
    }

    @Override
    public LeaseContract getLeaseById(int contractId) {
        String sql = "SELECT * FROM lease_contracts WHERE id = ?";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, contractId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                LeaseContract lease = new LeaseContract(
                        rs.getString("customer_name"),
                        rs.getString("customer_email"),
                        rs.getString("vin"),
                        rs.getDate("lease_date").toLocalDate(),
                        rs.getDouble("lease_price"),
                        rs.getDouble("tax_amount"),
                        rs.getDouble("expected_end_value"),
                        rs.getDouble("lease_fee"),
                        rs.getDouble("total_price"),
                        rs.getDouble("monthly_payment")
                );
                lease.setId(rs.getInt("id"));
                return lease;
            }

        } catch (SQLException e) {
            System.out.println("DB error (getLeaseById): " + e.getMessage());
        }

        return null;
    }

    @Override
    public void addLease(LeaseContract lease) {
        String sql = "INSERT INTO lease_contracts (customer_name, customer_email, vin, lease_date, lease_price, tax_amount, expected_end_value, lease_fee, total_price, monthly_payment) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, lease.getCustomerName());
            stmt.setString(2, lease.getCustomerEmail());
            stmt.setString(3, lease.getVin());
            stmt.setDate(4, Date.valueOf(lease.getLeaseDate()));
            stmt.setDouble(5, lease.getLeasePrice());
            stmt.setDouble(6, lease.getTaxAmount());
            stmt.setDouble(7, lease.getExpectedEndValue());
            stmt.setDouble(8, lease.getLeaseFee());
            stmt.setDouble(9, lease.getTotalPrice());
            stmt.setDouble(10, lease.getMonthlyPayment());

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("DB error (addLease): " + e.getMessage());
        }
    }

    @Override
    public void removeLease(int contractId) {
        String sql = "DELETE FROM lease_contracts WHERE id = ?";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, contractId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("DB error (removeLease): " + e.getMessage());
        }
    }
}
