package ObjectClassMethodsInnerClasses.class_probelms;
abstract class Vehicle {

    protected String vehicleName;
    private boolean available;

    public Vehicle(String vehicleName) {
        this.vehicleName = vehicleName;
        this.available = true;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public abstract double calculateCharge(int days);
}

class StandardCar extends Vehicle {

    public StandardCar(String vehicleName) {
        super(vehicleName);
    }

    @Override
    public double calculateCharge(int days) {
        return days * 50.0;
    }
}

class LuxuryCar extends Vehicle {

    public LuxuryCar(String vehicleName) {
        super(vehicleName);
    }

    @Override
    public double calculateCharge(int days) {
        return days * 100.0;
    }
}

class SUV extends Vehicle {

    public SUV(String vehicleName) {
        super(vehicleName);
    }

    @Override
    public double calculateCharge(int days) {
        return days * 80.0;
    }
}

class Customer {

    private String name;

    public Customer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Rental {

    private Customer customer;
    private Vehicle vehicle;
    private int days;
    private double totalCharge;

    public Rental(Customer customer,
                  Vehicle vehicle,
                  int days) {

        this.customer = customer;
        this.vehicle = vehicle;
        this.days = days;
        this.totalCharge =
                vehicle.calculateCharge(days);
    }

    public double getTotalCharge() {
        return totalCharge;
    }

    public void completeReturn() {
        vehicle.setAvailable(true);
    }
}

class RentalService {

    public Rental rentVehicle(
            Customer customer,
            Vehicle vehicle,
            int days) {

        if (!vehicle.isAvailable()) {

            System.out.println(
                    vehicle.getVehicleName()
                            + " is not available."
            );

            return null;
        }

        vehicle.setAvailable(false);

        Rental rental =
                new Rental(customer, vehicle, days);

        System.out.println(
                vehicle.getVehicleName()
                        + " rented for "
                        + days
                        + " days."
        );

        System.out.printf(
                "Total charge: $%.2f%n",
                rental.getTotalCharge()
        );

        return rental;
    }

    public void returnVehicle(Rental rental) {

        if (rental != null) {

            rental.completeReturn();

            System.out.println(
                    "Vehicle returned. Now available."
            );
        }
    }
}

public class VehicleRentalSystem {

    public static void main(String[] args) {

        Customer customer =
                new Customer("John");

        Vehicle luxury =
                new LuxuryCar("Luxury Car A");

        Vehicle standard =
                new StandardCar("Standard Car B");

        RentalService service =
                new RentalService();

        Rental r1 =
                service.rentVehicle(
                        customer,
                        luxury,
                        3
                );

        Rental r2 =
                service.rentVehicle(
                        customer,
                        standard,
                        5
                );

        service.returnVehicle(r1);

        service.rentVehicle(
                customer,
                luxury,
                2
        );
    }
}
