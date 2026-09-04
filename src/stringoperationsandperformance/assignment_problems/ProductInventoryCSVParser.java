package stringoperationsandperformance.assignment_problems;
public class ProductInventoryCSVParser {

    void parseInventoryRecord(String csvLine) {
        String[] fields = csvLine.split(",");

        if (fields.length != 3) {
            System.out.println("Invalid Record");
            return;
        }

        System.out.println("Product: " + fields[0]
                + " | SKU: " + fields[1]
                + " | Qty: " + fields[2]);
    }

    public static void main(String[] args) {
        String csvLine = "Wireless Mouse,WM-2201,150";

        ProductInventoryCSVParser obj = new ProductInventoryCSVParser();
        obj.parseInventoryRecord(csvLine);
    }
}
