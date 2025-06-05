package main;

import java.util.*;
import filter.*;
import formatter.*;
import sorter.*;
import model.Car;
import parser.CarParser;
import parser.XMLCarParser;
import parser.CSVCarParser;
import exception.CarProcessingException;

public class CarProcessorApp {
    public static void main(String[] args) {
        try {
            if (args.length < 2) {
                printUsage();
                return;
            }
            String inputFile = args[0];
            String filterType = args[1];

            CarParser parser = getParser(inputFile);
            List<Car> cars = parser.parse(inputFile);

            if (cars == null || cars.isEmpty()) {
                throw new CarProcessingException("No cars loaded from file: " + inputFile);
            }

            // --- FILTER SELECTION ---
            CarFilterStrategy filterStrategy = getFilterStrategy(filterType);
            String[] filterArgs = Arrays.copyOfRange(args, 2, Math.min(args.length, 4));
            List<Car> filtered = filterStrategy.filter(cars, filterArgs);

            // --- SORT SELECTION ---
            String sortType = args.length > 4 ? args[4] : "";
            if (!sortType.isEmpty()) {
                CarSorterStrategy sorter = getSorterStrategy(sortType);
                sorter.sort(filtered);
            }

            // --- FORMAT SELECTION ---
            String outputFormat = (args.length > 5) ? args[5] : "table";
            CarFormatterStrategy formatter = getFormatterStrategy(outputFormat);
            formatter.format(filtered);

        } catch (CarProcessingException e) {
            System.err.println("Error: " + e.getMessage());
            printUsage();
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
            printUsage();
        }
    }

    private static CarParser getParser(String inputFile) throws CarProcessingException {
        if (inputFile.endsWith(".xml")) {
            return new XMLCarParser();
        } else if (inputFile.endsWith(".csv")) {
            return new CSVCarParser();
        } else {
            throw new CarProcessingException("Unsupported file format: " + inputFile);
        }
    }

    private static CarFilterStrategy getFilterStrategy(String type) throws CarProcessingException {
        switch (type.toLowerCase()) {
            case "brand-price": return new BrandAndPriceFilter();
            case "brand-date":  return new BrandAndDateFilter();
            default:
                throw new CarProcessingException("Unsupported filter type: " + type);
        }
    }

    private static CarSorterStrategy getSorterStrategy(String type) throws CarProcessingException {
        switch (type.toLowerCase()) {
            case "year":
            case "releaseyear":
            case "release-year":
                return new sorter.ReleaseYearDescSorter();
            case "price":
                return new sorter.PriceDescSorter();
            case "currency-sort":
                return new sorter.CurrencySortSorter();
            default:
                throw new CarProcessingException("Unsupported sort type: " + type);
        }
    }

    private static CarFormatterStrategy getFormatterStrategy(String format) throws CarProcessingException {
        switch (format.toLowerCase()) {
            case "table":
                return new formatter.TableCarFormatter();
            case "xml":
                return new formatter.XmlCarFormatter();
            case "json":
                return new formatter.JsonCarFormatter();
            default:
                throw new CarProcessingException("Unknown output format: " + format);
        }
    }

    private static void printUsage() {
        System.out.println("Usage:");
        System.out.println("  java -jar CarApp.jar <inputFile> <filterType> <filterValue1> <filterValue2> [sortType] [outputFormat]");
        System.out.println("Examples:");
        System.out.println("  java -jar CarApp.jar cars.xml brand-price Toyota 25000 year table");
        System.out.println("  java -jar CarApp.jar cars.csv brand-date Honda 2022-05-01 price xml");
        System.out.println("  java -jar CarApp.jar cars.xml brand-price Toyota 25000 currency-sort json");
        System.out.println();
        System.out.println("Sort types: year, price, currency-sort");
        System.out.println("Output formats: table, xml, json");
    }
}