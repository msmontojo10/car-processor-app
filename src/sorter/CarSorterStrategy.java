package sorter;

import java.util.List;
import model.Car;

public interface CarSorterStrategy {
    void sort(List<Car> cars);
}