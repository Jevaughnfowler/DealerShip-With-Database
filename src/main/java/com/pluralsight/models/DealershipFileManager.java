
package com.pluralsight.models;

import com.pluralsight.data.DatabaseManager;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DealershipFileManager {


    public Dealership getDealership() {
        Dealership dealership = null;

        return dealership;
    }


    public List<Vehicle> getVehiclesByPrice(double min, double max) {
        List<Vehicle> vehicles = new ArrayList<>();

        String sql = """
        SELECT
                      vin,
                      make,
                      model,
                      year_of_veh,
                      mileage,
                      color,
                      type_of_veh,
                      price
                    FROM vehicles
                    WHERE price BETWEEN ? AND ?
    """;

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDouble(1, min);
            stmt.setDouble(2, max);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Vehicle v = new Vehicle(
                        rs.getString("vin"),
                        rs.getInt("year"),
                        rs.getString("make"),
                        rs.getString("model"),
                        rs.getString("type"),
                        rs.getString("color"),
                        rs.getInt("odometer"),
                        rs.getDouble("price")
                );

                // You can also include the dealership name if needed
                System.out.println("From Dealership: " + rs.getString("dealership_name"));
                vehicles.add(v);
            }

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }

        return vehicles;
    }

    // Saves dealership info and inventory to inventory.csv
    public void saveInventory(Dealership dealership) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("inventory.csv"))) {
            // Write dealership info on the first line
            writer.write(String.format("%s|%s|%s",
                    dealership.getName(),
                    dealership.getAddress(),
                    dealership.getPhone()));
            writer.newLine();

            // Write each vehicle
            for (Vehicle v : dealership.getAllVehicles()) {
                writer.write(String.format("%d|%d|%s|%s|%s|%s|%d|%.2f",
                        v.getVin(), v.getYear(), v.getMake(), v.getModel(),
                        v.getVehicleType(), v.getColor(), v.getOdometer(), v.getPrice()));
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving inventory: " + e.getMessage());
        }
    }


}
