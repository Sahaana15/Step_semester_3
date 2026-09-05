package OOPFundamental.assignment_problems;
public class LibraryMember {

    // BROKEN VERSION
    // These fields are static, so they are shared by every object.
    // name is wrong as static because each member has a different name.
    // memberId is wrong as static because each member has a different ID.
    // booksIssued is wrong as static because each member has different books.

    static String name;
    static String memberId;
    static int booksIssued;

    LibraryMember(String name, String memberId, int booksIssued) {
        LibraryMember.name = name;
        LibraryMember.memberId = memberId;
        LibraryMember.booksIssued = booksIssued;
    }

    public static void main(String[] args) {

        System.out.println("Broken version:");

        LibraryMember m1 =
                new LibraryMember("Aditi", "LM-1001", 2);

        LibraryMember m2 =
                new LibraryMember("Rohan", "LM-1002", 3);

        System.out.println(m1.name);
        System.out.println(m2.name);

        System.out.println();
        System.out.println("Fixed version:");

        FixedLibraryMember f1 =
                new FixedLibraryMember("Aditi", 2);

        FixedLibraryMember f2 =
                new FixedLibraryMember("Rohan", 3);

        f1.printMemberCard();
        f2.printMemberCard();

        FixedLibraryMember.printTotalMembers();
    }
}

class FixedLibraryMember {

    String name;
    String memberId;
    int booksIssued;

    static String libraryName = "SRM Library";
    static int memberCount = 0;

    FixedLibraryMember(String name, int booksIssued) {

        this.name = name;
        this.booksIssued = booksIssued;

        memberCount++;
        this.memberId = "LM-" + (1000 + memberCount);
    }

    void printMemberCard() {
        System.out.println(name + " | " + memberId);
    }

    static void printTotalMembers() {
        System.out.println("Total members: " + memberCount);
    }
}
