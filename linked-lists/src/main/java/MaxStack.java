import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Stack having a O(1) max function.
 */
public class MaxStack {
    private static class StackNode {
        public StackNode(int value, int max) {
            this.max = max;
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            StackNode stackNode = (StackNode) o;

            if (value != stackNode.value) return false;
            return max == stackNode.max;

        }

        @Override
        public int hashCode() {
            int result = value;
            result = 31 * result + max;
            return result;
        }

        /*

        @Override
        public boolean equals(Object obj) {
            if(obj == this) {
                return true;
            }
            if(obj == null || obj.getClass() != getClass()) {
                return false;
            }

            StackNode other = (StackNode)obj;
            if(other.max != max {
                return false;
            }
            if(other.value != value) {
                return false;
            }
            return true;
        }
        */

        public int value;
        public int max;
    }

    Deque<StackNode> stackNodes;

    public MaxStack() {
        stackNodes = new ArrayDeque<StackNode>();
    }

    public int max() {
        if(stackNodes.isEmpty()) {
            throw new IllegalStateException("Can not call max on empty stack.");
        }

        StackNode stackNode = stackNodes.peek();
        return stackNode.max;
    }
    public void push(int i) {
        int maxSoFar = Integer.MIN_VALUE;
        if(!stackNodes.isEmpty()) {
            maxSoFar = stackNodes.peek().max;
        }
        StackNode newNode = new StackNode(i, Math.max(maxSoFar, i));
        stackNodes.push(newNode);
    }

    public int pop() {
        if(stackNodes.isEmpty()) {
            throw new IllegalStateException("Stack empty for popping.");
        }
        StackNode top = stackNodes.pop();
        return top.value;
    }

    public boolean isEmpty() {
        return stackNodes.isEmpty();
    }

    public int peek() {
        return stackNodes.peek().value;
    }

    public static void main(String [] args) {
        MaxStack maxStack = new MaxStack();
        maxStack.push(1);
        System.out.println("Max = " + maxStack.max() + " Top = " + maxStack.peek());
        maxStack.push(2);
        System.out.println("Max = " + maxStack.max() + " Top = " + maxStack.peek());
        maxStack.push(3);
        System.out.println("Max = " + maxStack.max() + " Top = " + maxStack.peek());
        maxStack.push(2);
        System.out.println("Max = " + maxStack.max() + " Top = " + maxStack.peek());
        System.out.println("Popped element = " + maxStack.pop());
        System.out.println("Max = " + maxStack.max() + " Top = " + maxStack.peek());

        String path = "/a/b/c";
        String [] directories = path.substring(1).split("/");
        for (String directory : directories) {
            System.out.println(directory);
        }
    }
}
