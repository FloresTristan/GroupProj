package github.group.management;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.*;
import java.awt.event.*;

class ManagementGUI {
    private static final String databaseDirectory = "database/";
    private JFrame frame;
    private JPanel searchPanel;
    private ManagementController management;
    private Container contentPane;
    private JPanel addRolesPanel;
    private JTable resultArea;
    private List<Component> userRoleComponents;
    private JButton backButton;
    private String vehicleType = "";
    private String make = "";
    private int yearModel = 0;
    private String color = "";
    private char[] oR = {};
    private char[] cR = {};
    private char[] plateNo = {};
    private char[] licenseNo = {};

    public ManagementGUI(JFrame frame, ManagementController management) {
        this.frame = frame;
        this.management = management;
        contentPane = frame.getContentPane();
        ManagementInit();
    }

    public void ManagementInit() {
        JPanel initialPanel = new JPanel();
        initialPanel.setLayout(null);
        initialPanel.setBounds(0, 0, 400, 700);
        initialPanel.setBackground(new Color(109, 198, 248));

        JLabel label = new JLabel("Hello, Management!");
        label.setFont(new Font("Arial", Font.PLAIN, 35));
        label.setOpaque(true);
        label.setBackground(Color.BLUE);
        label.setForeground(Color.WHITE);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);
        label.setBounds(0, 0, 400, 100);
        initialPanel.add(label);

        ImageIcon originalIcon = new ImageIcon("database/citelogo.png");

        // Resize the image
        Image originalImage = originalIcon.getImage();
        Image resizedImage = originalImage.getScaledInstance(300, 300, Image.SCALE_SMOOTH); // Set the desired width and height

        // Create a new ImageIcon with the resized image
        ImageIcon resizedIcon = new ImageIcon(resizedImage);
        // Create a JLabel to display the image
        JLabel imageLabel = new JLabel(resizedIcon);
        imageLabel.setBounds(45, 100, 300, 300); // Set the bounds as per your requirement
        initialPanel.add(imageLabel);
    
        // Add label for Search, Edit, and Add Roles
        JLabel actionLabel = new JLabel("About");
        actionLabel.setFont(new Font("Arial", Font.BOLD, 25));
        actionLabel.setForeground(Color.BLACK);
        actionLabel.setHorizontalAlignment(JLabel.CENTER);
        actionLabel.setBounds(0, 400, 400, 100);
        initialPanel.add(actionLabel);

        JLabel infoLabel = new JLabel("<html><div style='text-align: center;'>" +
        "   Welcome to the CITE Vehicle Registration App, where convenience meets<br>" +
        "    compliance. Our mission is to simplify the vehicle registration process,<br>" +
        "      enhance school road safety, and streamline traffic enforcement<br>" +
        "through innovative technology.</div></html>");
        infoLabel.setFont(new Font("Arial", Font.PLAIN, 10));
        infoLabel.setForeground(Color.BLACK);

        infoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        infoLabel.setBounds(20, 450, 380, 100);
        initialPanel.add(infoLabel);
        // Set initial panel
        contentPane.add(initialPanel);

        // Create a JMenuBar
        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        // Create "Menu" menu
        JMenu menuMenu = new JMenu("Menu");

