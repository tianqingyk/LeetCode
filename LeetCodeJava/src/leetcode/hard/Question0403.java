package leetcode.hard;

import javax.print.attribute.standard.JobHoldUntil;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 2022/2/12
 */
public class Question0403 {

    /**
     * 403. Frog Jump
     */

    /**
     * Solution
     * Runtime: 231 ms, faster than 11.79% of Java online submissions for Frog Jump.
     * Memory Usage: 47 MB, less than 91.70% of Java online submissions for Frog Jump.
     */
    int[] stones;

    Map<String, Boolean> memo = new HashMap<>();
    public boolean canCross(int[] stones) {
        this.stones = stones;
        int jump = stones[1] - stones[0];
        if (jump != 1) return false;
        return recursive(1, 2, jump);
    }

    private boolean recursive(int cur, int next, int k) {
        if (memo.containsKey(cur+" "+k)) return memo.get(cur+" "+k);
        if (cur >= stones.length - 1) return true;
        if (next >= stones.length) return false;
        int jump = stones[next] - stones[cur];
        if (jump > k + 1) return false;
        if (jump >= k - 1 && jump <= k + 1) {
            if (recursive(next, next + 1, jump)) return true;
            memo.put(next+" "+jump, false);
        }
        if (recursive(cur, next + 1, k)) return true;
        memo.put(cur+" "+k, false);
        return false;
    }

    public static void main(String[] args) {
        Question0403 q = new Question0403();
//        System.out.println(q.canCross(new int[]{0, 1, 3, 5, 6, 8, 12, 17}));
//        System.out.println(q.canCross(new int[]{0, 1, 3, 6, 10, 15, 16, 21}));
        System.out.println(q.canCross(new int[]{0, 1, 4, 5,8}));
    }
}
