package leetcode.fmedium;

import java.util.Stack;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 3/27/2022
 */
public class Question02104 {
    /**
     * 2104. Sum of Subarray Ranges
     */

    /**
     * Solution 2 Copy from discussion
     * O(n)
     */
    public static long subArrayRanges(int[] A) {
        int n = A.length, j, k;
        long res = 0;

        Stack<Integer> s = new Stack<>();
        for (int i = 0; i <= n; i++) {
            while (!s.isEmpty() && A[s.peek()] > (i == n ? Integer.MIN_VALUE : A[i])) {
                j = s.pop();
                k = s.isEmpty() ? -1 : s.peek();
                res -= (long)A[j] * (i - j) * (j - k);

            }
            s.push(i);
        }

        s.clear();
        for (int i = 0; i <= n; i++) {
            while (!s.isEmpty() && A[s.peek()] < (i == n ? Integer.MAX_VALUE : A[i])) {
                j = s.pop();
                k = s.isEmpty() ? -1 : s.peek();
                res += (long)A[j] * (i - j) * (j - k);

            }
            s.push(i);
        }
        return res;
    }

    public static void main(String[] args) {
        subArrayRanges(new int[]{1,2,3,4,1});
    }
}
