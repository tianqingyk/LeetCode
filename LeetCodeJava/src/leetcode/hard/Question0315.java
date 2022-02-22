package leetcode.hard;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 2022/2/12
 */
public class Question0315 {

    /**
     * 315. Count of Smaller Numbers After Self
     * <p>
     * You are given an integer array nums and you have to return a new counts array. The counts array has the property
     * where counts[i] is the number of smaller elements to the right of nums[i].
     */

    /**
     * Solution 1
     * Time limit exceeded
     *
     * @param nums
     * @return
     */
    public List<Integer> countSmaller(int[] nums) {
        int[] counts = new int[nums.length];
        List<Integer> list = new LinkedList<>();
        list.add(nums[nums.length - 1]);

        for (int i = nums.length - 2; i >= 0; i--) {
            int num = nums[i];
            int count = -1;
            for (int j = 0; j < list.size(); j++) {
                if (num <= list.get(j)) {
                    list.add(j, num);
                    count = j;
                    break;
                }
            }
            if (count < 0) {
                count = list.size();
                list.add(num);
            }

            counts[i] = count;
        }

        return Arrays.stream(counts).boxed().collect(Collectors.toList());
    }

    /**
     * Solution 2 Copy from solution
     * Segment Tree
     */

    public List<Integer> countSmaller2(int[] nums) {
        int offset = 10000; // offset negative to non-negative
        int size = 2 * 10000 + 1;// total possible values in nums.
        int[] tree = new int[size * 2];
        List<Integer> result = new ArrayList<>();

        for (int i = nums.length - 1; i >= 0; i--) {
            int smaller_count = query(0, nums[i] + offset, tree, size);
            result.add(smaller_count);
            update(nums[i] + offset, 1, tree, size);
        }
        Collections.reverse(result);
        return result;
    }

    // implement segment tree
    private void update(int index, int value, int[] tree, int size) {
        index += size; // shift the index to the leaf
        // update from leaf to root
        tree[index] += value;
        while (index > 1) {
            index /= 2;
            tree[index] = tree[index * 2] + tree[index * 2 + 1];
        }
    }

    private int query(int left, int right, int[] tree, int size) {
        // return sum of [left, right)
        int result = 0;
        left += size; // shift the index to the leaf
        right += size;
        while (left < right) {
            // if left is a right node
            // bring the value and move to parent's right node
            if (left % 2 == 1) {
                result += tree[left];
                left++;
            }
            // else directly move to parent
            left /= 2;
            // if right is a right node
            // bring the value of the left node and move to parent
            if (right % 2 == 1) {
                right--;
                result += tree[right];
            }
            // else directly move to parent
            right /= 2;
        }
        return result;
    }

    /**
     * Solution 3 Copy from solution
     * Binary Indexed Tree(Fenwick Tree)
     * Runtime: 42 ms, faster than 92.88% of Java online submissions for Count of Smaller Numbers After Self.
     * Memory Usage: 115.7 MB, less than 60.79% of Java online submissions for Count of Smaller Numbers After Self.
     */

    public List<Integer> countSmaller3(int[] nums) {
        int offset = 10000;
        int size = 2 * offset + 1;
        int[] tree = new int[size + 1];
        List<Integer> result = new ArrayList<>();

        for (int i = nums.length - 1; i >= 0 ; i--) {
            int smaller_count = query3(nums[i] + offset, tree);
            result.add(smaller_count);
            update3(nums[i] + offset + 1, 1, tree, size); // index start from 1!!!!!!!!!!!!!
        }

        Collections.reverse(result);
        return result;
    }

    // implement Binary Index Tree
    private void update3(int index, int value, int[] tree, int size) {
        while (index < size){
            tree[index] += value;
            index += index & - index;
        }
    }

    private int query3(int index, int[] tree) {
       int count = 0;
       count += tree[index];
       while (index > 1) {
           index -= index & - index;
           count += tree[index];
       }

       return count;
    }

}
