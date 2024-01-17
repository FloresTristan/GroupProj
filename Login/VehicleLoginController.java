package github.group.login;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import javax.swing.*;
import java.awt.*;

import github.group.register.RegisterController;
import github.group.user.UserController;
import github.group.admin.AdminController;
import github.group.management.ManagementController;
import github.group.details.DetailsController;



public class VehicleLoginController extends AbstractVehicleLoginController{
   
    public void loginController(JFrame frame) {
        this.frame = frame;
        logView.setLoginController(this);
        logView.loginView(frame);
        initFrame();
    }

    public void showRegView(JFrame frame) {
        new RegisterController(frame);
    }

    public boolean getLogin(String username, char[] password) {
        user = new UserController();
        int roleId = user.authenticateAndGetRoleId(username, password);
        boolean login = user.isLogin(username, password);
    
        if (roleId != -1) {
            JOptionPane.showMessageDialog(frame, "Login successful", "Success", JOptionPane.INFORMATION_MESSAGE);
    
            if (roleId == 1) {
                showAdminInit(frame);
            } else if (roleId == 2) {
                showManagementInit(frame);
            } else if (roleId == 3) {
                try {
                    List<String> lines = Files.readAllLines(Paths.get(filePath));
                    List<String> remarkLines = Files.readAllLines(Paths.get(remarkPath));
    
                    // Use Java Streams to filter lines based on the username
                    lines.stream()
                            .filter(line -> {
                                String[] userData = line.split("\\|");
                                return userData.length >= 2 && userData[1].trim().equals(username);
                            })
                            .forEach(userLine -> {
                                String[] userData = userLine.split("\\|");
    
                                // Extract specific details for users with role ID 3
                                if (userData.length >= 16) {
                                    String make = userData[4].trim();  // Assuming make is at index 5
                                    String type = userData[3].trim();  // Assuming vehicleType is at index 4
                                    String color = userData[6].trim();
                                    String regDate = userData[12].trim();
                                    String expDate = userData[13].trim();
                                    String vehicleSticker = userData[11].trim();
    
                                    // Show the user details view using DetailsController
                                    showDetail(username,make, type, color, regDate, expDate, vehicleSticker);
                                }
                            });
    
                    // Check for matching username in remark.dat for role ID 3 users
                    remarkLines.stream()
                            .filter(line -> {
                                String[] remarkData = line.split("\\|");
                                return remarkData.length >= 2 && remarkData[0].trim().equals(username);
                            })
                            .findFirst()
                            .ifPresent(remarkLine -> {
                                String[] remarkData = remarkLine.split("\\|");
                                if (remarkData.length >= 2) {
                                    JOptionPane.showMessageDialog(frame, "   Warning: " + remarkData[1].trim() + "\n    Proceed to admin", "Warning", JOptionPane.WARNING_MESSAGE);
                                }
                            });
    
                    return true;
                } catch (IOException e) {
                    e.printStackTrace();  // Handle the IOException appropriately
                    return false;
                }
            } else {
                return false;
            }
        }else {
            JOptionPane.showMessageDialog(frame, "Invalid username or password. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
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
    public void showDetail(String username,String make, String type, String color, String regDate, String expDate, String sticker){
        frame.getContentPane().removeAll();
        frame.getContentPane().repaint();
        System.out.println("Details from showDet: " + make + ", " + type + ", " + color + ", " + regDate + ", " + expDate+ ", "+sticker);
        details.detailsController(frame,username,make,type,color,regDate,expDate,sticker);
    }
    
}
