package filter;

import java.util.*;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import model.Car;
import exception.CarProcessingException;

public class BrandAndDateFilter implements CarFilterStrategy {
    @Override
    public List<Car> filter(List<Car> cars, String... args) throws CarProcessingException {
        if (args.length < 2) {
            throw new CarProcessingException("Brand and release date must be provided for brand-date filter.");
        }
        String brand = args[0];
        Date date;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(args[1]);
        } catch (ParseException e) {
            throw new CarProcessingException("Invalid date format: " + args[1] + ". Use yyyy-MM-dd.");
        }
        List<Car> filtered = new ArrayList<>();
        for (Car car : cars) {
            if (car.getBrand().equalsIgnoreCase(brand) && car.getReleaseDate().equals(date)) {
                filtered.add(car);
            }
        }
        if (filtered.isEmpty()) {
            throw new CarProcessingException("No cars found for brand '" + brand + "' and release date " + args[1]);
        }
        return filtered;
    }
}