package leetcode.hard;

import java.util.*;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 2022/2/12
 */
public class Question0301 {

    /**
     * 301. Remove Invalid Parentheses
     * <p>
     * Given a string s that contains parentheses and letters, remove the minimum number of invalid parentheses to make
     * the input string valid.
     * <p>
     * Return all the possible results. You may return the answer in any order.
     */

    /**
     * Solution 1
     * Backtracking
     * Copy from solution
     */

    private Set<String> validExpressions = new HashSet<>();
    private int minimumRemoved;

    private void reset() {
        this.validExpressions.clear();
        this.minimumRemoved = Integer.MAX_VALUE;
    }

    private void recurse(String s, int index, int leftCount, int rightCount, StringBuilder expression, int removedCount) {
        // If we have reached the end of string.
        if (index == s.length()) {
            // If the current expression is valid
            if (leftCount == rightCount) {

                //If the current count of removed parentheses is the current minimum count
                if (removedCount <= this.minimumRemoved) {
                    // Convert StringBuilder to a string. This is an expensive operation.
                    // So we only perform this when needed.
                    String possibleAnswer = expression.toString();

                    // If the current count beats the overall minimum we have till now
                    if (removedCount < this.minimumRemoved) {
                        this.validExpressions.clear();
                        this.minimumRemoved = removedCount;
                    }
                    this.validExpressions.add(possibleAnswer);
                }
            }
        } else {

            char currentCharacter = s.charAt(index);
            int length = expression.length();

            //If the current character is neither an opening bracket nor a clossing one,
            // simply recurse further by adding it to the expression StringBuilder
            if (currentCharacter != '(' && currentCharacter != ')') {
                expression.append(currentCharacter);
                this.recurse(s, index + 1, leftCount, rightCount, expression, removedCount);
                expression.deleteCharAt(length);
            } else {
                // Recursion where we delete the current character and move forward
                this.recurse(s, index + 1, leftCount, rightCount, expression, removedCount + 1);
                expression.append(currentCharacter);

                //If it's an opening parenthesis, consider it and recurse
                if (currentCharacter == '(') {
                    this.recurse(s, index + 1, leftCount + 1, rightCount, expression, removedCount);
                } else if (rightCount < leftCount) {
                    // For a closing parenthesis, only recurse if fight < left
                    this.recurse(s, index + 1, leftCount, rightCount + 1, expression, removedCount);
                }

                // Undoing the append operation for other recursions
                expression.deleteCharAt(length);
            }
        }
    }

    public List<String> removeInvalidParentheses(String s) {
        this.reset();
        this.recurse(s, 0, 0, 0, new StringBuilder(), 0);
        return new ArrayList<>(this.validExpressions);
    }

    /**
     * Solution 2
     * Limited Backtracking
     * Copy from solution
     */

    private Set<String> validExpressions2 = new HashSet<String>();

    private void recurse2(String s, int index, int leftCount, int rightCount, int leftRem, int rightRem, StringBuilder expression) {
        //If we reached the end of the string, just check if the resulting expression is valid or not and also if we
        // have removed the total number of left and right parentheses that we should have removed
        if (index == s.length()) {
            if (leftRem == 0 && rightRem == 0) {
                this.validExpressions.add(expression.toString());
            }
            return;
        }

        char character = s.charAt(index);
        int length = expression.length();

        // The discard case. Note that here we have our pruning condition.
        // We don't recurse if the remaining count for that parenthesis is == 0;
        if ((character == '(' && leftRem > 0) || (character == ')' && rightRem > 0)) {
            this.recurse2(s, index + 1, leftCount, rightCount,
                    leftRem - (character == '(' ? 1 : 0),
                    rightRem - (character == ')' ? 1 : 0), expression);
        }

        expression.append(character);

        //Simply recurse one step further if the current character is not a parenthesis.
        if (character != '(' && character != ')') {
            this.recurse2(s, index + 1, leftCount, rightCount, leftRem, rightRem, expression);
        } else if (character == '('){
            // Consider an opening bracket.
            this.recurse2(s, index+1, leftCount + 1, rightCount, leftRem, rightRem, expression);
        } else if (rightCount < leftCount) {
            // Consider a closing bracket.
            this.recurse2(s, index+1, leftCount, rightCount + 1, leftRem, rightRem, expression);
        }

        // Delete for backtracking
        expression.deleteCharAt(length);
    }

    public List<String> removeInvalidParentheses2(String s) {

        int left = 0, right = 0;

        // First, we find out the number of misplaced left and right parentheses.
        for (int i = 0; i < s.length(); i++) {

            // Simply record the left one.
            if (s.charAt(i) == '(') {
                left++;
            } else if (s.charAt(i) == ')') {
                // If we don't have a matching left, then this is a misplaced right, record it.
                right = left == 0 ? right + 1 : right;

                // Decrement count of left parentheses because we have found a right
                // which CAN be a matching one for a left.
                left = left > 0 ? left - 1 : left;
            }
        }

        this.recurse2(s, 0, 0, 0, left, right, new StringBuilder());
        return new ArrayList<String>(this.validExpressions);
    }


}
