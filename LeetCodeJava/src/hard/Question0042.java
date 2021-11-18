package hard;

/**
 * @author yangke
 * @projectName LeetCodeJava
 * @date 11/11/21
 */

public class Question0042 {
    /**
     * QUESTION 42 Trapping Rain Water
     * Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it can trap after raining.
     */
    public int trap(int[] height) {
        int l = height.length;
        int[] result = new int[l];

        int maxL = 0;
        int maxLIndex = 0;
        for(int i = 0; i < l; i ++){
            int tmp = height[i];
            result[i] = tmp;
            if(maxL <= tmp) {
                for(int j = maxLIndex+1; j < i; j++){
                    result[j] = maxL;
                }
                maxL = tmp;
                maxLIndex = i;
            }
        }
        maxL = 0;
        maxLIndex = 0;
        for(int i = l-1; i >= 0; i--){
            int tmp = height[i];
            if(maxL <= tmp) {
                for(int j = maxLIndex - 1; j > i; j--){
                    result[j] = maxL;
                }
                maxL = tmp;
                maxLIndex = i;
            }
        }

        int rain = 0;
        for(int i = 0; i < l; i ++){
            rain += result[i] - height[i];
        }
        return rain;
    }
}
