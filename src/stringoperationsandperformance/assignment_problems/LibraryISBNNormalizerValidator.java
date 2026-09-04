package stringoperationsandperformance.assignment_problems;
public class LibraryISBNNormalizerValidator {

    String normalizeCode(String raw) {
        raw = raw.trim();

        if (raw.length() < 3)
            return raw.toUpperCase();

        return raw.substring(0, 3).toUpperCase() + raw.substring(3);
    }

    String validateAndFormat(String code) {

        if (code.length() != 13)
            return "Invalid: wrong length";

        for (int i = 0; i < 3; i++) {
            if (!Character.isLetter(code.charAt(i)))
                return "Invalid: publisher code must be 3 letters";
        }

        for (int i = 3; i < 13; i++) {
            if (!Character.isDigit(code.charAt(i)))
                return "Invalid: body must contain only digits";
        }

        String publisher = code.substring(0, 3);
        String year = code.substring(3, 7);
        String catalog = code.substring(7, 13);

        StringBuilder result = new StringBuilder();

        result.append("[")
                .append(publisher)
                .append("] YEAR: ")
                .append(year)
                .append(" | CATALOG: ")
                .append(catalog);

        return result.toString();
    }

    public static void main(String[] args) {
        String raw = " pen2026004251 ";

        LibraryISBNNormalizerValidator obj =
                new LibraryISBNNormalizerValidator();

        String code = obj.normalizeCode(raw);

        System.out.println(obj.validateAndFormat(code));
    }
}
