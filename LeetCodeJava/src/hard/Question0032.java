package hard;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 2021/10/11
 */

import java.util.Stack;

/**
 * QUESTION 32
 * Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.
 */

public class Question0032 {
    public int longestValidParentheses(String s) {
        char[] charArray = s.toCharArray();
        int len = s.length();

        int max = 0;
        int tmpBegin = 0;
        int tmpEnd = 0;
        int tmpCount = 0;

        for (int i = 0; i < len; i++) {
            tmpEnd = i + 1;
            switch (charArray[i]) {
                case '(':
                    tmpCount++;
                    break;
                case ')':
                    tmpCount--;
                    break;
                default:
                    break;
            }
            if (tmpCount == 0 && tmpEnd - tmpBegin > max) {
                max = tmpEnd - tmpBegin;
            }
            if (tmpCount < 0) {
                tmpBegin = i + 1;
                tmpCount = 0;
            }
        }

        tmpBegin = len - 1;
        tmpEnd = len - 1;
        tmpCount = 0;
        for (int i = len - 1; i >= 0; i--) {
            tmpBegin = i - 1;
            switch (charArray[i]) {
                case '(':
                    tmpCount--;
                    break;
                case ')':
                    tmpCount++;
                    break;
                default:
                    break;
            }
            if (tmpCount == 0 && tmpEnd - tmpBegin > max) {
                max = tmpEnd - tmpBegin;
            }
            if (tmpCount < 0) {
                tmpEnd = i - 1;
                tmpCount = 0;
            }
        }

        return max;

    }

    public int longestValidParentheses2(String s) {
        char[] charArray = s.toCharArray();
        int len = s.length();

        int max = 0;
        int tmpLen = 0;
        int tmpCount = 0;

        for (int i = 0; i < len; i++) {
            tmpLen++;
            switch (charArray[i]) {
                case '(':
                    tmpCount++;
                    break;
                case ')':
                    tmpCount--;
                    break;
                default:
                    break;
            }
            if (tmpCount == 0 && tmpLen > max) {
                max = tmpLen;
            }
            if (tmpCount < 0) {
                tmpLen = 0;
                tmpCount = 0;
            }
        }

        tmpLen = 0;
        tmpCount = 0;
        for (int i = len - 1; i >= 0; i--) {
            tmpLen++;
            switch (charArray[i]) {
                case '(':
                    tmpCount--;
                    break;
                case ')':
                    tmpCount++;
                    break;
                default:
                    break;
            }
            if (tmpCount == 0 && tmpLen > max) {
                max = tmpLen;
            }
            if (tmpCount < 0) {
                tmpLen = 0;
                tmpCount = 0;
            }
        }

        return max;

    }

    /**
     * Copy from solution
     * @param s
     * @return
     */
    public int longestValidParentheses3(String s) {
        Stack<Integer> stack = new Stack<Integer>();
        int max=0;
        int left = -1;
        for(int j=0;j<s.length();j++){
            if(s.charAt(j)=='(') stack.push(j);
            else {
                if (stack.isEmpty()) left=j;
                else{
                    stack.pop();
                    if(stack.isEmpty()) max=Math.max(max,j-left);
                    else max=Math.max(max,j-stack.peek());
                }
            }
        }
        return max;
    }
}
