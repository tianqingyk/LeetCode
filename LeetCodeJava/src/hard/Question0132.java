package hard;

import java.beans.beancontext.BeanContext;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 2021/12/27
 */
public class Question0132 {

    /**
     * 132. Palindrome Partitioning II
     * Given a string s, partition s such that every substring of the partition is a palindrome.
     *
     * Return the minimum cuts needed for a palindrome partitioning of s.
     */

    /**
     * Solution 1 not work
     * It can't handle "cbbbcc" or "bbbbaab"
     */

    class Link {
        String val;
        Link before;
        Link after;

        public Link(String val) {
            this.val = val;
        }

        public void setBefore(Link before) {
            this.before = before;
            before.after = this;
        }

        public void setAfter(Link after) {
            this.after = after;
            after.before = this;
        }

        public Link unite1() {
            this.val = this.val + after.val;
            if (this.after.after == null) {
                this.after = null;
                return null;
            }
            setAfter(this.after.after);
            return this.after;
        }

        public Link unite2() {
            this.val = this.val + after.val + after.after.val;
            if (this.after.after.after == null) {
                this.after = null;
                return null;
            }
            setAfter(this.after.after.after);
            return this.after;
        }
    }

    public int minCut1(String s) {
        int len = s.length();

        Link start = new Link(s.charAt(0) + "");
        Link before = start;
        for (int i = 1; i < len; i++) {
            Link now = new Link(s.charAt(i) + "");
            now.setBefore(before);
            before = now;
        }

        int count = 0;
        do {
            count = 0;
            Link now = start;
            for (; ; ) {
                if (now == null) {
                    break;
                }
                Link after = now.after;
                if (after == null) {
                    break;
                }
                if (isPalindrome(now.val + after.val)) {
                    now = now.unite1();
                    count++;
                    continue;
                }

                Link after2 = after.after;
                if (after2 == null) {
                    break;
                }
                if (isPalindrome(now.val + after.val + after2.val)) {
                    now = now.unite2();
                    count++;
                    continue;
                }
                now = after;
            }

        } while (count > 0);

        count = 0;
        Link now = start;
        while (now.after != null) {
            now = now.after;
            count++;
        }
        return count;
    }

    public boolean isPalindrome(String str) {
        int len = str.length();
        for (int i = 0; i < len / 2 + 1; i++) {
            if (str.charAt(i) != str.charAt(len - i - 1)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Solution 2 Backtracking
     *
     * @param s
     * @return
     */
    public int minCut(String s) {
        if (isPalindrome(s)) {
            return 0;
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < s.length() - 1; i++) {
            int f =  findMinCut(s, i);
            if (f < min){
                min = f;
            }
        }
        return min;
    }

    private int findMinCut(String s, int index) {
        if (isPalindrome(s.substring(0, index + 1))) {
            return minCut(s.substring(index + 1)) + 1;
        }
        return Integer.MAX_VALUE;
    }

    /**
     * Solution 3 Dynamic Programing
     * with memo
     * Runtime: 642 ms, faster than 24.52% of Java online submissions for Palindrome Partitioning II.
     * Memory Usage: 37.2 MB, less than 90.84% of Java online submissions for Palindrome Partitioning II.
     * without memo
     * Runtime: 1522 ms, faster than 5.02% of Java online submissions for Palindrome Partitioning II.
     * Memory Usage: 52 MB, less than 29.70% of Java online submissions for Palindrome Partitioning II.
     * @param s
     * @return
     */

    private Boolean memoPalindrome[][];

    public int minCut3(String s){
        int l = s.length();
        memoPalindrome = new Boolean[l][l];
        int[] dp = new int[l];


        for (int i = l - 1; i >= 0 ; i--){
            if (isPalindrome(s, i, l-1)){
                dp[i] = 0;
                continue;
            }
            int min = Integer.MAX_VALUE;
            for (int j  = i+1; j < l;j++){
                if (isPalindrome(s, i, j-1)){
                    min = Math.min(min, 1 + dp[j]);
                }
            }
            dp[i] = min;
        }

        return dp[0];
    }

    private boolean isPalindrome(String s, int start, int end){
        if (start >= end) {
            return true;
        }
        if (memoPalindrome[start][end] == null){
            int len = end - start;
            for (int i = 0; i < len / 2 + 1; i++) {
                if (s.charAt(i + start) != s.charAt(end - i)) {
                    memoPalindrome[start][end] = false;
                    return memoPalindrome[start][end];
                }
            }
            memoPalindrome[start][end] = true;
        }
        return memoPalindrome[start][end];
    }


    public static void main(String[] args) {
        Question0132 q = new Question0132();
        System.out.println(q.minCut3("cbbbcc"));
        System.out.println(q.minCut3("bbbbab"));
    }
}
