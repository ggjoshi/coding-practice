import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

/**
 * BST utils
 */
public class BST {
    private static class BSTNode {
        public int data;
        public BSTNode left, right, parent;
        public BSTNode(int data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return "Data = " + data;
        }
    }

    /*
     *      12
     *  12       14
     */
    public static BSTNode findFirstKeyLargerThanK(BSTNode root, int k) {
        BSTNode current = root;
        BSTNode firstValueFound = null;
        while(current != null) {
            if(current.data > k) {
                firstValueFound = current;
                current = current.left;
            } else {
                current = current.right;
            }
        }
        return firstValueFound;
    }

    public static List<Integer> getKLargestElements(BSTNode root, int k) {
        List<Integer> result = new ArrayList<Integer>(k);
        getKLargestElementsHelper(root, k, result);
        return result;
    }

    private static void getKLargestElementsHelper(BSTNode root, int k, List<Integer> result) {
        if(root == null) {
            return;
        }
        if(result.size() >= k) {
            return;
        }
        getKLargestElementsHelper(root.right, k, result);

        if(result.size() >= k) {
            return;
        }
        result.add(root.data);

        if(result.size() >= k) {
            return;
        }
        getKLargestElementsHelper(root.left, k, result);
    }

    public static BSTNode findLCA(BSTNode root, int value1, int value2) {
        BSTNode current = root;
        while(current != null) {
            if(value1 < current.data && value2 < current.data) {
                current = current.left;
            } else if(value1 > current.data && value2 > current.data) {
                current = current.right;
            } else {
                return current;
            }
        }
        return null;
    }

    /*
     *           3
     *     2           4
     *  1     2.5 3.5       5
     */
    public static void inorderTraversalIterative(BSTNode root) {
        if(root == null) {
            System.out.println("Tree is empty.");
            return;
        }
        System.out.println("Inorder traversal");
        // stack =>  5
        // traversal =>  1 2 2.5 3 3.5 4 5
        Deque<BSTNode> stack = new ArrayDeque<BSTNode>();
        stack.push(root);
        while(!stack.isEmpty()) {
            BSTNode top = stack.peek();
            while(top.left != null) {
                stack.push(top.left);
                top = top.left;
            }
            while(!stack.isEmpty()) {
                top = stack.pop();
                System.out.print(top.data + " ");
                if(top.right != null) {
                    stack.push(top.right);
                    break;
                }
            }
        }
        System.out.println();
    }

    public static BSTNode inOrderSuccessor(BSTNode givenNode) {
        if(givenNode == null) {
            return null;
        }
        BSTNode current = null;
        // Find min node in right subtree if it exists
        if(givenNode.right != null) {
            current = givenNode.right;
            while(current.left != null) {
                current = current.left;
            }
            return current;
        } else {
            BSTNode parent = givenNode.parent;
            current = givenNode;
            while(parent != null) {
                if(parent.left == current) {
                    break;
                }
                current = parent;
                parent = parent.parent;
            }
            return parent;
        }
    }

    public static class TraversedNode implements Comparable<TraversedNode> {
        private int depth, width;
        private BSTNode bNode;

        public TraversedNode(int depth, int width, BSTNode bNode) {
            this.depth = depth;
            this.width = width;
            this.bNode = bNode;
        }

        public int compareTo(TraversedNode other) {
            int diff = width - other.width;
            if(diff != 0) {
                return diff;
            }
            diff = depth - other.depth;
            if(diff != 0) {
                return diff;
            }
            if(bNode == other.bNode) return 0;
            else return -1;
        }

        public String toString() {
            return bNode == null ? "null" : Integer.toString(bNode.data);
        }
    }

    public static void printColumnWise(BSTNode root) {
        if(root == null) {
            System.out.println("Empty tree");
            return;
        }

        List<TraversedNode> traversedList = new ArrayList<TraversedNode>();
        preOrder(root, traversedList, 0, 0);
        Collections.sort(traversedList);
        System.out.println(traversedList);
    }

    public static void preOrder(BSTNode root, List<TraversedNode> traversedList, int width, int depth) {
        if(root == null) return;
        traversedList.add(new TraversedNode(depth, width, root));
        preOrder(root.left, traversedList, width - 1, depth + 1);
        preOrder(root.right, traversedList, width + 1, depth + 1);
    }

    public static void main(String [] args) {
        BSTNode node1 = new BSTNode(12);
        BSTNode node2 = new BSTNode(14);
        BSTNode node3 = new BSTNode(10);
        BSTNode node4 = new BSTNode(13);
        BSTNode node5 = new BSTNode(8);
        BSTNode node6 = new BSTNode(11);
        node1.left = node3; node3.parent = node1;
        node1.right = node2; node2.parent = node1;
        node2.left = node4; node4.parent = node2;
        node3.left = node5; node5.parent = node3;
        node3.right = node6; node6.parent = node3;
        int k = 12;
        System.out.println(" key > " + k + " = " + findFirstKeyLargerThanK(node1, k));
        int kLargest = 3;
        System.out.println(getKLargestElements(node1, kLargest));
        System.out.println(findLCA(node1, 15, 16));
        inorderTraversalIterative(node1);
        System.out.println(inOrderSuccessor(node3));
        System.out.println(inOrderSuccessor(node2));

        printColumnWise(node1);
    }
}
