package JavaConcepts.assignment_probelms;
import java.util.HashMap;

public class RecentCache {

    static class Node {
        int key;
        String page;
        Node prev;
        Node next;

        Node(int key, String page) {
            this.key = key;
            this.page = page;
        }
    }

    HashMap<Integer, Node> map;
    Node head;
    Node tail;
    int capacity;
    String lastMessage;

    RecentCache(int capacity) {

        this.capacity = capacity;
        map = new HashMap<>();

        head = new Node(0, "");
        tail = new Node(0, "");

        head.next = tail;
        tail.prev = head;
    }

    void removeNode(Node node) {

        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    void addToFront(Node node) {

        node.next = head.next;
        node.prev = head;

        head.next.prev = node;
        head.next = node;
    }

    void moveToFront(Node node) {

        removeNode(node);
        addToFront(node);
    }

    String get(int key) {

        if (!map.containsKey(key)) {
            return null;
        }

        Node node = map.get(key);

        moveToFront(node);

        return node.page;
    }

    void put(int key, String page) {

        if (map.containsKey(key)) {

            Node node = map.get(key);
            node.page = page;

            moveToFront(node);

            lastMessage = "put(" + key + "," + page + ") updated";
            return;
        }

        if (map.size() == capacity) {

            Node lru = tail.prev;

            removeNode(lru);
            map.remove(lru.key);

            lastMessage = "put(" + key + "," + page
                    + ") evicted " + lru.key;
        } else {

            lastMessage = "put(" + key + "," + page + ")";
        }

        Node newNode = new Node(key, page);

        addToFront(newNode);
        map.put(key, newNode);
    }

    void display() {

        System.out.print("Cache (most recent first): ");

        Node temp = head.next;

        while (temp != tail) {

            System.out.print(temp.key + "=" + temp.page);

            temp = temp.next;

            if (temp != tail) {
                System.out.print(", ");
            }
        }

        System.out.println();
    }

    public static void main(String[] args) {

        RecentCache cache = new RecentCache(2);

        cache.put(1, "Home");
        System.out.println(cache.lastMessage);

        cache.put(2, "Notes");
        System.out.println(cache.lastMessage);

        String result = cache.get(1);
        System.out.println("get(1) -> " + result);

        cache.put(3, "Quiz");
        System.out.println(cache.lastMessage);

        result = cache.get(2);
        System.out.println("get(2) -> "
                + (result == null ? "-1" : result));

        cache.put(1, "Home2");
        System.out.println(cache.lastMessage);

        cache.put(4, "Mail");
        System.out.println(cache.lastMessage);

        result = cache.get(3);
        System.out.println("get(3) -> "
                + (result == null ? "-1" : result));

        result = cache.get(1);
        System.out.println("get(1) -> " + result);

        cache.display();
    }
}
