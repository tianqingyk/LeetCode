package hard;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 2021/12/27
 */
public class Question0273 {

    /**
     * 273. Integer to English Words
     *
     * Convert a non-negative integer num to its English words representation
     */

    String[] tmb = new String[]{"Thousand", "Million", "Billion"};
    String hundred = "Hundred";
    String[] nums = new String[]{"One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
    String[] teens = new String[]{"Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    String[] ties = new String[]{"Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};

    /**
     * Solution 1
     * Runtime: 1 ms, faster than 100.00% of Java online submissions for Integer to English Words.
     * Memory Usage: 40.2 MB, less than 33.55% of Java online submissions for Integer to English Words.
     * @param num
     * @return
     */
    public String numberToWords(int num) {
        if (num <= 0) {
            return "Zero";
        }
        StringBuilder sb = new StringBuilder();
        if (num < 1000) {
            int h = num / 100;
            num = num % 100;
            int ty = num / 10;
            num = num % 10;
            if (h > 0) {
                sb.append(nums[h - 1]).append(" ").append(hundred).append(" ");
            }
            if (ty > 0) {
                if (ty > 1) {
                    sb.append(ties[ty - 1]).append(" ");
                }
                if (ty == 1) {
                    if (num <= 0) {
                        sb.append(ties[ty - 1]).append(" ");
                    } else {
                        sb.append(teens[num - 1]);
                        return sb.toString();
                    }
                }
            }

            if (num > 0) {
                sb.append(nums[num - 1]).append(" ");
            }

            sb.deleteCharAt(sb.length() - 1);
            return sb.toString();
        }

        List<String> tmp = new ArrayList<>();

        int count = -1;
        while (num >= 1){
            int remain = num%1000;
            num /= 1000;
            if (remain > 0) {
                StringBuilder newSb = new StringBuilder();
                newSb.append(numberToWords(remain)).append(" ");
                if (count >= 0){
                    newSb.append(tmb[count]).append(" ");
                }
                tmp.add(newSb.toString());
            }
            count ++;
        }

        for (int i = tmp.size() - 1; i >= 0 ; i--) {
            sb.append(tmp.get(i));
        }
        sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }
}
