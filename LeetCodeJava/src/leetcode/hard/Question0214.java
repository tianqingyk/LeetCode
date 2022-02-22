package leetcode.hard;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 2021/12/27
 */
public class Question0214 {

    /**
     * 214. Shortest Palindrome
     * Runtime: 613 ms, faster than 6.76% of Java online submissions for Shortest Palindrome.
     * Memory Usage: 41.9 MB, less than 13.20% of Java online submissions for Shortest Palindrome.
     * You are given a string s. You can convert s to a palindrome by adding characters in front of it.
     * Return the shortest palindrome you can find by performing this transformation.
     */

    public String shortestPalindrome(String s) {
        StringBuilder sb = new StringBuilder();
        int l = s.length();
        int index = 0;
        int returnI = l;

        for (int i = l - 1; i >= 0; i--) {
            char c = s.charAt(i);
            char c1 = s.charAt(index);

            if (c == c1){
                index ++;
            }else {
                returnI--;
                i = returnI;
                index = 0;
            }
        }

        for (int i = l-1; i >= returnI ; i--) {
            sb.append(s.charAt(i));
        }
        return sb.toString() + s;
    }


}
