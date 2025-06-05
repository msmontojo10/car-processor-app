package parser;

import java.util.*;
import java.text.SimpleDateFormat;
import javax.xml.parsers.*;
import org.w3c.dom.*;

import model.Car;

import java.io.File;

public class XMLCarParser implements CarParser {
    public List<Car> parse(String filename) throws Exception {
        List<Car> cars = new ArrayList<Car>();
        File file = new File(filename);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(file);
        doc.getDocumentElement().normalize();

        NodeList nList = doc.getElementsByTagName("car");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element e = (Element) nNode;
                String brand = e.getElementsByTagName("brand").item(0).getTextContent();
                String type = e.getElementsByTagName("type").item(0).getTextContent();
                double price = Double.parseDouble(e.getElementsByTagName("price").item(0).getTextContent());
                String currency = e.getElementsByTagName("currency").item(0).getTextContent();
                String dateStr = e.getElementsByTagName("releaseDate").item(0).getTextContent();
                Date releaseDate = sdf.parse(dateStr);
                cars.add(new Car(brand, type, price, currency, releaseDate));
            }
        }
        return cars;
    }
}