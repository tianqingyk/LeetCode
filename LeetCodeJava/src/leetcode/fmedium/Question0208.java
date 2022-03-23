package leetcode.fmedium;

import java.util.*;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 2022/3/4
 */
public class Question0208 {

    /**
     * 208.Implement Trie(Prefix Tree)
     */

    /**
     * Solution
     * Runtime: 82 ms, faster than 27.50% of Java online submissions for Implement Trie (Prefix Tree).
     * Memory Usage: 68.5 MB, less than 30.15% of Java online submissions for Implement Trie (Prefix Tree).
     */
    class Node {
        char c;
        Map<Character,Node> nextMap;

        Node(char c) {
            this.c = c;
            this.nextMap = new HashMap<>();
        }
    }


    class Trie {

        Set<String> words;
        Map<Character,Node> map;

        public Trie() {
            map = new HashMap<>();
            words = new HashSet<>();
        }

        public void insert(String word) {
            if (word == null || word.length() < 1) return;
            words.add(word);

            Map<Character, Node> tmpMap = map;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                tmpMap = tmpMap.compute(c, (k,v) -> v == null ? new Node(k):v).nextMap;
            }
        }

        public boolean search(String word) {
            return words.contains(word);
        }

        public boolean startsWith(String prefix) {
            Map<Character, Node> tmpMap = map;
            for (int i = 0; i < prefix.length(); i++) {
                char c = prefix.charAt(i);
                if (!tmpMap.containsKey(c)) return false;
                else tmpMap = tmpMap.get(c).nextMap;
            }
            return true;
        }
    }

}
