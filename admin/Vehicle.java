package github.group.admin;

import java.util.ArrayList;
import java.util.List;

public class Vehicle {
    String name;
    String make;
    String type;
    int year;
    String color;
    String plateNo;
    String sticker;
    String details; // New field to store the full details

    public Vehicle (String name, String make, String type, int year, String color, String plateNo, String sticker, String details) {
        this.name = name;
        this.make = make;
        this.type = type;
        this.year = year;
        this.color = color;
        this.plateNo = plateNo;
        this.sticker = sticker;
        this.details = details;
    }

    // Getters and setters if needed
}