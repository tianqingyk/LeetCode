package hard;

/**
 * @author yangke
 * @projectName LeetCodeJava
 * @date 12/2/21
 */
public class Question0065 {

    /**
     * QUESTION 65 valid number
     * A valid number can be split up into these components (in order):
     * <p>
     * A decimal number or an integer.
     * (Optional) An 'e' or 'E', followed by an integer.
     * A decimal number can be split up into these components (in order):
     * <p>
     * (Optional) A sign character (either '+' or '-').
     * One of the following formats:
     * One or more digits, followed by a dot '.'.
     * One or more digits, followed by a dot '.', followed by one or more digits.
     * A dot '.', followed by one or more digits.
     * An integer can be split up into these components (in order):
     * <p>
     * (Optional) A sign character (either '+' or '-').
     * One or more digits.
     * For example, all the following are valid numbers: ["2", "0089", "-0.1", "+3.14", "4.", "-.9", "2e10", "-90E3", "3e+7", "+6e-1", "53.5e93", "-123.456e789"], while the following are not valid numbers: ["abc", "1a", "1e", "e3", "99e2.5", "--6", "-+3", "95a54e53"].
     * <p>
     * Given a string s, return true if s is a valid number.
     */
    public boolean isNumber(String s) {
        char[] sArray = s.toCharArray();
        if (isInteger(sArray, 0, sArray.length)
                || isDecimal(sArray, 0, sArray.length)
                || isENumber(sArray, 0, sArray.length)){
            return true;
        }
        return false;
    }

    private boolean isENumber(char[] sArray, int start, int end) {
        for (int i = start; i < end; i++){
            char c = sArray[i];
            if (c == 'e' || c == 'E'){
                return (isInteger(sArray, 0, i) || isDecimal(sArray, 0, i)) && isInteger(sArray, i+1, end);
            }
        }
        return false;
    }

    private boolean isDecimal(char[] sArray, int start, int end){
        for (int i = start; i < end; i++){
            char c = sArray[i];
            if (c == '.'){
                if (i == 0 || ((sArray[start] == '-' || sArray[start] == '+') && i == 1)){
                    return isDigital(sArray, i+1, end, false);
                }
                return isInteger(sArray, start, i) && isDigital(sArray, i+1, end, true);
            }
        }
        return false;
    }
    private boolean isInteger(char[] sArray, int start, int end){
        if (start >= end){
            return false;
        }

        boolean isInteger = false;

        for (int i = start; i < end; i++){
            char c = sArray[i];
            if (c == '-' || c == '+') {
                if (i == start) {
                    continue;
                } else {
                    return false;
                }
            }

            if (isInteger(c)){
                isInteger = true;
                continue;
            }
            return false;
        }
        return isInteger;
    }

    private boolean isDigital(char[] sArray, int start, int end, boolean canNull) {
        boolean isDigital = canNull;
        for (int i = start; i < end; i++) {
           char c = sArray[i];
           if (isInteger(c)){
               isDigital = true;
               continue;
           }
           return false;
        }
        return isDigital;
    }

    private boolean isInteger(char c) {
        if (c >= '0' && c <= '9') {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Question0065 q = new Question0065();
        String[] strs = { "+.8", "3.", "2", "0089", "-0.1", "+3.14", "4.", "-.9", "2e10", "-90E3", "3e+7", "+6e-1", "53.5e93", "-123.456e789"};
        String[] strs2 = {"+.", ".", "abc", "1a", "1e", "e3", "99e2.5", "--6", "-+3", "95a54e53"};
        for (String str  : strs) {
            System.out.print(q.isNumber(str));
        }
        System.out.println();
        for (String str  : strs2) {
            System.out.print(q.isNumber(str));
        }
    }

}
