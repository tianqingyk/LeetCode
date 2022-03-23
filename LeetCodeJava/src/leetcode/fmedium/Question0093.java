package leetcode.fmedium;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 2022/2/28
 */
public class Question0093 {

    /**
     * 93. Restore IP Address
     * https://leetcode.com/problems/restore-ip-addresses/
     */

    /**
     * Solution 1
     * Runtime: 1 ms, faster than 99.86% of Java online submissions for Restore IP Addresses.
     * Memory Usage: 40.6 MB, less than 58.76% of Java online submissions for Restore IP Addresses.
     */
    int n = 4;
    String s;
    List<String> result = new ArrayList<>();

    public List<String> restoreIpAddresses(String s) {
        this.s = s;
        recursive(0, 0, new StringBuilder());
        return result;
    }

    private void recursive(int index, int num, StringBuilder sb) {
        if (num > n - 1 && index > s.length() - 1) {
            String str = sb.toString();
            result.add(str.substring(1));
            return;
        }

        if (num > n - 1 || index > s.length() - 1) return;



        recursive(index + 1, num + 1, sb.append('.').append(s.charAt(index)));
        sb.delete(sb.length() - 2, sb.length());

        if (s.charAt(index) == '0') return;

        if (index + 1 < s.length()) {
            recursive(index + 2, num + 1, sb.append('.').append(s.substring(index, index + 2)));
            sb.delete(sb.length() - 3, sb.length());
        }

        if (index + 2 < s.length() && Integer.valueOf(s.substring(index, index + 3)) <= 255) {
            recursive(index + 3, num + 1, sb.append('.').append(s.substring(index, index + 3)));
            sb.delete(sb.length() - 4, sb.length());
        }
    }
}
