package IntroductiontoDataStructures.class_problems;
class Pair {
    int left;
    int right;

    Pair(int left, int right) {
        this.left = left;
        this.right = right;
    }
}

public class PairSumSorted {
    public static Object pairSumSorted(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            int currentSum = nums[left] + nums[right];
            if (currentSum == target) {
                return new Pair(nums[left], nums[right]);
            } else if (currentSum < target) {
                left++;
            } else {
                right--;
            }
        }
        return "Not Found";
    }

    public static void main(String[] args) {
        int[] nums = {-4, -1, 0, 3, 5, 9};
        int target = 4;
        pairSumSorted(nums, target);
    }
}
