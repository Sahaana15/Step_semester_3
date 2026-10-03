package IntroductiontoDataStructures.assignment_probems;
public class ExamScoreBandCounter {
    public static int countInBand(int[] scores, int low, int high) {
        int lowerIndex = findLowerBound(scores, low);
        int upperIndex = findUpperBound(scores, high);
        return upperIndex - lowerIndex;
    }

    private static int findLowerBound(int[] scores, int target) {
        int left = 0;
        int right = scores.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (scores[mid] >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private static int findUpperBound(int[] scores, int target) {
        int left = 0;
        int right = scores.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (scores[mid] > target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        int[] scores = {35, 42, 42, 50, 58, 58, 58, 63, 71, 88};
        countInBand(scores, 42, 58);
    }
}