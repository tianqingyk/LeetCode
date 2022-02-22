package leetcode.hard;

/**
 * @author yangke
 * @projectName LeetCodeJava
 * @date 2021/1/6
 * Given an input string (s) and a pattern (p), implement regular expression matching with support for '.' and '*' where: 
 * <p>
 * '.' Matches any single character.​​​​
 * '*' Matches zero or more of the preceding element.
 * The matching should cover the entire input string (not partial).
 * <p>
 *  
 * <p>
 * Example 1:
 * <p>
 * Input: s = "aa", p = "a"
 * Output: false
 * Explanation: "a" does not match the entire string "aa".
 * Example 2:
 * <p>
 * Input: s = "aa", p = "a*"
 * Output: true
 * Explanation: '*' means zero or more of the preceding element, 'a'. Therefore, by repeating 'a' once, it becomes "aa".
 * Example 3:
 * <p>
 * Input: s = "ab", p = ".*"
 * Output: true
 * Explanation: ".*" means "zero or more (*) of any character (.)".
 * Example 4:
 * <p>
 * Input: s = "aab", p = "c*a*b"
 * Output: true
 * Explanation: c can be repeated 0 times, a can be repeated 1 time. Therefore, it matches "aab".
 * Example 5:
 * <p>
 * Input: s = "mississippi", p = "mis*is*p*."
 * Output: false
 */
public class Question0010 {

    public static void main(String[] args) {
        var q = new Question0010();
        System.out.println(q.isMatch("bbbba",".*a*a"));
    }

    public boolean isMatch(String s, String p) {
        return matchProcessing(0, 0, s.toCharArray(), p.toCharArray());

    }

    private boolean matchProcessing(int id1, int id2, char[] s, char[] p) {
        if (id1 >= s.length) {
            if (id2 >= p.length) {
                return true;
            }
            for (; id2 < p.length; id2 += 2) {
                if (id2 + 1 >= p.length || p[id2 + 1] != '*') {
                    return false;
                }
            }
            return true;
        }

        if (id2 >= p.length) {
            return false;
        }

        var sChar = s[id1];
        var pChar = p[id2];
        var isAsterisk = id2 + 1 < p.length && p[id2 + 1] == '*';
        if (pChar != '.' && pChar != sChar) {
            if (isAsterisk) {
                return matchProcessing(id1, id2 + 2, s, p);
            }
            return false;
        }
        if (isAsterisk) {
            return matchProcessing(id1 + 1, id2 + 2, s, p) || matchProcessing(id1 + 1, id2, s, p) || matchProcessing(id1, id2 + 2, s, p);
        }
        return matchProcessing(id1 + 1, id2 + 1, s, p);
    }

}
