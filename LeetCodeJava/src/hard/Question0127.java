package hard;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 2021/12/27
 */
public class Question0127 {

    /**
     * 127. Word Ladder
     * A transformation sequence from word beginWord to word endWord using a dictionary wordList is a sequence of words beginWord -> s1 -> s2 -> ... -> sk such that:
     *
     * Every adjacent pair of words differs by a single letter.
     * Every si for 1 <= i <= k is in wordList. Note that beginWord does not need to be in wordList.
     * sk == endWord
     * Given two words, beginWord and endWord, and a dictionary wordList, return the number of words in the shortest transformation sequence from beginWord to endWord, or 0 if no such sequence exists.
     */

    /**
     * Solution 1
     * Runtime: 390 ms, faster than 20.79% of Java online submissions for Word Ladder.
     * Memory Usage: 41.8 MB, less than 49.47% of Java online submissions for Word Ladder.
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public int ladderLength(String beginWord, String endWord, List<String> wordList)  {
        if (!wordList.contains(endWord)){
            return 0;
        }
        wordList.remove(endWord);

        List<String> handList = new ArrayList<>();
        handList.add(endWord);
        Set<String> tmp = null;

        int length = 1;
        if (oneLetterDiffer(endWord, beginWord)){
            length++;
            return length;
        }

        while (wordList.size() > 0 && handList.size() > 0){
            length++;
            tmp = new HashSet<>();
            for (String word : handList) {
                for (String str : wordList) {
                    if (oneLetterDiffer(word, str)){
                        tmp.add(str);
                        if (oneLetterDiffer(str, beginWord)){
                            length++;
                            return length;
                        }
                    }
                }
            }
            for (String str : tmp) {
                wordList.remove(str);
            }

            handList = new ArrayList<>();
            handList.addAll(tmp);
        }


        return 0;
    }


    private boolean oneLetterDiffer(String str1, String str2) {
        int count = 0;
        for (int i = 0; i < str1.length(); i++) {
            if (str1.charAt(i) != str2.charAt(i)) {
                count++;
                if (count > 1) {
                    return false;
                }
            }
        }
        return true;
    }
}
