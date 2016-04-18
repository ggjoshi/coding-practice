import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ArrayShiftingUtil {
    private void swap(int [] array, int pos1, int pos2) {
        int temp = array[pos1];
        array[pos1] = array[pos2];
        array[pos2] = temp;
    }
    /*
     * Remove duplicate elements in place and return the resulting length. 
     e.g. 1 => 
     1 4 1 2 1 4 => 1 4 2 X X X => length 3
     1 4 2 1 1 4
     e.g. 2 =>
     1 1 1 1 
    */
    public int removeDups(int array[]) {
        System.out.println(Arrays.toString(array));
        if(array == null || array.length <= 0) {
            return 0;
        }
        Set<Integer> dedupSet = new HashSet<Integer>();
        int p1 = 0, p2 = array.length - 1;
        while(p1 <= p2) {
            while(p1 <= p2 && !dedupSet.contains(array[p1])) {
                dedupSet.add(array[p1]);
                p1++;
            }

            while(p1 <= p2 && dedupSet.contains(array[p2])) {
                p2--;
            }

            if(p1 <= p2) {
                swap(array, p1, p2);
            }
        }
        return (p2 + 1);
    }

    public static void main(String[] args) {
        ArrayShiftingUtil arrayShiftingUtil = new ArrayShiftingUtil();
        System.out.println(arrayShiftingUtil.removeDups(new int[] {1, 1, 1,1}));
    }
}