package hard;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 2022/2/12
 */
public class Question0297 {

    /**
     * 297. Serialize and Deserialize Binary Tree
     * <p>
     * Serialization is the process of converting a data structure or object into a sequence of bits so that it can be a
     * file or memory buffer, or transimitted across a network connection link to be reconstructed later in the same or
     * another computer environment.
     * <p>
     * Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/
     * deserialization algorithm should work. You just need to ensure that a binary tree can be serialized to a string
     * and this string can be deserialized to the original tree structure.
     * <p>
     * Clarification: The input/output format is the same as how leetcode serializes a binary tree. You do not necessarily
     * need to follow this format, so please be creative and come up with different approaches yourself.
     */

    /**
     * Solution 1
     * Runtime: 11 ms, faster than 83.86% of Java online submissions for Serialize and Deserialize Binary Tree.
     * Memory Usage: 51.3 MB, less than 37.97% of Java online submissions for Serialize and Deserialize Binary Tree.
     */
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            this.val = x;
        }
    }

    //Encodes a tree to a single string
    public String serialize(TreeNode root) {
        if (root == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        List<TreeNode> list = new ArrayList<>();
        list.add(root);
        serialize(list, sb);
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    private void serialize(List<TreeNode> list, StringBuilder sb) {
        List<TreeNode> nextList = new ArrayList<>();
        boolean isOk = false;
        for (TreeNode node : list) {
            if (node == null) {
                sb.append("null").append(",");
                continue;
            }
            sb.append(node.val).append(",");
            nextList.add(node.left);
            nextList.add(node.right);
            if (node.left != null || node.right != null) {
                isOk = true;
            }
        }

        if (isOk) {
            serialize(nextList, sb);
        }
        return;
    }

    //Decodes your encode data to tree.
    public TreeNode deserialize(String data) {
        if (data.isEmpty()) {
            return null;
        }
        String[] ds = data.split(",");
        TreeNode root = new TreeNode(Integer.valueOf(ds[0]));

        List<TreeNode> list = new ArrayList<>();
        list.add(root);
        deserialize(list, ds, 0);
        return root;
    }

    private void deserialize(List<TreeNode> list, String[] ds, int index) {
        int l = ds.length;
        List<TreeNode> nextList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            TreeNode node = list.get(i);
            if (node != null) {
                index++;
                if (index > l - 1) break;
                String leftStr = ds[index];
                if (!leftStr.equals("null")) {
                    TreeNode left = new TreeNode(Integer.valueOf(leftStr));
                    node.left = left;
                    nextList.add(left);
                }

                index++;
                if (index > l - 1) break;
                String rightStr = ds[index];
                if (!rightStr.equals("null")) {
                    TreeNode right = new TreeNode(Integer.valueOf(rightStr));
                    node.right = right;
                    nextList.add(right);
                }
            }
        }

        if (index < ds.length - 1) {
            deserialize(nextList, ds, index);
        }
    }


    public static void main(String[] args) {
        Question0297 q = new Question0297();
        TreeNode root = q.deserialize("1,2,3,null,null,4,5");
        String str = q.serialize(root);
        System.out.println(str);

    }
}
