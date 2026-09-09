package OOPAccessModifier.Assignment_Problem;
public class ImmutableLoanReceiptNightlyCirculationLedger {

    static {
        System.out.println("Circulation ledger initialized");
    }

    static class LoanReceipt {

        private final String memberId;
        private final String[] bookIds;

        public LoanReceipt(String memberId, String[] bookIds) {

            if (memberId == null || bookIds == null) {
                throw new IllegalArgumentException(
                        "construction rejected");
            }

            for (String id : bookIds) {

                if (id == null ||
                        !id.matches("BK-\\d{3}")) {

                    throw new IllegalArgumentException(
                            "construction rejected");
                }
            }

            this.memberId = memberId;
            this.bookIds = bookIds.clone();
        }

        public String getMemberId() {
            return memberId;
        }

        public String[] getBookIds() {
            return bookIds.clone();
        }

        public LoanReceipt withCorrectedBookId(
                int index, String newId) {

            if (index < 0 ||
                    index >= bookIds.length ||
                    newId == null ||
                    !newId.matches("BK-\\d{3}")) {

                throw new IllegalArgumentException(
                        "Invalid book ID");
            }

            String[] copy = bookIds.clone();
            copy[index] = newId;

            return new LoanReceipt(memberId, copy);
        }
    }

    static class ReferenceOnlyLoanReceipt
            extends LoanReceipt {

        private final String roomNumber;

        public ReferenceOnlyLoanReceipt(
                String memberId,
                String[] bookIds,
                String roomNumber) {

            super(memberId, bookIds);
            this.roomNumber = roomNumber;
        }

        public String getRoomNumber() {
            return roomNumber;
        }
    }

    static String processNightlyCirculation(
            LoanReceipt[] receipts) {

        int processed = 0;
        int nullSkipped = 0;
        int referenceOnly = 0;
        int regular = 0;

        if (receipts != null) {

            for (LoanReceipt receipt : receipts) {

                if (receipt == null) {
                    nullSkipped++;
                    continue;
                }

                processed++;

                if (receipt instanceof ReferenceOnlyLoanReceipt) {
                    referenceOnly++;
                } else {
                    regular++;
                }
            }
        }

        return processed + " processed | "
                + nullSkipped + " null skipped | "
                + referenceOnly + " reference-only | "
                + regular + " regular";
    }

    public static void main(String[] args) {

        try {
            new LoanReceipt(
                    "LIB-8841",
                    new String[]{"BK-100", "bad"});
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        LoanReceipt r =
                new LoanReceipt(
                        "LIB-8841",
                        new String[]{"BK-100", "BK-101"});

        String[] ids = r.getBookIds();

        ids[0] = "HACKED";

        System.out.println(r.getBookIds()[0]);

        LoanReceipt corrected =
                r.withCorrectedBookId(0, "BK-999");

        System.out.println(corrected.getBookIds()[0]);

        LoanReceipt[] receipts = {

                new ReferenceOnlyLoanReceipt(
                        "LIB-001",
                        new String[]{"BK-200"},
                        "Reading Room 3"),

                null,

                new LoanReceipt(
                        "LIB-002",
                        new String[]{"BK-201"})
        };

        System.out.println(
                processNightlyCirculation(receipts));
    }
}