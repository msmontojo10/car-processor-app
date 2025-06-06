# CarProcessorApp

A standalone Java CLI tool for parsing, filtering, sorting, and outputting car data from XML or CSV files.

## Features

- Parse car data from XML/CSV
- Filter by brand & price or brand & release date
- Sort by release year, price, or (optionally) by type/currency
- Output as table, XML, or JSON

## Usage

```sh
java -jar CarProcessorApp.jar <inputFile> <filterType> <filterValue1> <filterValue2> [sortType] [outputFormat]
```

**Important:**  
The input file (e.g., `cars.xml` or `cars.csv`) **must be located in the same directory as the JAR file** when you run the application.  
If not, provide the full path to the input file.

### Examples

- Table output, filter by brand & price, sort by year:
  ```
  java -jar CarProcessorApp.jar cars.xml brand-price Toyota 25000 year table
  ```
- JSON output, filter by brand & release date, sort by price:
  ```
  java -jar CarProcessorApp.jar cars.csv brand-date Honda 2022-05-01 price json
  ```
- **Currency-based sorting** (SUVs in EUR, Sedans in JPY, Trucks in USD):
  ```
  java -jar CarProcessorApp.jar cars.xml brand-price Toyota 25000 currency-sort table
  ```

### Sort Types

- `year` - Sort by release year (desc)
- `price` - Sort by price (desc)
- `currency-sort` - SUVs in EUR, Sedans in JPY, Trucks in USD (price desc)

## File Formats

**CSV:**  
`brand,type,price,currency,releaseDate`  
Example:  
`Toyota,SUV,20000,EUR,2021-04-01`

**XML:**  
```xml
<cars>
  <car>
    <brand>Toyota</brand>
    <type>SUV</type>
    <price>20000</price>
    <currency>EUR</currency>
    <releaseDate>2021-04-01</releaseDate>
  </car>
  ...
</cars>
```

---