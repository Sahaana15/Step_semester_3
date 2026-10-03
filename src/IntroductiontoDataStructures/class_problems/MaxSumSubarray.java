package IntroductiontoDataStructures.class_problems;
public class MaxSumSubarray {
    public static int maxSumSubarray(int[] sales, int k) {
        if (sales == null || sales.length < k || k <= 0) {
            return 0;
        }

        int currentWindowSum = 0;
        for (int i = 0; i < k; i++) {
            currentWindowSum += sales[i];
        }

        int maxSalesSum = currentWindowSum;

        for (int i = k; i < sales.length; i++) {
            currentWindowSum += sales[i] - sales[i - k];
            maxSalesSum = Math.max(maxSalesSum, currentWindowSum);
        }

        return maxSalesSum;
    }

    public static void main(String[] args) {
        int[] sales = {2, 1, 5, 1, 3, 2};
        int k = 3;
        maxSumSubarray(sales, k);
    }
}