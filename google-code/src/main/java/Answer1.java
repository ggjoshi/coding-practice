import java.math.BigInteger;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Answer1 {
    /**
     * The problem is conceptually similar to finding the length of loop in a linked list.
     * What you do is find out the starting point of the loop using hare and tortoise algorithm.
     * Then you iterate the loop till you get back to starting point to find the length.
     */
    public static int answerLengthOfPirateLoop(int[] numbers) {
        int slow = 0, fast = 0;
        do {
            slow = numbers[slow];
            fast = numbers[numbers[fast]];
        } while(slow != fast);
        // here slow will be equal to fast;
        // start slow from first node again and
        // walk one at a time till you meet fast
        slow = 0;
        while(slow != fast) {
            slow = numbers[slow];
            fast = numbers[fast];
        }

        // At this point we are at the start of the loop.
        // walk the loop till we get back to start; counting each time;
        int lengthOfLoop = 0;
        int start = slow;
        do {
            lengthOfLoop++;
            slow = numbers[slow];
        } while(start != slow);
        return lengthOfLoop;
    }

    public static int answerWaterAccumulation(int[] heights) {
        Deque<Integer> stack1 = new ArrayDeque<Integer>(heights.length),
            stack2 = new ArrayDeque<Integer>(heights.length);
        int waterDropletCount = 0;
        stack1.push(heights[0]);
        int stackMax = heights[0];
        for(int i = 1; i < heights.length; i++) {
            int current = heights[i];
            // If current element is less or equal;
            // there is a possibility of water accumulation going further
            // so push it into the stack
            // in the else case; accumulate some water on the previous elements
            if(current > stack1.peek()) {
                // For greater case;
                // if stack just has one element; pop it and make this as max
                if(stack1.size() == 1) {
                    stack1.pop();
                    stackMax = current;
                } else {
                    // now we can fill water till min(current, stackMax)
                    // for that temporarily pop elements from stack1,
                    // push them to stack2 with water droplets on them
                    int fillTill = Math.min(current, stackMax);
                    while(!stack1.isEmpty()) {
                        int dropletCountToFill = (fillTill >= stack1.peek()) ? (fillTill - stack1.peek()) : 0;
                        stack2.push(stack1.pop() + dropletCountToFill);
                        waterDropletCount += dropletCountToFill;
                    }

                    // now depending on whether the current element is >= stackMax or not;
                    // decide whether to keep the stack2 elements or discard them
                    if(current >= stackMax) {
                        stack2.clear();
                        stackMax = current;
                    } else {
                        while(!stack2.isEmpty()) {
                            stack1.push(stack2.pop());
                        }
                    }
                }

            }
            stack1.push(current);
        }
        return waterDropletCount;
    }

    private static Map<Integer, BigInteger> factorialMap = new HashMap<Integer, BigInteger>();

    /*
     * Computes factorial and also remembers it
     * to avoid future recomputation if needed.
     */
    private static BigInteger factorial(int n) {
        if(n <= 1) {
            return BigInteger.ONE;
        } else if(factorialMap.containsKey(n)) {
            return factorialMap.get(n);
        }

        BigInteger result = BigInteger.ONE;
        for(int i = 2; i <= n; i++) {
            result = result.multiply(BigInteger.valueOf(i));
        }

        factorialMap.put(n, result);

        return result;
    }


    /*
     * Combination nCk i.e. k out of n.
     */
    private static BigInteger combination(int n, int k) {
        return factorial(n).divide((factorial(n - k).multiply(factorial(k))));
    }

    /*
     * This is a problem of finding out number of ways of
     * building identical binary trees.
     * The order of given sequence is important to keep so that the same binary tree could be built.
     * Specifically all elements greater than root should appear in same order
     * and all elements less than root can appear in same order.
     * This means that out of total children elements say C, we need to choose L positions for left children.
     * the R right children can take the remaining positions.
     * For each such left and right half; similar combinations could be done.
     * Thus this recursively computes the result.
     */
    private static BigInteger findNumOfWaysToFormIdenticalTree(List<Integer> seq) {
        if(seq.size() <= 1) {
            return BigInteger.ONE;
        }
        List<Integer> lessElements = new ArrayList<Integer>(), greaterElements = new ArrayList<Integer>();
        for(int i = 1; i < seq.size(); i++) {
            if(seq.get(i) < seq.get(0)) {
                lessElements.add(seq.get(i));
            } else {
                greaterElements.add(seq.get(i));
            }
        }

        return combination(lessElements.size() + greaterElements.size(), lessElements.size())
            .multiply(findNumOfWaysToFormIdenticalTree(lessElements))
            .multiply(findNumOfWaysToFormIdenticalTree(greaterElements));
    }

    /*
     * Calls a helper method to compute the number of ways to form identical tree.
     */
    public static String answer(int[] seq) {
        List<Integer> elements = new ArrayList<Integer>(seq.length);
        for (int i : seq) {
            elements.add(i);
        }
        return findNumOfWaysToFormIdenticalTree(elements).toString();
    }

    public static void main(String[] args) {
        int [] heights = {1, 4, 2, 5, 1, 2, 3};
        System.out.println(answerWaterAccumulation(heights));
        heights = new int [] {1, 2, 3, 2, 1};
        System.out.println(answerWaterAccumulation(heights));

        int [] pirates = {1, 0};
        System.out.println(answerLengthOfPirateLoop(pirates));
        pirates = new int[]{1, 2, 1};
        System.out.println(answerLengthOfPirateLoop(pirates));
        pirates = new int[]{1, 2, 3, 4, 4};
        System.out.println(answerLengthOfPirateLoop(pirates));

        int [] elementsInBinaryTree = {5, 9, 8, 2, 1};
        System.out.println(answer(elementsInBinaryTree));
        elementsInBinaryTree = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        System.out.println(answer(elementsInBinaryTree));
        elementsInBinaryTree = new int[]{5, 9, 8, 7, 2, 1};
        System.out.println(answer(elementsInBinaryTree));
        elementsInBinaryTree = new int[]{5, 1, 3, 7, 9, 2, 4, 6, 10, 8};
        System.out.println(answer(elementsInBinaryTree));
    }
}
