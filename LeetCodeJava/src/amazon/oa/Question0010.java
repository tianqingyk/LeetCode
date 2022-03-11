package amazon.oa;

import java.util.HashSet;
import java.util.Set;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 3/11/2022
 */
public class Question0010 {

    /**
     * 10. Split String Into Unique Primes Solution
     * <p>
     * Given a string made up of integers 0 to 9, count the number of ways to split the string into prime numbers in the
     * range of 2 to 1000 inclusive, using up all the characters in the string.
     * <p>
     * Each prime number, pn, must not contain leading 0s, and 2 <= pn <= 1000.
     */

    Set<Integer> primes = new HashSet<>();

    int count = 0;
    public int splitStringIntoPrimes(String input) {
        primes.add(2);
        recursive(input, 0, 1);
        return count;
    }

    private void recursive(String input, int start, int end) {
        if (end > input.length()) {
            if (start == input.length()) count++;
            return;
        }
        int value = Integer.valueOf(input.substring(start, end));
        if (!isPrime(value)) {
            recursive(input, start, end+1);
            return;
        }
        recursive(input, start, end+1);
        recursive(input, end, end+1);
    }

    private boolean isPrime(int num) {
        if (num < 2) return false;
        if (primes.contains(num)) return true;
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) return false;
        }

        primes.add(num);
        return true;
    }

    public static void main(String[] args) {
        Question0010 q = new Question0010();
        q.splitStringIntoPrimes("31173");
    }
}
