package main;

import java.util.*;

import model.Car;

public class CarSorter {
    public static void sortByReleaseYearDesc(List<Car> cars) {
        Collections.sort(cars, new Comparator<Car>() {
            public int compare(Car a, Car b) {
                return b.getReleaseDate().compareTo(a.getReleaseDate());
            }
        });
    }

    public static void sortByPriceDesc(List<Car> cars) {
        Collections.sort(cars, new Comparator<Car>() {
            public int compare(Car a, Car b) {
                return Double.compare(b.getPrice(), a.getPrice());
            }
        });
    }

    /**
     * Sorts SUVs by EUR, Sedans by JPY, Trucks by USD.
     * Only cars matching the type and currency are included in the result.
     */
    public static List<Car> sortByTypeAndCurrency(List<Car> cars) {
        List<Car> sorted = new ArrayList<Car>();
        sorted.addAll(sortTypeCurrency(cars, "SUV", "EUR"));
        sorted.addAll(sortTypeCurrency(cars, "Sedan", "JPY"));
        sorted.addAll(sortTypeCurrency(cars, "Truck", "USD"));
        return sorted;
    }

    private static List<Car> sortTypeCurrency(List<Car> cars, String type, String currency) {
        List<Car> filtered = new ArrayList<Car>();
        for (Car car : cars) {
            if (car.getType().equalsIgnoreCase(type) && car.getCurrency().equalsIgnoreCase(currency)) {
                filtered.add(car);
            }
        }
        // Sort descending by price
        Collections.sort(filtered, new Comparator<Car>() {
            public int compare(Car a, Car b) {
                return Double.compare(b.getPrice(), a.getPrice());
            }
        });
        return filtered;
    }
}