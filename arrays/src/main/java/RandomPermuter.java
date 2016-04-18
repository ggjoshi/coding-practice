import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Queue;
import java.util.Random;
import java.util.Stack;

/**
 * Produces a random permutation of given array.
 */
public class RandomPermuter {
    private final Random random;
    public RandomPermuter() {
        random = new Random();
    }

    public void permut(int [] array) {
        if(array == null) {
            return;
        }

        for (int i = array.length; i > 0; i--) {
            swap(array, random.nextInt(i), i - 1);
        }
    }

    private void swap(int [] array, int a, int b) {
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }

    public static abstract class FooBar {
        public abstract void DoSomething();
    }
    public class Nested {
        public void foo() {
        }
    }
    public static void main(String [] args) {
        RandomPermuter randomPermuter = new RandomPermuter();
        Nested nested = randomPermuter.new Nested();
        int [] array = {1, 2, 3, 4, 5, 6, 7};
        System.out.println(Arrays.toString(array));
        int [] arrayClone = array.clone();
        System.out.println(Arrays.toString(arrayClone));
        arrayClone[0] = 100;
        System.out.println(Arrays.toString(arrayClone));
        System.out.println(Arrays.toString(array));
        randomPermuter.permut(array);
        System.out.println(Arrays.toString(array));
        List<Integer> integerList = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4));
        System.out.println(integerList);
        integerList.remove(new Integer(1));
        System.out.println(integerList);
        System.out.println(integerList.get(2));
        integerList.addAll(Arrays.asList(5, 6, 7));
        integerList.set(0, 99);
        System.out.println(integerList);
        ListIterator<Integer> listIterator = integerList.listIterator();
        while(listIterator.hasNext()) {
            System.out.println(listIterator.nextIndex() + ":" + listIterator.next());
        }
        Deque<Integer> stack = new LinkedList<Integer>();
        stack.push(98); stack.push(100);
        System.out.println(stack.peek());
        stack.pop();
        System.out.println(stack.peek());
        stack.clear();

        Deque<Integer> queue = stack;
        queue.offer(11);queue.offer(12);
        System.out.println(queue);
        System.out.println(queue.remove(1));

        Queue<Integer> myQueue = new LinkedList<Integer>();
        myQueue.offer(1);myQueue.offer(2);myQueue.offer(3);
        while(!myQueue.isEmpty()) {
            Integer integer = myQueue.poll();
            System.out.println(integer);
        }

        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile("/Users/gjoshi/testfile", "r");
            randomAccessFile.seek(4);
            System.out.println(randomAccessFile.readChar());
        } catch (Exception ex) {
            System.out.println(ex);
        }

    }
}
