package stringoperationsandperformance.assignment_problems;
public class WordReversalEncoder {

    String reverseEachWord(String sentence) {
        String[] words = sentence.split(" ");
        StringBuilder result = new StringBuilder();

        for (String word : words) {
            StringBuilder reverse = new StringBuilder(word);
            result.append(reverse.reverse()).append(" ");
        }

        return result.toString().trim();
    }

    public static void main(String[] args) {
        String sentence = "hello club";

        WordReversalEncoder obj = new WordReversalEncoder();
        System.out.println(obj.reverseEachWord(sentence));
    }
}