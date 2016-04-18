import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Interval util.
 */
public class IntervalUtil {
    private static class Interval implements Comparable<Interval> {
        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public int compareTo(Interval other) {
            if(other == null) {
                return 1;
            }
            return (start != other.start ? start - other.start : end - other.end);
        }

        public boolean overlaps(Interval other) {
            if(other == null) {
                return false;
            }
            return !(end < other.start || other.end < start);
        }

        @Override
        public String toString() {
            return "[" + start + "," + end + "]";
        }

        public int start, end;
    }

    /*
     * [0, 1], [2, 3], [6, 8], [11, 14]
     * add [2-9]
     * result should be [0, 1], [2,9], [11, 14]
     */
    public static List<Interval> unionInterval(List<Interval> intervals, Interval newInterval) {
        if(intervals == null || newInterval == null) {
            throw new IllegalArgumentException("invalid input");
        }

        // sort intervals
        Collections.sort(intervals);

        List<Interval> result = new ArrayList<Interval>(intervals.size());
        Interval combinedInterval = newInterval;
        boolean overlapPhase = false;
        // iterate and keep track of low and high of combined intervals when reached.
        for (Interval interval : intervals) {
            if(!combinedInterval.overlaps(interval)) {
                if(overlapPhase) {
                    overlapPhase = false;
                    result.add(combinedInterval);
                }
                result.add(interval);
            } else {
                overlapPhase = true;
                combinedInterval = new Interval(
                    Math.min(combinedInterval.start, interval.start),
                    Math.max(combinedInterval.end, interval.end));
            }
        }
        return result;
    }

    public static void main(String [] args) {
        List<Interval> intervals = Arrays.asList(new Interval[] {new Interval(0, 1),
            new Interval(2, 3),
            new Interval(6, 8),
            new Interval(7, 10),
            new Interval(11, 14)});
        System.out.println(intervals);
        System.out.println(unionInterval(intervals, new Interval(3, 6)));
    }
}
