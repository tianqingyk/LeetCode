package amazon.oa;

import java.sql.Array;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 3/12/2022
 */
public class Question0023 {

    /**
     * 23. Shipment Imbalance
     *
     * Amazon logistics has multiple delivery centers from which products are sent.
     *
     * In one such delivery center, parcels are placed in a sequence where the i-th parcel has a weight of weight[i].
     * A shipment is constituted of a contiguous segment of parcels. The shipment imbalance of a shipment is defined as
     * the difference between the max and min weights within a shipment.
     *
     * Given the arrangement of parcels, find the sum of shipment imbalance of all the shipments that can be formed
     * from the given sequence of parcels.
     *
     * Input
     *  - weights: an array of integers that denote the weights of parcels
     *
     *  Output
     *  the sum of shipment imbalance
     */

    public int shipmentImbalance(int[] weights) {
        Map<Integer,Integer> map = new HashMap<>();
        map.entrySet();

        List<String> list = new ArrayList<String>();
        list.add("00");
        list.add("11");
        list.sort(String::compareTo);
        int result = 0;
        for (int i = 0; i < weights.length - 1; i++) {
            int max = weights[i];
            int min = weights[i];
            for (int j = i+1; j < weights.length; j++) {
                max = Math.max(max, weights[j]);
                min = Math.min(min, weights[j]);
                result += max - min;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        Set<String> list = new HashSet<String>();
        list.add("11");
        list.add("00");

        list.stream().sorted().collect(Collectors.toList());
        list.add("22");
    }
}
