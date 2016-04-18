import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Merge multiple arrays into single array using priority queue.
 */
public class ArrayMerger {
    private class QueueNode implements Comparable<QueueNode> {
        public int [] sortedArray;
        public int arrayIndex;
        public QueueNode(int [] sortedArray, int arrayIndex) {
            this.sortedArray = sortedArray;
            this.arrayIndex = arrayIndex;
        }

        private int getData() {
            return arrayIndex >= sortedArray.length ? Integer.MAX_VALUE : sortedArray[arrayIndex];
        }
        public int compareTo(QueueNode other) {
            if(other == null) {
                return 1;
            }
            return getData() - other.getData();
        }
    }

    public int [] merge(List<int []> arraysToMerge) {
        PriorityQueue<QueueNode> priorityQueue =
            new PriorityQueue<QueueNode>(arraysToMerge.size());
        int totalElements = 0;
        for(int [] array : arraysToMerge) {
            priorityQueue.offer(new QueueNode(array, 0));
            totalElements += array.length;
        }
        int [] merged = new int[totalElements];
        for(int i = 0; i < merged.length; i++) {
            QueueNode queueNode = priorityQueue.poll();
            merged[i] = queueNode.getData();
            queueNode.arrayIndex += 1;
            priorityQueue.offer(queueNode);
        }
        return merged;
    }

    public static void main(String [] args) {
        List<int []> arraysToMerge = new ArrayList();
        arraysToMerge.add(new int[] {1, 2, 3});
        arraysToMerge.add(new int[]{});
        arraysToMerge.add(new int[]{7, 8, 9});
        ArrayMerger arrayMerger = new ArrayMerger();
        System.out.println(Arrays.toString(arrayMerger.merge(arraysToMerge)));
    }
}
