package github.group.login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class VehicleFilterGUI {
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
                resultArea.append(vehicle.name + " | " + vehicle.make + " | " + vehicle.type + " | " +
                        vehicle.year + " | " + vehicle.color + " | " + vehicle.plateNo + " | " + vehicle.license + "\n");
            }
        }
    }
}
