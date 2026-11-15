package JavaConcepts.assignment_probelms;
import java.util.Scanner;

public class TokenMirrorCheck {

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    static Node reverse(Node head) {

        Node prev = null;
        Node current = head;

        while (current != null) {

            Node nextNode = current.next;

            current.next = prev;

            prev = current;
            current = nextNode;
        }

        return prev;
    }

    static boolean isPalindrome(Node head) {

        if (head == null || head.next == null) {
            return true;
        }

        Node slow = head;
        Node fast = head;

        while (fast.next != null && fast.next.next != null) {

            slow = slow.next;
            fast = fast.next.next;
        }

        Node secondHalf = reverse(slow.next);

        Node first = head;
        Node second = secondHalf;

        boolean result = true;

        while (second != null) {

            if (first.data != second.data) {
                result = false;
                break;
            }

            first = first.next;
            second = second.next;
        }

        // Restore the original linked list
        slow.next = reverse(secondHalf);

        return result;
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

        boolean result = isPalindrome(head);

        System.out.println("Palindrome: " + result);

        System.out.println("List after check:");

        display(head);
    }
}