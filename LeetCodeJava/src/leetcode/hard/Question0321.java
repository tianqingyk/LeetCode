package leetcode.hard;

import java.util.*;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 2022/2/12
 */
public class Question0321 {

    /**
     * 321. Create Maximum Number
     * <p>
     * You are given two integer arrays nums1 and nums2 of lengths m and n respectively. nums1 and nums2 represent the
     * digits of two numbers. You are also given an integer k.
     * <p>
     * Create the maximum number of length k <= m + n from digits of the two numbers. The relative order of the digits
     * from the same array must be preserved
     * <p>
     * Return an array of the k digits representing the answer.
     */


    /**
     * Solution 1
     * Time limit exceeded
     */
    int m, n, k;
    int[] nums1, nums2;
    int[] maxResult;
    Map<String, int[]> cache1 = new HashMap<>();
    Map<String, int[]> cache2 = new HashMap<>();

    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        m = nums1.length;
        n = nums2.length;
        this.nums1 = nums1;
        this.nums2 = nums2;
        this.k = k;
        maxResult = new int[k];
        maxNumberHelper(0, 0, 0, new int[k]);
        return maxResult;
    }

    public void maxNumberHelper(int start1, int start2, int index, int[] result) {
        if (index >= k) {
            for (int i = 0; i < k; i++) {
                if (maxResult[i] > result[i]) {
                    break;
                }
                if (maxResult[i] < result[i]) {
                    maxResult = Arrays.copyOf(result, k);
                    break;
                }
            }
            return;
        }

        int needNum = k - index - 1;
        int end1 = needNum - (n - start2);
        if (end1 < 0) end1 = 0;
        int end2 = needNum - (m - start1);
        if (end2 < 0) end2 = 0;

        int[] v1 = getMaxByRange(nums1, start1, m - end1, cache1);
        int max1 = v1[0];
        int index1 = v1[1];

        int[] v2 = getMaxByRange(nums2, start2, n - end2, cache2);
        int max2 = v2[0];
        int index2 = v2[1];

        if (max1 > max2) {
            result[index] = max1;
            maxNumberHelper(index1 + 1, start2, index + 1, result);
            return;
        }

        if (max2 > max1) {
            result[index] = max2;
            maxNumberHelper(start1, index2 + 1, index + 1, result);
            return;
        }

        if (max1 == max2) {
            int subMax1 = getMaxByRange(nums1, start1, index1, cache1)[0];
            int subMax2 = getMaxByRange(nums2, start2, index2, cache2)[0];
            if (subMax1 < subMax2) {
                result[index] = max1;
                maxNumberHelper(index1 + 1, start2, index + 1, result);
                return;
            }
            if (subMax1 > subMax2) {
                result[index] = max2;
                maxNumberHelper(start1, index2 + 1, index + 1, result);
                return;
            }
            result[index] = max1;
            maxNumberHelper(index1 + 1, start2, index + 1, result);
            result[index] = max2;
            maxNumberHelper(start1, index2 + 1, index + 1, result);

        }

    }

    private int[] getMaxByRange(int[] nums, int start, int end, Map<String, int[]> cache) {
        int max1 = Integer.MIN_VALUE;
        int index1 = start;
        String key1 = start + " " + end;
        if (cache.containsKey(key1)) {
            return cache.get(key1);
        } else {
            for (int i = start; i < end; i++) {
                if (max1 < nums[i]) {
                    max1 = nums[i];
                    index1 = i;
                }
            }
            int[] v = new int[]{max1, index1};
            cache.put(key1, v);
            return v;
        }
    }

    /**
     * Solution 2 Copy from discuss
     * Perfect and brilliant cody style
     */
    public int[] maxNumber2(int[] nums1, int[] nums2, int k) {
        int n = nums1.length;
        int m = nums2.length;
        int[] ans = new int[k];
        for (int i = Math.max(0, k - m); i <= k && i <= n; ++i) {
            int[] candidate = merge(maxArray(nums1, i), maxArray(nums2, k - i), k);
            if (greater(candidate, 0, ans, 0)) ans = candidate;
        }
        return ans;
    }

    private int[] merge(int[] nums1, int[] nums2, int k) {
        int[] ans = new int[k];
        for (int i = 0, j = 0, r = 0; r < k; ++r)
            ans[r] = greater(nums1, i, nums2, j) ? nums1[i++] : nums2[j++];
        return ans;
    }

    public boolean greater(int[] nums1, int i, int[] nums2, int j) {
        while (i < nums1.length && j < nums2.length && nums1[i] == nums2[j]) {
            i++;
            j++;
        }
        return j == nums2.length || (i < nums1.length && nums1[i] > nums2[j]);
    }

    public int[] maxArray(int[] nums, int k) {
        int n = nums.length;
        int[] ans = new int[k];
        for (int i = 0, j = 0; i < n; i++) {
            while (n - i + j > k && j > 0 && ans[j - 1] < nums[i]) j--;
            if (j < k) ans[j++] = nums[i];
        }
        return ans;
    }


    public static void main(String[] args) {
        Question0321 q = new Question0321();
        int[] result = q.maxNumber2(new int[]{3, 4, 6, 5}, new int[]{9, 1, 2, 5, 8, 3}, 5);
        System.out.println(result);
    }
}
