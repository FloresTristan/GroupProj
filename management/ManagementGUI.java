package github.group.management;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class ManagementGUI {
    private JFrame frame;
    private ManagementController manage;
    private Container contentPane;
    private JPanel addRolesPanel;
    private Component[] userRoleComponents;

    public ManagementGUI(JFrame frame, ManagementController manage) {
        this.frame = frame;
        this.manage = manage;
        contentPane = frame.getContentPane();
        managementInit();
    }

    private void createLabel(JPanel panel, String text, int y) {
        JLabel label = new JLabel(text);
        label.setBounds(10, y, 100, 30);
        panel.add(label);
    }

    private JComboBox<String> createComboBox(String[] options, int x, int y) {
        JComboBox<String> comboBox = new JComboBox<>(options);
        comboBox.setBounds(x, y, 150, 30);
        return comboBox;
    }

    private JTextField createTextField(int x, int y) {
        JTextField textField = new JTextField();
        textField.setBounds(x, y, 200, 30);
        return textField;
    }

    private void addFilterComponents(String filterType) {
        removeFilterComponents();

        JPanel filterPanel = new JPanel();
        filterPanel.setLayout(null);
        filterPanel.setBounds(0, 0, 400, 700);
        filterPanel.setBackground(new Color(109, 198, 248));
        contentPane.add(filterPanel);

        createLabel(filterPanel, "MANAGEMENT", 15);

        if ("Vehicle".equals(filterType)) {
            JComboBox<String> comboBox1 = createComboBox(new String[]{"Make", "Type", "Year Model"}, 10, 150);
            filterPanel.add(comboBox1);
        } else if ("By Date".equals(filterType)) {
            JComboBox<String> comboBox2 = createComboBox(new String[]{"3 months", "6 months", "9 months", "1 year+"}, 10, 150);
            filterPanel.add(comboBox2);
        }

        contentPane.revalidate();
        contentPane.repaint();
    }

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

    private void addRoles() {
        removeFilterComponents();

        addRolesPanel = new JPanel();
        addRolesPanel.setLayout(null);
        addRolesPanel.setBounds(0, 0, 400, 700);
        addRolesPanel.setBackground(new Color(109, 198, 248));
        contentPane.add(addRolesPanel);

        createLabel(addRolesPanel, "MANAGEMENT", 15);

        String[] roleOptions = {"Admin", "User", "Management"};
        JComboBox<String> rolesComboBox = createComboBox(roleOptions, 120, 150);
        addRolesPanel.add(rolesComboBox);

        int yOffset = rolesComboBox.getSelectedItem().equals("User") ? 50 : 0;

        createLabel(addRolesPanel, "Select Role:", 150 + yOffset);
        createLabel(addRolesPanel, "Username:", 200 + yOffset);
        createLabel(addRolesPanel, "Name:", 250 + yOffset);
        createLabel(addRolesPanel, "Password:", 300 + yOffset);

        JTextField usernameField = createTextField(120, 200 + yOffset);
        JTextField nameField = createTextField(120, 250 + yOffset);
        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(120, 300 + yOffset, 200, 30);

        JCheckBox showPasswordCheckBox = new JCheckBox("Show Password");
        showPasswordCheckBox.setBounds(10, 350 + yOffset, 150, 30);
        showPasswordCheckBox.setOpaque(false);

        showPasswordCheckBox.addActionListener(e -> {
            boolean showPassword = showPasswordCheckBox.isSelected();
            passwordField.setEchoChar(showPassword ? 0 : '*');
        });

        rolesComboBox.addActionListener(e -> {
            String selectedRole = (String) rolesComboBox.getSelectedItem();
            boolean isAdminOrManagement = "Admin".equals(selectedRole) || "Management".equals(selectedRole);
            int yOffsetRole = isAdminOrManagement ? 0 : 50;

            rolesComboBox.setBounds(120, 150 + yOffsetRole, 150, 30);
            usernameField.setBounds(120, 200 + yOffsetRole, 200, 30);
            nameField.setBounds(120, 250 + yOffsetRole, 200, 30);
            passwordField.setBounds(120, 300 + yOffsetRole, 200, 30);
            showPasswordCheckBox.setBounds(10, 350 + yOffsetRole, 150, 30);
        });

        JButton createRoleButton = new JButton("Create Account");
        createRoleButton.setBounds(230, 410 + yOffset, 130, 30);
        createRoleButton.addActionListener(e -> {
            String selectedRole = (String) rolesComboBox.getSelectedItem();
            String username = usernameField.getText();
            String name = nameField.getText();
            String password = new String(passwordField.getPassword());

            if (selectedRole.isEmpty() || username.isEmpty() || name.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please fill in all the blanks", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(frame, "Role created: " + selectedRole +
                        "\nUsername: " + username +
                        "\nName: " + name +
                        "\nPassword: " + password);
            }
        });

        addRolesPanel.add(usernameField);
        addRolesPanel.add(nameField);
        addRolesPanel.add(passwordField);
        addRolesPanel.add(showPasswordCheckBox);
        addRolesPanel.add(createRoleButton);

        contentPane.revalidate();
        contentPane.repaint();
    }

    public void managementInit() {
        JPanel initialPanel = new JPanel();
        initialPanel.setLayout(null);
        initialPanel.setBounds(0, 0, 400, 700);
        initialPanel.setBackground(new Color(109, 198, 248));

        JLabel label = new JLabel("MANAGEMENT");
        label.setFont(new Font("Arial", Font.PLAIN, 24));
        label.setOpaque(true);
        label.setBackground(Color.BLUE);
        label.setForeground(Color.WHITE);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);
        label.setBounds(0, 15, 400, 100);
        initialPanel.add(label);

        contentPane.add(initialPanel);

        // Create a JMenuBar
        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        // Create a "Menu" menu
        JMenu menuMenu = new JMenu("Menu");

        // Create "Filter" menu
        JMenu filterMenu = new JMenu("Filter");

        // Create submenus for "By Date" and "Vehicle" under "Filter"
        JMenuItem byDateItem = new JMenuItem("By Date");
        byDateItem.addActionListener(e -> addFilterComponents("By Date"));

        JMenuItem vehicleItem = new JMenuItem("Vehicle");
        vehicleItem.addActionListener(e -> addFilterComponents("Vehicle"));

        // Add submenus to the "Filter" menu
        filterMenu.add(byDateItem);
        filterMenu.add(vehicleItem);

        // Create "Add Roles" menu item
        JMenuItem addRolesItem = new JMenuItem("Add Roles");
        addRolesItem.addActionListener(e -> addRoles());

        // Add menus and menu items to the menu bar
        menuMenu.add(filterMenu);
        menuMenu.add(addRolesItem);
        menuBar.add(menuMenu);

        // Set the bounds of the menu bar
        menuBar.setBounds(0, 115, 385, 30);

        frame.setVisible(true);
    }
}
