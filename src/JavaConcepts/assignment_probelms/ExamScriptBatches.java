package JavaConcepts.assignment_probelms;
import java.util.Scanner;

public class ExamScriptBatches {

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    static Node reverseInGroups(Node head, int k) {

        Node dummy = new Node(0);
        dummy.next = head;

        Node groupPrev = dummy;

        while (true) {

            Node kth = groupPrev;

            for (int i = 0; i < k && kth != null; i++) {
                kth = kth.next;
            }

            if (kth == null) {
                break;
            }

            Node groupNext = kth.next;

            Node prev = groupNext;
            Node current = groupPrev.next;

            while (current != groupNext) {

                Node nextNode = current.next;

                current.next = prev;
                prev = current;
                current = nextNode;
            }

            Node oldGroupHead = groupPrev.next;

            groupPrev.next = kth;

            groupPrev = oldGroupHead;
        }

        return dummy.next;
    }

    static void display(Node head) {

        Node temp = head;

        while (temp != null) {
            System.out.print(temp.data + " -> ");
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

            Node newNode = new Node(sc.nextInt());

            if (head == null) {
                head = newNode;
                tail = newNode;
            } else {
                tail.next = newNode;
                tail = newNode;
            }
        }

        int k = sc.nextInt();

        head = reverseInGroups(head, k);

        display(head);
    }
}