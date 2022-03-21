package leetcode.hard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 2022/2/12
 */
public class Question0472 {

    /**
     * 472. Concatenated Words
     */


    /**
     * Solution
     * Runtime: 251 ms, faster than 34.07% of Java online submissions for Concatenated Words.
     * Memory Usage: 125.4 MB, less than 24.02% of Java online submissions for Concatenated Words.
     */
    class Node {
        Map<Character, Node> nextMap = new HashMap<>();
        String word = null;

        Node(){}

        public boolean findWords(int index, String word, boolean isConcatenated){
            if(index > word.length() - 1) return false;
            Node next = nextMap.get(word.charAt(index));
            if(next == null) return false;
            if(next.word != null) {
                if(index == word.length()-1) return isConcatenated;
                return next.findWords(index+1, word, isConcatenated) || root.findWords(index+1, word, true);
            }
            return next.findWords(index+1, word, isConcatenated);
        }

    }

    Node root = new Node();
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        List<String> result = new ArrayList<>();
        if(words == null || words.length == 0) return result;


        for(String word : words){
            Node cur = root;
            for(int index = 0; index < word.length(); index++){
                char c = word.charAt(index);
                cur = cur.nextMap.compute(c, (k,v) -> v == null ? new Node() : v);
                if(index == word.length() - 1) cur.word = word;
            }

        }


        for(String word : words){
            if(root.findWords(0, word, false)) result.add(word);
        }

        return result;
    }
}
