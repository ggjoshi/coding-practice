import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;


public class ArrayManipulationTest {

    @Test
    public void testRemoveElement() throws Exception {
        ArrayManipulation arrayManipulation = new ArrayManipulation();
        int array[] = new int[]{5, 3, 7, 11, 2, 3, 13, 5, 7};
        int element = 3;
        int output[] = new int[]{5, 7, 11, 2, 13, 5, 7, 5, 7};
        int result = arrayManipulation.removeElement(array, element);
        Assert.assertEquals(result, 7);
        Assert.assertTrue(Arrays.equals(array, output));

        array = new int[] {5, 5, 5, 5};
        output = new int[] {5, 5, 5, 5};
        result = arrayManipulation.removeElement(array, 5);
        Assert.assertEquals(result, 0);
        Assert.assertTrue(Arrays.equals(array, output));
    }

    @Test
    public void testRemoveDuplicatesFromUnsortedArray() throws Exception {
        ArrayManipulation arrayManipulation = new ArrayManipulation();
        int [] array = new int[] {5, 7, 9, 5, 3};
        int [] output = new int[] {5, 7, 9, 3, 3};
        int result = arrayManipulation.removeDuplicates(array);
        Assert.assertEquals(result, 4);
        Assert.assertTrue(Arrays.equals(array, output));
        array = new int[] {9, 9, 9, 9};
        output = new int[] {9, 9, 9, 9};
        result = arrayManipulation.removeDuplicates(array);
        Assert.assertEquals(result, 1);
        Assert.assertTrue(Arrays.equals(array, output));
    }

    @Test
    public void testUpdateArrayForFrequency() throws Exception {
        ArrayManipulation arrayManipulation = new ArrayManipulation();
        int [] array = new int[] {3, 5, 5, 7, 9};
        int [] output = new int[] {3, 5, 5, 7, 9};
        int result = arrayManipulation.updateArrayForFrequency(array, 3);
        Assert.assertEquals(result, 5);
        Assert.assertTrue(Arrays.equals(array, output));
        array = new int[] {9, 9, 9, 9};
        output = new int[] {9, 9, 9, 9};
        result = arrayManipulation.updateArrayForFrequency(array, 3);
        Assert.assertEquals(result, 2);
        Assert.assertTrue(Arrays.equals(array, output));
    }
}