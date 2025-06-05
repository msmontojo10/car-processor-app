package formatter;

import java.util.List;
import java.text.SimpleDateFormat;
import model.Car;
import exception.CarProcessingException;

public class JsonCarFormatter implements CarFormatterStrategy {
    @Override
    public void format(List<Car> cars) throws CarProcessingException {
        if (cars == null || cars.isEmpty()) {
            throw new CarProcessingException("No car data to display.");
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println("[");
        for (int i = 0; i < cars.size(); i++) {
            Car car = cars.get(i);
            System.out.print("  {");
            System.out.print("\"brand\":\"" + car.getBrand() + "\", ");
            System.out.print("\"type\":\"" + car.getType() + "\", ");
            System.out.print("\"price\":" + car.getPrice() + ", ");
            System.out.print("\"currency\":\"" + car.getCurrency() + "\", ");
            System.out.print("\"releaseDate\":\"" + sdf.format(car.getReleaseDate()) + "\"");
            System.out.print("}");
            if (i != cars.size() - 1) System.out.println(",");
            else System.out.println();
        }
        System.out.println("]");
    }
}