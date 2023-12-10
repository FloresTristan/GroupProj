/*package github.group.login;

public class Vehicle {
    String name;
    String type;
    String make;
    int year;
    String color;
    String plateNo;
    String license;
    int roleId;

    public Vehicle(String name, String make, String type, int year, String color,
                   String plateNo, String license, int roleId) {
        this.name = name;
        this.make = make;
        this.type = type;
        this.year = year;
        this.color = color;
        this.plateNo = plateNo;
        this.license = license;
        this.roleId = roleId;
    }
}*/



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