package leetcode.hard;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 2022/2/12
 */
public class Question0336 {

    /**
     * 336. Palindrome Pairs
     */

    /**
     * Solution
     * Runtime: 679 ms, faster than 82.65% of Java online submissions for Palindrome Pairs.
     * Memory Usage: 180.8 MB, less than 71.62% of Java online submissions for Palindrome Pairs.
     */
    Map<String, Integer> wordIndexMap = new HashMap<>();
    List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> palindromePairs(String[] words) {
        for (int i = 0; i < words.length; i++) {
            wordIndexMap.put(words[i], i);
        }
        Set<String> wordSet = Arrays.stream(words).collect(Collectors.toSet());
        palindromePairsHelper(wordSet, wordSet, 0);
        return result;
    }

    public void palindromePairsHelper(Set<String> beginWords, Set<String> endWords, int index) {
        Map<Character, Set<String>> beginCharMap = new HashMap<>();
        Map<Character, Set<String>> endCharMap = new HashMap<>();
        for (String word : beginWords) {
            if (word.length() - 1 < index) {
                for (String endWord : endWords) {
                    if (word.equals(endWord)) continue;
                    if (isPalindrome(endWord.substring(0, endWord.length() - index))) {
                        List<Integer> newList = new ArrayList<>();
                        newList.add(wordIndexMap.get(word));
                        newList.add(wordIndexMap.get(endWord));
                        result.add(newList);
                    }
                }
                continue;
            }
            beginCharMap.compute(word.charAt(index), (k, v) -> v == null ? new HashSet<>() : v).add(word);
        }

        for (String word : endWords) {
            if (word.length() - 1 < index) {
                for (String beginWord : beginWords) {
                    if (beginWord.length() - 1 < index) continue;
                    if (isPalindrome(beginWord.substring(index))) {
                        if (word.equals(beginWord)) continue;
                        List<Integer> newList = new ArrayList<>();
                        newList.add(wordIndexMap.get(beginWord));
                        newList.add(wordIndexMap.get(word));
                        result.add(newList);
                    }
                }
                continue;
            }
            endCharMap.compute(word.charAt(word.length() - 1 - index), (k, v) -> v == null ? new HashSet<>() : v).add(word);
        }

        for (Character key : beginCharMap.keySet()) {
            if (!endCharMap.containsKey(key)) continue;
            Set<String> newBeginWords = beginCharMap.get(key);
            Set<String> newEndWords = endCharMap.get(key);
            palindromePairsHelper(newBeginWords, newEndWords, index+1);
        }
    }

    private boolean isPalindrome(String s) {
        if (s.length() < 2) return true;
        for (int i = 0; i < s.length() / 2; i++) {
            if (s.charAt(i) != s.charAt(s.length() - 1 - i)) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Question0336 q = new Question0336();
        q.palindromePairs(new String[]{"abcd","dcba"});
    }
}
