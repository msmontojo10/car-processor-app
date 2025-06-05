package parser;

import java.util.List;

import model.Car;

public interface CarParser {
    List<Car> parse(String filename) throws Exception;
}
