package amazon.oa;

import javax.swing.*;
import javax.xml.transform.Result;
import java.lang.reflect.WildcardType;
import java.sql.Array;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 3/12/2022
 */
public class Question0023 {

    /**
     * 23. Shipment Imbalance
     * <p>
     * Amazon logistics has multiple delivery centers from which products are sent.
     * <p>
     * In one such delivery center, parcels are placed in a sequence where the i-th parcel has a weight of weight[i].
     * A shipment is constituted of a contiguous segment of parcels. The shipment imbalance of a shipment is defined as
     * the difference between the max and min weights within a shipment.
     * <p>
     * Given the arrangement of parcels, find the sum of shipment imbalance of all the shipments that can be formed
     * from the given sequence of parcels.
     * <p>
     * Input
     * - weights: an array of integers that denote the weights of parcels
     * <p>
     * Output
     * the sum of shipment imbalance
     */

    public int shipmentImbalance(int[] weights) {
        if (weights == null || weights.length < 2) return 0;
        int len = weights.length;
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());


        int left = 0;
        int right = left + 1;
        boolean towardsRight = true;
        int result = 0;
        minHeap.offer(weights[left]);
        minHeap.offer(weights[right]);

        maxHeap.offer(weights[left]);
        maxHeap.offer(weights[right]);


        while (left < len - 1) {
            result += maxHeap.peek() - minHeap.peek();
            if (towardsRight) {
                if (right == len - 1) {
                    towardsRight = !towardsRight;
                    minHeap.remove(weights[left]);
                    maxHeap.remove(weights[left]);
                    left++;
                    continue;
                }
                right++;
            } else {
                if (right == left + 1) {
                    if (left >= len - 2) break;
                    towardsRight = !towardsRight;
                    minHeap.remove(weights[left]);
                    maxHeap.offer(weights[right+1]);
                    left++;
                    right++;
                    continue;
                }
                right--;
            }

            minHeap.offer(weights[right]);
            maxHeap.offer(weights[right]);
            continue;
        }
        return result;
    }

    public static void main(String[] args) {
        Question0023 q = new Question0023();
        System.out.println(q.shipmentImbalance(new int[]{1,2,3}));
    }
}
