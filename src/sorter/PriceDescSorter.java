package sorter;

import java.util.List;
import java.util.Comparator;
import model.Car;

public class PriceDescSorter implements CarSorterStrategy {
    @Override
    public void sort(List<Car> cars) {
        cars.sort(new Comparator<Car>() {
            @Override
            public int compare(Car a, Car b) {
                return Double.compare(b.getPrice(), a.getPrice());
            }
        });
    }
}