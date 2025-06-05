package model;

import java.util.Date;

public class Car {
    private String brand;
    private String type; // SUV, Sedan, Truck, etc.
    private double price;
    private String currency; // USD, EUR, JPY, etc.
    private Date releaseDate;

    public Car(String brand, String type, double price, String currency, Date releaseDate) {
        this.brand = brand;
        this.type = type;
        this.price = price;
        this.currency = currency;
        this.releaseDate = releaseDate;
    }

    public String getBrand() { return brand; }
    public String getType() { return type; }
    public double getPrice() { return price; }
    public String getCurrency() { return currency; }
    public Date getReleaseDate() { return releaseDate; }

    public int getReleaseYear() {
        @SuppressWarnings("deprecation")
        int year = releaseDate.getYear() + 1900;
        return year;
    }
}