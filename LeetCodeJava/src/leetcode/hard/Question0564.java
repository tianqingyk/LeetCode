package leetcode.hard;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 3/21/2022
 */
public class Question0564 {


    /**
     * 564. Find the Closest Palindrome
     */

    /**
     * Solution
     * Runtime: 2 ms, faster than 95.92% of Java online submissions for Find the Closest Palindrome.
     * Memory Usage: 40.3 MB, less than 90.82% of Java online submissions for Find the Closest Palindrome.
     */
    int len;
    public String nearestPalindromic(String n) {
        len = n.length();
        long v = Long.valueOf(n);
        if(v < 10){
            if(v == 0) return "1";
            return v-1+"";
        }

        long v1, v2;
        long h = Long.valueOf(n.substring(0, (len+1)/2));
        if(isPalindromic(n)){
            long h1 =  h - 1;
            long h2 = h + 1;
            if(h1 == 0) return "9";
            v1 = getPalindromic(h1);
            v2 = getPalindromic(h2);
        }else {
            v1 = getPalindromic(h);
            if(v - v1 > Long.MAX_VALUE - v) v2 = v1;
            else {
                String newN = (2 * v - v1+"");
                if (isPalindromic(newN)) v2 = Long.valueOf(newN);
                else v2 = getPalindromic(Long.valueOf(newN.substring(0, (newN.length()+1)/2)));
            }
        }

        long d1 = Math.abs(v1-v);
        long d2 = Math.abs(v2-v);
        if(d1 == d2) return Math.min(v1,v2)+"";
        if(d2 == 0) return v1+"";
        return d1 > d2 ? v2+"" :v1+"";
    }

    private boolean isPalindromic(String n){
        for(int i = 0; i < n.length()/2;i++){
            if(n.charAt(i) != n.charAt(n.length()-i-1)){
                return false;
            }
        }
        return true;
    }

    private long getPalindromic(long h) {
        String hStr = h+"";
        StringBuilder sb = new StringBuilder();
        sb.append(hStr, 0, len/2);
        sb.reverse();
        sb.insert(0, hStr);
        return Long.valueOf(sb.toString());
    }

    public static void main(String[] args) {
        Question0564 q = new Question0564();
        System.out.println(q.nearestPalindromic("1"));
    }
}
