package leetcode.hard;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 2021/12/27
 */
public class Question0248 {

    /**
     * 248. Strobogrammatic Number III
     * Given two strings low and high that represent two integers low and high where low <= high, return the number
     * of strobogrammatic numbers in the range [low, high].
     *
     * A strobogrammatic number is a number that looks the same when rotated 180 degrees(looked at upside down)
     */

    /**
     * Solution 1
     * Runtime: 715 ms, faster than 5.04% of Java online submissions for Strobogrammatic Number III.
     * Memory Usage: 187.6 MB, less than 5.04% of Java online submissions for
     * @param low
     * @param high
     * @return
     */
    public int strobogrammaticInRange(String low, String high) {
        // 1. 0 1 8 :3
        // 2. (00) 11 69 88 96 : 4
        // 3. 101 111 181 609 619 689 808 818 888 906 916 986 : 3*4
        // 4. 1001 1111 1691 1881 1961  6009 6119 6699 6889 6969 : 4*5
        // 5. :4 * 5 * 3
        // 6. :4 * 5 * 5
        // 7. :4 * 5 * 5 * 3
        int l = Integer.parseInt(low) - 1;
        return strobogrammactic(high) - strobogrammactic(l+"");
    }

    public int strobogrammactic(String high) {
        Long h = Long.valueOf(high);
        if (h < 0) {
            return 0;
        }
        int result = 0;
        int n = high.length();
        for (int i = 1; i < n; i++) {
            result += strobogrammacticNDigital(i);
        }
        String[] nums = strobogrammacticArray(n);
        for (int i = 0; i < nums.length; i++) {
            if (h < Long.valueOf(nums[i])) {
                result += i;
                return result;
            }
        }
        result += nums.length;
        return result;
    }

    public int strobogrammacticNDigital(int n) {
        if (n <= 0) {
            return 0;
        }

        int result = 1;

        int du = n/2;
        int re = n%2;
        for (int i = 0; i < du; i++) {
            if (i == 0){
                result *= 4;
                continue;
            }
            result *= 5;
        }
        if (re >= 1) {
            result *= 3;
        }
        return result;
    }

    String[] nums1 = new String[] {"0", "1", "8"};
    String[] nums2 = new String[] {"11", "69", "88", "96"};
    String[] nums2_ = new String[] {"00", "11", "69", "88", "96"};
    public String[] strobogrammacticArray(int n) {
        if (n == 1){
            return nums1;
        }
        if (n == 2) {
            return nums2;
        }
        int len = strobogrammacticNDigital(n);
        int mod = len;
        String[] result = new String[len];

        int du = n/2;
        int re = n%2;
        for (int i = 0; i < du; i++) {
            if (i == 0){
                mod = len/4;
                for (int j = 0; j < len; j++) {
                    result[j] = nums2[j/mod];
                }
                continue;
            }
            mod = mod/5;
            for (int j = 0; j < len; j++) {
                String tmp = result[j];
                int l = tmp.length();
                result[j] = tmp.substring(0, l/2)+ nums2_[(j/mod) % 5] + tmp.substring(l/2, l);
            }
        }
        if (re >= 1) {
            for (int j = 0; j < len; j++) {
                String tmp = result[j];
                int l = tmp.length();
                result[j] = tmp.substring(0, l / 2) + nums1[j % 3] + tmp.substring(l / 2, l);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Question0248 q = new Question0248();
        System.out.println(q.strobogrammaticInRange("0","2147483647"));
    }
}
