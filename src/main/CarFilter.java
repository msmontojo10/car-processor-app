package main;

import java.util.*;

import model.Car;

public class CarFilter {
    public static List<Car> filterByBrandAndPrice(List<Car> cars, String brand, double maxPrice) {
        List<Car> filtered = new ArrayList<Car>();
        for (Car car : cars) {
            if (car.getBrand().equalsIgnoreCase(brand) && car.getPrice() <= maxPrice) {
                filtered.add(car);
            }
        }
        return filtered;
    }

    public static List<Car> filterByBrandAndReleaseDate(List<Car> cars, String brand, Date releaseDate) {
        List<Car> filtered = new ArrayList<Car>();
        for (Car car : cars) {
            if (car.getBrand().equalsIgnoreCase(brand) && car.getReleaseDate().equals(releaseDate)) {
                filtered.add(car);
            }
        }
        return filtered;
    }
}
