import static org.junit.Assert.*;

/**
 *
 */
public class SearchUtilTest {

    @org.junit.Test
    public void testBinarySearch() throws Exception {
        assertEquals(SearchUtil.binarySearch(new int[]{1, 5, 9, 11}, 5), 1);
        assertEquals(SearchUtil.binarySearch(new int[]{1, 2}, 5), -1);
    }
}