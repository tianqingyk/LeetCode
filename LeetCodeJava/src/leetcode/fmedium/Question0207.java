package leetcode.fmedium;

import java.util.*;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 2022/3/3
 */
public class Question0207 {

    /**
     * 207. Course Schedule
     */

    /**
     * Solution 1 Copy From Solution
     * Backtracking
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {

        // course -> list of next courses
        HashMap<Integer, List<Integer>> courseDict = new HashMap<>();

        // build the graph first
        for (int[] relation : prerequisites) {
            // relation[0] depends on relation[1]
            courseDict.compute(relation[1], (k, v) -> v == null ? new ArrayList<>() : v).add(relation[0]);
        }

        boolean[] path = new boolean[numCourses];

        for (int currCourse = 0; currCourse < numCourses; ++currCourse) {
            if (this.isCyclic(currCourse, courseDict, path)) {
                return false;
            }
        }

        return true;
    }


    /*
     * backtracking method to check that no cycle would be formed starting from currCourse
     */
    protected boolean isCyclic(
            Integer currCourse,
            HashMap<Integer, List<Integer>> courseDict,
            boolean[] path) {

        if (path[currCourse]) {
            // come across a previously visited node, i.e. detect the cycle
            return true;
        }

        // no following courses, no loop.
        if (!courseDict.containsKey(currCourse))
            return false;

        // before backtracking, mark the node in the path
        path[currCourse] = true;

        // backtracking
        boolean ret = false;
        for (Integer nextCourse : courseDict.get(currCourse)) {
            ret = this.isCyclic(nextCourse, courseDict, path);
            if (ret)
                break;
        }
        // after backtracking, remove the node from the path
        path[currCourse] = false;
        return ret;
    }

    /**
     * Solution 2 Copy From Solution
     * Postorder DFS (Depth-First Search)
     */

    public boolean canFinish2(int numCourses, int[][] prerequisites) {

        // course -> list of next courses
        HashMap<Integer, List<Integer>> courseDict = new HashMap<>();

        // build the graph first
        for (int[] relation : prerequisites) {
            // relation[0] depends on relation[1]
            courseDict.compute(relation[1], (k, v) -> v == null ? new ArrayList<>() : v).add(relation[0]);
        }

        boolean[] checked = new boolean[numCourses];
        boolean[] path = new boolean[numCourses];

        for (int currCourse = 0; currCourse < numCourses; ++currCourse) {
            if (this.isCyclic(currCourse, courseDict, checked, path))
                return false;
        }

        return true;
    }


    /*
     * postorder DFS check that no cycle would be formed starting from currCourse
     */
    protected boolean isCyclic(
            Integer currCourse, HashMap<Integer, List<Integer>> courseDict,
            boolean[] checked, boolean[] path) {

        // bottom cases
        if (checked[currCourse])
            // this node has been checked, no cycle would be formed with this node.
            return false;
        if (path[currCourse])
            // come across a previously visited node, i.e. detect the cycle
            return true;

        // no following courses, no loop.
        if (!courseDict.containsKey(currCourse))
            return false;

        // before backtracking, mark the node in the path
        path[currCourse] = true;

        boolean ret = false;
        // postorder DFS, to visit all its children first.
        for (Integer child : courseDict.get(currCourse)) {
            ret = this.isCyclic(child, courseDict, checked, path);
            if (ret)
                break;
        }

        // after the visits of children, we come back to process the node itself
        // remove the node from the path
        path[currCourse] = false;

        // Now that we've visited the nodes in the downstream,
        // we complete the check of this node.
        checked[currCourse] = true; // NOTICE!!!!
        return ret;
    }


}
