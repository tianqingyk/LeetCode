package hard;

/**
 * @author yangke
 * @projectName LeetCodeJava
 * @date 11/11/21
 */
public class Question0044 {
    /**
     * QUESTION 44
     * Given an input string (s) and a pattern (p), implement wildcard pattern matching with support for '?' and '*' where:
     * <p>
     * '?' Matches any single character.
     * '*' Matches any sequence of characters (including the empty sequence).
     * The matching should cover the entire input string (not partial).
     */
    public boolean isMatch(String s, String p) {
        char[] sArray = s.toCharArray();
        int sl = s.length();
        char[] pArray = p.toCharArray();
        int pl = p.length();

        return isMatch(0, sArray, 0, pArray);

    }

    private boolean isMatch(int i, char[] sArray, int j, char[] pArray) {
        if (i > sArray.length - 1) {
            for (int k = j; k < pArray.length; k++) {
                if (pArray[k] != '*') {
                    return false;
                }
            }
            return true;
        }

        if (j > pArray.length - 1) {
            return false;
        }

        char sc = sArray[i];
        char pc = pArray[j];
        if (sc == pc || pc == '?') {
            return isMatch(i + 1, sArray, j + 1, pArray);
        }

        if (pc == '*') {
            if (j + 1 < pArray.length && pArray[j + 1] == '*') {
                return isMatch(i, sArray, j + 1, pArray);
            }
            return isMatch(i, sArray, j + 1, pArray) ||
                    isMatch(i + 1, sArray, j + 1, pArray) ||
                    isMatch(i + 1, sArray, j, pArray);
            /**
             * There is a problem that there are tons of repeated calculations.
             */
        }
        return false;
    }

    /**
     * copy from https://leetcode.com/pandora111/
     * @param str
     * @param pattern
     * @return
     */
    public boolean isMatch2(String str, String pattern) {
        int s = 0, p = 0, match = 0, starIdx = -1;
        while (s < str.length()) {
            // advancing both pointers
            if (p < pattern.length() && (pattern.charAt(p) == '?' || str.charAt(s) == pattern.charAt(p))) {
                s++;
                p++;
            }
            // * found, only advancing pattern pointer
            else if (p < pattern.length() && pattern.charAt(p) == '*') {
                starIdx = p;
                match = s;
                p++;
            }
            // last pattern pointer was *, advancing string pointer
            else if (starIdx != -1) {
                p = starIdx + 1;
                match++;
                s = match;
            }
            //current pattern pointer is not star, last patter pointer was not *
            //characters do not match
            else {
                return false;
            }
        }

        //check for remaining characters in pattern
        while (p < pattern.length() && pattern.charAt(p) == '*') {
            p++;
        }

        return p == pattern.length();
    }

    public static void main(String[] args) {
        Question0044 q = new Question0044();
        String s = "a*a**aa";
        String[] r = s.split("\\*");
        System.out.println();
    }
}
