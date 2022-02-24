package google.easy;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 2022/2/23
 */
public class Question0028 {

    /**
     * 28. Implement strStr()
     *Implement strStr().
     *
     * Return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
     *
     * Clarification:
     *
     * What should we return when needle is an empty string? This is a great question to ask during an interview.
     *
     * For the purpose of this problem, we will return 0 when needle is an empty string. This is consistent to C's strstr() and Java's indexOf().
     *
     */

    /**
     * Solution 1
     * Runtime: 540 ms, faster than 58.64% of Java online submissions for Implement strStr().
     * Memory Usage: 42.1 MB, less than 51.41% of Java online submissions for Implement strStr().
     */
    public int strStr(String haystack, String needle) {
        if (haystack == null || needle == null || needle.isEmpty()){
            return 0;
        }
        char[] hayArray = haystack.toCharArray();
        char[] needleArray = needle.toCharArray();
        int limit = haystack.length() - needle.length() + 1;
        for (int i = 0; i < limit ; i++) {
            for (int j = 0;; j++) {
                if (j == needle.length()) return i;
                if (hayArray[i+j] != needleArray[j]) break;
            }
        }
        return -1;
    }
}
