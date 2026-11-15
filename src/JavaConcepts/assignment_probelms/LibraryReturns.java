package JavaConcepts.assignment_probelms;
import java.util.Scanner;

public class LibraryReturns {

    static class Node {
        String title;
        int dueDay;
        Node next;

        Node(String title, int dueDay) {
            this.title = title;
            this.dueDay = dueDay;
            this.next = null;
        }
    }

    static Node getMiddle(Node head) {

        Node slow = head;
        Node fast = head;
        Node prev = null;

        while (fast != null && fast.next != null) {

            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        prev.next = null;

        return slow;
    }

    static Node merge(Node left, Node right) {

        Node dummy = new Node("", 0);
        Node tail = dummy;

        while (left != null && right != null) {

            if (left.dueDay <= right.dueDay) {

                tail.next = left;
                left = left.next;
            } else {

                tail.next = right;
                right = right.next;
            }

            tail = tail.next;
        }

        if (left != null) {
            tail.next = left;
        }

        if (right != null) {
            tail.next = right;
        }

        return dummy.next;
    }

    static Node sortByDueDay(Node head) {

        if (head == null || head.next == null) {
            return head;
        }

        Node middle = getMiddle(head);

        Node left = sortByDueDay(head);
        Node right = sortByDueDay(middle);

        return merge(left, right);
    }

    static void display(Node head) {

        Node temp = head;

        while (temp != null) {

            System.out.print(
                    temp.title + ":" + temp.dueDay + " -> "
            );

            temp = temp.next;
        }

        System.out.println("null");
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        Node head = null;
        Node tail = null;

        for (int i = 0; i < n; i++) {

            String title = sc.next();
            int day = sc.nextInt();

            Node newNode = new Node(title, day);

            if (head == null) {
                head = newNode;
                tail = newNode;
            } else {
                tail.next = newNode;
                tail = newNode;
            }
        }

        head = sortByDueDay(head);

        display(head);
    }
}