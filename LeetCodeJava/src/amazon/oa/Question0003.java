package amazon.oa;

import java.util.Arrays;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 3/10/2022
 */
public class Question0003 {

    /**
     * Given an array containing only 0 and 1 as its elements. We have to sort the array in such a manner that all the ones are grouped together and all the zeros are grouped together. The group of ones can be either at the start of the array or at the end of the array. The constraint while sorting is that every one/zero can be swapped only with its adjacent zero/one. Find the minimum number of moves to sort the array as per the description.
     * Example:
     * input array ={0,1,0,1}
     * Final array = {0,0,1,1}
     * Minimum moves = 1 (1 at index 1 is swapped with 0 at index 2)
     * <p>
     * input array = { 1101}
     * Final array - {1110}
     * Minimum moves = 1 {1 at index 2 is swapped with index 3}
     */

    int result = Integer.MAX_VALUE;

    public int minimumMoves(int[] array) {
        recursive(Arrays.copyOf(array, array.length), 1, 0, 0, 0);
        recursive(Arrays.copyOf(array, array.length), 0, 1, 0, 0);
        return result;
    }

    private void recursive(int[] array, int left, int right, int index, int count) {
        if (count >= result) return;

        if (index > array.length - 1){
            result = Math.min(count, result);
            return;
        }

        if (array[index] == left || index == array.length - 1) {
            recursive(array, left, right, index + 1, count);
            return;
        }

        if (array[index + 1] == left) {
            array[index] = left;
            array[index + 1] = right;
            count++;

            if (index == 0 || array[index - 1] == left) recursive(array, left, right, index + 1, count);
            else recursive(array, left, right, index - 1, count);
        } else recursive(array, left, right, index + 1, count);
    }

    public static void main(String[] args) {
        Question0003 q = new Question0003();
        System.out.println(q.minimumMoves(new int[]{0,1,0,1}));
        System.out.println(q.minimumMoves(new int[]{1,1,0,1}));
        System.out.println(q.minimumMoves(new int[]{0,0,1,0, 0,0}));
    }
}
