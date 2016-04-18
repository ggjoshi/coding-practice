import java.util.Arrays;
import java.util.Comparator;

public class Answer {
    /*
     * Since there can be 7 direct reports; with x levels the total will be
     * 1 + 7 + 7 ^ 2 + 7 ^ x
     * answer(x) = (7 ^ (x + 1) - 1)/(7 - 1);
     */
    public static int answer(int x) {
        return ((int)Math.pow(7, (x + 1)) - 1)/(7 - 1);
    }

    /*
     * Compares name by their value computed by taking 'a' to 'z' as 1 to 26.
     * If they match; then reverse compare the strings lexigographically.
     */
    public static class LargeNamesComparator implements Comparator<String> {
        public int valueOfString(String s) {
            int value = 0;
            for(int i = 0; i < s.length(); i++) {
                char current = s.charAt(i);
                if(current < 'a' ||
                    current > 'z') {
                    throw new IllegalArgumentException("Non lowercase letter");
                }
                value += s.charAt(i) - 'a' + 1;
            }
            return value;
        }

        public int compare(String s1, String s2) {
            int value1 = valueOfString(s1), value2 = valueOfString(s2);
            if(value1 < value2) {
                return 1;
            } else if(value1 > value2) {
                return -1;
            } else {
                return s2.compareTo(s1);
            }
        }
    }

    public static String [] answer(String [] names) {
        Arrays.sort(names, new LargeNamesComparator());
        return names;
    }

    /*
     * Comparator to sort by earliest end value of interval.
     */
    public static class IntervalComparatorByEndValue implements Comparator<int []> {
        public int compare(int[] o1, int[] o2) {
            int secondValueDiff = o1[1] - o2[1];
            if(secondValueDiff > 0) {
                return 1;
            } else if(secondValueDiff < 0) {
                return -1;
            } else {
                int firstValueDiff = o1[0] - o2[0];
                if(firstValueDiff > 0) {
                    return 1;
                } else if(firstValueDiff < 0) {
                    return -1;
                } else {
                    return 0;
                }
            }
        }
    }
    public static int answer(int[][] meetings) {
        Arrays.sort(meetings, new IntervalComparatorByEndValue());
        int lastFinish = -1, count = 0;
        for(int i = 0; i < meetings.length; i++) {
            int currentStart = meetings[i][0];
            if(currentStart >= lastFinish) {
                count++;
                lastFinish = meetings[i][1];
            }
        }
        return count;
    }

    private static int getDigest(int prevM, int currentM) {
        return ((129 * currentM) ^ prevM) % 256;
    }

    /**
     * Approach is brute force; what we do is we reverse engineer
     * the digest function as m[i] = (m[i - 1] ^ (d[i] + (k) * 256))/129
     * but we need to find k; we iterate through it from 0 to 255
     * and find out which value gives us the right digest back.
     */
    public static int [] answer(int [] digest) {
        System.out.println(Arrays.toString(digest));
        int [] message = new int[digest.length];
        int lastMessage = 0;
        for(int i = 0; i < digest.length; i++) {
            for(int k = 0; k < 256; k++) {
                message[i] = (lastMessage ^ (digest[i] + (k) * 256)) / 129;
                if(getDigest(lastMessage, message[i]) == digest[i]) {
                    lastMessage = message[i];
                    break;
                }
            }
        }
        return message;
    }

    public static void main(String[] args) {
        System.out.println(answer(1));
        System.out.println(answer(10));

        String [] names = {"annie", "bonnie", "liz"};
        System.out.println(Arrays.toString(answer(names)));
        names = new String[]{"abcdefg", "vi"};
        System.out.println(Arrays.toString(answer(names)));

        int [][] intervals  = {{0, 100},
            {1, 3},
            {2, 100},
            {1, 3}};
        System.out.println(answer(intervals));

        int [] digest = {0, 129, 3, 129, 7, 129, 3, 129, 15, 129, 3, 129, 7, 129, 3, 129};
        System.out.println(Arrays.toString(answer(digest)));

        digest = new int[]{0, 129, 5, 141, 25, 137, 61, 149, 113, 145, 53, 157, 233, 185, 109, 165};
        System.out.println(Arrays.toString(answer(digest)));
    }
}