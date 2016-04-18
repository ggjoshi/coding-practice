import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

/*
 * Util functions for sorted arrays.
 */
public class SortedArraysUtil {
    private static class SetNode implements Comparable<SetNode> {
        public SetNode(List<List<Integer>> listOfLists,
                       int indexInListOfLists,
                       int indexWithinSingleList) {
            this.indexInListOfLists = indexInListOfLists;
            this.indexWithinSingleList = indexWithinSingleList;
            this.listOfLists = listOfLists;
        }

        public boolean atEndOfList() {
            return indexWithinSingleList == listOfLists.get(indexInListOfLists).size();
        }

        public void nextElement() {
            indexWithinSingleList++;
        }

        public int getValue() {
            return listOfLists.get(indexInListOfLists).get(indexWithinSingleList);
        }

        public int compareTo(SetNode other) {
            if(other == null) {
                return 1;
            }
            return (getValue() != other.getValue() ?
                (getValue() - other.getValue()) :
                (indexInListOfLists - other.indexInListOfLists));
        }

        public int indexInListOfLists;
        public int indexWithinSingleList;
        public List<List<Integer>> listOfLists;
    }

    /**
     * Find closest k numbers from k sorted arrays.
     */
    public static List<Integer> findClosestElements(List<List<Integer>> kLists) {
        if(kLists == null) {
            throw new IllegalArgumentException("invalid param");
        }
        int k = kLists.size();
        TreeSet<SetNode> setNodeTreeSet = new TreeSet<SetNode>();
        for(int i = 0; i < k; i++) {
            setNodeTreeSet.add(new SetNode(kLists, i, 0));
        }

        int minDifference = Integer.MAX_VALUE;
        List<Integer> closestValues = new ArrayList<Integer>();
        while(true) {
            SetNode smallestNode = setNodeTreeSet.first();
            SetNode largestNode = setNodeTreeSet.last();
            if(largestNode.getValue() - smallestNode.getValue() < minDifference) {
                minDifference = largestNode.getValue() - smallestNode.getValue();
                List<SetNode> closestNodes =
                    Arrays.asList(setNodeTreeSet.toArray(new SetNode[setNodeTreeSet.size()]));
                closestValues.clear();
                for(SetNode setNode : closestNodes) {
                    closestValues.add(setNode.getValue());
                }
            }
            setNodeTreeSet.remove(smallestNode);
            smallestNode.nextElement();
            if(!smallestNode.atEndOfList()) {
                setNodeTreeSet.add(smallestNode);
            } else {
                break;
            }
        }
        return closestValues;
    }

    public static void main(String [] args) {
        List<List<Integer>> listOfLists = new ArrayList<List<Integer>>();
        listOfLists.add(Arrays.asList(new Integer[] {5, 10, 15}));
        listOfLists.add(Arrays.asList(new Integer[] {3, 6, 9, 10, 12, 15}));
        listOfLists.add(Arrays.asList(new Integer[] {8, 9, 16, 24}));

        System.out.println(findClosestElements(listOfLists));
    }
}
