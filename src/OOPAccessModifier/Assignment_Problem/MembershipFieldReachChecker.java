package OOPAccessModifier.Assignment_Problem;
public class MembershipFieldReachChecker {

    static String classifyAccess(String fieldModifier, String accessorContext) {

        if (fieldModifier.equals("private")) {
            return accessorContext.equals("SAME_CLASS")
                    ? "ALLOWED" : "DENIED";
        }

        if (fieldModifier.equals("default")) {
            return accessorContext.equals("SAME_CLASS") ||
                    accessorContext.equals("SAME_PACKAGE")
                    ? "ALLOWED" : "DENIED";
        }

        if (fieldModifier.equals("protected")) {
            return accessorContext.equals("SAME_CLASS") ||
                    accessorContext.equals("SAME_PACKAGE")
                    ? "ALLOWED" : "DENIED";
        }

        if (fieldModifier.equals("public")) {
            return "ALLOWED";
        }

        return "DENIED";
    }

    static String summarizeByModifier(String[][] attempts) {

        String[] modifiers = {"private", "default", "protected", "public"};
        int[] allowed = new int[4];
        int[] denied = new int[4];

        for (String[] attempt : attempts) {

            String modifier = attempt[0];
            String context = attempt[1];

            int index = -1;

            for (int i = 0; i < modifiers.length; i++) {
                if (modifiers[i].equals(modifier)) {
                    index = i;
                    break;
                }
            }

            if (classifyAccess(modifier, context).equals("ALLOWED")) {
                allowed[index]++;
            } else {
                denied[index]++;
            }
        }

        String result = "";

        for (int i = 0; i < modifiers.length; i++) {

            if (i > 0) {
                result += " | ";
            }

            result += modifiers[i] + ": "
                    + allowed[i] + " allowed / "
                    + denied[i] + " denied";
        }

        return result;
    }

    static class LibraryMember {

        private String membershipId;
        String branchCode;
        protected double finesOwed;
        public String displayName;

        public LibraryMember(String membershipId,
                             String branchCode,
                             double finesOwed,
                             String displayName) {

            if (membershipId == null ||
                    membershipId.trim().length() < 4) {

                throw new IllegalArgumentException(
                        "construction rejected");
            }

            this.membershipId = membershipId;
            this.branchCode = branchCode;
            this.finesOwed = finesOwed;
            this.displayName = displayName;
        }
    }

    public static void main(String[] args) {

        System.out.println(
                classifyAccess("private", "SAME_CLASS"));

        System.out.println(
                classifyAccess("protected", "DIFFERENT_PACKAGE"));

        String[][] attempts = {
                {"private", "SAME_CLASS"},
                {"private", "SAME_PACKAGE"},
                {"default", "SAME_PACKAGE"},
                {"default", "DIFFERENT_PACKAGE"},
                {"protected", "SAME_PACKAGE"},
                {"protected", "SAME_CLASS"},
                {"public", "DIFFERENT_PACKAGE"}
        };

        System.out.println(summarizeByModifier(attempts));

        try {
            new LibraryMember(
                    "LB9", "BR1", 0, "Priya Nair");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        LibraryMember member = new LibraryMember(
                "LB94", "BR1", 0, "Priya Nair");

        System.out.println("LibraryMember created");
    }
}
