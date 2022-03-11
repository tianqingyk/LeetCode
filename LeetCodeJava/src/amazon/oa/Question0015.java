package amazon.oa;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 3/11/2022
 */
public class Question0015 {

    /**
     * 15. Merge Two Sorted Lists Solution
     *
     * Merge two sorted linked lists and return it as a new sorted list. The new list should be made by splicing together
     * the nodes of the given two lists.
     */

    public int[] mergeTwoSorted(int[] l1, int[] l2) {
        int i1 = 0, i2 = 0;
        int[] result = new int[l1.length + l2.length];

        for (int i = 0; i < result.length; i++) {
            if (i1 >= l1.length){
                result[i] = l2[i2];
                i2++;
                continue;
            }

            if (i2 >= l2.length) {
                result[i] = l1[i1];
                i1++;
                continue;
            }

            if (l1[i1] < l2[i2]) {
                result[i] = l1[i1];
                i1++;
                continue;
            }

            result[i] = l2[i2];
            i2++;
        }

        return result;
    }

    public static void main(String[] args) {
        Question0015 q = new Question0015();
        System.out.println(q.mergeTwoSorted(new int[]{}, new int[]{}));
    }
}
