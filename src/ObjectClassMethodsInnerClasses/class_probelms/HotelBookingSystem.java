package ObjectClassMethodsInnerClasses.class_probelms;
import java.time.LocalDate;
import java.util.*;

abstract class Room {

    protected String roomNumber;
    protected String category;

    public Room(String roomNumber, String category) {
        this.roomNumber = roomNumber;
        this.category = category;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public String getCategory() {
        return category;
    }

    public abstract double calculatePrice(long days);
}

class StandardRoom extends Room {

    public StandardRoom(String roomNumber) {
        super(roomNumber, "Standard");
    }

    @Override
    public double calculatePrice(long days) {
        return days * 150.0;
    }
}

class DeluxeRoom extends Room {

    public DeluxeRoom(String roomNumber) {
        super(roomNumber, "Deluxe");
    }

    @Override
    public double calculatePrice(long days) {
        return days * 200.0;
    }
}

class SuiteRoom extends Room {

    public SuiteRoom(String roomNumber) {
        super(roomNumber, "Suite");
    }

    @Override
    public double calculatePrice(long days) {
        return days * 300.0;
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

class Reservation {

    private Room room;
    private Customer customer;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean cancelled;

    public Reservation(
            Room room,
            Customer customer,
            LocalDate startDate,
            LocalDate endDate) {

        this.room = room;
        this.customer = customer;
        this.startDate = startDate;
        this.endDate = endDate;
        this.cancelled = false;
    }

    public boolean overlaps(
            LocalDate start,
            LocalDate end) {

        return !cancelled
                && start.isBefore(endDate)
                && end.isAfter(startDate);
    }

    public double getPrice() {

        long days =
                java.time.temporal.ChronoUnit.DAYS.between(
                        startDate,
                        endDate
                );

        return room.calculatePrice(days);
    }

    public void cancel() {
        cancelled = true;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public Room getRoom() {
        return room;
    }
}

class Hotel {

    private List<Room> rooms;
    private List<Reservation> reservations;

    public Hotel() {
        rooms = new ArrayList<>();
        reservations = new ArrayList<>();
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public boolean isAvailable(
            Room room,
            LocalDate start,
            LocalDate end) {

        for (Reservation reservation : reservations) {

            if (reservation.getRoom() == room
                    && reservation.overlaps(start, end)) {

                return false;
            }
        }

        return true;
    }

    public Reservation bookRoom(
            Customer customer,
            Room room,
            LocalDate start,
            LocalDate end) {

        if (!isAvailable(room, start, end)) {

            System.out.println(
                    "Booking failed: "
                            + room.getCategory()
                            + " Room "
                            + room.getRoomNumber()
                            + " is not available."
            );

            return null;
        }

        Reservation reservation =
                new Reservation(
                        room,
                        customer,
                        start,
                        end
                );

        reservations.add(reservation);

        System.out.println(
                room.getCategory()
                        + " Room "
                        + room.getRoomNumber()
                        + " booked from "
                        + start
                        + " to "
                        + end
        );

        System.out.printf(
                "Total price: $%.2f%n",
                reservation.getPrice()
        );

        return reservation;
    }

    public void cancelReservation(
            Reservation reservation,
            LocalDate cancellationDeadline) {

        if (reservation == null) {
            return;
        }

        LocalDate today = LocalDate.now();

        if (today.isBefore(cancellationDeadline)) {

            reservation.cancel();

            System.out.println(
                    "Reservation cancelled successfully."
            );

        } else {

            System.out.println(
                    "Cancellation deadline has passed."
            );
        }
    }
}

public class HotelBookingSystem {

    public static void main(String[] args) {

        Hotel hotel = new Hotel();

        Room deluxe =
                new DeluxeRoom("101");

        Room standard =
                new StandardRoom("205");

        hotel.addRoom(deluxe);
        hotel.addRoom(standard);

        Customer customer =
                new Customer("John");

        Reservation r1 =
                hotel.bookRoom(
                        customer,
                        deluxe,
                        LocalDate.of(2024, 12, 1),
                        LocalDate.of(2024, 12, 5)
                );

        Reservation r2 =
                hotel.bookRoom(
                        customer,
                        standard,
                        LocalDate.of(2024, 12, 3),
                        LocalDate.of(2024, 12, 7)
                );


        hotel.bookRoom(
                customer,
                deluxe,
                LocalDate.of(2024, 12, 3),
                LocalDate.of(2024, 12, 7)
        );


        if (r1 != null) {
            r1.cancel();

            System.out.println(
                    "Reservation for Deluxe Room 101 "
                            + "cancelled successfully."
            );
        }
    }
}
