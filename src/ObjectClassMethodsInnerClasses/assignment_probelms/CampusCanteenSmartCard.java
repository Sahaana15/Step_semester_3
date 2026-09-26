package ObjectClassMethodsInnerClasses.assignment_probelms;
import java.util.*;

interface PricingPlan {

    double calculatePrice(double originalPrice);

    String getName();
}

class DayScholarPlan implements PricingPlan {

    @Override
    public double calculatePrice(double originalPrice) {
        return originalPrice;
    }

    @Override
    public String getName() {
        return "Day Scholar";
    }
}

class HostellerPlan implements PricingPlan {

    @Override
    public double calculatePrice(double originalPrice) {
        return originalPrice * 0.90;
    }

    @Override
    public String getName() {
        return "Hosteller";
    }
}

class StaffPlan implements PricingPlan {

    @Override
    public double calculatePrice(double originalPrice) {
        return originalPrice * 0.80;
    }

    @Override
    public String getName() {
        return "Staff";
    }
}

class Transaction {

    private double amount;
    private String description;

    public Transaction(
            double amount,
            String description) {

        this.amount = amount;
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }
}

class FoodItem {

    private String name;
    private double price;

    public FoodItem(
            String name,
            double price) {

        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}

class Purchase {

    private FoodItem item;
    private double chargedAmount;
    private boolean refunded;

    public Purchase(
            FoodItem item,
            double chargedAmount) {

        this.item = item;
        this.chargedAmount = chargedAmount;
        refunded = false;
    }

    public FoodItem getItem() {
        return item;
    }

    public double getChargedAmount() {
        return chargedAmount;
    }

    public boolean isRefunded() {
        return refunded;
    }

    public void markRefunded() {
        refunded = true;
    }
}

class SmartCard {

    private String cardNumber;
    private PricingPlan plan;

    private double balance;

    private List<Transaction> transactions;

    private boolean blocked;

    public SmartCard(
            String cardNumber,
            PricingPlan plan) {

        this.cardNumber = cardNumber;
        this.plan = plan;

        balance = 0;
        blocked = false;

        transactions =
                new ArrayList<>();
    }

    public void topUp(double amount) {

        if (blocked) {

            System.out.println(
                    "Top-up rejected: Card is blocked."
            );

            return;
        }

        if (amount < 100) {

            System.out.println(
                    "Top-up rejected: Minimum top-up is ₹100."
            );

            return;
        }

        if (balance + amount > 5000) {

            System.out.println(
                    "Top-up rejected: Maximum balance is ₹5000."
            );

            return;
        }

        balance += amount;

        transactions.add(
                new Transaction(
                        amount,
                        "Top-up"
                )
        );

        System.out.printf(
                "%s topped up with ₹%.2f. Balance: ₹%.2f%n",
                cardNumber,
                amount,
                balance
        );
    }

    public Purchase purchase(
            FoodItem item) {

        if (blocked) {

            System.out.println(
                    "Purchase failed: Card is blocked."
            );

            return null;
        }

        double chargedPrice =
                plan.calculatePrice(
                        item.getPrice()
                );

        if (chargedPrice > balance) {

            System.out.printf(
                    "Purchase failed: Insufficient balance "
                            + "(required ₹%.2f, available ₹%.2f).%n",
                    chargedPrice,
                    balance
            );

            return null;
        }

        balance -= chargedPrice;

        transactions.add(
                new Transaction(
                        -chargedPrice,
                        item.getName()
                )
        );

        Purchase purchase =
                new Purchase(
                        item,
                        chargedPrice
                );

        System.out.printf(
                "%s purchased for ₹%.2f. Balance: ₹%.2f%n",
                item.getName(),
                chargedPrice,
                balance
        );

        return purchase;
    }

    public void refund(Purchase purchase) {

        if (purchase == null) {
            return;
        }

        if (purchase.isRefunded()) {

            System.out.println(
                    "Refund rejected: "
                            + purchase.getItem().getName()
                            + " has already been refunded."
            );

            return;
        }

        double amount =
                purchase.getChargedAmount();

        if (balance + amount > 5000) {

            System.out.println(
                    "Refund rejected: Balance limit exceeded."
            );

            return;
        }

        balance += amount;

        transactions.add(
                new Transaction(
                        amount,
                        "Refund - "
                                + purchase.getItem().getName()
                )
        );

        purchase.markRefunded();

        System.out.printf(
                "Refund of ₹%.2f for %s processed. "
                        + "Balance: ₹%.2f%n",
                amount,
                purchase.getItem().getName(),
                balance
        );
    }

    public void block() {
        blocked = true;
        System.out.println("Card blocked.");
    }

    public void unblock() {
        blocked = false;
        System.out.println("Card unblocked.");
    }

    public void miniStatement() {

        System.out.print(
                "Mini-statement for "
                        + cardNumber
                        + ": "
        );

        for (int i = 0;
             i < transactions.size();
             i++) {

            double amount =
                    transactions.get(i).getAmount();

            if (amount >= 0) {
                System.out.printf(
                        "+%.2f",
                        amount
                );
            } else {
                System.out.printf(
                        "%.2f",
                        amount
                );
            }

            if (i < transactions.size() - 1) {
                System.out.print(", ");
            }
        }

        System.out.printf(
                " = ₹%.2f%n",
                balance
        );
    }
}

public class CampusCanteenSmartCard {

    public static void main(String[] args) {

        SmartCard card =
                new SmartCard(
                        "C-2045",
                        new HostellerPlan()
                );

        card.topUp(500);

        FoodItem vegThali =
                new FoodItem(
                        "Veg Thali",
                        120
                );

        FoodItem coldCoffee =
                new FoodItem(
                        "Cold Coffee",
                        60
                );

        FoodItem expensiveItem =
                new FoodItem(
                        "Special Meal",
                        400
                );

        Purchase p1 =
                card.purchase(vegThali);

        Purchase p2 =
                card.purchase(coldCoffee);

        card.purchase(expensiveItem);

        card.refund(p1);

        card.refund(p1);

        card.miniStatement();
    }
}
