package leetcode.fmedium;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 3/23/2022
 */
public class Question0038 {

    /**
     * 38. Count and Say
     */

    /**
     * Solution
     * Runtime: 4 ms, faster than 74.48% of Java online submissions for Count and Say.
     * Memory Usage: 41.4 MB, less than 80.20% of Java online submissions for Count and Say.
     */
    public String countAndSay(int n) {
        if(n == 1) return "1";
        return say(countAndSay(n-1));
    }

    public String say(String s){
        StringBuilder sb = new StringBuilder();

        char pre = s.charAt(0);
        int count = 1;
        for(int i = 1 ; i < s.length(); i++) {
            char cur = s.charAt(i);
            if(pre == cur){
                count++;
                continue;
            }
            sb.append(count).append(pre);
            pre = cur;
            count = 1;
        }
        sb.append(count).append(pre);
        return sb.toString();
    }
}
