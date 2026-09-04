package stringoperationsandperformance.assignment_problems;
import java.util.*;

public class StopWordFilteredWordFrequencyReport {

    void printFilteredWordFrequency(String feedback) {

        String[] stopWords = {"the", "was", "and", "a", "is", "of", "in"};

        feedback = feedback.toLowerCase();
        feedback = feedback.replace(".", "");
        feedback = feedback.replace(",", "");

        String[] words = feedback.split("\\s+");

        HashMap<String, Integer> map = new HashMap<>();

        for (String word : words) {

            boolean stop = false;

            for (String s : stopWords) {
                if (word.equals(s)) {
                    stop = true;
                    break;
                }
            }

            if (!stop) {
                map.put(word, map.getOrDefault(word, 0) + 1);
            }
        }

        ArrayList<Map.Entry<String, Integer>> list =
                new ArrayList<>(map.entrySet());

        list.sort((a, b) -> b.getValue() - a.getValue());

        for (Map.Entry<String, Integer> entry : list) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    public static void main(String[] args) {

        String feedback =
                "The mentor was great, the session was great and clear.";

        StopWordFilteredWordFrequencyReport obj =
                new StopWordFilteredWordFrequencyReport();

        obj.printFilteredWordFrequency(feedback);
    }
}
