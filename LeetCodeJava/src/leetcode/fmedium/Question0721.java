package leetcode.fmedium;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 3/12/2022
 */
public class Question0721 {


    /**
     * 721. Accounts Merge
     */

    /**
     * Solution
     *  Runtime: 40 ms, faster than 78.28% of Java online submissions for Accounts Merge.
     *  Memory Usage: 67 MB, less than 27.98% of Java online submissions for Accounts Merge.
     */

    class Node{
        int index;
        String name;
        Set<String> emails = new HashSet<>();
        Set<Node> children = new HashSet<>();

        public Node(int index, String name) {
            this.index = index;
            this.name = name;
        }

        public void addEmail(String email) {
            emails.add(email);
        }

        public void addChild(Node child) {
            if (children.contains(child)) return;
            children.add(child);
        }
    }

    public List<List<String>> accountsMerge(List<List<String>> accounts) {

        List<Node> nodes = new ArrayList<>();
        Map<String, Node> emailToNode = new HashMap<>();
        int index = 0;
        for(List<String> account : accounts){
            Node node = new Node(index, account.remove(0));
            nodes.add(node);
            for(String email : account){
                if(emailToNode.containsKey(email)) {
                    Node neighbor = emailToNode.get(email);
                    neighbor.addChild(node);
                    node.addChild(neighbor);
                }else {
                    emailToNode.put(email, node);
                    node.addEmail(email);
                }
            }
            index++;
        }


        List<List<String>> result = new ArrayList<>();
        for(Node node : nodes) {
            List<String> list = new ArrayList<>();
            recursive(node, list);
            if(list.isEmpty()) continue;

            list.sort(String::compareTo);
            list.add(0, node.name);
            result.add(list);
        }

        return result;
    }

    Set<Integer> visited = new HashSet<>();
    public void recursive(Node node, List<String> list) {
        if(visited.contains(node.index)) return;
        visited.add(node.index);

        list.addAll(node.emails);

        for(Node child: node.children){
            recursive(child, list);
        }
    }

    public static void main(String[] args) {
        Question0721 q = new Question0721();
        List<List<String>> list = new ArrayList<>();
        for (String[] strings : new String[][]{{"David", "0", "1"}, {"David", "3", "4"}, {"David", "4", "5"}, {"David", "2", "3"}, {"David", "1", "2"}}) {
            list.add(Arrays.stream(strings).collect(Collectors.toList()));
        }
        list = list;
        List<List<String>> lists = q.accountsMerge(list);
        System.out.println(lists);
    }
}

/**
 * [["Kevin","Kevin0@m.co","Kevin3@m.co","Kevin5@m.co"],
 * ["Ethan","Ethan4@m.co","Ethan0@m.co","Ethan5@m.co"],
 * ["Fern","Fern0@m.co","Fern5@m.co","Fern1@m.co"],
 * ["Gabe","Gabe1@m.co","Gabe3@m.co","Gabe0@m.co"],
 * ["Hanzo","Hanzo1@m.co","Hanzo3@m.co","Hanzo0@m.co"]]
 *
 * [["Ethan","Ethan0@m.co","Ethan4@m.co","Ethan5@m.co"],
 * ["Gabe","Gabe0@m.co","Gabe1@m.co","Gabe3@m.co"],
 * ["Hanzo","Hanzo0@m.co","Hanzo1@m.co","Hanzo3@m.co"],
 * ["Kevin","Kevin0@m.co","Kevin3@m.co","Kevin5@m.co"],
 * ["Fern","Fern0@m.co","Fern1@m.co","Fern5@m.co"]]
 */