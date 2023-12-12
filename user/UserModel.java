package github.group.user;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserModel {

    public static boolean register(String name, String vehicleType, String make, int yearModel, String color, char[] oR, char[] cR, char[] plateNo, char[] licenseNo, String vehicleSticker, char[] password) {

        // File path for user.dat
        String filePath = "C:\\Users\\rhyni\\OneDrive\\Desktop\\github\\database\\user.dat";
        String strMake = new String(make);
        int yrModel = yearModel;
        String vColor = color;
        String strOr = new String(oR);
        String strCr = new String(cR);
        String strPlateNo = new String(plateNo);
        String strLicenseNo = new String(licenseNo);
        String strPassword = new String(password);

        // Role ID for regular users (you can modify this as needed)
        int roleId = 3;

        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath, true))) {
            // Append the role ID to the registration information
            writer.println(name + " | " + vehicleType + " | " + strMake + " | " + yrModel + " | " + vColor + " | " +strOr + " | " + strCr + " | " + strPlateNo + " | " + strLicenseNo + " | " + vehicleSticker+ " | " + strPassword + " | " + roleId);
            System.out.println("Registration successful!");
            return true;
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
            return false;
        }
    }

    private static boolean userExists(String userName) {
        // Check if the user already exists in user.dat
        File file = new File("C:\\Users\\rhyni\\OneDrive\\Desktop\\github\\database\\user.dat");

        // If the file doesn't exist, there are no existing users
        if (!file.exists()) {
            return false;
        }

        // Read user.dat line by line and check for existing user
        try (java.util.Scanner scanner = new java.util.Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] userData = line.split(" \\| ");

                // Check if name and username match
                if (userData.length >= 10 && userData[0].equals(userName)) {
                    return true; // User already exists
                }
            }
        } catch (FileNotFoundException e) {
            // Handle the exception (e.g., print an error message)
            System.err.println("Error reading from file: " + e.getMessage());
        }
        return false; // User does not exist
    }

    public int authenticateAndGetRoleId(String username, char[] password) {
        String filePath = "C:\\Users\\rhyni\\OneDrive\\Desktop\\github\\database\\user.dat";

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userData = line.split("\\|");

                // Check if username and password match
                if (userData.length >= 12 && userData[0].trim().equals(username) && userData[10].trim().equals(new String(password).trim())) {
                    return Integer.parseInt(userData[11].trim()); // Return the role ID
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }

        // Return -1 if authentication fails
        return -1;
    }

    // The login method remains unchanged
    public static boolean login(String name, char[] password) {
        String filePath = "C:\\Users\\rhyni\\OneDrive\\Desktop\\github\\database\\user.dat";

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userData = line.split("\\|");

                // Check if username and password match
                if (userData.length >= 11 && userData[0].trim().equals(name) && userData[9].trim().equals(new String(password).trim())) {
                    int roleId = Integer.parseInt(userData[10].trim());

                    // Check the role ID and print the appropriate message
                    if (roleId == 1) {
                        System.out.println("Login successful! Welcome, Admin!");
                    } else if (roleId == 2) {
                        System.out.println("Login successful! Welcome, Management!");
                    } else if (roleId == 3) {
                        System.out.println("Login successful! Welcome, User!");
                    } else {
                        System.out.println("Invalid role ID!");
                    }

                    return true;
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }

        System.out.println("Invalid username or password. Please try again.");
        return false;
    }

    public static List<String> filterRegisteredVehicles(
        String name,
        String type,
        String make,
        String year,
        String color,
        String plateNo,
        String sticker
) {
    List<String> filteredResults = new ArrayList<>();

    // Read user.dat and filter the entries
    try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\rhyni\\OneDrive\\Desktop\\github\\database\\user.dat"))) {
        String line;
        while ((line = reader.readLine()) != null) {
            String[] userData = line.split("\\|");

            // Check if the entry matches all provided keywords
            if (entryMatchesKeywords(userData, name, type, make, year, color, plateNo, sticker)) {
                filteredResults.add(line);
            }
        }
    } catch (IOException e) {
        System.err.println("Error reading from file: " + e.getMessage());
    }

    return filteredResults;
}

private static boolean entryMatchesKeywords(String[] userData, String... keywords) {
    for (int i = 0; i < keywords.length; i++) {
        String keyword = keywords[i];
        String userValue = i < userData.length ? userData[i].trim() : "";

        // Check if the keyword is not empty and the entry contains the keyword (case-insensitive)
        if (!keyword.isEmpty() && !userValue.toLowerCase().contains(keyword.toLowerCase())) {
            return false; // If any keyword doesn't match, exclude the entry
        }
    }
    return true; // All keywords match
}

private static boolean userDataMatchesKeyword(String[] userData, String keyword) {
    for (String data : userData) {
        // Check if the data contains the keyword (case-insensitive)
        if (data.trim().equalsIgnoreCase(keyword.trim())) {
            return true; // Keyword found in the data
        }
    }
    return false; // Keyword not found in the data
}

}