        // Create "Search" menu item
        JMenuItem searchItem = new JMenuItem("Search");
        searchItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle "Search" menu option
                addSearchBar();
            }
        });

        // Create "Add Roles" menu item
        JMenuItem addRolesItem = new JMenuItem("Add Roles");
        addRolesItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle "Add Roles" menu option
                addRoles();
            }
        });
        JMenuItem logOutItem = new JMenuItem("Log Out");
        logOutItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle "Add Roles" menu option
                
                returnToLogin();
            }
        });

        // Add menu items to the "Menu" menu
        menuMenu.add(searchItem);
        menuMenu.add(addRolesItem);
        menuMenu.add(logOutItem);

        // Add menus and menu items to the menu bar
        menuBar.add(menuMenu);

        // Set the bounds of the menu bar
        menuBar.setBounds(0, 200, 385, 30);
        frame.setVisible(true);
    }
    private void addBackButton(Container panel) {
        backButton = new JButton("Back");
        backButton.setBounds(15, 521, 100, 30);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showInitialPanel();
            }
        });
    
        // Add the back button to the specified panel
        panel.add(backButton);
    }

    private void showInitialPanel() {
        // Remove existing filter or addRoles components
        removeFilterComponents();
        if (addRolesPanel != null) {
            contentPane.remove(addRolesPanel);
        }

        // Show the initial panel
        ManagementInit();
    }

    private void addSearchBar() {
        // Remove existing filter components
        removeFilterComponents();

        // Create a new panel for search bar components
        searchPanel = new JPanel();
        searchPanel.setLayout(null);
        searchPanel.setBounds(0, 0, 400, 700);
        searchPanel.setBackground(new Color(109, 198, 248));
        contentPane.add(searchPanel);

        JLabel searchLabel = new JLabel("Search Filter");
        searchLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        searchLabel.setOpaque(true);
        searchLabel.setBackground(Color.BLUE);
        searchLabel.setForeground(Color.WHITE);
        searchLabel.setHorizontalAlignment(JLabel.CENTER);
        searchLabel.setVerticalAlignment(JLabel.CENTER);
        searchLabel.setBounds(0, 15, 400, 100);
        searchPanel.add(searchLabel);

        // Add search bar components
        JTextField searchBar = new JTextField();
        searchBar.setBounds(10, 150, 250, 30);

        JButton searchButton = new JButton("Search");
        searchButton.setBounds(270, 150, 100, 30);
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle search logic here
                String searchText = searchBar.getText();
                // Perform search and display results
                performSearchAndDisplay(searchText);
            }
        });

        // Create the result area table
        resultArea = new JTable();
        resultArea.setDefaultEditor(Object.class, null);

        // Create a JScrollPane with dynamic size
        JScrollPane scrollPane = new JScrollPane(resultArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(10, 200, 360, 310); // You can set initial bounds if needed
        scrollPane.setPreferredSize(new Dimension(360, 310)); // Set an initial preferred size

        // Add a component listener to dynamically adjust the size when the panel is resized
        searchPanel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                scrollPane.setBounds(10, 200, searchPanel.getWidth() - 20, searchPanel.getHeight() - 210);
                scrollPane.setPreferredSize(new Dimension(scrollPane.getWidth(), scrollPane.getHeight()));
            }
        });

        // Add components to the panel
        searchPanel.add(searchBar);
        searchPanel.add(searchButton);
        searchPanel.add(scrollPane);
        addEditButton();
        addBackButton(searchPanel);

        contentPane.revalidate();
        contentPane.repaint();
    }

    private List<String> performSearch(String searchText) {
        List<String> matchingResults = new ArrayList<>();
        String filePath = databaseDirectory + "user.dat";

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] userInfo = line.split("\\|");

                boolean matchFound = true;

                for (String term : searchText.toLowerCase().split("\\s+")) {
                    boolean termMatched = false;

                    for (String info : userInfo) {
                        // Use \\b to match the whole word
                        if (info.trim().toLowerCase().matches(".*\\b" + term + "\\b.*")) {
                            termMatched = true;
                            break;
                        }
                    }

                    if (!termMatched) {
                        matchFound = false;
                        break;
                    }
                }

                if (matchFound) {
                    matchingResults.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return matchingResults;
    }
    private void performSearchAndDisplay(String searchText) {
    // Check if the search bar is blank
        if (searchText.trim().isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please enter a search term", "Empty Search", JOptionPane.WARNING_MESSAGE);
        } else {
            // Logic for performing search (similar to VehicleSearch class)
            List<String> matchingResults = performSearch(searchText);

            if (matchingResults.isEmpty()) {
                // Display message for no matches found
                JOptionPane.showMessageDialog(frame, "No matches found for the search: " + searchText, "No Matches", JOptionPane.INFORMATION_MESSAGE);
            } else {
                // Display search results
                displayResults(matchingResults);
            }
        }
    }

// Modify the displayResults method to call addEditButton
    private void displayResults(List<String> results) {
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new Object[]{"No.", "Username", "Name", "Vehicle Type", "Make", "Year Model", "Color", "Official Receipt", "Cert Registration", "Plate No", "License No", "Vehicle Sticker", "Registration Date", "Expiry Date", "Password", "Role ID"});

        for (String result : results) {
            String[] resultData = result.split("\\|");
            model.addRow(resultData);
        }

        resultArea.setModel(model);

        // Set different preferred width for the first column
        int firstColumnWidth = 30; // Adjust this value based on your requirement
        resultArea.getColumnModel().getColumn(0).setPreferredWidth(firstColumnWidth);

        // Set fixed column widths for the rest of the columns
        int fixedColumnWidth = 100; // Adjust this value based on your requirement
        for (int i = 1; i < model.getColumnCount(); i++) {
            resultArea.getColumnModel().getColumn(i).setPreferredWidth(fixedColumnWidth);
        }

        // Set auto-resize mode to OFF for the horizontal scrollbar to work correctly
        resultArea.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        // Call the method to add the "Edit" button
    }


