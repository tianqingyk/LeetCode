package amazon.oa;

import java.lang.annotation.ElementType;
import java.util.*;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 3/13/2022
 */
public class Question0029 {

    /**
     * 29. Count Maximum Teams
     *
     * Amazon is hosting a team hackathon.
     *      1.Each team will have exactly teamSize developers.
     *      2.A developer's skill level is denoted by skill[i].
     *      3.The difference between the maximum and minimum skill levels within a team cannot exceed a threshold, maxDiff.
     *      Determine the maximum number of teams that can be formed from the contestants.
     */


    public int countMaximumTeams(int[] skill, int teamSize, int maxDiff) {
        int count = 0;
        Arrays.sort(skill);

        int left = 0;
        int right = teamSize -1;
        while (right < skill.length){
            if (skill[right] - skill[left] <= maxDiff) {
                count++;
                left = right+1;
                right = right+teamSize;
            }else{
                left++;
                right++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        Question0029 q = new Question0029();
        System.out.println(q.countMaximumTeams(new int[]{4,4,3,1,6,5}, 3, 2));

    }
}
