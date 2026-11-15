package JavaConcepts.assignment_probelms;
import java.util.Scanner;

public class DeliveryRouteLoopDetector {

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    static String detectLoop(Node head) {

        Node slow = head;
        Node fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {

                int length = 1;
                Node temp = slow.next;

                while (temp != slow) {
                    length++;
                    temp = temp.next;
                }

                slow = head;

                while (slow != fast) {
                    slow = slow.next;
                    fast = fast.next;
                }

                return "Loop: yes, starts at stop "
                        + slow.data + ", length " + length;
            }
        }

        return "Loop: no";
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        Node head = null;
        Node tail = null;

        Node[] nodes = new Node[n];

        for (int i = 0; i < n; i++) {
            int value = sc.nextInt();

            nodes[i] = new Node(value);

            if (head == null) {
                head = nodes[i];
                tail = nodes[i];
            } else {
                tail.next = nodes[i];
                tail = nodes[i];
            }
        }

        int pos = sc.nextInt();

        if (pos >= 0) {
            tail.next = nodes[pos];
        }

        System.out.println(detectLoop(head));
    }
}