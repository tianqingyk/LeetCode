package hard;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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
                if (maxResult[i] > result[i]){
                    break;
                }
                if (maxResult[i] < result[i]){
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
            maxNumberHelper(start1, index2 + 1,  index + 1, result);
            return;
        }

        if (max1 == max2) {
            int subMax1 = getMaxByRange(nums1, start1, index1, cache1)[0];
            int subMax2 = getMaxByRange(nums2, start2, index2, cache2)[0];
            if (subMax1 < subMax2) {
                result[index] = max1;
                maxNumberHelper( index1 + 1, start2, index + 1, result);
                return;
            }
            if (subMax1 > subMax2){
                result[index] = max2;
                maxNumberHelper( start1, index2 + 1, index + 1, result);
                return;
            }
            result[index] = max1;
            maxNumberHelper( index1 + 1, start2, index + 1, result);
            result[index] = max2;
            maxNumberHelper( start1, index2 + 1, index + 1, result);

        }

    }

    private int[] getMaxByRange(int[] nums, int start, int end, Map<String, int[]> cache) {
        int max1 = Integer.MIN_VALUE;
        int index1 = start;
        String key1 = start +" " + end;
        if (cache.containsKey(key1)){
            return cache.get(key1);
        }else {
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

    public static void main(String[] args) {
        Question0321 q = new Question0321();
        int[] result = q.maxNumber(new int[]{8,9,7,3,5,9,1,0,8,5,3,0,9,2,7,4,8,9,8,1,0,2,0,2,7,2,3,5,4,7,4,1,4,0,1,4,2,1,3,1,5,3,9,3,9,0,1,7,0,6,1,8,5,6,6,5,0,4,7,2,9,2,2,7,6,2,9,2,3,5,7,4,7,0,1,8,3,6,6,3,0,8,5,3,0,3,7,3,0,9,8,5,1,9,5,0,7,9,6,8,5,1,9,6,5,8,2,3,7,1,0,1,4,3,4,4,2,4,0,8,4,6,5,5,7,6,9,0,8,4,6,1,6,7,2,0,1,1,8,2,6,4,0,5,5,2,6,1,6,4,7,1,7,2,2,9,8,9,1,0,5,5,9,7,7,8,8,3,3,8,9,3,7,5,3,6,1,0,1,0,9,3,7,8,4,0,3,5,8,1,0,5,7,2,8,4,9,5,6,8,1,1,8,7,3,2,3,4,8,7,9,9,7,8,5,2,2,7,1,9,1,5,5,1,3,5,9,0,5,2,9,4,2,8,7,3,9,4,7,4,8,7,5,0,9,9,7,9,3,8,0,9,5,3,0,0,3,0,4,9,0,9,1,6,0,2,0,5,2,2,6,0,0,9,6,3,4,1,2,0,8,3,6,6,9,0,2,1,6,9,2,4,9,0,8,3,9,0,5,4,5,4,6,1,2,5,2,2,1,7,3,8,1,1,6,8,8,1,8,5,6,1,3,0,1,3,5,6,5,0,6,4,2,8,6,0,3,7,9,5,5,9,8,0,4,8,6,0,8,6,6,1,6,2,7,1,0,2,2,4,0,0,0,4,6,5,5,4,0,1,5,8,3,2,0,9,7,6,2,6,9,9,9,7,1,4,6,2,8,2,5,3,4,5,2,4,4,4,7,2,2,5,3,2,8,2,2,4,9,8,0,9,8,7,6,2,6,7,5,4,7,5,1,0,5,7,8,7,7,8,9,7,0,3,7,7,4,7,2,0,4,1,1,9,1,7,5,0,5,6,6,1,0,6,9,4,2,8,0,5,1,9,8,4,0,3,1,2,4,2,1,8,9,5,9,6,5,3,1,8,9,0,9,8,3,0,9,4,1,1,6,0,5,9,0,8,3,7,8,5}, new int[]{7,8,4,1,9,4,2,6,5,2,1,2,8,9,3,9,9,5,4,4,2,9,2,0,5,9,4,2,1,7,2,5,1,2,0,0,5,3,1,1,7,2,3,3,2,8,2,0,1,4,5,1,0,0,7,7,9,6,3,8,0,1,5,8,3,2,3,6,4,2,6,3,6,7,6,6,9,5,4,3,2,7,6,3,1,8,7,5,7,8,1,6,0,7,3,0,4,4,4,9,6,3,1,0,3,7,3,6,1,0,0,2,5,7,2,9,6,6,2,6,8,1,9,7,8,8,9,5,1,1,4,2,0,1,3,6,7,8,7,0,5,6,0,1,7,9,6,4,8,6,7,0,2,3,2,7,6,0,5,0,9,0,3,3,8,5,0,9,3,8,0,1,3,1,8,1,8,1,1,7,5,7,4,1,0,0,0,8,9,5,7,8,9,2,8,3,0,3,4,9,8,1,7,2,3,8,3,5,3,1,4,7,7,5,4,9,2,6,2,6,4,0,0,2,8,3,3,0,9,1,6,8,3,1,7,0,7,1,5,8,3,2,5,1,1,0,3,1,4,6,3,6,2,8,6,7,2,9,5,9,1,6,0,5,4,8,6,6,9,4,0,5,8,7,0,8,9,7,3,9,0,1,0,6,2,7,3,3,2,3,3,6,3,0,8,0,0,5,2,1,0,7,5,0,3,2,6,0,5,4,9,6,7,1,0,4,0,9,6,8,3,1,2,5,0,1,0,6,8,6,6,8,8,2,4,5,0,0,8,0,5,6,2,2,5,6,3,7,7,8,4,8,4,8,9,1,6,8,9,9,0,4,0,5,5,4,9,6,7,7,9,0,5,0,9,2,5,2,9,8,9,7,6,8,6,9,2,9,1,6,0,2,7,4,4,5,3,4,5,5,5,0,8,1,3,8,3,0,8,5,7,6,8,7,8,9,7,0,8,4,0,7,0,9,5,8,2,0,8,7,0,3,1,8,1,7,1,6,9,7,9,7,2,6,3,0,5,3,6,0,5,9,3,9,1,1,0,0,8,1,4,3,0,4,3,7,7,7,4,6,4,0,0,5,7,3,2,8,5,1,4,5,8,5,6,7,5,7,3,3,9,6,8,1,5,1,1,1,0,3}, 200);
        System.out.println(result);
    }
}
