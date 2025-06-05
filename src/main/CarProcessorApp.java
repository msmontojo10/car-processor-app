package main;

import java.util.*;
import java.text.SimpleDateFormat;
import parser.CarParser;
import parser.XMLCarParser;
import parser.CSVCarParser;
import model.Car;

public class CarProcessorApp {
    public static void main(String[] args) {
        try {
            if (args.length < 2) {
                printUsage();
                return;
            }
            String inputFile = args[0];
            String format = inputFile.endsWith(".xml") ? "xml" : "csv";
            CarParser parser = format.equals("xml") ? new XMLCarParser() : new CSVCarParser();
            List<Car> cars = parser.parse(inputFile);

            // CLI options
            String filterType = args[1];
            List<Car> filtered = cars;

            if (filterType.equalsIgnoreCase("brand-price")) {
                String brand = args[2];
                double price = Double.parseDouble(args[3]);
                filtered = CarFilter.filterByBrandAndPrice(cars, brand, price);
            } else if (filterType.equalsIgnoreCase("brand-date")) {
                String brand = args[2];
                String dateStr = args[3];
                Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
                filtered = CarFilter.filterByBrandAndReleaseDate(cars, brand, date);
            }

            // Sorting
            if (args.length > 4) {
                String sortType = args[4];
                if (sortType.equalsIgnoreCase("year")) {
                    CarSorter.sortByReleaseYearDesc(filtered);
                } else if (sortType.equalsIgnoreCase("price")) {
                    CarSorter.sortByPriceDesc(filtered);
                } else if (sortType.equalsIgnoreCase("currency-sort")) {
                    // Only use cars matching the type/currency rules
                    filtered = CarSorter.sortByTypeAndCurrency(filtered);
                }
            }

            // Output
            String outputFormat = (args.length > 5) ? args[5] : "table";
            if (outputFormat.equalsIgnoreCase("table")) {
                CarFormatter.printTable(filtered);
            } else if (outputFormat.equalsIgnoreCase("xml")) {
                CarFormatter.printXml(filtered);
            } else if (outputFormat.equalsIgnoreCase("json")) {
                CarFormatter.printJson(filtered);
            } else {
                System.out.println("Unknown output format.");
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            printUsage();
        }
    }

    private static void printUsage() {
        System.out.println("Usage:");
        System.out.println("  java -jar CarApp.jar <inputFile> <filterType> <filterValue1> <filterValue2> [sortType] [outputFormat]");
        System.out.println("Examples:");
        System.out.println("  java -jar CarApp.jar cars.xml brand-price Toyota 25000 year table");
        System.out.println("  java -jar CarApp.jar cars.csv brand-date Honda 2022-05-01 price json");
        System.out.println("  java -jar CarApp.jar cars.xml brand-price Toyota 25000 currency-sort table");
        System.out.println();
        System.out.println("Sort types:");
        System.out.println("  year           - Sort by release year (desc)");
        System.out.println("  price          - Sort by price (desc)");
        System.out.println("  currency-sort  - SUVs in EUR, Sedans in JPY, Trucks in USD (price desc)");
    }
}