package stringoperationsandperformance.class_problems;
public class BankTransactionReferenceGeneratorValidator {

    String normalizeReference(String raw) {
        raw = raw.trim();

        if (raw.length() < 3)
            return raw.toUpperCase();

        return raw.substring(0, 3).toUpperCase() + raw.substring(3);
    }

    String validateAndFormat(String reference) {

        if (reference.length() != 14)
            return "Invalid: wrong length";

        for (int i = 0; i < 3; i++) {
            if (!Character.isLetter(reference.charAt(i)))
                return "Invalid: bank code must be 3 letters";
        }

        for (int i = 3; i < 14; i++) {
            if (!Character.isDigit(reference.charAt(i)))
                return "Invalid: body must contain only digits";
        }

        String bank = reference.substring(0, 3);
        String date = reference.substring(3, 9);
        String seq = reference.substring(9);

        StringBuilder result = new StringBuilder();

        result.append("[")
                .append(bank)
                .append("] DATE: ")
                .append(date.substring(0, 2))
                .append("/")
                .append(date.substring(2, 4))
                .append("/")
                .append(date.substring(4, 6))
                .append(" | SEQ: ")
                .append(seq);

        return result.toString();
    }

    public static void main(String[] args) {
        String raw = " hdf03022600042 ";

        BankTransactionReferenceGeneratorValidator obj =
                new BankTransactionReferenceGeneratorValidator();

        String normalized = obj.normalizeReference(raw);

        System.out.println(obj.validateAndFormat(normalized));
    }
}


