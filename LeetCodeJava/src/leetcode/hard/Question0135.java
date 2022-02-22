package leetcode.hard;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 2021/12/27
 */
public class Question0135 {

    /**
     * 135. Candy
     * There are n children standing in a line. Each child is assigned a rating value given in the integer array ratings.
     *
     * You are giving candies to these children subjected to the following requirements:
     *
     * Each child must have at least one candy.
     * Children with a higher rating get more candies than their neighbors.
     * Return the minimum number of candies you need to have to distribute the candies to the children.
     */

    /**
     * Solution 1
     * Runtime: 6 ms, faster than 21.29% of Java online submissions for Candy.
     * Memory Usage: 48.8 MB, less than 18.67% of Java online submissions for Candy.
     * @param ratings
     * @return
     */
    public int candy(int[] ratings) {
        int l = ratings.length;
        int[] candies = new int[l];

        for(int i = 0; i < l; i++){
            if(i <= 0 ){
                candies[i] = 1;
                continue;
            }
            if(ratings[i] > ratings[i-1]){
                candies[i] = candies[i-1] + 1;
            }else{
                candies[i] = 1;
            }
        }

        for(int i = l-1; i >= 1; i--){
            if(ratings[i] < ratings[i-1] && candies[i] >= candies[i-1]){
                candies[i-1] = candies[i] + 1;
            }
        }

        int sum = 0;
        for(int i = 0; i < l; i++){
            sum += candies[i];
        }

        return sum;
    }
}
