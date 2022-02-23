package google.medium;

import javax.swing.tree.FixedHeightLayoutCache;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 2022/2/22
 */
public class Question0011 {

    /**
     * 11. Container With Most Water
     *
     * You are given an integer array height of length n. There are n vertical lines drawn such that two endpoints
     * of the ith line are (i,0) and (i, height[i]).
     *
     * Find Two lines that together with the x-axis form a container, such that the contains the most water.
     *
     * Return the maximum amount of water a container can store.
     *
     * Notice that you may not slant the container.
     */


    /**
     * Solution 1
     * Brute Force
     * Time Limit Exceeded
     * @param height
     * @return
     */
    public int maxArea(int[] height) {
       int maxarea = 0;
        for (int i = 0; i < height.length; i++) {
            for (int j = i+1; j < height.length; j++) {
                maxarea = Math.max(maxarea, Math.min(height[i], height[j]) * (j - i));
            }
        }
        return maxarea;
    }

    /**
     * Solution 2
     * Two Pointer Approach
     */
    public int maxArea2(int[] height){
        int maxarea = 0;
        int l = 0;
        int r = height.length - 1;
        while (l < r) {
            maxarea = Math.max(maxarea, Math.min(height[l], height[r]) * (r - l));
            if (height[l] < height[r])
                l++;
            else
                r--;
        }
        return maxarea;
    }

}
