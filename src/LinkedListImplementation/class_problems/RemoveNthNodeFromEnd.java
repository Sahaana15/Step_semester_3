package LinkedListImplementation.class_problems;
class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
    }
}

public class RemoveNthNodeFromEnd {

    static Node removeNthFromEnd(Node head, int n) {
        Node dummy = new Node(0);
        dummy.next = head;

        Node fast = dummy;
        Node slow = dummy;

        for (int i = 0; i <= n; i++) {
            fast = fast.next;
        }

        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }

        slow.next = slow.next.next;

        return dummy.next;
    }

    static void display(Node head) {
        while (head != null) {
            System.out.print(head.data);
            if (head.next != null)
                System.out.print(" -> ");
            head = head.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);

        head = removeNthFromEnd(head, 2);
        display(head);

        Node head2 = new Node(1);
        head2 = removeNthFromEnd(head2, 1);
        display(head2);
    }
}
