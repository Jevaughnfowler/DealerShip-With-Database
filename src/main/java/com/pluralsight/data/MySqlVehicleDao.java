package com.pluralsight.data;

import com.pluralsight.models.Vehicle;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlVehicleDao implements VehicleDao {

    private Vehicle mapRowToVehicle(ResultSet rs) throws SQLException {
        return new Vehicle(
                rs.getString("vin"),
                rs.getInt("year_of_veh"),
                rs.getString("make"),
                rs.getString("model"),
                rs.getString("type_of_veh"),
                rs.getString("color"),
                rs.getInt("mileage"),
                rs.getDouble("price")
        );
    }

    private List<Vehicle> queryVehicles(String sql, Object... params) {
        List<Vehicle> vehicles = new ArrayList<>();
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            for (int i = 0; i < params.length; i++) {
                stmt.setObject(i + 1, params[i]);
            }

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                vehicles.add(mapRowToVehicle(rs));
            }
        } catch (SQLException e) {
            System.out.println("DB error: " + e.getMessage());
        }
        return vehicles;
    }

    public List<Vehicle> getAllVehicles() {
        return queryVehicles("SELECT * FROM vehicles");
    }

    public List<Vehicle> getVehiclesByPrice(double min, double max) {
        return queryVehicles("SELECT * FROM vehicles WHERE price BETWEEN ? AND ?", min, max);
    }

    public List<Vehicle> getVehiclesByMakeModel(String make, String model) {
        return queryVehicles("SELECT * FROM vehicles WHERE make = ? AND model = ?", make, model);
    }

    public List<Vehicle> getVehiclesByYear(int min, int max) {
        return queryVehicles("SELECT * FROM vehicles WHERE year_of_veh BETWEEN ? AND ?", min, max);
    }

    public List<Vehicle> getVehiclesByColor(String color) {
        return queryVehicles("SELECT * FROM vehicles WHERE color = ?", color);
    }

    public List<Vehicle> getVehiclesByMileage(int min, int max) {
        return queryVehicles("SELECT * FROM vehicles WHERE mileage BETWEEN ? AND ?", min, max);
    }

    public List<Vehicle> getVehiclesByType(String type) {
        return queryVehicles("SELECT * FROM vehicles WHERE type_of_veh = ?", type);
    }

    // ✅ Add a vehicle to the database
    public void addVehicle(Vehicle vehicle) {
        String sql = """
            INSERT INTO vehicles (vin, year_of_veh, make, model, type_of_veh, color, mileage, price)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?)
        """;

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, vehicle.getVin());
            stmt.setInt(2, vehicle.getYear());
            stmt.setString(3, vehicle.getMake());
            stmt.setString(4, vehicle.getModel());
            stmt.setString(5, vehicle.getVehicleType());
            stmt.setString(6, vehicle.getColor());
            stmt.setInt(7, vehicle.getOdometer());
            stmt.setDouble(8, vehicle.getPrice());

            stmt.executeUpdate();
            System.out.println("✅ Vehicle added to database.");
        } catch (SQLException e) {
            System.out.println("DB error (addVehicle): " + e.getMessage());
        }
    }

    // ✅ Remove a vehicle from the database by VIN
    public void removeVehicle(String vin) {
        String sql = "DELETE FROM vehicles WHERE vin = ?";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, vin);
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("✅ Vehicle removed from database.");
            } else {
                System.out.println("⚠️ No vehicle found with VIN: " + vin);
            }
        } catch (SQLException e) {
            System.out.println("DB error (removeVehicle): " + e.getMessage());
        }
    }
}