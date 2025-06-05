package formatter;

import java.util.List;
import java.text.SimpleDateFormat;
import model.Car;
import exception.CarProcessingException;

public class TableCarFormatter implements CarFormatterStrategy {
    @Override
    public void format(List<Car> cars) throws CarProcessingException {
        if (cars == null || cars.isEmpty()) {
            throw new CarProcessingException("No car data to display.");
        }
        System.out.printf("%-10s %-8s %-10s %-8s %-12s\n", "Brand", "Type", "Price", "Currency", "ReleaseDate");
        for (Car car : cars) {
            System.out.printf("%-10s %-8s %-10.2f %-8s %-12s\n",
                car.getBrand(), car.getType(), car.getPrice(), car.getCurrency(),
                new SimpleDateFormat("yyyy-MM-dd").format(car.getReleaseDate()));
        }
    }
}