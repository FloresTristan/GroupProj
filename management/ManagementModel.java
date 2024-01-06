package github.group.management;

import javax.swing.table.DefaultTableModel;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ManagementModel {
    public List<String> performSearch(String searchText) {
        List<String> matchingResults = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\rhyni\\OneDrive\\Desktop\\Original Files\\database\\user.dat"))) {
            String line;

            while ((line = reader.readLine()) != null) {
                if (line.toLowerCase().contains(searchText.toLowerCase())) {
                    matchingResults.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return matchingResults;
    }

    public DefaultTableModel createTableModel(List<String> results) {
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new Object[]{"Name", "Vehicle Type", "Make", "Year Model", "Color", "Official Receipt", "Cert Registration", "Plate No", "License No", "Vehicle Sticker", "Registration Date", "Expiry Date", "Password", "Role ID"});

        for (String result : results) {
            String[] resultData = result.split("\\|");
            model.addRow(resultData);
        }

        return model;
    }
}