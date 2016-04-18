import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Merges two sorted singly linked lists.
 */
public class LinkedListMerger {
    public static class Node {
        private int data;
        private Node next;

        public Node(int data) {
            this.data = data;
            this.next = null;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }
    }

    public static void printList(Node head) {
        if(head == null) {
            System.out.println("Empty list.");
            return;
        }
        while(head != null) {
            System.out.print(head.getData() + " ");
            head = head.getNext();
        }
        System.out.println();
    }

    /*
     * Merge two sorted singly linked lists:
     * 1 => 5 => 6
     * 0 => 2 => 9
     * output : 0 => 1 => 2 => 5 => 6 => 9
     */
    public static Node mergeSortedLists(Node head1, Node head2) {
        if(head1 == null) {
            return head2;
        } else if(head2 == null) {
            return head1;
        }
        Node resultHead;
        if(head1.getData() < head2.getData()) {
            resultHead = head1;
            head1 = head1.getNext();
        } else {
            resultHead = head2;
            head2 = head2.getNext();
        }
        Node resultCurrent = resultHead;
        while (head1 != null ||
                head2 != null) {
            if(head2 == null) {
                resultCurrent.setNext(head1);
                break;
            } else if(head1 == null) {
                resultCurrent.setNext(head2);
                break;
            } else {
                if(head1.getData() < head2.getData()) {
                    resultCurrent.setNext(head1);
                    resultCurrent = head1;
                    head1 = head1.getNext();
                } else {
                    resultCurrent.setNext(head2);
                    resultCurrent = head2;
                    head2 = head2.getNext();
                }
            }
        }
        return resultHead;
    }

    /*
     * 1 => 2 => 3
     */
    public Node simpleRecursiveReverse(Node head) {
        if(head == null ||
            head.next == null) {
            return head;
        }
        Node newHead = simpleRecursiveReverse(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    /*
     * 1 => 2 => 3
     */
    public Node simpleReverse(Node head) {
        Node prev = null, current = head;
        while(current != null) {
            Node next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        return prev;
    }
    /*
     * 1 => 2 => 3
     * 3 => 2 => 1
     */
    public static Node reverseList(Node head) {
        Node prev = null, current = head;
        while(current != null) {
            Node next = current.getNext();
            current.setNext(prev);
            prev = current;
            current = next;
        }
        return prev;
    }

    /*
     * 1 => 2 => 3
     * 3 => 2 => 1
     */
    public static Node reverseListRecursive(Node head) {
        if(head == null || head.getNext() == null) {
            return head;
        }

        Node newHead = reverseListRecursive(head.getNext());
        head.getNext().setNext(head);
        head.setNext(null);
        return newHead;
    }

    /*
     * given a linked list and 1 based start/end indices,
     * reverse the sublist between start and end inclusive
     * and keep the other list connected. do in a single pass.
     * 1 => 2 => 3 => 4 => 5
     * start = 2, end = 4
     * 1 => 4 => 3 => 2 => 5
     */
    public static Node reverseSubList(Node head, int start, int end) {
        if(start > end) {
            throw new IllegalArgumentException("start > end");
        }
        Node prev = null, current = head;
        int count = 0;
        while (current != null) {
            count++;
            if(count == start) {
                break;
            }
            prev = current;
            current = current.getNext();
        }

        // Start is out of range of the list;
        if(count < start) {
            throw new IllegalArgumentException("Linkedlist not big enough to reach start");
        }
        Node nodeBeforeStart = prev;
        while(current != null && start <= end) {
            Node next = current.getNext();
            current.setNext(prev);
            prev = current;
            current = next;
            start++;
        }
        if(start <= end) {
            throw new IllegalArgumentException("Linkedlist not big enough to reach end");
        }

        Node nodeAfterEnd = current;
        Node endNode = prev;

        nodeBeforeStart.getNext().setNext(nodeAfterEnd);
        nodeBeforeStart.setNext(endNode);
        return head;
    }

    private static int getLength(Node head) {
        int count = 0;
        while(head != null) {
            count++;
            head = head.getNext();
        }
        return count;
    }

    /*
     * 1 => 2 => 3 => 5
     * 2 => 3 => 5
     * 4 => 3 => 5
     */
    public static Node findIntersection(List<Node> linkedLists) {
        if(linkedLists == null || linkedLists.size() <= 1) {
            return null;
        }
        Node prevList = linkedLists.get(0);
        int lengthOfPrev = getLength(prevList);
        for(int i = 1; i < linkedLists.size(); i++) {
            Node currentList = linkedLists.get(i);
            int lengthOfCurrent = getLength(currentList);
            int lengthDiff = Math.abs(lengthOfCurrent - lengthOfPrev);
            Node larger = (lengthOfCurrent > lengthOfPrev) ? currentList : prevList;
            Node smaller = (lengthOfCurrent > lengthOfPrev) ? prevList : currentList;
            if(larger == prevList) {
                lengthOfPrev = lengthOfPrev - lengthDiff;
            }
            int count = 0;
            while (count < lengthDiff) {
                count++;
                larger = larger.getNext();
            }
            while((larger != null) &&
                larger != smaller) {
                larger = larger.getNext();
                smaller = smaller.getNext();
                lengthOfPrev--;
            }
            if(larger == null) {
                return null; // no intersection
            }
            prevList = larger;
        }
        return prevList;
    }

    /*
     * 0 => 1 => 2 => 3
     * 0 => 1 => 2
     */

    public static Node evenOddMerge(Node head) {
        if(head == null ||
            head.getNext() == null ||
            head.getNext().getNext() == null) {
            return head;
        }

        Node evenHead = head, oddHead = head.getNext();
        Node currentEven = evenHead, currentOdd = oddHead;
        while (true) {
            currentEven.setNext(currentOdd.getNext());
            currentOdd.setNext((currentOdd.getNext() == null) ? null : currentOdd.getNext().getNext());
            if(currentEven.getNext() == null) {
                break;
            }
            currentEven = currentEven.getNext();
            if(currentOdd.getNext() == null) {
                break;
            }
            currentOdd = currentOdd.getNext();
        }
        currentEven.setNext(oddHead);
        return evenHead;
    }

    public static void main(String [] args) {
        Node firstList = new Node(2);
        firstList.setNext(new Node(5));
        firstList.getNext().setNext(new Node(6));

        Node secondList = new Node(7);
        secondList.setNext(new Node(8));
        secondList.getNext().setNext(new Node(11));

        printList(firstList);
        printList(secondList);
        Node sortedList = mergeSortedLists(firstList, secondList);
        printList(sortedList);
        System.out.println("Even odd merge executing");
        printList(evenOddMerge(sortedList));

        Node reverseListIterative = reverseList(sortedList);
        printList(reverseListIterative);
        Node reverseListRecursive = reverseListRecursive(reverseListIterative);
        printList(reverseListRecursive);

        Node reversedSubListFrom2To4 = reverseSubList(reverseListRecursive, 2, 6);
        printList(reversedSubListFrom2To4);

        Node node1 = new Node(1); Node node2 = new Node(2);
        Node node3 = new Node(3); Node node4 = new Node(4);
        Node node5 = new Node(5); Node node6 = new Node(6);

        node1.setNext(node2);node2.setNext(node3);node3.setNext(node4);
        node5.setNext(node2);
        node6.setNext(node3);
        printList(node1);
        printList(node5);
        printList(node6);
        printList(findIntersection(Arrays.asList(node1, node5, node6)));
        System.out.println("Even odd merge executing");
        printList(evenOddMerge(node1));
    }
}

/*
 Stack with a max API.
 * 2 => 5 => 3 => 4
 */