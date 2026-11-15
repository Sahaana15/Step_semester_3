package JavaConcepts.class_probelms;
import java.util.*;

class Candidate {
    String name;
    int[] scores = new int[6];
    int total;
    int bestGain;
    int startTest;
    int endTest;
}

public class PlacementShortlist {

    static void calculate(Candidate c) {

        for (int score : c.scores) {
            c.total += score;
        }

        int currentSum = 0;
        int bestSum = Integer.MIN_VALUE;

        int currentStart = 0;
        int bestStart = 0;
        int bestEnd = 0;

        for (int i = 0; i < 5; i++) {

            int change = c.scores[i + 1] - c.scores[i];

            if (currentSum < 0) {
                currentSum = 0;
                currentStart = i;
            }

            currentSum += change;

            if (currentSum > bestSum ||
                    (currentSum == bestSum &&
                            currentStart < bestStart)) {

                bestSum = currentSum;
                bestStart = currentStart;
                bestEnd = i;
            }
        }

        c.bestGain = bestSum;
        c.startTest = bestStart + 1;
        c.endTest = bestEnd + 2;
    }

    static void mergeSort(Candidate[] arr, int left, int right) {

        if (left >= right) {
            return;
        }

        int mid = (left + right) / 2;

        mergeSort(arr, left, mid);
        mergeSort(arr, mid + 1, right);

        merge(arr, left, mid, right);
    }

    static void merge(Candidate[] arr, int left,
                      int mid, int right) {

        Candidate[] temp = new Candidate[right - left + 1];

        int i = left;
        int j = mid + 1;
        int k = 0;

        while (i <= mid && j <= right) {

            if (arr[i].total >= arr[j].total) {
                temp[k++] = arr[i++];
            }
            else {
                temp[k++] = arr[j++];
            }
        }

        while (i <= mid) {
            temp[k++] = arr[i++];
        }

        while (j <= right) {
            temp[k++] = arr[j++];
        }

        for (int x = 0; x < temp.length; x++) {
            arr[left + x] = temp[x];
        }
    }

    static int cutoff(Candidate[] arr, int target) {

        int low = 0;
        int high = arr.length;

        while (low < high) {

            int mid = (low + high) / 2;

            if (arr[mid].total >= target) {
                low = mid + 1;
            }
            else {
                high = mid;
            }
        }

        return low;
    }

    static void findPair(Candidate[] arr, int target) {

        int left = 0;
        int right = arr.length - 1;

        while (left < right) {

            int sum = arr[left].total + arr[right].total;

            if (sum == target) {

                System.out.println(
                        "PAIR " + target + ": "
                                + arr[left].name + " + "
                                + arr[right].name
                );

                return;
            }

            else if (sum > target) {
                left++;
            }

            else {
                right--;
            }
        }

        System.out.println("PAIR " + target + ": no pair");
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        Candidate[] arr = new Candidate[n];

        for (int i = 0; i < n; i++) {

            arr[i] = new Candidate();

            arr[i].name = sc.next();

            for (int j = 0; j < 6; j++) {
                arr[i].scores[j] = sc.nextInt();
            }

            calculate(arr[i]);
        }

        mergeSort(arr, 0, n - 1);

        System.out.println("Shortlist:");

        for (int i = 0; i < n; i++) {

            Candidate c = arr[i];

            String sign = c.bestGain > 0 ? "+" : "";

            System.out.println(
                    (i + 1) + ". " + c.name + " "
                            + c.total + " | best climb "
                            + sign + c.bestGain
                            + " (test " + c.startTest
                            + " to " + c.endTest + ")"
            );
        }

        while (sc.hasNext()) {

            String command = sc.next();

            int value = sc.nextInt();

            if (command.equalsIgnoreCase("CUTOFF")) {

                int count = cutoff(arr, value);

                System.out.println(
                        "CUTOFF " + value + ": "
                                + count + " candidates"
                );
            }

            else if (command.equalsIgnoreCase("PAIR")) {

                findPair(arr, value);
            }
        }
    }
}
