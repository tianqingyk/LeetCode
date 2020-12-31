package hard;

import java.util.Arrays;

/**
 * @author yangke
 * @projectName LeetCodeJava
 * @date 2020/12/31
 * 4. Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.
 * <p>
 * Follow up: The overall run time complexity should be O(log (m+n)).
 * <p>
 *  
 * <p>
 * Example 1:
 * <p>
 * Input: nums1 = [1,3], nums2 = [2]
 * Output: 2.00000
 * Explanation: merged array = [1,2,3] and median is 2.
 * Example 2:
 * <p>
 * Input: nums1 = [1,2], nums2 = [3,4]
 * Output: 2.50000
 * Explanation: merged array = [1,2,3,4] and median is (2 + 3) / 2 = 2.5.
 * Example 3:
 * <p>
 * Input: nums1 = [0,0], nums2 = [0,0]
 * Output: 0.00000
 * Example 4:
 * <p>
 * Input: nums1 = [], nums2 = [1]
 * Output: 1.00000
 * Example 5:
 * <p>
 * Input: nums1 = [2], nums2 = []
 * Output: 2.00000
 */
public class Question0004 {

    public static void main(String[] args){
        Question0004 q = new Question0004();
        System.out.println(q.findMedianSortedArrays(new int[]{4}, new int[]{1,2,3}));
        System.out.println(q.findMedianSortedArrays(new int[]{1,4,5,6,7}, new int[]{2,3,4,5}));
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int l = nums1.length + nums2.length;
        int k1 = (l - 1) / 2 + 1;
        int k2 = l / 2 + 1;
        if (k1 == k2) {
            return findKMedianSortedArrays(k1, nums1, nums2);
        }
        return (findKMedianSortedArrays(k1, nums1, nums2) +
                findKMedianSortedArrays(k2, nums1, nums2)) / 2;
    }

    public double findKMedianSortedArrays(int k, int[] nums1, int[] nums2) {
        if (nums1.length <= 0 ){
            return nums2[k-1];
        }
        if (nums2.length <= 0) {
            return nums1[k-1];
        }

        if (k <= 1){
            return nums1[0] > nums2[0] ? nums2[0] : nums1[0];
        }

        int medK = k / 2;
        if (nums1.length< medK) {
            return findSpecialKMedianSortedArrays(k, nums1.length, k - nums1.length, nums1, nums2);
        }

        if (nums2.length < medK) {
            return findSpecialKMedianSortedArrays(k, k - nums2.length, nums2.length, nums1, nums2);
        }

       return findSpecialKMedianSortedArrays(k, medK, medK, nums1, nums2);
    }

    private double findSpecialKMedianSortedArrays(int k, int k1, int k2, int[] nums1, int[] nums2){
        if (nums1[k1-1] > nums2[k2-1]) {
            return findKMedianSortedArrays(k - k2, nums1, Arrays.copyOfRange(nums2, k2, nums2.length));
        } else if (nums1[k1-1] < nums2[k2-1]) {
            return findKMedianSortedArrays(k - k1, nums2, Arrays.copyOfRange(nums1, k1, nums1.length));
        } else {
            k = k - k1 - k2;
            if (k <= 0 ){
                return nums1[k1-1];
            }else {
                return findKMedianSortedArrays(k, Arrays.copyOfRange(nums1, k1, nums1.length), Arrays.copyOfRange(nums2, k2, nums2.length));
            }
        }
    }
}
