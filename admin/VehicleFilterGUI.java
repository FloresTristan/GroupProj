package github.group.admin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class VehicleFilterGUI {
    private JFrame frame;
    private JTextField nameField;
    private JTextField makeField;
    private JTextField typeField;
    private JTextField yearField;
    private JTextField colorField;
    private JTextField plateNoField;
    private JTextField stickerField;
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

        // Left panel for Name, Make, Type, Year
        JPanel leftPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        leftPanel.add(new JLabel("Name:"));
        leftPanel.add(nameField = new JTextField(10));
        leftPanel.add(new JLabel("Make:"));
        leftPanel.add(makeField = new JTextField(10));
        leftPanel.add(new JLabel("Type:"));
        leftPanel.add(typeField = new JTextField(10));
        leftPanel.add(new JLabel("Year:"));
        leftPanel.add(yearField = new JTextField(10));

        // Right panel for Color, Plate No, Sticker, and Filter button
        JPanel rightPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        rightPanel.add(new JLabel("Color:"));
        rightPanel.add(colorField = new JTextField(10));
        rightPanel.add(new JLabel("Plate No:"));
        rightPanel.add(plateNoField = new JTextField(10));
        rightPanel.add(new JLabel("Sticker:"));
        rightPanel.add(stickerField = new JTextField(10));

        JButton filterButton = new JButton("Filter");
        filterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText().trim();
                String make = makeField.getText().trim();
                String type = typeField.getText().trim();
                String year = yearField.getText().trim();
                String color = colorField.getText().trim();
                String plateNo = plateNoField.getText().trim();
                String sticker = stickerField.getText().trim();

                List<Vehicle> filteredList = model.filterVehicles(name, make, type, year, color, plateNo, sticker);
                displayResults(filteredList);
            }
        });

        rightPanel.add(new JLabel(""));
        rightPanel.add(filterButton);

        // Combine left and right panels
        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.add(leftPanel, BorderLayout.WEST);
        inputPanel.add(rightPanel, BorderLayout.EAST);

        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(new JScrollPane(resultArea = new JTextArea(10, 30)), BorderLayout.CENTER);

        frame.setSize(600, 400);
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
                resultArea.append(vehicle.details + "\n");
            }
        }
    }
}