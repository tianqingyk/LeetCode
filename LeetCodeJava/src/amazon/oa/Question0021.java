package amazon.oa;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 3/11/2022
 */
public class Question0021 {

    /**
     * 21. Counting Binary Substrings
     * <p>
     * Kindle Direct Publishing, Amazon's e-book self-publishing platform, is working on a new feature to help authors
     * track the use of text strings in different ways. A substring is a group of contiguous characters in a string.
     * For instance, all substring of abc are [a, b, c, ab, bc, abc].
     * <p>
     * Given a binary representation of a number, determine the total number of substring present that match the following
     * conditions:
     * 1. The 0s and 1s are grouped consecutively(e.g., 01, 10, 0011, 1100, 000111, etc.)
     * 2. The number of 0s in the substring is equal to the number of 1s in the substring.
     */

    public int countingBinarySubstrings(String input) {
        int count = 0;
        int index = 0;

        while (index < input.length() - 1) {
            char cur = input.charAt(index);
            char next = input.charAt(index + 1);
            if (cur == next) {
                index++;
                continue;
            }

            count++;
            int exp = 1;
            while (index - exp >= 0 && index + exp + 1< input.length()) {
                char left = input.charAt(index - exp);
                char right = input.charAt(index + exp + 1);
                if (left == cur && right == next) {
                    count++;
                    exp++;
                } else break;
            }
            index++;
        }
        return count;
    }


    public static void main(String[] args) {
        Question0021 q = new Question0021();
        System.out.println(q.countingBinarySubstrings("0011011000111"));
    }
}
