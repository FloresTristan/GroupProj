import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class VehicleFilterModel extends JFrame {
    private JComboBox<String> typeDropdown;
    private JComboBox<String> makeDropdown;
    private JComboBox<String> yearDropdown;
    private JTextArea resultsArea;

    private ArrayList<Vehicle> vehicles;

    public VehicleFilterModel() {
        // Initialize JFrame
        super("Vehicle Filter App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);

        // Initialize components
        typeDropdown = new JComboBox<>(new String[]{"", "2 wheels", "4 wheels"});
        makeDropdown = new JComboBox<>(new String[]{"", "Toyota", "Honda", "Ford"});
        yearDropdown = new JComboBox<>(new String[]{"", "2020", "2021", "2022"});
        resultsArea = new JTextArea(10, 30);

        JButton filterButton = new JButton("Filter");
        filterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filterVehicles();
            }
        });

        // Layout components
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Type:"), gbc);
        gbc.gridx = 1;
        add(typeDropdown, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Make:"), gbc);
        gbc.gridx = 1;
        add(makeDropdown, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("Year:"), gbc);
        gbc.gridx = 1;
        add(yearDropdown, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        add(filterButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        add(new JScrollPane(resultsArea), gbc);

        // Initialize vehicle data
        vehicles = new ArrayList<>();
        vehicles.add(new Vehicle("Car1", "4 wheels", "Toyota", "2020"));
        vehicles.add(new Vehicle("Bike1", "2 wheels", "Honda", "2021"));
        vehicles.add(new Vehicle("Car2", "4 wheels", "Ford", "2022"));
    }

    private void filterVehicles() {
        String selectedType = (String) typeDropdown.getSelectedItem();
        String selectedMake = (String) makeDropdown.getSelectedItem();
        String selectedYear = (String) yearDropdown.getSelectedItem();

        resultsArea.setText(""); // Clear previous results

        try (BufferedReader br = new BufferedReader(new FileReader("user.dat"))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Assuming the format of each line in the user.dat file is:
                // name | type | make | year | OR | CR | Plate_No | license | Role_ID |
                String[] parts = line.split("\\|");
                if (parts.length >= 4) {
                    String type = parts[1].trim();
                    String make = parts[2].trim();
                    String year = parts[3].trim();

                    if ((selectedType.isEmpty() || type.equals(selectedType))
                            && (selectedMake.isEmpty() || make.equals(selectedMake))
                            && (selectedYear.isEmpty() || year.equals(selectedYear))) {
                        resultsArea.append(line + "\n");
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error reading user.dat file", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new VehicleFilterModel().setVisible(true);
            }
        });
    }
}

class Vehicle {
    private String name;
    private String type;
    private String make;
    private String year;

    public Vehicle(String name, String type, String make, String year) {
        this.name = name;
        this.type = type;
        this.make = make;
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getMake() {
        return make;
    }

    public String getYear() {
        return year;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Type: " + type + ", Make: " + make + ", Year: " + year;
    }
}
