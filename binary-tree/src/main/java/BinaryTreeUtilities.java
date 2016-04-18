import java.util.ArrayDeque;
import java.util.Deque;

/**
 *
 */
public class BinaryTreeUtilities {
    public static class BinaryNode {
        int data;
        BinaryNode left, right, parent;
    }

    public boolean isSymmetric(BinaryNode root) {
        if(root == null) {
            return true;
        }
        return isSymmetricHelper(root.left, root.right);
    }

    private boolean isSymmetricHelper(BinaryNode node1, BinaryNode node2) {
        if((node1 == null) && (node2 == null)) {
            return true;
        } else if(((node1 == null) && (node2 != null)) ||
            ((node1 != null) && (node2 == null))) {
            return false;
        }
        if(node1.data != node2.data) {
            return false;
        }
        return isSymmetricHelper(node1.left, node2.right) &&
            isSymmetricHelper(node1.right, node2.left);
    }

    /*
     * Recursive inorder traversal.
     */
    public void inorderTraversalRecursive(BinaryNode root) {
        if(root == null) {
            return;
        }
        inorderTraversalRecursive(root.left);
        System.out.print(root.data + " ");
        inorderTraversalRecursive(root.right);
    }

    /*
     *           3
     *     2           4
     *  1     2.5 3.5       5
     */
    public void inorderTraversalIterative(BinaryNode root) {
        if(root == null) {
            System.out.println("Tree is empty.");
            return;
        }
        // stack =>  5
        // traversal =>  1 2 2.5 3 3.5 4 5
        Deque<BinaryNode> stack = new ArrayDeque<BinaryNode>();
        stack.push(root);
        while(!stack.isEmpty()) {
            BinaryNode top = stack.peek();
            while(top.left != null) {
                stack.push(top.left);
                top = top.left;
            }
            while(!stack.isEmpty()) {
                top = stack.pop();
                System.out.println(top.data + " ");
                if(top.right != null) {
                    stack.push(top.right);
                    break;
                }
            }
        }
    }
}
