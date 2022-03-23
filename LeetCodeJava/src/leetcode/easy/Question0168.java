package leetcode.easy;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 2022/3/3
 */
public class Question0168 {

    /**
     * 168. Excel Sheet Column Title
     */


    /**
     * Solution
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Excel Sheet Column Title.
     * Memory Usage: 41.7 MB, less than 21.82% of Java online submissions for Excel Sheet Column Title.
     */
    public String convertToTitle(int columnNumber) {
        StringBuilder sb = new StringBuilder();
        convertToTitleHelper(columnNumber, sb);
        return sb.reverse().toString();
    }

    private void convertToTitleHelper(int columnNumber, StringBuilder sb) {
        if (columnNumber > 26){
            int reminder = columnNumber%26;
            if (reminder == 0) reminder = 26;
            sb.append((char)( reminder + 'A' - 1));
            columnNumber =  (columnNumber - reminder) / 26;
            convertToTitleHelper(columnNumber,sb);
            return;
        }
        sb.append((char)(columnNumber + 'A' - 1));
    }
}
