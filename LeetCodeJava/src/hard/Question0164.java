package hard;

import java.util.*;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 2021/12/27
 */
public class Question0164 {
    /**
     * 164. Maximum Gap
     * Given an integer array nums, return the maximum difference between two successive elements in its sorted form. If the array contains less than two elements, return 0.
     * <p>
     * You must write an algorithm that runs in linear time and uses linear extra space.
     */

    public int maximumGap(int[] nums) {
        List<Integer> list = new ArrayList<>();
        for (int num : nums) {
            list.add(num);
        }
        return maximumGap(list);
    }

    /**
     * Solution 1
     * Runtime: 219 ms, faster than 7.56% of Java online submissions for Maximum Gap.
     * Memory Usage: 50.6 MB, less than 81.33% of Java online submissions for Maximum Gap.
     *
     * @param nums
     * @return
     */
    public int maximumGap(List<Integer> nums) {
        if (nums.size() <= 1) {
            return 0;
        }
        if (nums.size() <= 2) {
            return Math.abs(nums.get(1) - nums.get(0));
        }

        int cp = nums.get(0);
        int leftMin = Integer.MAX_VALUE;
        int rightMin = Integer.MAX_VALUE;
        List<Integer> leftList = new ArrayList<>();
        List<Integer> rightList = new ArrayList<>();

        for (int i = 1; i < nums.size(); i++) {
            int num = nums.get(i);
            if (num <= cp) {
                leftList.add(num);
                leftMin = Math.min(leftMin, cp - num);
            } else {
                rightList.add(num);
                rightMin = Math.min(rightMin, num - cp);
            }
        }
        if (leftMin >= Integer.MAX_VALUE) {
            leftMin = 0;
        }
        if (rightMin >= Integer.MAX_VALUE) {
            rightMin = 0;
        }
        int max = Math.max(leftMin, rightMin);

        max = Math.max(max, Math.max(maximumGap(leftList), maximumGap(rightList)));
        return max;

    }

    /**
     * Solution 2 copy from discuss
     *
     * @param nums
     * @return
     */
    public int maximumGap2(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }

        // m is the maximal number in nums
        int m = nums[0];
        for (int i = 1; i < nums.length; i++) {
            m = Math.max(m, nums[i]);
        }

        int exp = 1; // 1, 10, 100, 1000 ...
        int R = 10; // 10 digits

        int[] aux = new int[nums.length];

        while (m / exp > 0) { // Go through all digits from LSB to MSB
            int[] count = new int[R];

            for (int i = 0; i < nums.length; i++) {
                count[(nums[i] / exp) % 10]++;
            }

            for (int i = 1; i < count.length; i++) {
                count[i] += count[i - 1];
            }

            for (int i = nums.length - 1; i >= 0; i--) {
                aux[--count[(nums[i] / exp) % 10]] = nums[i];
            }

            for (int i = 0; i < nums.length; i++) {
                nums[i] = aux[i];
            }
            exp *= 10;
        }

        int max = 0;
        for (int i = 1; i < aux.length; i++) {
            max = Math.max(max, aux[i] - aux[i - 1]);
        }

        return max;
    }

    /**
     * solution 3 buckets and The Pigeonhole Principle
     * Runtime: 527 ms, faster than 5.04% of Java online submissions for Maximum Gap.
     * Memory Usage: 222.7 MB, less than 5.04% of Java online submissions for Maximum Gap.
     * @param nums
     * @return
     */
    public int maximumGap3(int[] nums) {
        int n = nums.length;
        if(n < 2){
            return 0;
        }
        int max = Arrays.stream(nums).max().getAsInt();
        int min = Arrays.stream(nums).min().getAsInt();
        int gap = Math.max((max - min) / (n - 1), 1);

        Map<Integer, List<Integer>> buckets = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int key = (nums[i] - min) / gap;
            buckets.compute(key, (k, v) -> v == null ? new ArrayList<>() : v).add(nums[i]);
        }

        List<Integer> tmpList = new ArrayList<>();
        for (int i = n; i >= 0; i--) {
            if (buckets.containsKey(i)) {
                if (tmpList.size() > 0) {
                    tmpList.add(buckets.get(i).stream().max(Integer::compare).get());
                }
                if (i > 0) {
                    tmpList.add(buckets.get(i).stream().min(Integer::compare).get());
                }
            }
        }

        max = 0;
        for (int i = 0; i < tmpList.size() - 1; i++) {
            max = Math.max(max, tmpList.get(i) - tmpList.get(i + 1));
        }
        return max;
    }

    public static void main(String[] args) {
        Question0164 q = new Question0164();
        int reulst = q.maximumGap3(new int[]{1,1,1,1});
        System.out.println(reulst);
    }
}
