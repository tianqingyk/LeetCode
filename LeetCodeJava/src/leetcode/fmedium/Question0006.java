package leetcode.fmedium;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 3/23/2022
 */
public class Question0006 {

    /**
     * 6. Zigzag Conversion
     */

    /**
     * Solution
     * Runtime: 4 ms, faster than 95.03% of Java online submissions for Zigzag Conversion.
     * Memory Usage: 44.9 MB, less than 74.90% of Java online submissions for Zigzag Conversion.
     */
    public String convert(String s, int numRows) {
        if(numRows < 2) return s;
        int mod = 2*numRows - 2;

        StringBuilder sb = new StringBuilder();
        for(int j = 0; j < numRows; j ++){
            for(int i = 0; i * mod < s.length(); i++){
                if(i*mod + j > s.length() - 1) break;
                sb.append(s.charAt(i*mod+j));
                if(i*mod+mod-j < s.length() && (mod - j)%mod != j) sb.append(s.charAt(i*mod+mod-j));
            }
        }
        return sb.toString();
    }
}
