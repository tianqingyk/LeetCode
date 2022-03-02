package google.easy;

import javax.naming.event.NamingExceptionEvent;
import java.awt.font.NumericShaper;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 2022/3/1
 */
public class Question0118 {

    /**
     * 118. Pascal's Triangle
     */


    /**
     * Solution 1
     * Runtime: 1 ms, faster than 66.67% of Java online submissions for Pascal's Triangle.
     * Memory Usage: 42.1 MB, less than 11.85% of Java online submissions for Pascal's Triangle.
     */
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 1; i <= numRows; i++) {
            List<Integer> list =  new ArrayList<>();
            if (i == 1) {
                list.add(1);
                result.add(list);
                continue;
            }
            if (i == 2) {
                list.add(1);
                list.add(1);
                result.add(list);
                continue;
            }
            List<Integer> prev = result.get(i-2);
            for (int j = 0; j < i; j++) {
                if (j == 0 || j == i -1) list.add(1);
                else {
                    list.add(prev.get(j-1) + prev.get(j));
                }

            }
            result.add(list);
        }
        return result;
    }

}
