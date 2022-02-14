package hard;

import java.util.HashSet;
import java.util.Set;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 2022/2/12
 */
public class Question0302 {

    /**
     * 302. Smallest Rectangle Enclosing Black Pixels
     * <p>
     * You are given an m x n binary matrix image where 0 represents a white pixel and 1 represents a black pixel.
     * The black pixels are connected(i.e., there is only one black region.) Pixels are connected horizontally and vertically.
     * Given two integers x and y that represents the location of one of the black pixels, return the area of the
     * smallest(axis-aligned) rectangle that encloses all black pixels.
     * <p>
     * You must write an algorithm with less than O(mn) runtime complexity.
     */

    /**
     * Solution 1
     * Runtime: 44 ms, faster than 5.22% of Java online submissions for Smallest Rectangle Enclosing Black Pixels.
     * Memory Usage: 42.9 MB, less than 70.06% of Java online submissions for Smallest Rectangle Enclosing Black Pixels.
     */
    int maxX = Integer.MIN_VALUE;
    int maxY = Integer.MIN_VALUE;
    int minX = Integer.MAX_VALUE;
    int minY = Integer.MAX_VALUE;

    Set<String> cache = new HashSet<>();
    int m;
    int n;
    char[][] img;

    public void findBoundary(int x, int y) {
        if (x >= m || y >= n || x < 0 || y < 0) {
            return;
        }
        if (img[x][y] != '1') {
            return;
        }
        if (cache.contains(getPosition(x, y))) {
            return;
        }
        cache.add(getPosition(x, y));
        maxX = Math.max(x, maxX);
        maxY = Math.max(y, maxY);

        minX = Math.min(x, minX);
        minY = Math.min(y, minY);
        // UP
        findBoundary(x + 1, y);
        // Left
        findBoundary(x, y - 1);
        // Down
        findBoundary(x - 1, y);
        //Right
        findBoundary(x, y + 1);
    }

    public int minArea(char[][] image, int x, int y) {
        img = image;
        m = img.length;
        n = img[0].length;
        findBoundary(x, y);

        return (maxX - minX + 1) * (maxY - minY + 1);
    }

    private String getPosition(int x, int y) {
        return x + " " + y;
    }

    public static void main(String[] args) {
        Question0302 q = new Question0302();
        int reuslt = q.minArea(new char[][]{
                {'1', '1', '1', '1', '1', '1', '1', '1', '0', '1'},
                {'1', '0', '0', '0', '0', '0', '0', '1', '0', '1'},
                {'1', '0', '1', '1', '1', '1', '0', '1', '0', '1'},
                {'1', '0', '1', '0', '0', '1', '0', '1', '0', '1'},
                {'1', '0', '1', '0', '1', '1', '0', '1', '0', '1'},
                {'1', '0', '1', '0', '0', '0', '0', '1', '0', '1'},
                {'1', '0', '1', '1', '1', '1', '1', '1', '0', '1'},
                {'1', '0', '0', '0', '0', '0', '0', '0', '0', '1'},
                {'1', '1', '1', '1', '1', '1', '1', '1', '1', '1'}},
                4,
                4);
        System.out.println(reuslt);
    }

}
