package leetcode.fmedium;

import java.util.LinkedList;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 2022/2/26
 */
public class Question0057 {

    /**
     * 57. Insert Interval
     * You are given an array of non-overlapping intervals intervals where intervals[i] = [starti, endi] represent the start and the end of the ith interval and intervals is sorted in ascending order by starti. You are also given an interval newInterval = [start, end] that represents the start and end of another interval.
     * <p>
     * Insert newInterval into intervals such that intervals is still sorted in ascending order by starti and intervals still does not have any overlapping intervals (merge overlapping intervals if necessary).
     * <p>
     * Return intervals after the insertion.
     */

    public int[][] insert(int[][] intervals, int[] newInterval) {

        int[] tmp = newInterval;
        LinkedList<int[]> result = new LinkedList<>();

        for (int i = 0; i < intervals.length; i++) {
            int[] interval = intervals[i];
            if (tmp[0] > interval[1]) {
                result.add(interval);
                continue;
            }
            if (tmp[1] < interval[0]) {
                result.add(tmp);
                tmp = interval;
                continue;
            }

            tmp = new int[]{
                    Math.min(tmp[0], interval[0]),
                    Math.max(tmp[1], interval[1])
            };
        }
        result.add(tmp);

        return result.toArray(new int[result.size()][]);
    }
}
