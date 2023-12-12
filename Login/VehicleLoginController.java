package github.group.login;

import javax.swing.*;
import github.group.register.RegisterController;
import github.group.user.UserController;
import github.group.admin.AdminController;
import github.group.management.ManagementController;

public class VehicleLoginController {
    private JFrame frame;
    private UserController user;

    public VehicleLoginController(JFrame frame) {
        this.frame = frame;
        this.user = new UserController(); // Initialize the UserController
        new VehicleLoginView(frame, this);
        initFrame();
    }

    public void showRegView(JFrame frame) {
        new RegisterController(frame);
    }

    public boolean getLogin(String name, char[] password) {
        // Ensure that user is not null before calling methods on it
        if (user == null) {
            System.err.println("UserController is not initialized!");
            return false;
        }

        int roleId = user.authenticateAndGetRoleId(name, password);

        if (roleId != -1) {
            JOptionPane.showMessageDialog(frame, "Login successful", "Success", JOptionPane.INFORMATION_MESSAGE);

            if (roleId == 1) {
                showAdminInit(frame);
            } else if (roleId == 2) {
                showManagementInit(frame);
                // Add code to show the management panel
                // Example: showManagementPanel();
            } else if (roleId == 3) {
                // Add code to show the user details panel
                // Example: showUserDetailsPanel();
            }

            return true;
        } else {
            JOptionPane.showMessageDialog(frame, "Invalid username or password. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public void showAdminInit(JFrame frame) {
        frame.getContentPane().removeAll();
        frame.getContentPane().repaint();
        new AdminController(frame);
    }
    public void showManagementInit(JFrame frame) {
        frame.getContentPane().removeAll();
        frame.getContentPane().repaint();
        new ManagementController(frame);
    }

    public void initFrame() {
        frame.setSize(400, 700);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
