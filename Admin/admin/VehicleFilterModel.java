package github.group.login;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class VehicleFilterModel {
    private List<Vehicle> vehicleList;

    public VehicleFilterModel() {
        loadVehicleData();
    }

    private void loadVehicleData() {
        try (BufferedReader reader = new BufferedReader(new FileReader("user.dat"))) {
            vehicleList = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 10) {
                    String name = parts[0].trim();
                    String make = parts[1].trim();
                    String type = parts[2].trim();
                    int year = Integer.parseInt(parts[3].trim());
                    String color = parts[4].trim();
                    String plateNo = parts[6].trim();
                    String license = parts[7].trim();
                    int roleId = Integer.parseInt(parts[9].trim());

                    Vehicle vehicle = new Vehicle(name, make, type, year, color, plateNo, license, roleId);
                    vehicleList.add(vehicle);
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }

    public List<Vehicle> filterVehicles(String make, String type, String year, String color) {
        List<Vehicle> filteredList = new ArrayList<>();

        for (Vehicle vehicle : vehicleList) {
            boolean makeMatches = make.isEmpty() || vehicle.make.toLowerCase().contains(make.toLowerCase());
            boolean typeMatches = type.isEmpty() || vehicle.type.toLowerCase().contains(type.toLowerCase());
            boolean yearMatches = year.isEmpty() || String.valueOf(vehicle.year).contains(year);
            boolean colorMatches = color.isEmpty() || vehicle.color.toLowerCase().contains(color.toLowerCase());

            if (makeMatches && typeMatches && yearMatches && colorMatches) {
                filteredList.add(vehicle);
            }
        }

        if (filteredList.isEmpty()) {
            System.out.println("No match found");
        }

        return filteredList;
    }
}
