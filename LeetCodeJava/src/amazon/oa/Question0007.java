package amazon.oa;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.function.Function;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 3/10/2022
 */
public class Question0007 {

    /**
     * 7. Fill The Truck Maximum Units on a Trunk Solution Amazon
     * <p>
     * You are assigned to put some amount of boxes onto one truck. You are given a 2D array boxTypes,
     * Where boxTypes[i] = [numberOfBoxesi, numberOfUnitsPerBoxi]:
     * - numberOfBoxesi is the number of boxes of type i.
     * - numberOfUnitsPerBoxi is the number of units in each box of the type i.
     * <p>
     * You are also given an integer truckSize, which is the maximum number of boxes that can be put
     * on the truck. You can choose any boxes to put on the truck as long as the number of boxes dose not exceed truckSize.
     * <p>
     * Return the maximum total number of units that can be put on the truck.
     */

    public int totalMaximumNumberOfTruck(int[][] boxTypes, int truckSize) {
        Arrays.sort(boxTypes, (a, b) -> b[1] - a[1]);
        int result = 0;
        int i = 0;
        while (truckSize > 0 && i < boxTypes.length) {
            if (boxTypes[i][0] < truckSize) {
                truckSize -= boxTypes[i][0];
                result += boxTypes[i][0] * boxTypes[i][1];
                i++;
                continue;
            }
            result += truckSize * boxTypes[i][1];
            break;
        }
        return result;
    }

    public static void main(String[] args) {
        Question0007 q = new Question0007();
        q.totalMaximumNumberOfTruck(new int[][]{{1, 3}, {2,2}, {3,1}}, 4);

    }
}
