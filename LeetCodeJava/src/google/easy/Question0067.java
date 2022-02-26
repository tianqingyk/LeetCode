package google.easy;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 2022/2/26
 */
public class Question0067 {

    /**
     * 67. Add Binary
     * Given two binary strings a and b, return their sum as a binary string.
     */

    /**
     * 67. Add Binary
     * Runtime: 2 ms, faster than 89.48% of Java online submissions for Add Binary.
     * Memory Usage: 42.3 MB, less than 21.60% of Java online submissions for Add Binary.
     */
    public String addBinary(String a, String b) {
        int aLen = a.length();
        int bLen = b.length();
        int maxLen = Math.max(aLen, bLen);
        StringBuilder sb = new StringBuilder();
        boolean offset = false;
        char cA, cB;
        for (int i = 0; i < maxLen; i++) {
            if (i < aLen) cA = a.charAt(aLen - 1 - i);
            else cA = '0';
            if (i < bLen) cB = a.charAt(bLen - 1- i);
            else cB = '0';

            if (cA == '1' && cB == '1'){
                if (offset) sb.append('1');
                else sb.append('0');
                offset = true;
                continue;
            }

            if (cA == '1' || cB == '1'){
                if (offset) {
                    sb.append('0');
                    offset = true;
                }
                else sb.append('1');
                continue;
            }

            if (offset){
                sb.append('1');
                offset = false;
            }else sb.append('0');
        }
        if (offset){
            sb.append('1');
            offset = false;
        }

        return sb.reverse().toString();
    }
}
