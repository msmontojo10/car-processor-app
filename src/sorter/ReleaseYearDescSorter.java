package sorter;

import java.util.List;
import java.util.Comparator;
import model.Car;

public class ReleaseYearDescSorter implements CarSorterStrategy {
    @Override
    public void sort(List<Car> cars) {
        cars.sort(new Comparator<Car>() {
            @Override
            public int compare(Car a, Car b) {
                // Assuming getReleaseDate() returns java.util.Date
                return b.getReleaseDate().compareTo(a.getReleaseDate());
            }
        });
    }
}