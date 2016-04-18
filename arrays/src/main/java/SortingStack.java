import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 2 5 1 8 7 => 8 7 5 2 1
 */
class SortingStack {
    private Deque<Integer> stack;
    private int capacity;
    public SortingStack(int capacity) {
        this.stack = new ArrayDeque<Integer>(capacity);
        this.capacity = capacity;
    }

    public void push(Integer data) {
        stack.push(data);
    }

    public Integer pop(Integer data) {
        return stack.pop();
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }

    public Integer peek() {
        return stack.peek();
    }

    public void sort() {
        Deque<Integer> temp = new ArrayDeque<Integer>(capacity);
        // For each element in stack
        // pop the stack into temp stack
        // keep track of max element
        // at the end push max element into the first stack
        // pop the tempstack into stack;
        // again do the same till all elements are over.
        int stackSize = stack.size();
        for(int i = 0; i < stackSize; i++) {
            // transfer (stackSize - i) elements from stack to temp keeping track of max element.
            int maxFound = transferAndFindMax(stack, temp, stackSize - i);
            stack.push(maxFound);
            transferByIgnoringElement(stack, temp, maxFound);
        }
    }

    // stack = 5 4 2 1 tempStack = empty numElements = 3
    // tempStack 1 2 4 maxFound = 4
    private int transferAndFindMax(Deque<Integer> stack, Deque<Integer> tempStack, int numElements) {
        int maxFound = Integer.MIN_VALUE;
        for(int i = 0; i < numElements; i++) {
            int current = stack.pop();
            tempStack.push(current);
            if(maxFound < current) {
                maxFound = current;
            }
        }
        return maxFound;
    }

    // stack = 5 4 tempStack = 1 2 4 maxFound = 4
    // stack = 5 4 2 1
    private void transferByIgnoringElement(Deque<Integer> stack, Deque<Integer> tempStack, int maxFound) {
        boolean ignoredMaxFound = false;
        while(!tempStack.isEmpty()) {
            int current = tempStack.pop();
            if(current == maxFound && !ignoredMaxFound) {
                ignoredMaxFound = true;
                continue;
            }
            stack.push(current);
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(stack.size());
        Deque<Integer> tempStack = new ArrayDeque<Integer>();
        while(!stack.isEmpty()) {
            int current = stack.pop();
            sb.append(current + " ");
            tempStack.push(current);
        }
        while(!tempStack.isEmpty()) {
            stack.push(tempStack.pop());
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        SortingStack sortingStack = new SortingStack(10);
        sortingStack.push(1);sortingStack.push(10);sortingStack.push(24);
        sortingStack.push(11); sortingStack.push(15);sortingStack.push(6);
        sortingStack.push(8); sortingStack.push(4);
        System.out.println(sortingStack.toString());
        sortingStack.sort();
        System.out.println(sortingStack.toString());
    }
}