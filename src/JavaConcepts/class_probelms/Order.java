package JavaConcepts.class_probelms;
import java.util.*;

public class Order {

    static class LineItem {
        String productName;
        int quantity;
        double unitPrice;

        LineItem(String productName, int quantity, double unitPrice) {
            this.productName = productName;
            this.quantity = quantity;
            this.unitPrice = unitPrice;
        }

        double total() {
            return quantity * unitPrice;
        }
    }

    LineItem[] items = new LineItem[100];
    int count = 0;

    void addItem(LineItem item) {
        if (count < items.length) {
            items[count++] = item;
        }
    }

    class Cursor {
        int position = 0;

        boolean hasNext() {
            return position < count;
        }

        LineItem next() {
            if (hasNext()) {
                return items[position++];
            }
            return null;
        }

        void reset() {
            position = 0;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Order order = new Order();

        int n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            String name = sc.next();
            int quantity = sc.nextInt();
            double price = sc.nextDouble();

            order.addItem(new LineItem(name, quantity, price));
        }

        Cursor cursor = order.new Cursor();

        double grandTotal = 0;

        while (cursor.hasNext()) {
            LineItem item = cursor.next();

            System.out.println(item.productName + " "
                    + item.total());

            grandTotal += item.total();
        }

        System.out.println("Total " + grandTotal);

        System.out.println(
                "cursor: inner (reads the order's array)");

        System.out.println(
                "line item: static nested (needs no order)");
    }
}