package filter;

import java.util.*;
import model.Car;
import exception.CarProcessingException;

public class BrandAndPriceFilter implements CarFilterStrategy {
    @Override
    public List<Car> filter(List<Car> cars, String... args) throws CarProcessingException {
        if (args.length < 2) {
            throw new CarProcessingException("Brand and max price must be provided for brand-price filter.");
        }
        String brand = args[0];
        double maxPrice;
        try {
            maxPrice = Double.parseDouble(args[1]);
        } catch (NumberFormatException e) {
            throw new CarProcessingException("Invalid price value: " + args[1]);
        }
        List<Car> filtered = new ArrayList<>();
        for (Car car : cars) {
            if (car.getBrand().equalsIgnoreCase(brand) && car.getPrice() <= maxPrice) {
                filtered.add(car);
            }
        }
        if (filtered.isEmpty()) {
            throw new CarProcessingException("No cars found matching brand '" + brand + "' and price ≤ " + maxPrice);
        }
        return filtered;
    }
}