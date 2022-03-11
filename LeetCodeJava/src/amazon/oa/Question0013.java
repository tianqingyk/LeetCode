package amazon.oa;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 3/11/2022
 */
public class Question0013 {

    /**
     * 13. Autoscaling Policy, Utilization Check Solution
     * <p>
     * A scaling computing system checks its average utilization every second while it monitors. It implements an autoscale
     * policy to increase or reduce instances depending on the current load as described below. Once an action of increasing
     * or reducing the number of instances is performed, the system will stop monitoring for 10 seconds. During that time, the number
     * of instances does not change.
     * - If the average utilization < 25%, then an action is instantiated to reduce the number of instances by half if
     * the number of instances is greater than 1. Take the ceiling if the number is not an integer. If the number of instances
     * is 1, take no action.
     * - If 25% s average utilization s 60%, take no action.
     * - if the average utilization > 60%, then an action is instantiated to double the number of instances if the
     * doubled value does not exceed 2* 108. If the number of instances exceeds this limit upon doubling, take no
     * action.
     * <p>
     * Given an array of integers that represent the average utilization at each second, determine the number of instances
     * at the end of the time frame.
     */

    public int numberOfInstances(int instance, int[] averageUtil) {
        for (int i = 0; i < averageUtil.length; i++) {
            int average = averageUtil[i];
            if (average < 25) {
                instance = (instance + 1) / 2;
                i += 10;
                continue;
            }

            if (average > 60) {
                if (instance <= 108) {
                    instance *= 2;
                    i += 10;
                }
                continue;
            }
        }

        return instance;
    }

    public static void main(String[] args) {
        Question0013 q = new Question0013();
        System.out.println(q.numberOfInstances(2, new int[]{25, 23, 1, 2,3,4,5,6,7,8,9,10,76,80}));

    }
}
