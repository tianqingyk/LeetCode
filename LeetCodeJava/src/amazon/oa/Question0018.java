package amazon.oa;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 3/11/2022
 */
public class Question0018 {

    /**
     * 18. Reorder Data in Log Files
     *
     * You are given an array of logs. Each log is a space-delimited string of words, where the first word is the identifier.
     *
     * There are two types of logs:
     *      - Letter-logs: All words(except the identifier) consist of lowercase English letters.
     *      - Digit-logs: All words(except the identifier) consist of digits.
     *
     * Reorder these logs so that:
     *      1. The letter-logs come before all digit-logs.
     *      2. The letter-logs are sorted lexicographically by their contents. If their contents are the same, then sort
     *      them lexicographically by their identifiers.
     *      3. The digit-logs maintain their relative ordering.
     *
     * Return the final order of the logs.
     */

    public String[] reoderDataLog(String[] logs) {
        Comparator<String> cmp = (log1, log2) -> {
            String[] strs1 = log1.split(" ", 2);
            String[] strs2 = log2.split(" ", 2);
            boolean isDigit1 = Character.isDigit(strs1[1].charAt(0));
            boolean isDigit2 = Character.isDigit(strs2[1].charAt(0));
            if (isDigit1 && isDigit2) return 0;
            if (isDigit1) return 1;
            if (isDigit2) return -1;

            int rt = strs1[1].compareTo(strs2[1]);
            if (rt == 0) rt = strs1[0].compareTo(strs2[0]);
            return rt;
        };
        Arrays.sort(logs, cmp);
        return logs;
    }

    public static void main(String[] args) {
        Question0018 q = new Question0018();
        String[] result2 = q.reoderDataLog(new String[]{"a1 9 2 3 1", "g1 act car", "zo4 4 7", "ab1 off key dog", "a8 act zoo"});
        Arrays.stream(result2).forEach(System.out::println);
    }

}
