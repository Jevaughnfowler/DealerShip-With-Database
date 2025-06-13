package com.pluralsight.data;

import com.pluralsight.models.Vehicle;
import java.util.List;

public interface VehicleDao {
    List<Vehicle> getAllVehicles();

    List<Vehicle> getVehiclesByPrice(double min, double max);

    List<Vehicle> getVehiclesByMakeModel(String make, String model);

    List<Vehicle> getVehiclesByYear(int min, int max);

    List<Vehicle> getVehiclesByColor(String color);

    List<Vehicle> getVehiclesByMileage(int min, int max);

    List<Vehicle> getVehiclesByType(String type);
}