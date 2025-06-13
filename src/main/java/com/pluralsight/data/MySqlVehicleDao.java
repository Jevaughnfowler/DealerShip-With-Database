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
        // Use your actual column name "year_of_veh"
        return queryVehicles("SELECT * FROM vehicles WHERE year_of_veh BETWEEN ? AND ?", min, max);
    }

    public List<Vehicle> getVehiclesByColor(String color) {
        return queryVehicles("SELECT * FROM vehicles WHERE color = ?", color);
    }

    public List<Vehicle> getVehiclesByMileage(int min, int max) {
        // Use your actual mileage column name "mileage"
        return queryVehicles("SELECT * FROM vehicles WHERE mileage BETWEEN ? AND ?", min, max);
    }

    public List<Vehicle> getVehiclesByType(String type) {
        // Use your actual vehicle type column name "type_of_veh"
        return queryVehicles("SELECT * FROM vehicles WHERE type_of_veh = ?", type);
    }
}