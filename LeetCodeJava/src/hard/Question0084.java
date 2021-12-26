package hard;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 2021/12/23
 */
public class Question0084 {

    /**
     * Question 84 Largest Rectangle in Histogram
     * Given an array of integers heights representing the histogram's bar height
     * where the width of each bar is 1, return the area of the largest rectangle in the histogram.
     */

    /**
     * solution 1
     * Runtime: 10 ms, faster than 92.27% of Java online submissions for Largest Rectangle in Histogram.
     * Memory Usage: 50.9 MB, less than 45.48% of Java online submissions for Largest Rectangle in Histogram.
     * @param heights
     * @return
     */
    public int largestRectangleArea(int[] heights) {
        return largestRectangleArea(heights, 0, heights.length);
    }

    private int largestRectangleArea(int[] heights, int begin, int end) {
        if (begin == end) {
            return 0;
        }
        if (begin + 1 == end) {
            return heights[begin];
        }
        int max = heights[begin];
        int min = heights[begin];
        int minIndex = begin;
        for (int i = begin; i < end; i++) {
            int height = heights[i];
            if (max < height) {
                max = height;
            } else if (min > height) {
                min = height;
                minIndex = i;
            }
        }
        int max1 = Math.max(max, min * (end - begin));
        int left = 0;
        int right = 0;
        if ((minIndex - begin) * max > max1) {
            left = largestRectangleArea(heights, begin, minIndex);
        }
        if ((end - minIndex - 1) * max > max1) {
            right = largestRectangleArea(heights, minIndex + 1, end);
        }
        int max2 = Math.max(left, right);

        return Math.max(max1, max2);
    }

    public static void main(String[] args) {
        Question0084 q = new Question0084();
        int[] heights = {2,1,5,6,2,3};
        System.out.println(q.largestRectangleArea(heights));
    }
}
