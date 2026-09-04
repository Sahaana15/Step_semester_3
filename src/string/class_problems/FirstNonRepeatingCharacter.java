package string.class_problems;
import java.util.*;

public class FirstNonRepeatingCharacter {

    static char findFirstNonRepeatingChar(String text) {

        HashMap<Character, Integer> map = new HashMap<>();

        for (char c : text.toCharArray())
            map.put(c, map.getOrDefault(c, 0) + 1);

        for (char c : text.toCharArray()) {
            if (map.get(c) == 1)
                return c;
        }

        return '\0';
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter text: ");
        String text = sc.nextLine();

        char result = findFirstNonRepeatingChar(text);

        if (result == '\0')
            System.out.println("No Non-Repeating Character Found");
        else
            System.out.println(
                    "First Non-Repeating Character: '" + result + "'"
            );
    }
}
