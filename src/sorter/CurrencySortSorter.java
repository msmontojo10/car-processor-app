package sorter;

import java.util.*;
import model.Car;

public class CurrencySortSorter implements CarSorterStrategy {
    @Override
    public void sort(List<Car> cars) {
        List<Car> sorted = new ArrayList<>();
        sorted.addAll(filterAndSort(cars, "SUV", "EUR"));
        sorted.addAll(filterAndSort(cars, "Sedan", "JPY"));
        sorted.addAll(filterAndSort(cars, "Truck", "USD"));
        cars.clear();
        cars.addAll(sorted);
    }

    private List<Car> filterAndSort(List<Car> cars, String type, String currency) {
        List<Car> filtered = new ArrayList<>();
        for (Car car : cars) {
            if (car.getType().equalsIgnoreCase(type) && car.getCurrency().equalsIgnoreCase(currency)) {
                filtered.add(car);
            }
        }
        filtered.sort((a, b) -> Double.compare(b.getPrice(), a.getPrice()));
        return filtered;
    }
}