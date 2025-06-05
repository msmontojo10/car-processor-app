package parser;

import java.util.*;

import model.Car;

import java.io.*;
import java.text.SimpleDateFormat;

public class CSVCarParser implements CarParser {
    public List<Car> parse(String filename) throws Exception {
        List<Car> cars = new ArrayList<Car>();
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        boolean header = true;
        while ((line = br.readLine()) != null) {
            if (header) { header = false; continue; }
            String[] parts = line.split(",");
            if (parts.length < 5) continue;
            String brand = parts[0].trim();
            String type = parts[1].trim();
            double price = Double.parseDouble(parts[2].trim());
            String currency = parts[3].trim();
            Date releaseDate = sdf.parse(parts[4].trim());
            cars.add(new Car(brand, type, price, currency, releaseDate));
        }
        br.close();
        return cars;
    }
}