package google.medium;

import javax.xml.transform.Templates;
import java.util.HashSet;
import java.util.Set;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 2022/3/3
 */
public class Question0189 {

    /**
     * 189. Rotate Array
     */

    /**
     * Soluton 1
     * Runtime: 2 ms, faster than 47.31% of Java online submissions for Rotate Array.
     * Memory Usage: 64.7 MB, less than 15.31% of Java online submissions for Rotate Array.
     */
    public void rotate(int[] nums, int k) {
        if (nums == null || nums.length < 2) return;
        int len = nums.length;
        int[] tmpNums = new int[nums.length];
        for (int i = 0; i < len; i++) {
            nums[(i + k) % len] = tmpNums[i];
        }
    }

    /**
     * Solution 2
     * Using Cyclic Replacements
     * Runtime: 11 ms, faster than 5.32% of Java online submissions for Rotate Array.
     * Memory Usage: 57.9 MB, less than 88.95% of Java online submissions for Rotate Array.
     */

    public void rotate2(int[] nums, int k) {
        int len = nums.length;
        Set<Integer> ks = new HashSet<>();

        int minLimit = Math.min(k, len);
        for (int i = 0; i < minLimit; i++) ks.add(i);

        for (int i = 0; i < minLimit; i++) {
            int index = i;

            int tmp = nums[index];
            while (ks.contains(index % k)) {
                index = index + k;
                if (index >= len){
                    ks.remove((index-k)%k);
                    index %= len;
                }
                int num = nums[index];
                nums[index] = tmp;
                tmp = num;
            }
        }
    }
}
