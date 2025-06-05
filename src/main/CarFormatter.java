package main;

import java.util.*;

import model.Car;

import java.text.SimpleDateFormat;

public class CarFormatter {
    public static void printTable(List<Car> cars) {
        System.out.printf("%-10s %-8s %-10s %-8s %-12s\n", "Brand", "Type", "Price", "Currency", "ReleaseDate");
        for (Car car : cars) {
            System.out.printf("%-10s %-8s %-10.2f %-8s %-12s\n",
                car.getBrand(), car.getType(), car.getPrice(), car.getCurrency(),
                new SimpleDateFormat("yyyy-MM-dd").format(car.getReleaseDate()));
        }
    }

    public static void printXml(List<Car> cars) {
        System.out.println("<cars>");
        for (Car car : cars) {
            System.out.println("  <car>");
            System.out.println("    <brand>" + car.getBrand() + "</brand>");
            System.out.println("    <type>" + car.getType() + "</type>");
            System.out.println("    <price>" + car.getPrice() + "</price>");
            System.out.println("    <currency>" + car.getCurrency() + "</currency>");
            System.out.println("    <releaseDate>" + new SimpleDateFormat("yyyy-MM-dd").format(car.getReleaseDate()) + "</releaseDate>");
            System.out.println("  </car>");
        }
        System.out.println("</cars>");
    }

    public static void printJson(List<Car> cars) {
        System.out.println("[");
        for (int i = 0; i < cars.size(); i++) {
            Car car = cars.get(i);
            System.out.print("  {");
            System.out.print("\"brand\":\"" + car.getBrand() + "\", ");
            System.out.print("\"type\":\"" + car.getType() + "\", ");
            System.out.print("\"price\":" + car.getPrice() + ", ");
            System.out.print("\"currency\":\"" + car.getCurrency() + "\", ");
            System.out.print("\"releaseDate\":\"" + new SimpleDateFormat("yyyy-MM-dd").format(car.getReleaseDate()) + "\"");
            System.out.print("}");
            if (i != cars.size() - 1) System.out.println(",");
            else System.out.println();
        }
        System.out.println("]");
    }
}