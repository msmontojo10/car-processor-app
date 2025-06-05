package formatter;

import java.util.List;
import model.Car;
import exception.CarProcessingException;

public interface CarFormatterStrategy {
    void format(List<Car> cars) throws CarProcessingException;
}