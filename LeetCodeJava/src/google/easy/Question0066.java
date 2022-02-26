package google.easy;

import javax.swing.plaf.metal.MetalIconFactory;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 2022/2/26
 */
public class Question0066 {

    /**
     * 66. Plus One
     * You are given a large integer represented as an integer array digits, where each digits[i] is the ith digit of the integer. The digits are ordered from most significant to least significant in left-to-right order. The large integer does not contain any leading 0's.
     *
     * Increment the large integer by one and return the resulting array of digits.
     */
    public int[] plusOne(int[] digits) {
        int index = digits.length - 1;
        while (index >= 0){
            digits[index] += 1;
            if ( digits[index]>= 10){
                digits[index] %= 10;
                index--;
            }else return digits;
        }
        int[] newDigits = new int[digits.length + 1];
        newDigits[0] = 1;
        for (int i = 1; i < newDigits.length; i++) {
            newDigits[i] = digits[i-1];
        }
        return newDigits;
    }
}
