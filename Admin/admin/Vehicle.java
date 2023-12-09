package github.group.login;

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
}