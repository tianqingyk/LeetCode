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

    /**
     * Solution 2
     * copy from solution
     */

    public String one(int num) {
        switch(num) {
            case 1: return "One";
            case 2: return "Two";
            case 3: return "Three";
            case 4: return "Four";
            case 5: return "Five";
            case 6: return "Six";
            case 7: return "Seven";
            case 8: return "Eight";
            case 9: return "Nine";
        }
        return "";
    }

    public String twoLessThan20(int num) {
        switch(num) {
            case 10: return "Ten";
            case 11: return "Eleven";
            case 12: return "Twelve";
            case 13: return "Thirteen";
            case 14: return "Fourteen";
            case 15: return "Fifteen";
            case 16: return "Sixteen";
            case 17: return "Seventeen";
            case 18: return "Eighteen";
            case 19: return "Nineteen";
        }
        return "";
    }

    public String ten(int num) {
        switch(num) {
            case 2: return "Twenty";
            case 3: return "Thirty";
            case 4: return "Forty";
            case 5: return "Fifty";
            case 6: return "Sixty";
            case 7: return "Seventy";
            case 8: return "Eighty";
            case 9: return "Ninety";
        }
        return "";
    }

    public String two(int num) {
        if (num == 0)
            return "";
        else if (num < 10)
            return one(num);
        else if (num < 20)
            return twoLessThan20(num);
        else {
            int tenner = num / 10;
            int rest = num - tenner * 10;
            if (rest != 0)
                return ten(tenner) + " " + one(rest);
            else
                return ten(tenner);
        }
    }

    public String three(int num) {
        int hundred = num / 100;
        int rest = num - hundred * 100;
        String res = "";
        if (hundred*rest != 0)
            res = one(hundred) + " Hundred " + two(rest);
        else if ((hundred == 0) && (rest != 0))
            res = two(rest);
        else if ((hundred != 0) && (rest == 0))
            res = one(hundred) + " Hundred";
        return res;
    }

    public String numberToWords2(int num) {
        if (num == 0)
            return "Zero";

        int billion = num / 1000000000;
        int million = (num - billion * 1000000000) / 1000000;
        int thousand = (num - billion * 1000000000 - million * 1000000) / 1000;
        int rest = num - billion * 1000000000 - million * 1000000 - thousand * 1000;

        String result = "";
        if (billion != 0)
            result = three(billion) + " Billion";
        if (million != 0) {
            if (! result.isEmpty())
                result += " ";
            result += three(million) + " Million";
        }
        if (thousand != 0) {
            if (! result.isEmpty())
                result += " ";
            result += three(thousand) + " Thousand";
        }
        if (rest != 0) {
            if (! result.isEmpty())
                result += " ";
            result += three(rest);
        }
        return result;
    }

}
