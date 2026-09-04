package stringoperationsandperformance.class_problems;
public class MaskedPhoneNumberFormatter {

    String maskPhoneNumber(String phone) {

        if (phone.length() != 10)
            return "Invalid phone number";

        for (int i = 0; i < phone.length(); i++) {
            if (!Character.isDigit(phone.charAt(i)))
                return "Invalid phone number";
        }

        StringBuilder result = new StringBuilder("XXXXXX");
        result.insert(6, "-");
        result.append(phone.substring(6));

        return result.toString();
    }

    public static void main(String[] args) {
        String phone = "9876543210";

        MaskedPhoneNumberFormatter obj = new MaskedPhoneNumberFormatter();
        System.out.println(obj.maskPhoneNumber(phone));
    }
}

