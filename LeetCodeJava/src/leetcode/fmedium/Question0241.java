package leetcode.fmedium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 3/9/2022
 */
public class Question0241 {

    /**
     * 241. Different Ways to Add Parentheses
     */

    /**
     * Solution
     * Runtime: 5 ms, faster than 41.12% of Java online submissions for Different Ways to Add Parentheses.
     * Memory Usage: 42.7 MB, less than 34.68% of Java online submissions for Different Ways to Add Parentheses.
     */
    public static Map<Character, BiFunction<Integer, Integer, Integer>> operatorsMap = new HashMap<>();
    static{
        operatorsMap.put('+', (i1, i2) -> i1 + i2);
        operatorsMap.put('-', (i1, i2) -> i1 - i2);
        operatorsMap.put('*', (i1, i2) -> i1 * i2);
    }

    Map<String, List<Integer>> dp = new HashMap<>();
    public List<Integer> diffWaysToCompute(String expression) {
        if(dp.containsKey(expression)) return dp.get(expression);

        List<Integer> result = new ArrayList<>();
        for(int i = 0; i < expression.length(); i++) {
            if(operatorsMap.containsKey(expression.charAt(i))){
                List<Integer> leftList = diffWaysToCompute(expression.substring(0,i));
                BiFunction<Integer, Integer, Integer> op = operatorsMap.get(expression.charAt(i));
                List<Integer> rightList = diffWaysToCompute(expression.substring(i+1,expression.length()));
                for(int l : leftList){
                    for(int r : rightList) {
                        result.add(op.apply(l,r));
                    }
                }
            }
        }
        if(result.isEmpty()) result.add(Integer.valueOf(expression));

        dp.put(expression, result);
        return result;
    }
}
