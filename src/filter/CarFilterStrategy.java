package filter;

import java.util.List;
import model.Car;
import exception.CarProcessingException;

public interface CarFilterStrategy {
    List<Car> filter(List<Car> cars, String... args) throws CarProcessingException;
}
