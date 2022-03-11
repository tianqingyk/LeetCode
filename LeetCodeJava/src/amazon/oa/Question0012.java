package amazon.oa;

import java.util.Arrays;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 3/11/2022
 */
public class Question0012 {

    /**
     * 12. Minimum Difficulty of a Job Schedule Solution
     * <p>
     * You want to schedule a list of jobs in d days. Jobs are dependent(i.e To work on the i-th job, you have to finish
     * all the jobs j where 0 <= j < i).
     * <p>
     * You have to finish at least one task every day. The difficulty of a job schedule is the sum of difficulties of each
     * day of the d days. The difficulty of a day is the maximum difficulty of a job done in that day.
     * <p>
     * Given an array of integers jobDifficulty and an integer d. The difficulty of the i-th job is jobDifficulty[i].
     */

    int minimumDf;
    public int minimumDifficulty(int[] jobDifficulty, int d) {
        minimumDf = Integer.MAX_VALUE;
        if (d > jobDifficulty.length) return -1;
        recursive(jobDifficulty, 0 , 1, d, 0);
        return minimumDf;
    }

    private void recursive(int[] jobDifficulty, int start, int end, int d, int result) {
        if (d == 0 && start == jobDifficulty.length) {
            minimumDf = Math.min(minimumDf, result);
            return;
        }

        if (start == jobDifficulty.length || end == jobDifficulty.length + 1 || d == 0) return;

        recursive(jobDifficulty, start, end+1, d, result);

        int max = Integer.MIN_VALUE;
        for (int i = start; i < end; i++) {
            max = Math.max(max, jobDifficulty[i]);
        }
        result += max;
        recursive(jobDifficulty, end, end+1, d-1, result);
    }

    public static void main(String[] args) {
        Question0012 q = new Question0012();
        System.out.println(q.minimumDifficulty(new int[]{6, 5, 4, 3, 2, 1}, 2));
        System.out.println(q.minimumDifficulty(new int[]{9, 9, 9}, 4));
        System.out.println(q.minimumDifficulty(new int[]{1, 1, 1}, 3));
        System.out.println(q.minimumDifficulty(new int[]{7, 1, 7, 1, 7, 1}, 3));
        System.out.println(q.minimumDifficulty(new int[]{11, 111, 22, 222, 33, 333, 44, 444}, 6));
    }
}
