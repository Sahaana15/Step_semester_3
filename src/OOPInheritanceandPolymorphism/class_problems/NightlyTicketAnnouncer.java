package OOPInheritanceandPolymorphism.class_problems;
class EventTicket {
    protected double balanceDue;

    public EventTicket(double basePrice) {
        balanceDue = basePrice;
    }

    public double getBalanceDue() {
        return balanceDue;
    }

    public void printTicket() {
        System.out.print(
                "Standard | Balance: " + balanceDue
        );
    }
}

class WorkshopTicket extends EventTicket {
    private String track;

    public WorkshopTicket(double basePrice, String track) {
        super(basePrice);
        this.track = track;
    }

    public String getTrack() {
        return track;
    }

    @Override
    public void printTicket() {
        System.out.print(
                "Workshop | Track: " + track +
                        " | Balance: " + balanceDue
        );
    }
}

public class NightlyTicketAnnouncer {

    public static String batchPrint(EventTicket[] tickets) {

        StringBuilder result = new StringBuilder();

        for (EventTicket ticket : tickets) {

            ticket.printTicket();

            if (ticket instanceof WorkshopTicket) {
                WorkshopTicket workshop =
                        (WorkshopTicket) ticket;

                result.append(
                        "Workshop | Track: "
                );
                result.append(workshop.getTrack());
                result.append(
                        " | Balance: "
                );
                result.append(workshop.getBalanceDue());
                result.append(
                        " [Track via downcast: "
                );
                result.append(workshop.getTrack());
                result.append("] | ");
            } else {
                result.append(
                        "Standard | Balance: "
                );
                result.append(ticket.getBalanceDue());
                result.append(" | ");
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {

        EventTicket standard =
                new EventTicket(500);

        WorkshopTicket workshop =
                new WorkshopTicket(1200, "AI/ML");

        EventTicket[] tickets = {
                standard,
                workshop
        };

        System.out.println(
                batchPrint(tickets)
        );
    }
}