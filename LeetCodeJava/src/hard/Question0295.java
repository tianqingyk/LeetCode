package hard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 2022/2/10
 */
public class Question0295 {

    /**
     * 295. Find Median from Data Stream
     * <p>
     * The median is the middle value in an ordered integer list. If the size of the list is even, there is no middle
     * value and the median is the mean of the two middle values.
     * For example, for arr = [2, 3, 4], the median is 3.
     * For example, for arr = [2, 3], the median is (2 + 3) / 2 = 2.5
     * Implement the MedianFinder class:
     * MedianFinder() initializes the MedianFinder object.
     * void addNum(int num) adds the integer num from the data stream to the data structure.
     * double finMedian() returns the median of all elements so far.
     * Answers within 10^-5 of the actual answer will be accepted.
     */

    List<Integer> nums = new ArrayList<>();

    public void addNum(int num) {
        int i = 0;
        List<Integer> newNums = new ArrayList<>();
        for (; i < nums.size(); i++) {
            int cur = nums.get(i);
            if (num <= cur){
                break;
            }
            newNums.add(cur);
        }

        newNums.add(num);
        newNums.addAll(nums.subList(i, nums.size()));
        this.nums = newNums;
    }

    public double findMedian() {
        int n = nums.size();
        double left = nums.get((n-1)/2);
        double right = nums.get(n/2);
        return (left + right) / 2;
    }

    public static void main(String[] args) {
        Question0295 q = new Question0295();
        q.addNum(-1);
        q.addNum(-2);
        q.addNum(-3);
        double param_2 = q.findMedian();
        System.out.println(param_2);
    }
}
