package amazon.oa;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 3/11/2022
 */
public class Question0016 {

    /**
     * 16. Two Sum Unique Pairs Solution
     *
     * Write a function that takes a list of numbers and a target number, and then returns  the number of unique pairs
     * that add up to the target number. [X, Y] and [Y, X] are considered the same pair, and not unique.
     */

    class Pair{
        int a;
        int b;

        Pair(int a, int b){
            this.a = a;
            this.b = b;
        }

        @Override
        public int hashCode() {
            return (a+" "+b).hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            Pair p2 = (Pair) obj;
            return (a == p2.a && b == p2.b);
        }
    }

    public int twoSumUniquePairs(int[] input, int target) {
        Set<Pair> cache = new HashSet<>();
        Arrays.sort(input);
        int left = 0;
        int right = input.length -1;
        while (left < right) {
            int l = input[left];
            int r = input[right];
            if (target == l + r) {
                int a = Math.min(l, r);
                int b = Math.max(l, r);
                cache.add(new Pair(a, b));
                left++;
                right--;
                continue;
            }

            if (target > l + r) {
                left++;
                continue;
            }

            right--;
        }

        return cache.size();
    }

    public static void main(String[] args) {
        Question0016 q = new Question0016();
        System.out.println(q.twoSumUniquePairs(new int[]{1,1,2,45,46,46}, 47));
    }
}
