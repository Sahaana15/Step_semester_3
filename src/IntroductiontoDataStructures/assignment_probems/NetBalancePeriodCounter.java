package IntroductiontoDataStructures.assignment_probems;
import java.util.HashMap;
import java.util.Map;

public class NetBalancePeriodCounter {
    public static int countPeriods(int[] transactions, long k) {
        Map<Long, Integer> prefixSumCounts = new HashMap<>();
        prefixSumCounts.put(0L, 1);

        long currentSum = 0;
        int count = 0;

        for (int transaction : transactions) {
            currentSum += transaction;

            if (prefixSumCounts.containsKey(currentSum - k)) {
                count += prefixSumCounts.get(currentSum - k);
            }

            prefixSumCounts.put(currentSum, prefixSumCounts.getOrDefault(currentSum, 0) + 1);
        }

        return count;
    }

    public static void main(String[] args) {
        int[] transactions = {3, 4, -7, 1, 3, 3, 1, -4};
        long k = 7;
        countPeriods(transactions, k);
    }
}
