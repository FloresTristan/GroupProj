package github.group.admin;

import github.group.user.UserModel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AdminGUI {
    private JFrame frame;
    private AdminController admin;
    private Container contentPane;
    private JPanel addRolesPanel;
    private Component[] userRoleComponents;

    private JTextField nameSearchBar;
    private JTextField typeSearchBar;
    private JTextField makeSearchBar;
    private JTextField yearSearchBar;
    private JTextField colorSearchBar;
    private JTextField plateSearchBar;
    private JTextField stickerSearchBar;

    public AdminGUI(JFrame frame, AdminController admin) {
        this.frame = frame;
        this.admin = admin;
        contentPane = frame.getContentPane();
        adminInit();
    }

    public void adminInit() {
        // Initialize the initial panel
        JPanel initialPanel = new JPanel();
        initialPanel.setLayout(null);
        initialPanel.setBounds(0, 0, 400, 700);
        initialPanel.setBackground(new Color(109, 198, 248));

        JLabel label = new JLabel("Hello, Admin!");
        label.setFont(new Font("Arial", Font.PLAIN, 24));
        label.setOpaque(true);
        label.setBackground(Color.BLUE);
        label.setForeground(Color.WHITE);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);
        label.setBounds(0, 15, 400, 100);
        initialPanel.add(label);

        // Set initial panel
        contentPane.add(initialPanel);

        // Create and set up the menu bar
        setupMenuBar();

        // Set the visibility of the frame
        frame.setVisible(true);
    }

    // Method to set up the menu bar
    private void setupMenuBar() {
        // Create a JMenuBar
        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        // Create a "Menu" menu
        JMenu menuMenu = new JMenu("Menu");

        // Create "Filter" menu
        JMenu filterMenu = new JMenu("Filter");

        // Create submenus for "By Date" and "Vehicle" under "Filter"
        JMenuItem byDateItem = createMenuItem("By Date", e -> addFilterComponents("By Date"));
        JMenuItem vehicleItem = createMenuItem("Vehicle", e -> addFilterComponents("Vehicle"));

        // Add submenus to the "Filter" menu
        filterMenu.add(byDateItem);
        filterMenu.add(vehicleItem);

        // Create "Add Roles" menu item
        JMenuItem addRolesItem = createMenuItem("Add Roles", e -> addRoles());

        // Add menus and menu items to the menu bar
        menuMenu.add(filterMenu);
        menuMenu.add(addRolesItem);
        menuBar.add(menuMenu);

        // Set the bounds of the menu bar
        menuBar.setBounds(0, 115, 385, 30);
    }

    // Method to create a JMenuItem with an ActionListener
    private JMenuItem createMenuItem(String label, ActionListener actionListener) {
        JMenuItem menuItem = new JMenuItem(label);
        menuItem.addActionListener(actionListener);
        return menuItem;
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

    // Method to add filter components
    private void addFilterComponents(String filterType) {
        // Remove existing filter components
        removeFilterComponents();

        // Create new panel for filter components
        JPanel filterPanel = new JPanel();
        filterPanel.setLayout(null);
        filterPanel.setBounds(0, 0, 400, 700);
        filterPanel.setBackground(new Color(109, 198, 248));
        contentPane.add(filterPanel);

        // Add common components
        JLabel label = new JLabel("Hello, Admin!");
        label.setFont(new Font("Arial", Font.PLAIN, 24));
        label.setOpaque(true);
        label.setBackground(Color.BLUE);
        label.setForeground(Color.WHITE);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);
        label.setBounds(0, 15, 400, 100);
        filterPanel.add(label);

        // Add components specific to each filter type
        if ("Vehicle".equals(filterType)) {
            addVehicleSearchBarsAndFilterButton(filterPanel);
        } else if ("By Date".equals(filterType)) {
            String[] options2 = {"3 months", "6 months", "9 months", "1 year+"};
            JComboBox<String> comboBox2 = new JComboBox<>(options2);
            comboBox2.setBounds(10, 150, 150, 30);
            filterPanel.add(comboBox2);
        }

        contentPane.revalidate();
        contentPane.repaint();
    }

    // Method to add search bars and filter button for "Vehicle" filter
    private void addVehicleSearchBarsAndFilterButton(JPanel filterPanel) {
        // Create search bars
        nameSearchBar = createSearchBar(filterPanel, "Name:", 10, 150);
        typeSearchBar = createSearchBar(filterPanel, "Type:", 10, 200);
        makeSearchBar = createSearchBar(filterPanel, "Make:", 10, 250);
        yearSearchBar = createSearchBar(filterPanel, "Year:", 10, 300);

        colorSearchBar = createSearchBar(filterPanel, "Color:", 200, 150);
        plateSearchBar = createSearchBar(filterPanel, "Plate No:", 200, 200);
        stickerSearchBar = createSearchBar(filterPanel, "Sticker:", 200, 250);

        // Create filter button
        JButton filterButton = new JButton("Filter");
        filterButton.setBounds(280, 300, 100, 30);
        filterButton.addActionListener(e -> performFiltering());
        filterPanel.add(filterButton);

        // Create text area to display registered vehicles
        JTextArea resultTextArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(resultTextArea);
        scrollPane.setBounds(8, 350, 370, 280);
        filterPanel.add(scrollPane);
    }


    // Method to create a search bar with a label and absolute positioning
    private JTextField createSearchBar(JPanel filterPanel, String label, int x, int y) {
        JLabel searchBarLabel = new JLabel(label);
        searchBarLabel.setBounds(x, y, 80, 30);
        filterPanel.add(searchBarLabel);

        JTextField searchBar = new JTextField();
        searchBar.setBounds(x + 80, y, 100, 30);
        filterPanel.add(searchBar);

        return searchBar;
    }

    // Method to perform filtering based on search criteria
    private void performFiltering() {
        // Call the new method in UserModel for filtering
        List<String> results = UserModel.filterRegisteredVehicles(
                nameSearchBar.getText(),
                typeSearchBar.getText(),
                makeSearchBar.getText(),
                yearSearchBar.getText(),
                colorSearchBar.getText(),
                plateSearchBar.getText(),
                stickerSearchBar.getText()
        );

        // Display the results as needed in your GUI
        displayFilterResults(results);
    }

    // Method to display the filtered results in your GUI
    private void displayFilterResults(List<String> results) {
        // Implement how to display the filtered results in your GUI
    }

    // Method to add roles (modified to include password visibility toggle)
    private void addRoles() {
        // Remove existing filter components
        removeFilterComponents();

        // Create new panel for add roles components
        addRolesPanel = new JPanel();
        addRolesPanel.setLayout(null);
        addRolesPanel.setBounds(0, 0, 400, 700);
        addRolesPanel.setBackground(new Color(109, 198, 248));
        contentPane.add(addRolesPanel);

        JLabel label = new JLabel("Hello, Admin!");
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

        String[] roleOptions = {"Admin", "User", "Management"};
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

        // Add action listener to show/hide password based on checkbox state
        showPasswordCheckBox.addActionListener(e -> {
            boolean showPassword = showPasswordCheckBox.isSelected();
            passwordField.setEchoChar(showPassword ? 0 : '*');
        });

        // Add action listener to dynamically show/hide components based on the selected role
        rolesComboBox.addActionListener(e -> {
            String selectedRole = (String) rolesComboBox.getSelectedItem();
            boolean isAdminOrManagement = "Admin".equals(selectedRole) || "Management".equals(selectedRole);

            // Reposition the components based on visibility
            int yOffset = isAdminOrManagement ? 0 : 50;

            rolesLabel.setBounds(10, 150 + yOffset, 100, 30);
            rolesComboBox.setBounds(120, 150 + yOffset, 150, 30);
            usernameLabel.setBounds(10, labelY + yOffset, 100, 30);
            usernameField.setBounds(120, fieldY + yOffset, 200, 30);
            nameLabel.setBounds(10, labelY + 50 + yOffset, 100, 30);
            nameField.setBounds(120, fieldY + 50 + yOffset, 200, 30);
            passwordLabel.setBounds(10, labelY + 100 + yOffset, 100, 30);
            passwordField.setBounds(120, fieldY + 100 + yOffset, 200, 30);
            showPasswordCheckBox.setBounds(10, fieldY + 150 + yOffset, 150, 30);

            // Show/hide components specific to "User" role
            if ("User".equals(selectedRole)) {
                // Clear components specific to "User" role
                addRolesPanel.removeAll();
            } else {
                // Hide components specific to "User" role
                if (userRoleComponents != null) {
                    for (Component component : userRoleComponents) {
                        component.setVisible(false);
                    }
                }
            }

            // Update visibility for the entire addRolesPanel
            addRolesPanel.revalidate();
            addRolesPanel.repaint();
        });

        JButton createRoleButton = new JButton("Create Account");
        createRoleButton.setBounds(230, fieldY + 310, 130, 30);
        createRoleButton.addActionListener(e -> {
            // Handle role creation logic here
            String selectedRole = (String) rolesComboBox.getSelectedItem();
            String username = usernameField.getText();
            String name = nameField.getText();
            String password = new String(passwordField.getPassword());

            // Perform validation: Check if any field is empty
            if (selectedRole.isEmpty() || username.isEmpty() || name.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please fill in all the blanks", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                // Perform necessary actions with the entered values (e.g., create the role)
                JOptionPane.showMessageDialog(frame, "Role created: " + selectedRole +
                        "\nUsername: " + username +
                        "\nName: " + name +
                        "\nPassword: " + password);
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

        contentPane.revalidate();
        contentPane.repaint();
    }
}
