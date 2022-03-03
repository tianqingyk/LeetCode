package google.easy;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 2022/3/3
 */
public class Question0171 {

    /**
     * 171. Excel Sheet Column Number
     */

    /**
     * Solution
     * Runtime: 1 ms, faster than 99.41% of Java online submissions for Excel Sheet Column Number.
     * Memory Usage: 40.8 MB, less than 81.64% of Java online submissions for Excel Sheet Column Number.
     */
    public int titleToNumber(String columnTitle) {
        if (columnTitle == null || columnTitle.length() < 1) return 0;
        int result = 0;
        for (int i = 0; i < columnTitle.length(); i++) {
            result = result*26+columnTitle.charAt(i) - 'A' + 1;
        }
        return result;
    }
}
