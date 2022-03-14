package amazon.oa;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 3/13/2022
 */
public class Question0026 {

    /**
     * 26. Simple Cipher
     *
     * As part of a Day 1 challenge, your new team at amazon has created a basic alphabet-based encryption and has
     * asked members to test the cipher. A simple cipher is build on the alphabet wheel which has uppercase english
     * letters['A'-'Z'] written on it;
     *
     * Given an encrypted string consisting of english letters['A'-'Z'] only, decrypt the string by replacing each
     * character with the kth character away on the wheel in counterclockwise direction.
     */

    public String simpleCipher(String encryped, int k) {
        StringBuilder sb = new StringBuilder();
        char[] array = encryped.toCharArray();
        for (int i = 0; i < encryped.length(); i++) {
            char c = (char) (array[i] - k);
            if (c < 'A') {
                c = (char)(c - 'A' + 'Z' + 1);
            }
            sb.append(c);
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        Question0026 q = new Question0026();
        System.out.println(q.simpleCipher("VTAOG", 2));
    }
}
