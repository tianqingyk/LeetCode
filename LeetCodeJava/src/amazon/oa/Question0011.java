package amazon.oa;

import java.util.Arrays;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 3/11/2022
 */
public class Question0011 {

    /**
     * 11. Storage Optimization Solution
     *
     * You are given a rectangular cake of size h x w and two arrays of
     * integers horizontalCuts and verticalCuts where:
     *  - horizontalCuts[i] is the distance from the top of the rectangular cake to the ith horizontal cut and similarly, and
     *  - verticalCuts[j] is the distance from the left of the rectangular cake to the jth vertical cut.
     *
     *  Return the maximum area of a piece of cake after you cut at each horizontal and vertical position provided in the
     *  arrays horizontalCuts and verticalCuts. Since the answer can be a large number, return this modulo 10^9 + 7.
     */

    public int maximumCake(int h, int w, int[] horizontalCuts, int[] verticalCuts) {
        int maxH = Integer.MIN_VALUE;
        int maxV = Integer.MIN_VALUE;

        Arrays.sort(horizontalCuts);
        Arrays.sort(verticalCuts);

        for (int i = 0; i <= horizontalCuts.length; i++) {
            if (i == 0) {
                maxH = Math.max(maxH, horizontalCuts[i]);
                continue;
            }
            if (i == horizontalCuts.length){
                maxH = Math.max(maxH, h - horizontalCuts[i-1]);
                continue;
            }
            maxH = Math.max(maxH, horizontalCuts[i] - horizontalCuts[i-1]);
        }

        for (int i = 0; i <= verticalCuts.length ; i++) {
            if (i == 0) {
                maxV = Math.max(maxV, verticalCuts[i]);
                continue;
            }
            if (i == verticalCuts.length) {
                maxV = Math.max(maxV, w - verticalCuts[i-1]);
                continue;
            }
            maxV = Math.max(maxV, verticalCuts[i] - verticalCuts[i-1]);
        }

        return maxH * maxV;
    }

    public static void main(String[] args) {
        Question0011 q = new Question0011();
        System.out.println(q.maximumCake(5, 4, new int[]{1,2,4}, new int[]{1,3}));
        System.out.println(q.maximumCake(5, 4, new int[]{3, 1}, new int[]{1}));
        System.out.println(q.maximumCake(5, 4, new int[]{3}, new int[]{3}));
    }
}
