import java.util.ArrayList;

/**
 * Arraylist related problems.
 */
public class ArrayListUtil {
    public void mergeSort(ArrayList<Integer> arrayList) {
        mergeSortHelper(arrayList, 0, arrayList.size() - 1);
    }

    private void mergeSortHelper(ArrayList<Integer> arrayList, int start, int end) {
        if(start >= end) {
            return;
        }
        int mid = start + (end - start)/2;
        mergeSortHelper(arrayList, start, mid);
        mergeSortHelper(arrayList, mid + 1, end);
        mergeHelper(arrayList, start, mid, end);
    }

    private void mergeHelper(ArrayList<Integer> arrayList, int start, int mid, int end) {
        int lengthOfResult = end - start + 1;
        int [] tempList = new int[lengthOfResult];
        int index1 = start, index2 = mid + 1;
        for (int current = 0; current < lengthOfResult; current++) {
            if((index2 > end) || arrayList.get(index1) < arrayList.get(index2)) {
                tempList[current] = arrayList.get(index1);
                index1++;
            } else {
                tempList[current] = arrayList.get(index2);
                index2++;
            }
        }
        for ( int i = start; i <= end; i++) {
            arrayList.set(i, tempList[i - start]);
        }
    }
}
