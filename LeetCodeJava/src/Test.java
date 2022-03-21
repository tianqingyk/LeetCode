import com.sun.jdi.Value;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.util.*;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 3/16/2022
 */
public class Test {
    public static int reversePairs(int[] nums) {
        if(nums == null || nums.length < 2) return 0;

        int result = 0;
        for(int i = 1; i < nums.length; i++ ){

            int num = nums[i];

            if(num > Integer.MAX_VALUE/2 + 1) ;
            else if(num < Integer.MIN_VALUE/2) result += i;
            else{
                int r = Arrays.binarySearch(nums, 0, i, 2 * num);
                if(r < 0){
                    result += i+r+1;
                }else{
                    result += r;
                }
            }

            Arrays.sort(nums, 0, i+1);
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(reversePairs(new int[]{1, 3, 2, 3, 1}));
    }
}
