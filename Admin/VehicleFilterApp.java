import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
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
                if (parts.length == 10) { // Ensure the correct number of fields
                    String name = parts[0].trim();
                    String type = parts[1].trim();
                    String make = parts[2].trim();
                    int year = Integer.parseInt(parts[3].trim());
                    String color = parts[4].trim();
                    String plateNo = parts[6].trim(); // Assuming Plate_No is the 7th field
                    String license = parts[7].trim();
                    int roleId = Integer.parseInt(parts[9].trim()); // Assuming Role_ID is the 10th field

                    Vehicle vehicle = new Vehicle(name, type, make, year, color, plateNo, license, roleId);
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
            boolean makeMatches = make.isEmpty() || vehicle.make.equalsIgnoreCase(make);
            boolean typeMatches = type.isEmpty() || vehicle.type.equalsIgnoreCase(type);
            boolean yearMatches = year.isEmpty() || String.valueOf(vehicle.year).equalsIgnoreCase(year);
            boolean colorMatches = color.isEmpty() || vehicle.color.equalsIgnoreCase(color);

            if (makeMatches && typeMatches && yearMatches && colorMatches) {
                filteredList.add(vehicle);
            }
        }

        return filteredList;
    }
}

class VehicleFilterGUI extends JFrame {
    private JTextField makeField;
    private JTextField typeField;
    private JTextField yearField;
    private JTextField colorField;
    private JTextArea resultArea;

    private VehicleFilterModel model;

    public VehicleFilterGUI(VehicleFilterModel model) {
        this.model = model;

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

        setLayout(new BorderLayout());
        add(panel, BorderLayout.NORTH);
        add(new JScrollPane(resultArea), BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setTitle("Vehicle Filter");
        setVisible(true);
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
}