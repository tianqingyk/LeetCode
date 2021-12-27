package hard;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

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
        int max1 = min * (end - begin);
        int left = 0;
        int right = 0;
        if ((minIndex - begin) * max > max1) {
            left = largestRectangleArea(heights, begin, minIndex);
        }
        if ((end - minIndex - 1) * max > max1) {
            right = largestRectangleArea(heights, minIndex + 1, end);
        }

        return Math.max(max1, Math.max(left, right));
    }

    /**
     * solution 2 use stack
     * copy from Solution
     * @param heights
     * @return
     */
    public int largestRectangleArea2(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        int length = heights.length;
        int maxArea = 0;
        for (int i = 0; i < length; i++) {
            while ((stack.peek() != -1)
                    && (heights[stack.peek()] >= heights[i])) {
                int currentHeight = heights[stack.pop()];
                int currentWidth = i - stack.peek() - 1;
                maxArea = Math.max(maxArea, currentHeight * currentWidth);
            }
            stack.push(i);
        }
        while (stack.peek() != -1) {
            int currentHeight = heights[stack.pop()];
            int currentWidth = length - stack.peek() - 1;
            maxArea = Math.max(maxArea, currentHeight * currentWidth);
        }
        return maxArea;
    }

    public static void main(String[] args) {
        Question0084 q = new Question0084();
        int[] heights = {2,1,5,6,2,3};
        System.out.println(q.largestRectangleArea(heights));
    }
}
