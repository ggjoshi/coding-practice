import org.junit.Assert;
import java.util.Arrays;

public class ArrayUtil {
    public static class Pair<T> {
        private T value1, value2;
        public Pair(T value1, T value2) {
            this.value1 = value1;
            this.value2 = value2;
        }

        public T getValue1() {return value1;}
        public T getValue2() {return value2;}

        public boolean equals(Object other) {
            if(other == null || other.getClass() != getClass())
                return false;
            Pair<T> otherPair = (Pair<T>)other;
            return (value1 == otherPair.value1 && value2 == otherPair.value2) ||
                (value1 == otherPair.value2 && value2 == otherPair.value1);
        }

        @Override
        public String toString() {
            return "(" + value1 + "," + value2 + ")";
        }
    }

    public static Pair<Integer> findPairWithSum(int [] values, int k)  {
        if(values == null || values.length <= 1) {
            throw new IllegalArgumentException("Values are not valid.");
        }

        Arrays.sort(values);

        int low = 0, high = values.length - 1;
        while(low < high) {
            int sum = values[low] + values[high];
            if(sum == k) {
                return new Pair<Integer>(values[low], values[high]);
            } else if(sum < k) {
                low++;
            } else {
                high--;
            }
        }

        return null;
    }

    public static void main(String [] args) {
        int [] values = {5, 1, 7, 9};
        // 1 5 7 9
        System.out.println(findPairWithSum(values, 14));
        System.out.println(findPairWithSum(values, 17));
        System.out.println(findPairWithSum(values, 8));
        System.out.println(findPairWithSum(values, 6));
        Assert.assertEquals(findPairWithSum(values, 14), new Pair<Integer>(5, 9));
        Assert.assertEquals(findPairWithSum(values, 17), null);
    }
}