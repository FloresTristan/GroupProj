import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class Vehicle {
    String name;
    String type;
    String make;
    int year;
    String color;
    String plateNo;
    String license;
    int roleId;

    public Vehicle(String name, String type, String make, int year, String color,
                   String plateNo, String license, int roleId) {
        this.name = name;
        this.type = type;
        this.make = make;
        this.year = year;
        this.color = color;
        this.plateNo = plateNo;
        this.license = license;
        this.roleId = roleId;
    }

    // Getters and setters if needed
}

class VehicleFilterModel {
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
            // Handle exceptions appropriately
        }
    }

    public List<Vehicle> filterVehicles(String make, String type, String year, String color) {
        List<Vehicle> filteredList = new ArrayList<>();

        for (Vehicle vehicle : vehicleList) {
            boolean makeMatches = make.isEmpty() || vehicle.make.toLowerCase().contains(make.toLowerCase());
            boolean typeMatches = type.isEmpty() || vehicle.type.toLowerCase().contains(type.toLowerCase());
            boolean yearMatches = year.isEmpty() || String.valueOf(vehicle.year).contains(year);
            boolean colorMatches = color.isEmpty() || vehicle.color.toLowerCase().contains(color.toLowerCase());

            if (makeMatches || typeMatches || yearMatches || colorMatches) {
                filteredList.add(vehicle);
            }
        }

        return filteredList;
    }
}

class VehicleFilterGUI {
    private JFrame frame;
    private JTextField makeField;
    private JTextField typeField;
    private JTextField yearField;
    private JTextField colorField;
    private JTextArea resultArea;

    private VehicleFilterModel model;

    public VehicleFilterGUI(VehicleFilterModel model) {
        this.model = model;
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        makeField = new JTextField(10);
        typeField = new JTextField(10);
        yearField = new JTextField(10);
        colorField = new JTextField(10);
        resultArea = new JTextArea(10, 30);
        JButton filterButton = new JButton("Filter");

        filterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String make = makeField.getText().trim();
                String type = typeField.getText().trim();
                String year = yearField.getText().trim();
                String color = colorField.getText().trim();

                List<Vehicle> filteredList = model.filterVehicles(make, type, year, color);
                displayResults(filteredList);
            }
        });

        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
        panel.add(new JLabel("Make:"));
        panel.add(makeField);
        panel.add(new JLabel("Type:"));
        panel.add(typeField);
        panel.add(new JLabel("Year:"));
        panel.add(yearField);
        panel.add(new JLabel("Color:"));
        panel.add(colorField);
        panel.add(new JLabel(""));
        panel.add(filterButton);

        frame.add(panel, BorderLayout.NORTH);
        frame.add(new JScrollPane(resultArea), BorderLayout.CENTER);

        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        frame.setTitle("Vehicle Filter");
        frame.setVisible(true);
    }

    private void displayResults(List<Vehicle> resultList) {
        resultArea.setText(""); // Clear previous results

        if (resultList.isEmpty()) {
            resultArea.append("No matching vehicles found.");
        } else {
            for (Vehicle vehicle : resultList) {
                resultArea.append(vehicle.make + " | " + vehicle.type + " | " + vehicle.year + " | " + vehicle.color + " | " + vehicle.plateNo + " | " + vehicle.license + "\n");
            }
        }
    }
}


public class VehicleFilterApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new VehicleFilterGUI(new VehicleFilterModel());
            }
        });
    }
}