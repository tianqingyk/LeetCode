package amazon.oa;

import java.util.*;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 2022/2/25
 */
public class Question0001 {
    /**
     * Let's have a string: abbbcacbcdce
     *
     * For substring abbb, you have most frequent letter b -> 3 and least frequent letter a -> 1.
     * So the deviation is = most frequent - least frequent = 3 - 1 = 2. You need to look at all the substrings and find the max deviation.
     *
     * Here substring cacbcdc has the max deviation.
     * Frequency is like below:
     * c -> 4, a ->1, b->1, d->1.
     * So max freq - min freq = 4 - 1 = 3.
     *
     * Among all substrings deviation, this is the max. So need to return it.
     *
     * String length is 10^4. So you can't check each substring.
     *
     * Could anyone please help to get the solving technique of this problem?
     */

    public static int getMaxDeviation(String s) {
        int len = s.length();
        int[][] dp  = new int[len][26];
        for (int i = 0; i < len ; i++) {
            if (i != 0 ){
                dp[i] = Arrays.copyOf(dp[i-1], 26);
            }
            dp[i][s.charAt(i) - 'a'] += 1;
        }

        int maxD = Integer.MIN_VALUE;
        for (int i = 0; i < len; i++) {
            for (int j = i+1; j < len; j++) {
                if (i == 0) maxD = Math.max(maxD, findMaxDeviationArray(dp[j]));
                else maxD = Math.max(maxD, findMaxDeviationTwoArray(dp[j], dp[i]));
            }
        }
        return maxD;
    }

    public static int getMaxDeviation2(String s) {
        int len = s.length();
        Map<Character, List<Integer>> cache = new HashMap<>();
        int[][] dp  = new int[len][26];
        for (int i = 0; i < len ; i++) {
            if (i != 0 ){
                dp[i] = Arrays.copyOf(dp[i-1], 26);
            }
            dp[i][s.charAt(i) - 'a'] += 1;
            cache.compute(s.charAt(i), (k,v) -> v == null ? new ArrayList<>(): v).add(i);
        }

        boolean[] bool = new boolean[26];
        int maxD = Integer.MIN_VALUE;
        while (true){

            // Find max number of letters
            int maxIndex = -1;
            for (int i = 0; i < 26; i ++){
                if (bool[i] == true){
                    continue;
                }
                if (maxIndex == -1) {
                    maxIndex = i;
                    continue;
                }

                if (dp[len-1][i] > dp[len-1][maxIndex]){
                    maxIndex = i;
                }

            }
            if (maxIndex == -1) break;
            bool[maxIndex] = true;


            int maxNum = dp[len-1][maxIndex];
            if (maxD >= maxNum - 1) break;

            char key = (char) (maxIndex + 'a');
            List<Integer> list = cache.get(key);// 1 4 5 6 9 10

            for (int i = 0 ; i < maxNum; i++) {
                int left = 0;
                int right = maxNum -1;

                for (int j = 0; j <= i; j++) {
                    left = left + j;
                    right = right - (i - j);
                    if (left >= right) break;
                    if (left == 0) maxD = Math.max(maxD, findMaxDeviationArray(dp[list.get(right)]));
                    else maxD = Math.max(maxD, findMaxDeviationTwoArray(dp[list.get(right)], dp[list.get(left-1)]));
                    if (maxD >= maxNum - i - 1) break;
                }
                if (maxD >= maxNum - i - 1) break;
            }
        }
        return maxD;
    }

    public static int findMaxDeviationArray(int[] array) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < array.length; i++) {
            if (array[i] > 0 ) {
                max = Math.max(max, array[i]);
                min = Math.min(min, array[i]);
            }
        }
        return max- min;
    }

    public static int findMaxDeviationTwoArray(int[] array1, int[] array2) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < array1.length; i++) {
            int v = array1[i] - array2[i];
            if (v > 0 ){
                max = Math.max(max, v);
                min = Math.min(min, v);
            }

        }
        return max- min;
    }


    public static void main(String[] args) {
        String s = "abcdefghijklmna";

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            sb.append(s);
        }
        s = sb.toString();

        long cur = System.currentTimeMillis();
        System.out.println(getMaxDeviation(s));
        long end1 = System.currentTimeMillis();
        System.out.println(getMaxDeviation2(s));
        long end2 = System.currentTimeMillis();

        System.out.println(end1-cur);
        System.out.println(end2 - end1);
    }
}
