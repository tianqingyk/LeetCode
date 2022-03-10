package amazon.oa;

import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 3/10/2022
 */
public class Question0004 {

    /**
     * 4. Robot Bounded in Circle Solution
     * On an infinite plane, a robot initially stands at (0,0) and faces north. The root can receive one of three instructions:
     * -'G': go straight 1 unit;
     * -'L': turn 90 degrees to the left;
     * -'R': turn 90 degrees to the right;
     * <p>
     * Example 1:
     * input: instructions = 'GGLLGG'
     * Output: true
     * <p>
     * Example 2:
     * input: instructions = "GG"
     * Output: false
     */

    int[][] directions = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public boolean robotRunCircle(String instructions) {
        int x = 0, y = 0;
        int direction = 0;
        for (int i = 0; i < instructions.length(); i++) {
            switch (instructions.charAt(i)){
                case 'G': {
                    x += directions[direction][0];
                    y += directions[direction][1];
                    break;
                }
                case 'R' : {
                    direction = (direction + 1)%4;
                    break;
                }
                case 'L' :{
                    direction = (direction - 1)%4;
                    break;
                }
            }
        }
        return (direction != 0 || (x == 0 && y == 0));
    }
}
