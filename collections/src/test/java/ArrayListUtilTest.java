import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class ArrayListUtilTest {

    @Test
    public void testMergeSort() throws Exception {
        ArrayList<Integer> arrayList = new ArrayList<Integer>(Arrays.asList(9, 7, 8, 5, 4, 6, 3, 2, 1));
        ArrayListUtil arrayListUtil = new ArrayListUtil();
        System.out.println(arrayList);
        arrayListUtil.mergeSort(arrayList);
        System.out.println(arrayList);
    }
}