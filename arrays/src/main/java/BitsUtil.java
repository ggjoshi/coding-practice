/*
 * Bunch of utility functions doing bit manipulation.
 */
public class BitsUtil {
    int swapBits(int givenInt, byte firstBit, byte secondBit) {
        boolean isFirstBitSet = (givenInt & (1 << firstBit)) != 0;
        boolean isSecondBitSet = (givenInt & (1 << secondBit)) != 0;
        givenInt = changeBit(givenInt, firstBit, isSecondBitSet);
        givenInt = changeBit(givenInt, secondBit, isFirstBitSet);
        return givenInt;
    }

    int changeBit(int givenInt, byte bitNumber, boolean shouldSet) {
        int mask = 1 << bitNumber;
        if(shouldSet) {
            givenInt |= mask;
        } else {
            givenInt &= ~mask;
        }
        return givenInt;
    }
}
