/**
 * Linked list related code.
 */
public class LinkedListUtil {
    /* Given two linked lists representing numbers; add them up and create a result list.
     * First list:- 9 => 2 => 4 repr 429
     * Second list:- 1 => 5 => 6 repr 651
      * Result => 0 => 8 => 0 => 1 representing 1080
    */
    private static class Node {
        private int data;
        private Node next;
        public Node(int data) {
            this.data = data;
        }
    }

    public void printList(Node head) {
        while(head != null) {
            System.out.print(head.data + " ");
            head = head.next;
        }
        System.out.println();
    }

    public Node addNumbersFromSLLists(Node list1, Node list2) {
        if(list1 == null || list2 == null) {
            return (list1 == null) ? list2 : list1;
        }

        int carry = 0;Node list3Head = null, list3Tail = null;
        while(list1 != null || list2 != null || carry != 0) {
            int sum = carry;
            if(list1 != null) {
                sum += list1.data;
                list1 = list1.next;
            }
            if(list2 != null) {
                sum += list2.data;
                list2 = list2.next;
            }
            Node list3Node = new Node(sum % 10);
            carry = sum / 10;
            if(list3Head == null) {
                list3Head = list3Tail = list3Node;
            } else {
                list3Tail.next = list3Node;
                list3Tail = list3Node;
            }
        }
        return list3Head;
    }


    // 1 => 2 => 3 => 4 => 5
    // 1 => 2 => 3 => 4
    public boolean isPalindrome(Node head) {
        if(head == null || head.next == null) {
            return true;
        }
        // Go to the middle of the list while reversing the first half of the list
        Node prev = null, slow = head, fast = head;
        while(fast != null && fast.next != null) {
            fast = fast.next.next;
            Node next = slow.next;
            slow.next = prev;
            prev = slow;
            slow = next;
        }

        // Find the starting point for left and right list to compare from
        // fast == null means even length; start right from the slow node.
        // else start right from node next to slow
        Node rightStart = (fast == null) ? slow : slow.next;
        Node leftStart = prev;

        // compare the lists to find out if the list is palindrome
        boolean isPali = true;
        while(leftStart != null) {
            if(leftStart.data != rightStart.data) {
                isPali = false;
                break;
            }
            leftStart = leftStart.next;
            rightStart = rightStart.next;
        }

        // restore the reversed half of the list
        Node temp = prev;
        prev = slow;
        slow = temp;
        while(slow != null) {
            Node next = slow.next;
            slow.next = prev;
            prev = slow;
            slow = next;
        }

        return isPali;
    }

    public static void main(String[] args) {
        Node n11 = new Node(9), n12 = new Node(2), n13 = new Node(4);
        Node n21 = new Node(1), n22 = new Node(5), n23 = new Node(6);
        n11.next = n12;n12.next = n13;
        n21.next = n22; n22.next = n23;
        LinkedListUtil linkedListUtil = new LinkedListUtil();
        linkedListUtil.printList(n11);
        linkedListUtil.printList(n21);
        linkedListUtil.printList(linkedListUtil.addNumbersFromSLLists(n11, n21));

        Node n1 = new Node(1), n2 = new Node(2), n3 = new Node(2), n4 = new Node(1), n5 = new Node(1);
        n1.next = n2;n2.next = n3;n3.next = n4;//n4.next = n5;
        linkedListUtil.printList(n1);
        System.out.println(linkedListUtil.isPalindrome(n1));
        linkedListUtil.printList(n1);
    }
}