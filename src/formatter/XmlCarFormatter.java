package formatter;

import java.util.List;
import java.text.SimpleDateFormat;
import model.Car;
import exception.CarProcessingException;

public class XmlCarFormatter implements CarFormatterStrategy {
    @Override
    public void format(List<Car> cars) throws CarProcessingException {
        if (cars == null || cars.isEmpty()) {
            throw new CarProcessingException("No car data to display.");
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println("<cars>");
        for (Car car : cars) {
            System.out.println("  <car>");
            System.out.println("    <brand>" + car.getBrand() + "</brand>");
            System.out.println("    <type>" + car.getType() + "</type>");
            System.out.println("    <price>" + car.getPrice() + "</price>");
            System.out.println("    <currency>" + car.getCurrency() + "</currency>");
            System.out.println("    <releaseDate>" + sdf.format(car.getReleaseDate()) + "</releaseDate>");
            System.out.println("  </car>");
        }
        System.out.println("</cars>");
    }
}