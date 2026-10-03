package IntroductiontoDataStructures.assignment_probems;
public class LongestBudgetFriendlyStreak {
    public static int[] longestStreak(int[] costs, long budget) {
        int maxLength = 0;
        int bestStartIndex = -1;
        long currentSum = 0;
        int left = 0;

        for (int right = 0; right < costs.length; right++) {
            currentSum += costs[right];

            while (currentSum > budget && left <= right) {
                currentSum -= costs[left];
                left++;
            }

            int currentLength = right - left + 1;
            if (currentSum <= budget && currentLength > maxLength) {
                maxLength = currentLength;
                bestStartIndex = left;
            }
        }

        if (maxLength == 0) {
            return new int[]{0, -1};
        }

        return new int[]{maxLength, bestStartIndex};
    }

    public static void main(String[] args) {
        int[] costs = {4, 2, 1, 7, 3, 1, 2, 1, 5};
        long budget = 8;
        longestStreak(costs, budget);
    }
}