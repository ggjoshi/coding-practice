import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BitsUtilTest {
    BitsUtil bitsUtil = new BitsUtil();

    @Test
    public void testSwapBits() throws Exception {
        Assert.assertEquals(bitsUtil.swapBits(73, (byte)1, (byte)6), 11);
    }
}