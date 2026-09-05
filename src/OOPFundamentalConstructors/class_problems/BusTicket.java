package OOPFundamentalConstructors.class_problems;
import java.util.HashSet;

public class BusTicket {

    private String passengerName;
    private String destination;
    private boolean checkedIn;

    public BusTicket(String passengerName, String destination) {

        if (passengerName == null || destination == null ||
                passengerName.trim().isEmpty() ||
                destination.trim().isEmpty() ||
                !passengerName.matches("[A-Za-z ]+") ||
                !destination.matches("[A-Za-z ]+")) {

            throw new IllegalArgumentException("Invalid booking");
        }

        this.passengerName = passengerName;
        this.destination = destination;
        this.checkedIn = false;
    }

    void markCheckedIn() {
        if (checkedIn) {
            throw new IllegalStateException("Already checked in");
        }

        checkedIn = true;
    }

    static void processBatch(String[][] rawBookings) {

        HashSet<String> accepted = new HashSet<>();

        int valid = 0;
        int rejected = 0;
        int duplicates = 0;

        for (String[] booking : rawBookings) {

            if (booking == null || booking.length < 2) {
                rejected++;
                continue;
            }

            try {
                BusTicket ticket =
                        new BusTicket(booking[0], booking[1]);

                String key = booking[0].trim().toLowerCase()
                        + "|" + booking[1].trim().toLowerCase();

                if (accepted.contains(key)) {
                    duplicates++;
                } else {
                    accepted.add(key);
                    valid++;
                }

            } catch (Exception e) {
                rejected++;
            }
        }

        System.out.println("Valid: " + valid +
                " | Rejected: " + rejected +
                " | Duplicates skipped: " + duplicates);
    }

    public static void main(String[] args) {

        String[][] bookings = {
                {"Divya", "Chennai"},
                {"", "Bangalore"},
                {"Ravi123", "Pune"},
                {"Divya", "Chennai"},
                {" ", " "}
        };

        processBatch(bookings);
    }
}
