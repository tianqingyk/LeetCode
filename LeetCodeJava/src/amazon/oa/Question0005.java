package amazon.oa;

import java.awt.font.NumericShaper;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 3/10/2022
 */
public class Question0005 {
    /**
     * 5. Maximize Score After N Operations / Number Game Solution
     *
     * You are given nums, an array of positive integers of size 2*n. You must perform n operations on this array.
     *
     * In the ith operation(1-indexed), you will:
     *      - Choose two elements, x and y.
     *      - Receive a score of i * gcd(x,y)
     *      - Remove x and y from nums.
     *
     * Return the maximum score you can receive after performing n operations.
     *
     * The function gcd(x, y) is the greatest common divisor of x and y.
     */

    int max = Integer.MIN_VALUE;
    public int numberGame(int[] nums){
        recursive(Arrays.stream(nums).boxed().collect(Collectors.toList()), new LinkedList<>());
        return max;
    }

    private void recursive(List<Integer> numbers, LinkedList<Integer> gcds) {
        if (numbers.isEmpty()){
            int result = 0;
            gcds.sort(Integer::compare);
            for (int i = gcds.size(); i >= 1; i--) {
                result += i*gcds.get(i-1);
            }
            max = Math.max(result, max);
            return;
        }
        int num1 = numbers.get(0);
        numbers.remove(0);
        for (int i = 0; i < numbers.size(); i++) {
            int num2 = numbers.get(i);
            numbers.remove(i);
            int gcd = gcd(num1, num2);
            gcds.add(gcd);
            recursive(numbers, gcds);
            gcds.removeLast();
            numbers.add(i, num2);
        }
        numbers.add(0, num1);
    }

    private int gcd(int a, int b){
        if (b == 0) return a;
        return gcd(b, a%b);
    }

    public static void main(String[] args) {
        Question0005 q = new Question0005();
        System.out.println(q.numberGame(new int[] {1,2}));
    }
}
