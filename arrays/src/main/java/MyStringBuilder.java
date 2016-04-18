import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Locale;

/**
 * Attempt to implement small subset of functionality in StringBuilder.
 */
public class MyStringBuilder {
    private char [] chars;
    private int length;
    private int capacity;

    public MyStringBuilder(int capacity) {
        if(capacity <= 0) {
            throw new IllegalArgumentException("Capacity should be > 0");
        }
        chars = new char[capacity];
        length = 0;
        this.capacity = capacity;
    }

    private void resizeIfNeeded(int lengthOfAppendedValue) {
        if(lengthOfAppendedValue + length > capacity) {
            long newCapacity = 2 << ((int)Math.log((double)lengthOfAppendedValue + (double)length) + 1);
            if(newCapacity > Integer.MAX_VALUE) {
                throw new IllegalStateException("Can not increase capacity beyond this point.");
            }

            capacity = (int)newCapacity;
            chars = Arrays.copyOf(chars, capacity);
        }
    }

    public MyStringBuilder append(String string) {
        if(string == null || string.length() == 0) {
            return this;
        }
        resizeIfNeeded(string.length());
        for(int i = 0; i < string.length(); i++) {
            chars[i + length] = string.charAt(i);
        }
        length = length + string.length();
        return this;
    }

    @Override
    public String toString() {
        return new String(chars);
    }

    public static void main(String[] args) throws Exception{
        MyStringBuilder myStringBuilder = new MyStringBuilder(2);
        myStringBuilder.append("fo");
        System.out.println(myStringBuilder.toString());
        myStringBuilder.append("anotherstr");
        System.out.println(myStringBuilder.toString());
        String mydate = "2016-02-14 02:49:090123456";
        System.out.println(Arrays.toString(mydate.split("\\.")));
    }
}
