package com.pluralsight.ui;

import com.pluralsight.data.MySqlVehicleDao;
import com.pluralsight.models.Vehicle;

import java.util.List;

public class UserInterface {
    private final Console console = new Console();
    private final MySqlVehicleDao vehicleDao = new MySqlVehicleDao(); // Directly use MySQL DAO

    public void display() {
        int choice;
        do {
            displayMenu();
            choice = console.promptForInt("");

            switch (choice) {
                case 1 -> processVehiclesByPriceRequest();
                case 2 -> processVehiclesByMakeModelRequest();
                case 3 -> processVehiclesByYearRequest();
                case 4 -> processVehiclesByColorRequest();
                case 5 -> processVehiclesByMileageRequest();
                case 6 -> processVehiclesByTypeRequest();
                case 7 -> processAllVehiclesRequest();
                case 8 -> processAddVehicle();
                case 9 -> processRemoveVehicle();
                case 99 -> System.out.println("Goodbye!");
                default -> System.out.println("Invalid option. Please try again.");
            }
        } while (choice != 99);
    }

    private void displayMenu() {
        System.out.println("""
                ++++++++++++++++++++++++
                    Dealership Menu
                ++++++++++++++++++++++++
                1 - Find vehicles within a price range
                2 - Find vehicles by make / model
                3 - Find vehicles by year range
                4 - Find vehicles by color
                5 - Find vehicles by mileage range
                6 - Find vehicles by type (car, truck, SUV, van)
                7 - List ALL vehicles
                8 - Add a vehicle
                9 - Remove a vehicle
                99 - Quit
                """);
        System.out.print("Choose an option: ");
    }

    private void displayVehicles(List<Vehicle> vehicles) {
        if (vehicles.isEmpty()) {
            System.out.println("No vehicles found.");
        } else {
            for (Vehicle v : vehicles) {
                System.out.println(v);
            }
        }
    }

    private void processVehiclesByPriceRequest() {
        double min = console.promptForDouble("Enter minimum price: ");
        double max = console.promptForDouble("Enter maximum price: ");
        List<Vehicle> vehicles = vehicleDao.getVehiclesByPrice(min, max);
        displayVehicles(vehicles);
    }

    private void processVehiclesByMakeModelRequest() {
        String make = console.promptForString("Enter make: ");
        String model = console.promptForString("Enter model: ");
        List<Vehicle> vehicles = vehicleDao.getVehiclesByMakeModel(make, model);
        displayVehicles(vehicles);
    }

    private void processVehiclesByYearRequest() {
        int min = console.promptForInt("Enter minimum year: ");
        int max = console.promptForInt("Enter maximum year: ");
        List<Vehicle> vehicles = vehicleDao.getVehiclesByYear(min, max);
        displayVehicles(vehicles);
    }

    private void processVehiclesByColorRequest() {
        String color = console.promptForString("Enter color: ");
        List<Vehicle> vehicles = vehicleDao.getVehiclesByColor(color);
        displayVehicles(vehicles);
    }

    private void processVehiclesByMileageRequest() {
        int min = console.promptForInt("Enter minimum mileage: ");
        int max = console.promptForInt("Enter maximum mileage: ");
        List<Vehicle> vehicles = vehicleDao.getVehiclesByMileage(min, max);
        displayVehicles(vehicles);
    }

    private void processVehiclesByTypeRequest() {
        String type = console.promptForString("Enter vehicle type: ");
        List<Vehicle> vehicles = vehicleDao.getVehiclesByType(type);
        displayVehicles(vehicles);
    }

    private void processAllVehiclesRequest() {
        List<Vehicle> vehicles = vehicleDao.getAllVehicles();
        displayVehicles(vehicles);
    }

    private void processAddVehicle() {
        System.out.println("\n--- Add a Vehicle ---");
        String vin = console.promptForString("Enter VIN: ");
        int year = console.promptForInt("Enter year: ");
        String make = console.promptForString("Enter make: ");
        String model = console.promptForString("Enter model: ");
        String type = console.promptForString("Enter vehicle type: ");
        String color = console.promptForString("Enter color: ");
        int mileage = console.promptForInt("Enter mileage: ");
        double price = console.promptForDouble("Enter price: ");

        Vehicle vehicle = new Vehicle(vin, year, make, model, type, color, mileage, price);
        vehicleDao.addVehicle(vehicle);
        System.out.println("Vehicle added successfully.");
    }

    private void processRemoveVehicle() {
        System.out.println("\n--- Remove a Vehicle ---");
        String vin = console.promptForString("Enter VIN of vehicle to remove: ");
        vehicleDao.removeVehicle(vin);
        System.out.println("Vehicle removed successfully (if it existed).");
    }
}