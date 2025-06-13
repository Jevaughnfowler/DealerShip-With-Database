package com.pluralsight.ui;

import com.pluralsight.models.Dealership;
import com.pluralsight.models.DealershipFileManager;
import com.pluralsight.models.Vehicle;

import java.util.List;

public class UserInterface {

    //entry point to start the user interface.
    //loads dealership info and inventory, then shows the menu until user quits.
    private Dealership dealership;
    private DealershipFileManager fileManager = new DealershipFileManager();
    private Console console = new Console();

    //displays the main menu options to the user.
    public void display() {
        init();

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
                case 8 -> processAddVehicleRequest();
                case 9 -> processRemoveVehicleRequest();
                case 99 -> System.out.println("Goodbye!");
                default -> System.out.println("Invalid option. Please try again.");
            }
        } while (choice != 99);
    }

    //loads dealership info and inventory from file.
    private void init() {
        dealership = fileManager.getDealership();
        if (dealership == null) {
            System.out.println("Dealership loading failed: null returned");
            return;
        }
        System.out.println("Loaded dealership: " + dealership.getName());
    }

    //displaying the main menu
    private void displayMenu() {
        System.out.println("++++++++++++++++++++++++\n    Dealership Menu   \n++++++++++++++++++++++++");
        System.out.println("1 - Find vehicles within a price range");
        System.out.println("2 - Find vehicles by make / model");
        System.out.println("3 - Find vehicles by year range");
        System.out.println("4 - Find vehicles by color");
        System.out.println("5 - Find vehicles by mileage range");
        System.out.println("6 - Find vehicles by type (car, truck, SUV, van)");
        System.out.println("7 - List ALL vehicles");
        System.out.println("8 - Add a vehicle");
        System.out.println("9 - Remove a vehicle");
        System.out.println("99 - Quit");
        System.out.print("Choose an option: ");
    }

    //displaying list of vehicles to the main.
    private void displayVehicles(List<Vehicle> vehicles) {
        if (vehicles == null || vehicles.isEmpty()) {
            System.out.println("No vehicles found.");
            return;
        }
        for (Vehicle v : vehicles) System.out.println(v);
    }

    //search by price
    private void processVehiclesByPriceRequest() {
        double min = console.promptForDouble("Enter minimum price: ");
        double max = console.promptForDouble("Enter maximum price: ");
        List<Vehicle> filtered = dealership.getVehiclesByPrice(min, max);
        displayVehicles(filtered);
    }

    //search by make and model
    private void processVehiclesByMakeModelRequest() {
        String make = console.promptForString("Enter make: ");
        String model = console.promptForString("Enter model: ");
        List<Vehicle> filtered = dealership.getVehiclesByMakeModel(make, model);
        displayVehicles(filtered);
    }

    //search by year that the vehicle was made
    private void processVehiclesByYearRequest() {
        int min = console.promptForInt("Enter minimum year: ");
        int max = console.promptForInt("Enter maximum year: ");
        List<Vehicle> filtered = dealership.getVehiclesByYear(min, max);
        displayVehicles(filtered);
    }

    //search by colors of the vehicles
    private void processVehiclesByColorRequest() {
        String color = console.promptForString("Enter color: ");
        List<Vehicle> filtered = dealership.getVehiclesByColor(color);
        displayVehicles(filtered);
    }

    //searching vehicle by mileage
    private void processVehiclesByMileageRequest() {
        int min = console.promptForInt("Enter minimum mileage: ");
        int max = console.promptForInt("Enter maximum mileage: ");
        List<Vehicle> filtered = dealership.getVehiclesByMileage(min, max);
        displayVehicles(filtered);
    }

    //searching vehicle by type
    private void processVehiclesByTypeRequest() {
        String type = console.promptForString("Enter vehicle type (car, truck, SUV, van): ");
        List<Vehicle> filtered = dealership.getVehiclesByType(type);
        displayVehicles(filtered);
    }

    //handles the user request to display all vehicles in the dealership inventory.
    //retrieves all vehicles from the dealership and passes them to displayVehicles for output.
    private void processAllVehiclesRequest() {
        displayVehicles(dealership.getAllVehicles());
    }

    //adding vehicle to the inventory
    private void processAddVehicleRequest() {
        int vin = console.promptForInt("Enter VIN: ");
        int year = console.promptForInt("Enter Year: ");
        String make = console.promptForString("Enter Make: ");
        String model = console.promptForString("Enter Model: ");
        String type = console.promptForString("Enter Vehicle Type: ");
        String color = console.promptForString("Enter Color: ");
        int odometer = console.promptForInt("Enter Odometer: ");
        double price = console.promptForDouble("Enter Price: ");

        Vehicle vehicle = new Vehicle(vin, year, make, model, type, color, odometer, price);
        dealership.addVehicle(vehicle);
        fileManager.saveInventory(dealership);

        System.out.println("Vehicle added.");
    }

    //removing a vehicle
    private void processRemoveVehicleRequest() {
        int vin = console.promptForInt("Enter VIN to remove: ");
        dealership.removeVehicle(vin);
        fileManager.saveInventory(dealership);

        System.out.println("Vehicle removed if it existed.");
    }
}