// Modify the addEditButton method
    private void addEditButton() {
        JButton editButton = new JButton("Edit");
        editButton.setBounds(265, 521, 100, 30);
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleEditButton();
            }
        });
        JButton remarkButton = new JButton("Remark");
        remarkButton.setBounds(140, 521, 100, 30);
        remarkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleRemarkButton();
            }
        });

        searchPanel.add(editButton);
        searchPanel.add(remarkButton); // Change to "Remark" button
  
        searchPanel.revalidate();
        searchPanel.repaint();
    }
    private void handleRemarkButton() {
        int selectedRow = resultArea.getSelectedRow();
    
        if (selectedRow != -1) {
            String selectedUsername = (String) resultArea.getValueAt(selectedRow, 1);
    
            // Retrieve the current remark for the selected username
            String currentRemark = getRemarkForUsername(selectedUsername);
    
            // Create a panel for remark components
            JPanel remarkPanel = new JPanel(null); // Use null layout for precise positioning
            remarkPanel.setPreferredSize(new Dimension(300, 150));
    
            // Add labels and text area for remark
            JTextArea remarkTextArea = new JTextArea(currentRemark);
            JScrollPane scrollPane = new JScrollPane(remarkTextArea);
            scrollPane.setBounds(5, 25, 290, 110);
    
            JLabel remarkLabel = new JLabel("Remark:");
            remarkLabel.setBounds(5, 5, 100, 20);
            remarkPanel.add(remarkLabel);
            remarkPanel.add(scrollPane);
    
            while (true) {
                int option = JOptionPane.showOptionDialog(
                        frame,
                        remarkPanel,
                        "Manage Remark",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        new Object[]{"Submit", "Delete", "Cancel"},
                        null
                );
    
                switch (option) {
                    case 0: // Submit
                        String editedRemark = remarkTextArea.getText().trim();
                        if (!editedRemark.isEmpty()) {
                            saveRemarkToFile(selectedUsername, editedRemark);
                            JOptionPane.showMessageDialog(frame, "Remark submitted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                            return; // Exit the method
                        } else {
                            JOptionPane.showMessageDialog(frame, "Please type a remark to submit.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                        break;
                    case 1: // Delete
                        String currentRemarkToDelete = getRemarkForUsername(selectedUsername);
                        if (!currentRemarkToDelete.trim().isEmpty()) {
                            deleteRemark(selectedUsername);
                            JOptionPane.showMessageDialog(frame, "Remark deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                            return; // Exit the method
                        } else {
                            JOptionPane.showMessageDialog(frame, "No remarks to be removed.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                        break;
                    case 2: // Cancel
                        return; // Exit the method
                }
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Please select a row to manage remarks.", "Remark", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    
    private String getRemarkForUsername(String username) {
        // Implement logic to retrieve and return the remark for the given username from the file
        String filePath = databaseDirectory + "remark.dat";
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 2 && parts[0].equals(username)) {
                    return parts[1];
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
    private void saveRemarkToFile(String username, String remark) {
        // Implement logic to save or update the remark for the given username in the file
        String filePath = databaseDirectory + "remark.dat";
        List<String> lines = new ArrayList<>();
    
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 2 && parts[0].equals(username)) {
                    // Update the existing line with the new remark
                    line = username + "|" + remark;
                }
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    
        // Check if the username already has a remark, if not, add a new line
        boolean usernameExists = lines.stream().anyMatch(line -> {
            String[] parts = line.split("\\|");
            return parts.length == 2 && parts[0].equals(username);
        });
    
        if (!usernameExists) {
            lines.add(username + "|" + remark);
        }
    
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }    
    
    private void deleteRemark(String username) {
        // Implement logic to delete the remark for the given username from the file
        String filePath = databaseDirectory + "remark.dat";
        List<String> lines = new ArrayList<>();
    
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 2 && parts[0].equals(username)) {
                    // Skip the line to delete it
                    continue;
                }
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }    
    private void handleEditButton() {
        int selectedRow = resultArea.getSelectedRow();
        if (selectedRow != -1) {
            // Get the data from the selected row
            Vector<Object> rowData = ((DefaultTableModel) resultArea.getModel()).getDataVector().get(selectedRow);
            String[] selectedData = new String[rowData.size()];
            for (int i = 0; i < rowData.size(); i++) {
                selectedData[i] = String.valueOf(rowData.get(i));
            }

            // Create a panel for edit components
            JPanel editPanel = new JPanel();
            editPanel.setLayout(new GridLayout(0, 2));
            editPanel.setPreferredSize(new Dimension(300, 350));

            // Add labels and text fields for each column
            String[] columnNames = {"Row No.","Username","Name", "Vehicle Type", "Make", "Year Model", "Color", "Official Receipt", "Cert Registration", "Plate No", "License No", "Vehicle Sticker", "Registration Date", "Expiry Date", "Password", "Role ID"};
            JTextField[] textFields = new JTextField[columnNames.length];

            for (int i = 0; i < columnNames.length; i++) {
                editPanel.add(new JLabel(columnNames[i]));
                textFields[i] = new JTextField(selectedData[i]);
                editPanel.add(textFields[i]);
            }

            int result = JOptionPane.showConfirmDialog(frame, editPanel, "Edit Row", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                // Update the data in the selected row
                for (int i = 0; i < columnNames.length; i++) {
                    String newValue = textFields[i].getText();
                    ((DefaultTableModel) resultArea.getModel()).setValueAt(newValue, selectedRow, i);
                    selectedData[i] = newValue;
                }

                // Update the data in the user.dat file
                updateDataInFile(selectedData);

                JOptionPane.showMessageDialog(frame, "Row edited successfully", "Edit", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Please select a row to edit", "Edit", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void updateFileWithModifiedData(DefaultTableModel model) {
        String filePath = databaseDirectory + "user.dat";
        // Get the updated data from the table model
        List<String> updatedData = new ArrayList<>();
        for (int i = 0; i < model.getRowCount(); i++) {
            Vector<Object> rowData = model.getDataVector().get(i);
            String[] rowArray = new String[rowData.size()];
            for (int j = 0; j < rowData.size(); j++) {
                rowArray[j] = String.valueOf(rowData.get(j));
            }
            updatedData.add(String.join("|", rowArray));
        }

        // Write the updated data back to the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String line : updatedData) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Add this method to your AdminGUI class
    private void updateDataInFile(String[] newData) {
        try {
            String filePath = databaseDirectory + "user.dat";
            List<String> lines = new ArrayList<>();

            // Read all lines from the file
            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    lines.add(line);
                }
            }

            // Find and update the line based on a unique identifier (you can adjust this based on your data structure)
            String uniqueIdentifier = newData[0]; // Assuming the first column is a unique identifier (e.g., username)
            for (int i = 0; i < lines.size(); i++) {
                String[] userInfo = lines.get(i).split("\\|");

                if (userInfo.length > 0 && userInfo[0].equals(uniqueIdentifier)) {
                    lines.set(i, String.join("|", newData));
                    break;
                }
            }

            // Write the updated lines back to the file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
                for (String line : lines) {
                    writer.write(line);
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int findRowIndex(List<String> lines, String[] newData) {
        for (int i = 0; i < lines.size(); i++) {
            String[] userInfo = lines.get(i).split("\\|");
            if (Arrays.equals(userInfo, newData)) {
                return i;
            }
        }
        return -1;  // Not found
    }

    // Method to remove filter components
    private void removeFilterComponents() {
        Component[] components = contentPane.getComponents();
        for (Component component : components) {
            if (component instanceof JPanel) {
                contentPane.remove(component);
            }
        }
        contentPane.revalidate();
        contentPane.repaint();
    }

    // Method to add roles (modified to include password visibility toggle)
    private void addRoles() {
        // Remove existing filter components
        removeFilterComponents();

        // Create a new panel for add roles components
        addRolesPanel = new JPanel();
        addRolesPanel.setLayout(null);
        addRolesPanel.setBounds(0, 0, 400, 700);
        addRolesPanel.setBackground(new Color(109, 198, 248));
        contentPane.add(addRolesPanel);

        JLabel label = new JLabel("Add User Roles");
        label.setFont(new Font("Arial", Font.PLAIN, 24));
        label.setOpaque(true);
        label.setBackground(Color.BLUE);
        label.setForeground(Color.WHITE);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);
        label.setBounds(0, 15, 400, 100);
        addRolesPanel.add(label);

        // Add components for adding roles
        JLabel rolesLabel = new JLabel("Select Role:");
        rolesLabel.setBounds(10, 150, 100, 30);

        String[] roleOptions = {"User"};
        JComboBox<String> rolesComboBox = new JComboBox<>(roleOptions);
        rolesComboBox.setBounds(120, 150, 150, 30);

        // Set the bounds for the labels and text fields based on a fixed reference point
        int labelY = 200;
        int fieldY = 200;

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(10, labelY, 100, 30);
        JTextField usernameField = new JTextField();
        usernameField.setBounds(120, fieldY, 200, 30);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(10, labelY + 50, 100, 30);
        JTextField nameField = new JTextField();
        nameField.setBounds(120, fieldY + 50, 200, 30);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(10, labelY + 100, 100, 30);
        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(120, fieldY + 100, 200, 30);

        // Password visibility toggle checkbox
        JCheckBox showPasswordCheckBox = new JCheckBox("Show Password");
        showPasswordCheckBox.setBounds(10, fieldY + 150, 150, 30);
        showPasswordCheckBox.setOpaque(false);

        JLabel detailslabel = new JLabel("Add more details?");
        detailslabel.setFont(detailslabel.getFont().deriveFont(Font.PLAIN));
        detailslabel.setBounds(170, fieldY + 150, 150, 30);

        detailslabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                detailslabel.setForeground(Color.BLUE);
                detailslabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                detailslabel.setForeground(Color.BLACK);
                detailslabel.setCursor(Cursor.getDefaultCursor());
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                detailslabel.setForeground(Color.RED);
                JPanel detPanel = new JPanel();
                detPanel.setLayout(new GridLayout(0, 2));
                detPanel.setPreferredSize(new Dimension(300, 350));
                JComboBox vehicleTypeComboBox = null;
                JComboBox yearModelComboBox = null;

                // Add labels and combo boxes for each column
                String[] columnNames = {"Vehicle Type", "Make", "Year Model", "Color", "Official Receipt", "Cert Registration", "Plate No", "License No"};
                Component[] components = new Component[columnNames.length];

                for (int i = 0; i < columnNames.length; i++) {
                    detPanel.add(new JLabel(columnNames[i]));

                    if (columnNames[i].equals("Vehicle Type")) {
                        String[] vehicleTypes = {"", "2 wheels", "4 wheels"};
                        vehicleTypeComboBox = new JComboBox<>(vehicleTypes);
                        detPanel.add(vehicleTypeComboBox);
                        components[i] = vehicleTypeComboBox;
                    } else if (columnNames[i].equals("Year Model")) {
                        Integer[] yearModelOptions = new Integer[2023 - 1990 + 1];
                        for (int j = 0; j < yearModelOptions.length; j++) {
                            yearModelOptions[j] = 1990 + j;
                        }
                        yearModelComboBox = new JComboBox<>(yearModelOptions);
                        detPanel.add(yearModelComboBox);
                        components[i] = yearModelComboBox;
                    } else {
                        JTextField textField = new JTextField();
                        detPanel.add(textField);
                        components[i] = textField;
                    }
                }

                int result = JOptionPane.showConfirmDialog(frame, detPanel, "Add details", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    vehicleType = (String) vehicleTypeComboBox.getSelectedItem();
                    make = ((JTextField) components[1]).getText();
                    yearModel = (Integer) yearModelComboBox.getSelectedItem();
                    color = ((JTextField) components[3]).getText();
                    oR = ((JTextField) components[4]).getText().toCharArray();
                    cR = ((JTextField) components[5]).getText().toCharArray();
                    plateNo = ((JTextField) components[6]).getText().toCharArray();
                    licenseNo = ((JTextField) components[7]).getText().toCharArray();
                    
                }
            }
            
        });

        // Add action listener to show/hide the password based on checkbox state
        showPasswordCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean showPassword = showPasswordCheckBox.isSelected();
                passwordField.setEchoChar(showPassword ? 0 : '*');
            }
        });

        // Add the back button to the addRoles panel
        addBackButton(addRolesPanel);

        // Add action listener to dynamically show/hide components based on the selected role
        rolesComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedRole = (String) rolesComboBox.getSelectedItem();
                boolean isAdminOrManagement = "Admin".equals(selectedRole) || "Management".equals(selectedRole) || "User".equals(selectedRole);
                usernameField.setText("");
                nameField.setText("");
                passwordField.setText("");
                showPasswordCheckBox.setSelected(false);

                    if (userRoleComponents != null) {
                        for (Component component : userRoleComponents) {
                            component.setVisible(false);
                        }
                    }
                

                // Update visibility for the entire addRolesPanel
                addRolesPanel.revalidate();
                addRolesPanel.repaint();
            }
        });

        JButton createRoleButton = new JButton("Create Account");
        createRoleButton.setBounds(230, 521, 130, 30);
        createRoleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle role creation logic here
                Date regDate = new Date();
		        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		        String formattedRegDate = dateFormat.format(regDate);

		        Calendar expCalendar = Calendar.getInstance();
		        expCalendar.setTime(regDate);
		        expCalendar.add(Calendar.YEAR, 1);
		        String formattedExpDate = dateFormat.format(expCalendar.getTime());

                // Handle role creation logic here
                String selectedRole = (String) rolesComboBox.getSelectedItem();
                String username = usernameField.getText();
                String name = nameField.getText();
                String password = new String(passwordField.getPassword());
                int roleID = 0;

                if("Admin".equals(selectedRole)){
                    roleID = 1;
                } else if("Management".equals(selectedRole)){
                    roleID = 2;
                } else if("User".equals(selectedRole)){
                    roleID = 3;
                }
                String plateNoString;

                if (plateNo == null || plateNo.length == 0) {
                    plateNoString = ""; // Set to empty string if plateNo is empty
                } else {
                    plateNoString = new String(plateNo);
                }

                String vehicleSticker = generateVehicleSticker(name, plateNoString);

                // Perform validation: Check if any field is empty
                if (selectedRole.isEmpty() || username.isEmpty() || name.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Please fill in all the blanks", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                } 
                boolean create = management.addRole(username, name, vehicleType, make, yearModel, color, oR, cR, plateNo, licenseNo, vehicleSticker, formattedRegDate, formattedExpDate, password, roleID);
                if(create){
                    
                    // Perform necessary actions with the entered values (e.g., create the role)
                    JOptionPane.showMessageDialog(frame, "Role created: " + selectedRole +
                            "\nUsername: " + username +
                            "\nName: " + name +
                            "\nPassword: " + password);
                    usernameField.setText("");
                    nameField.setText("");
                    passwordField.setText("");
                    showPasswordCheckBox.setSelected(false);

                    vehicleType = "";
                    make = "";
                    yearModel = 0;
                    color = "";
                    oR = new char[0];
                    cR = new char[0];
                    plateNo = new char[0];
                    licenseNo = new char[0];
                    


                } else {
                    JOptionPane.showMessageDialog(frame, "Registration failed. Username already exists.", "Error",JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
        });
       
        // Add components to the panel
        addRolesPanel.add(rolesLabel);
        addRolesPanel.add(rolesComboBox);
        addRolesPanel.add(usernameLabel);
        addRolesPanel.add(usernameField);
        addRolesPanel.add(nameLabel);
        addRolesPanel.add(nameField);
        addRolesPanel.add(passwordLabel);
        addRolesPanel.add(passwordField);
        addRolesPanel.add(showPasswordCheckBox);
        addRolesPanel.add(createRoleButton);
        addRolesPanel.add(detailslabel);

        contentPane.revalidate();
        contentPane.repaint();
    }
    private String generateVehicleSticker(String name, String licensePlate) {
        // Check if the name is empty
        if (name.isEmpty() || licensePlate.isEmpty()) {
            return "";
        }
        // Extract the first letter of the name
        char firstLetter = name.charAt(0);
    
        // Extract the last two digits of the license plate
        String lastTwoDigits = licensePlate.substring(Math.max(0, licensePlate.length() - 2));
    
        // Build the vehicle sticker
        return "VH-" + Character.toUpperCase(firstLetter) + lastTwoDigits;
    }
    public void returnToLogin(){
        int confirmation = JOptionPane.showConfirmDialog(frame, "Proceed to Log Out?", "Log Out Confirmation", JOptionPane.YES_NO_OPTION);
    
        if (confirmation == JOptionPane.YES_OPTION) {
            contentPane.removeAll();

            // Remove the menu bar
            frame.setJMenuBar(null);
    
            // Show the login screen
            management.showLogin();
    
            // Revalidate and repaint the frame
            frame.revalidate();
            frame.repaint();
        }
    }
}