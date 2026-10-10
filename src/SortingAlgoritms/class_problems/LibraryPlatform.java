package SortingAlgoritms.class_problems;
import java.util.*;

class Resource {
    String id, type;
    boolean reservable, downloadable;

    Resource(String id, String type,
             boolean reservable, boolean downloadable) {
        this.id = id;
        this.type = type;
        this.reservable = reservable;
        this.downloadable = downloadable;
    }
}

public class LibraryPlatform {
    static Resource[] resources = new Resource[100];
    static int size = 0;

    static void add(Resource r) {
        if (findIndex(r.id) != -1) {
            System.out.println("duplicate rejected");
            return;
        }

        int i = size - 1;

        while (i >= 0 && resources[i].id.compareTo(r.id) > 0) {
            resources[i + 1] = resources[i];
            i--;
        }

        resources[i + 1] = r;
        size++;
        System.out.println(r.id + " added");
    }

    static int findIndex(String id) {
        for (int i = 0; i < size; i++) {
            if (resources[i].id.equals(id)) {
                return i;
            }
        }
        return -1;
    }

    static void reserve(String id, String member) {
        int i = findIndex(id);

        if (i == -1) {
            System.out.println(id + " not found");
        } else if (!resources[i].reservable) {
            System.out.println(id + " rejected: reserve unsupported");
        } else {
            System.out.println(id + " reserved for " + member);
        }
    }

    static void download(String id) {
        int i = findIndex(id);

        if (i == -1) {
            System.out.println(id + " not found");
        } else if (!resources[i].downloadable) {
            System.out.println(id + " rejected: download unsupported");
        } else {
            System.out.println(id + " downloaded");
        }
    }

    static void find(String id) {
        int i = findIndex(id);

        if (i == -1)
            System.out.println(id + " not found");
        else
            System.out.println(id + " found at index " + i);
    }

    public static void main(String[] args) {
        add(new Resource("B1", "Book", true, false));
        add(new Resource("B1", "Book", true, false));
        add(new Resource("E1", "EBook", false, true));

        reserve("B1", "M1");
        download("B1");
        download("E1");
        find("E1");
    }
}