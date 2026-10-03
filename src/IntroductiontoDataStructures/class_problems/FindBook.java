package IntroductiontoDataStructures.class_problems;
class Book {
    String isbn;
    String title;

    Book(String isbn, String title) {
        this.isbn = isbn;
        this.title = title;
    }
}

public class FindBook {
    public static String findBook(Book[] catalog, String targetIsbn) {
        int low = 0;
        int high = catalog.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int cmp = catalog[mid].isbn.compareTo(targetIsbn);

            if (cmp == 0) {
                return catalog[mid].title;
            } else if (cmp < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return "Not Found";
    }

    public static void main(String[] args) {
        Book[] catalog = {
                new Book("0001112223", "Introduction to Algebra"),
                new Book("0002223334", "Beginning Python"),
                new Book("0003334445", "Classic Mythology")
        };
        findBook(catalog, "0003334445");
    }
}