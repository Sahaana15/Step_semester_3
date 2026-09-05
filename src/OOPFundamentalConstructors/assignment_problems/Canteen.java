package OOPFundamentalConstructors.assignment_problems;
public class Canteen {

    private String canteenCode;
    private String canteenName;
    private int trustScore;

    public Canteen(String canteenCode,
                   String canteenName,
                   int trustScore) {

        this.canteenCode = canteenCode;
        this.canteenName = canteenName;
        this.trustScore = trustScore;
    }

    public Canteen(String canteenCode, String canteenName) {
        this(canteenCode, canteenName, 3);
    }

    int compareTo(Canteen other) {

        // Higher trust score comes first
        if (this.trustScore != other.trustScore) {
            return other.trustScore - this.trustScore;
        }

        // Code comparison ignoring case
        int codeResult =
                this.canteenCode.compareToIgnoreCase(other.canteenCode);

        if (codeResult != 0) {
            return codeResult;
        }

        // Name length as final tie-breaker
        return this.canteenName.length() -
                other.canteenName.length();
    }

    static Canteen[] rankCanteens(Canteen[] canteens) {

        Canteen[] result = canteens.clone();

        // Manual bubble sort
        for (int i = 0; i < result.length - 1; i++) {

            for (int j = 0; j < result.length - i - 1; j++) {

                if (result[j].compareTo(result[j + 1]) > 0) {

                    Canteen temp = result[j];
                    result[j] = result[j + 1];
                    result[j + 1] = temp;
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {

        Canteen[] canteens = {
                new Canteen("HB3-C", "Spice Junction", 3),
                new Canteen("hb1-c", "Grand Mess", 5),
                new Canteen("HB2-C", "Southern Treats")
        };

        Canteen[] ranked = rankCanteens(canteens);

        for (Canteen c : ranked) {
            System.out.println(c.canteenCode);
        }
    }
}