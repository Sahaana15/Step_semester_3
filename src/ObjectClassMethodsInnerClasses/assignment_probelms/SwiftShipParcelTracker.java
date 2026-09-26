package ObjectClassMethodsInnerClasses.assignment_probelms;
import java.util.*;

interface ShippingType {
    double calculateCharge(double weight);
    String getName();
}

class StandardShipping implements ShippingType {

    @Override
    public double calculateCharge(double weight) {
        return 40 + (10 * weight);
    }

    @Override
    public String getName() {
        return "Standard";
    }
}

class ExpressShipping implements ShippingType {

    @Override
    public double calculateCharge(double weight) {
        return 80 + (15 * weight);
    }

    @Override
    public String getName() {
        return "Express";
    }
}

class FragileShipping implements ShippingType {

    @Override
    public double calculateCharge(double weight) {
        return new StandardShipping()
                .calculateCharge(weight) + 50;
    }

    @Override
    public String getName() {
        return "Fragile";
    }
}

interface NotificationChannel {
    void notify(String parcelId, String status);
}

class SmsChannel implements NotificationChannel {

    @Override
    public void notify(String parcelId, String status) {
        System.out.println(
                "[SMS] " + parcelId
                        + " is now " + status + "."
        );
    }
}

class EmailChannel implements NotificationChannel {

    @Override
    public void notify(String parcelId, String status) {
        System.out.println(
                "[Email] " + parcelId
                        + " is now " + status + "."
        );
    }
}

class Customer {

    private String name;

    public Customer(String name) {
        this.name = name;
    }
}

class Parcel {

    private String parcelId;
    private double weight;
    private ShippingType shippingType;
    private String status;

    private List<NotificationChannel> channels;

    public Parcel(String parcelId,
                  double weight,
                  ShippingType shippingType) {

        this.parcelId = parcelId;
        this.weight = weight;
        this.shippingType = shippingType;

        status = "BOOKED";

        channels = new ArrayList<>();
    }

    public void subscribe(NotificationChannel channel) {
        channels.add(channel);
    }

    public double calculateCharge() {
        return shippingType.calculateCharge(weight);
    }

    public void changeStatus(String newStatus) {

        if (!isValidTransition(newStatus)) {

            System.out.println(
                    "Invalid transition: "
                            + status
                            + " → "
                            + newStatus
                            + " is not allowed."
            );

            return;
        }

        status = newStatus;

        notifyChannels();
    }

    private boolean isValidTransition(String newStatus) {

        if (status.equals("BOOKED")
                && newStatus.equals("PICKED_UP")) {
            return true;
        }

        if (status.equals("PICKED_UP")
                && newStatus.equals("IN_TRANSIT")) {
            return true;
        }

        if (status.equals("IN_TRANSIT")
                && newStatus.equals("OUT_FOR_DELIVERY")) {
            return true;
        }

        if (status.equals("OUT_FOR_DELIVERY")
                && newStatus.equals("DELIVERED")) {
            return true;
        }

        return false;
    }

    private void notifyChannels() {

        for (NotificationChannel channel : channels) {
            channel.notify(parcelId, status);
        }
    }

    public void cancel() {

        if (!status.equals("BOOKED")) {

            System.out.println(
                    "Cancellation failed: "
                            + parcelId
                            + " can be cancelled only while BOOKED."
            );

            return;
        }

        status = "CANCELLED";

        System.out.println(
                "Parcel " + parcelId + " cancelled."
        );
    }
}

class ParcelService {

    public Parcel bookParcel(
            Customer customer,
            String parcelId,
            double weight,
            ShippingType shippingType) {

        Parcel parcel =
                new Parcel(
                        parcelId,
                        weight,
                        shippingType
                );

        System.out.println(
                "Parcel " + parcelId
                        + " booked ("
                        + shippingType.getName()
                        + ", "
                        + weight
                        + " kg)."
        );

        System.out.printf(
                "Charge: ₹%.2f%n",
                parcel.calculateCharge()
        );

        return parcel;
    }
}

public class SwiftShipParcelTracker {

    public static void main(String[] args) {

        Customer customer =
                new Customer("Asha");

        ParcelService service =
                new ParcelService();

        Parcel parcel =
                service.bookParcel(
                        customer,
                        "P101",
                        2,
                        new ExpressShipping()
                );

        parcel.subscribe(
                new SmsChannel()
        );

        parcel.subscribe(
                new EmailChannel()
        );

        parcel.changeStatus("PICKED_UP");

        parcel.cancel();

        parcel.changeStatus("IN_TRANSIT");

        parcel.changeStatus("DELIVERED");
    }
}