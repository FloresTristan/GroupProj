package github.group.admin;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class VehicleFilterModel {
    private List<Vehicle> vehicleList;

    public VehicleFilterModel() {
        // Load data from user.dat
        loadVehicleData();
    }

    private void loadVehicleData() {
        try (BufferedReader reader = new BufferedReader(new FileReader("user.dat"))) {
            vehicleList = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length >= 12) {
                    String name = parts[0].trim();
                    String make = parts[1].trim();
                    String type = parts[2].trim();
                    int year = Integer.parseInt(parts[3].trim());
                    String color = parts[4].trim();
                    String plateNo = parts[7].trim();
                    String sticker = parts[9].trim();
                    String details = line.trim(); // Store the full details

                    Vehicle vehicle = new Vehicle(name, make, type, year, color, plateNo, sticker, details);
                    vehicleList.add(vehicle);
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
            // Handle exceptions appropriately
        }
    }

    public List<Vehicle> filterVehicles(String name, String make, String type, String year, String color, String plateNo, String sticker) {
        List<Vehicle> filteredList = new ArrayList<>();

        for (Vehicle vehicle : vehicleList) {
            boolean nameMatches = name.isEmpty() || vehicle.name.toLowerCase().contains(name.toLowerCase());
            boolean makeMatches = make.isEmpty() || vehicle.make.toLowerCase().contains(make.toLowerCase());
            boolean typeMatches = type.isEmpty() || vehicle.type.toLowerCase().contains(type.toLowerCase());
            boolean yearMatches = year.isEmpty() || String.valueOf(vehicle.year).contains(year);
            boolean colorMatches = color.isEmpty() || vehicle.color.toLowerCase().contains(color.toLowerCase());
            boolean plateNoMatches = plateNo.isEmpty() || vehicle.plateNo.toLowerCase().contains(plateNo.toLowerCase());
            boolean stickerMatches = sticker.isEmpty() || vehicle.sticker.toLowerCase().contains(sticker.toLowerCase());

            if (nameMatches && makeMatches && typeMatches && yearMatches && colorMatches && plateNoMatches && stickerMatches) {
                filteredList.add(vehicle);
            }
        }

        // If no matches found, display "No match found"
        if (filteredList.isEmpty()) {
            System.out.println("No match found");
        }

        return filteredList;
    }
}