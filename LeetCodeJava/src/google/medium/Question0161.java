package google.medium;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 2022/3/3
 */
public class Question0161 {

    /**
     * 161. One Edit Distance
     */

    /**
     * Solution
     * Runtime: 2 ms, faster than 40.23% of Java online submissions for One Edit Distance.
     * Memory Usage: 41.8 MB, less than 46.18% of Java online submissions for One Edit Distance.
     */
    String s, t;
    public boolean isOneEditDistance(String s, String t) {
        this.s = s;
        this.t = t;
        int minLen = Math.min(s.length(), t.length());
        for (int i = 0; i < minLen; i++) {
            if (s.charAt(i) != t.charAt(i)){
                return recursive(i+1,i) || recursive(i+1, i+1) || recursive(i, i+1);
            }
        }
        if (s.length() == t.length() - 1 || t.length() == s.length() - 1) return true;
        return false;
    }

    private boolean recursive(int indexS, int indexT){
        if (indexS == s.length() - 1 && indexT == t.length() - 1) return true;
        if (indexS >= s.length() - 1 || indexT >= t.length() - 1) return false;
        if (s.charAt(indexS) != t.charAt(indexT)) return false;
        return recursive(indexS + 1, indexT + 1);
    }
}
