package leetcode.hard;

import java.util.*;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 2021/12/27
 */
public class Question0149 {

    /**
     * 149. Max Points on a Line
     * Given an array of points where points[i] = [xi, yi] represents a point on the X-Y plane, return the maximum number of points that lie on the same straight line.
     */

    /**
     * Solution 1
     * Runtime: 31 ms, faster than 13.13% of Java online submissions for Max Points on a Line.
     * Memory Usage: 43.5 MB, less than 6.72% of Java online submissions for Max Points on a Line.
     * @param points
     * @return
     */
    public int maxPoints(int[][] points) {
        int l = points.length;
        if (l <= 1){
            return 1;
        }
        Map<Double, Set<int[]>> memoMap = new HashMap<>();
        Map<Double, Set<int[]>> xMemoMap = new HashMap<>();
        Map<Double, Set<int[]>> yMemoMap = new HashMap<>();


        for (int i = 0; i < l; i++) {
            for (int j = i+1; j < l ; j++) {
                int[] point1 = points[i];
                int[] point2 = points[j];
                Double key = null;
                Map<Double, Set<int[]>> map = memoMap;
                if (point1[0] == point2[0]){
                    key = (double)point1[0];
                    map = xMemoMap;
                }

                if (point1[1] == point2[1]){
                    key = (double)point1[1];
                    map = yMemoMap;
                }

                if (key == null) {
                    key = (double) (point1[1] - point2[1]) / (double) (point1[0] - point2[0]);
                }

                Set<int[]> val = map.compute(key, (k,v) -> v == null? new HashSet<>():v);
                val.add(point1);
                val.add(point2);
            }
        }

        int max = 0;
        for (Set<int[]> v: xMemoMap.values()) {
            int size = v.size();
            max = Math.max(size, max);
        }
        for (Set<int[]> v: yMemoMap.values()) {
            int size = v.size();
            max = Math.max(size, max);
        }

        for (Map.Entry<Double, Set<int[]>> entry : memoMap.entrySet()) {
            int size = entry.getValue().size();
            double key = entry.getKey();
            if (size > max){
                Map<Double, Set<int[]>> bMap = new HashMap<>();
                ArrayList<int[]> list = new ArrayList<>(entry.getValue());
                for (int i = 0; i < size; i++) {
                    for (int j = i+1; j < size; j++) {
                        int[] p1 = list.get(i);
                        int[] p2 = list.get(j);
                        double k = (double) (p1[1] - p2[1]) / (double) (p1[0] - p2[0]);
                        if (k == key){
                            double b = p1[1] - k*p1[0];
                            Set<int[]> set = bMap.compute(b, (k1,v) -> v == null? new HashSet<>():v);
                            set.add(p1);
                            set.add(p2);
                        }
                    }
                }

                for (Set<int[]> v: bMap.values()) {
                    size = v.size();
                    max = Math.max(size, max);
                }
            }
        }


        return max;
    }

    public static void main(String[] args) {
        Question0149 q = new Question0149();
//        int[][] points = new int[][] {{1,1},{3,2},{5,3},{4,1},{2,3},{1,4}};
//        q.maxPoints(points);
        int[][] points2 = {{0,0},{4,5},{7,8},{8,9},{5,6},{3,4},{1,1}};
        q.maxPoints(points2);
    }
}
