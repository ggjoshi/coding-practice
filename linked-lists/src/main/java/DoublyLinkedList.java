/**
 * doubly linked list implementation
 */
public class DoublyLinkedList {
    private static class Node {
        public Node(int data) {
            next = prev = null;
            this.data = data;
        }
        public Node next, prev;
        int data;
    }

    private Node head;

    public DoublyLinkedList() {
        head = null;
    }

    public void addNode(int data) {
        Node newNode = new Node(data);
        newNode.next = head;
        if(head != null) {
            head.prev = newNode;
        }
        head = newNode;
    }

    /*
     * 1 = 2 = 3
     */
    public void reverse() {
        Node current = head;
        while(current != null) {
            Node temp = current.next;
            current.next = current.prev;
            current.prev = temp;
            if(temp == null) {
                head = current;
            }
            current = temp;
        }
    }

    @Override
    public String toString() {
        if(head == null) {
            return "Empty list";
        }
        StringBuilder output = new StringBuilder();
        Node current = head;
        while(current != null) {
            output.append(current.data + " ");
            current = current.next;

        }
        return output.toString();
    }

    public static void main(String [] args) {
        DoublyLinkedList doublyLinkedList = new DoublyLinkedList();
        for (int i = 5; i >= 0; i--) {
            doublyLinkedList.addNode(i);
        }
        System.out.println(doublyLinkedList);
        doublyLinkedList.reverse();
        System.out.println(doublyLinkedList);
        doublyLinkedList.reverse();
        System.out.println(doublyLinkedList);
    }
}
