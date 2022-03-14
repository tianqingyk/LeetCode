package amazon.oa;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 3/13/2022
 */
public class Question0027 {

    /**
     * 27. Decode String Frequency
     *
     * Amazon Web Services(AWS) is working on a new security feature to help encode text. Consider a string that
     * Consists of lowercase English alphabetic letters (i.e., [a, z]) only. The following rules are used to encode all
     * of its characters into string s:
     *
     *  - a is encoded as 1, b is encoded as 2, c is encoded as 3,..., and i is encoded as 9.
     *  - j is encoded as 10#, k is encoded as 11#, l is encoded as 12#,..., and z is encoded as 26#.
     */

    public String decodeStringFrequency(String s) {
        StringBuilder sb = new StringBuilder();

        char[] chars = s.toCharArray();
        int count = 1;
        for (int i = 0; i < s.length(); i++) {
            int c = chars[i] - 'a' + 1;
            if (i > 0 && chars[i] == chars[i-1]) {
                count++;
                continue;
            }

            if (count > 1){
                sb.append("("+count+")");
                count = 1;
            }
            if (c >= 10) sb.append(c+"#");
            else sb.append(c+"");
        }
        if (count > 1) sb.append("("+count+")");


        return sb.toString();
    }

    public static void main(String[] args) {
        Question0027 q = new Question0027();
        System.out.println(q.decodeStringFrequency("abzx"));
        System.out.println(q.decodeStringFrequency("aabccc"));
    }
}
