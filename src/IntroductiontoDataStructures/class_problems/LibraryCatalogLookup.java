package IntroductiontoDataStructures.class_problems;
class BookRecord {
    String isbn;
    String title;

    BookRecord(String isbn, String title) {
        this.isbn = isbn;
        this.title = title;
    }
}

public class LibraryCatalogLookup {
    public static String findBook(BookRecord[] catalog, String targetIsbn) {
        int left = 0;
        int right = catalog.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int compareResult = catalog[mid].isbn.compareTo(targetIsbn);

            if (compareResult == 0) {
                return catalog[mid].title;
            } else if (compareResult < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return "Not Found";
    }

    public static void main(String[] args) {
        BookRecord[] catalog = {
                new BookRecord("0001112223", "Introduction to Algebra"),
                new BookRecord("0002223334", "Beginning Python"),
                new BookRecord("0003334445", "Classic Mythology")
        };
        findBook(catalog, "0009998887");
    }
}