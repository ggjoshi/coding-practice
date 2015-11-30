import static org.junit.Assert.assertEquals;

/**
 *
 */
public class SearchUtilTest {

    @org.junit.Test
    public void testBinarySearch() throws Exception {
        assertEquals(SearchUtil.binarySearch(new int[]{-5, -1, 9, 11, 13}, 9), 2);
        assertEquals(SearchUtil.binarySearch(new int[]{0, 2, 3, 5}, 5), 3);
        assertEquals(SearchUtil.binarySearch(new int[]{1, 5, 9, 11}, 5), 1);
        assertEquals(SearchUtil.binarySearch(new int[]{-2, -1, 0}, 5), -1);
    }
}