
package com.pluralsight.models;

import java.io.*;

public class DealershipFileManager {

    // Loads dealership info and inventory from inventory.csv
    public Dealership getDealership() {
        Dealership dealership = null;

        try (BufferedReader reader = new BufferedReader(new FileReader("inventory.csv"))) {
            // Read first line for dealership info
            String dealershipLine = reader.readLine();
            if (dealershipLine != null) {
                String[] dealershipParts = dealershipLine.split("\\|");
                String name = dealershipParts[0];
                String address = dealershipParts[1];
                String phone = dealershipParts[2];

                dealership = new Dealership(name, address, phone);

                // Read remaining lines for vehicle inventory
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split("\\|");
                    int vin = Integer.parseInt(parts[0]);
                    int year = Integer.parseInt(parts[1]);
                    String make = parts[2];
                    String model = parts[3];
                    String type = parts[4];
                    String color = parts[5];
                    int odometer = Integer.parseInt(parts[6]);
                    double price = Double.parseDouble(parts[7]);

                    Vehicle vehicle = new Vehicle(vin, year, make, model, type, color, odometer, price);
                    dealership.addVehicle(vehicle);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading inventory file: " + e.getMessage());
        }

        return dealership;
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
