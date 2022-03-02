package google.medium;

import java.util.*;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 2022/3/2
 */
public class Question0133 {

    /**
     * 133. Clone Graph
     */
    class Node {
        public int val;
        public List<Node> neighbors;
        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

    /**
     * Solution
     * Runtime: 34 ms, faster than 72.76% of Java online submissions for Clone Graph.
     * Memory Usage: 43.6 MB, less than 18.91% of Java online submissions for Clone Graph.
     */
    Map<Integer, Node> cloned = new HashMap<>();
    public Node cloneGraph(Node node) {
        if (node == null) return null;
        if (cloned.containsKey(node.val)) return cloned.get(node.val);
        Node newNode = new Node(node.val);
        cloned.put(node.val, newNode);
        for (Node neighbor : node.neighbors) {
            newNode.neighbors.add(cloneGraph(neighbor));
        }
        return newNode;
    }
}
