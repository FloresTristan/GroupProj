package github.group.user;

import java.util.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;


class UserModel {
    private UserController user;
	private static final String databaseDirectory = "database/";

    public UserModel(UserController user) {
        this.user = user;
    }

    public static boolean register(String userName, String name, String vehicleType, String make, int yearModel, String color, char[] oR, char[] cR, char[] plateNo, char[] licenseNo, String vehicleSticker, String formattedRegDate, String formattedExpDate, char[] password) {
        String filePath = databaseDirectory + "user.dat";
        String strMake = new String(make);
        int yrModel = yearModel;
        String vColor = color;
        String strOr = new String(oR);
        String strCr = new String(cR);
        String strPlateNo = new String(plateNo);
        String strLicenseNo = new String(licenseNo);
        String strPassword = new String(password);
    
        if (userExists(userName) || isVehicleExists(strOr, strCr, strPlateNo, strLicenseNo)) {
            return false;
        }
    
        // Generate a unique identifier based on the number of users in the file
        int userNumber = getUserCount(filePath) + 1;
    
        int roleId = 3; // Role ID for regular users (you can modify this as needed)
    
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath, true))) {
            // Append the role ID and user number to the registration information
            writer.println(userNumber + " | " + userName + " | " + name + " | " + vehicleType + " | " + strMake + " | " + yrModel + " | " + vColor + " | " + strOr + " | " + strCr + " | " + strPlateNo + " | " + strLicenseNo + " | " + vehicleSticker + " | " + formattedRegDate + " | " + formattedExpDate + " | " + strPassword + " | " + roleId);
            System.out.println("Registration successful!");
            return true;
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
            return false;
        }
    }
    public static boolean createRoles(String userName, String name, String vehicleType, String make, int yearModel, String color, char[] oR, char[] cR, char[] plateNo, char[] licenseNo, String vehicleSticker, String formattedRegDate, String formattedExpDate, String pass, int roleID){
        String filePath = databaseDirectory + "user.dat";
        String strOr = new String(oR);
        String strCr = new String(cR);
        String strPlateNo = new String(plateNo);
        String strLicenseNo = new String(licenseNo);

        if(userExists(userName)){
            return false;
        }
        int userNumber = getUserCount(filePath) + 1;

        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath, true))) {
            // Append the role ID and user number to the registration information
            writer.println(userNumber + " | " + userName + " | " + name + " | " + vehicleType+ " | " + make+" | " + yearModel+  " | " + color+  " | " +strOr+   " | " +strCr+   " | " +strPlateNo+   " | " +strLicenseNo+   " | " + vehicleSticker+  " | " +formattedRegDate+   " | " +formattedExpDate+   " | " +   pass + " | " + roleID);
            System.out.println("Registration successful!");
            return true;
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
            return false;
        }
    }
    
    private static int getUserCount(String filePath) {
        int count = 0;
        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNextLine()) {
                scanner.nextLine();
                count++;
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }
        return count;
    }
    

   private static boolean userExists(String userName) {
    // Check if the user already exists in user.dat
    String filePath = databaseDirectory + "user.dat";

    // If the file doesn't exist, there are no existing users
    File file = new File(filePath);
    if (!file.exists()) {
        return false;
    }

    // Read user.dat line by line and check for an existing user
    try (java.util.Scanner scanner = new java.util.Scanner(file)) {
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] userData = line.split(" \\| ");

            // Check if the name and username match
            if (userData.length >= 14 && userData[1].equals(userName)) {
                return true; // User already exists
            }
        }
    } catch (FileNotFoundException e) {
        // Handle the exception (e.g., print an error message)
        System.err.println("Error reading from file: " + e.getMessage());
    }
    return false; // User does not exist
}


    private static boolean isVehicleExists(String strOR, String strCR, String strPlateNo, String strLicenseNo) {
    String filePath = databaseDirectory + "user.dat";
    File file = new File(filePath);

    if (!file.exists()) {
        return false;
    }

    try (java.util.Scanner scanner = new java.util.Scanner(file)) {
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] userData = line.split(" \\| ");

            // Check if the vehicle information already exists
            if (userData.length >= 15 && userData[7].equals(strOR) && userData[8].equals(strCR) && userData[9].equals(strPlateNo) && userData[10].equals(strLicenseNo)) {
                return true;
            }
        }
    } catch (FileNotFoundException e) {
        System.err.println("Error reading from file: " + e.getMessage());
    }
    return false;
}

    public int authenticateAndGetRoleId(String username, char[] password) {
        String filePath = databaseDirectory + "user.dat";
        System.out.println("Entering authenticateAndGetRoleId");


        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userData = line.split("\\|");

                // Check if the username and password match
                if (userData.length > 1 && userData[1].trim().equals(username) && userData[userData.length - 2].trim().equals(new String(password).trim())) {
                    try {
                        return Integer.parseInt(userData[userData.length - 1].trim()); // Return the role ID
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid role ID format. Please check the user data.");
                        return -1;
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }

        // Return -1 if authentication fails
        return -1;
    }

    // The login method remains unchanged
    // Modify the login method to dynamically determine the role ID position
   public  boolean login(String username, char[] password) {
   String filePath = databaseDirectory + "user.dat";

    try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
        String line;
        System.out.println("Before while loop in login method");
        while ((line = reader.readLine()) != null) {
            String[] userData = line.split("\\|");

            // Check if the line has at least two elements (username, password)
            if (userData.length >= 2) {
                String trimmedUsername = userData[1].trim();
                System.out.println("Username: " + trimmedUsername);
                String trimmedPassword = userData[userData.length - 2].trim();
                System.out.println("Password: " + trimmedPassword);

                // Check if the username and password match
                if (trimmedUsername.equals(username) && trimmedPassword.equals(new String(password).trim())) {
                    try {
                        int roleId = Integer.parseInt(userData[findRoleIdPosition(userData)].trim());
                        System.out.println("Role ID"+ roleId);
                        // Check the role ID and print the appropriate message
                        if (roleId == 1) {
                            System.out.println("Login successful! Welcome, Admin!");
                        } else if (roleId == 2) {
                            System.out.println("Login successful! Welcome, Management!");
                        } else if (roleId == 3) {
                            System.out.println("Login successful! Welcome, User!");

                            // If the role ID is 3, return the original userData
                            System.out.println("Before calling showUserDetails");
                            showUserDetails(username, filePath);
                        } else {
                            System.out.println("Invalid role ID!");
                        }

                        return true;
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid role ID format. Please check the user data.");
                        return false;
                    }
                }
            }
        }
    } catch (IOException e) {
        System.err.println("Error reading from file: " + e.getMessage());
    }

        System.out.println("Invalid username or password. Please try again.");
        return false;
    }
    private static int findRoleIdPosition(String[] userData) {
        int position = userData.length - 1;
        System.out.println("Role ID Position: " + position);
        return position;
    }

// Method to show user details when the role ID is 3
private void showUserDetails(String username, String filePath) {
    try {
        List<String> lines = Files.readAllLines(Paths.get(filePath));

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
                        String vehicleType = userData[3].trim();  // Assuming vehicleType is at index 4
                        String color = userData[6].trim();
                        String regDate = userData[12].trim();
                        String expDate = userData[13].trim();

                        System.out.println("Details from UserModel: " + make + ", " + vehicleType + ", " + color + ", " + regDate + ", " + expDate);
                        
                    } else {
                        System.out.println("Invalid data structure in the file.");
                    }
                });

    } catch (IOException e) {
        System.err.println("Error reading from file: " + e.getMessage());
    }
}




}
