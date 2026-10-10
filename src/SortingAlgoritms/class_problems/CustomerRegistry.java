package SortingAlgoritms.class_problems;
import java.util.*;

class Customer {
    int id;
    String name;

    Customer(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Customer)) return false;

        Customer c = (Customer) obj;
        return id == c.id && name.equals(c.name);
    }

    public int hashCode() {
        return Objects.hash(id, name);
    }
}

public class CustomerRegistry {
    public static void main(String[] args) {
        Set<Customer> customers = new HashSet<>();

        System.out.println(customers.add(new Customer(101, "Asha")));
        System.out.println(customers.add(new Customer(101, "Asha"))
                ? "added" : "false (duplicate rejected)");
        System.out.println(customers.add(new Customer(102, "Ravi")));
        System.out.println("unique count " + customers.size());
        System.out.println("contains: " +
                customers.contains(new Customer(101, "Asha")));
    }
}
