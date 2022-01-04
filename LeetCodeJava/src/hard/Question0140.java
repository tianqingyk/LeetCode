package hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 2021/12/27
 */
public class Question0140 {

    /**
     * 140. Word Break II
     *
     * Given a string s and a dictionary of strings wordDict, add spaces in s to construct a sentence where each word is a valid dictionary word. Return all such possible sentences in any order.
     *
     * Note that the same word in the dictionary may be reused multiple times in the segmentation.
     */



    /**
     * Solution 1
     * Runtime: 4 ms, faster than 59.80% of Java online submissions for Word Break II.
     * Memory Usage: 37.9 MB, less than 27.51% of Java online submissions for Word Break II.
     * @param s
     * @param wordDict
     * @return
     */
    int windows = 0;
    public List<String> wordBreak(String s, List<String> wordDict) {
        for (String word : wordDict) {
            windows = Math.max(windows, word.length());
        }
        
        return wordBreakPro(s, 0, wordDict);
    }
    
    private List<String> wordBreakPro(String s, int begin, List<String> wordDict){
        List<String> result = new ArrayList<>();
        for (int i = 0; i < windows  && begin+i < s.length(); i++) {
            String str = s.substring(begin, begin+i+1);
            if (wordDict.contains(str)){
                if (begin+i+1 >= s.length()){
                    result.add(str);
                    return result;
                }
                List<String> tmp = wordBreakPro(s, begin+i+1, wordDict);
                for (String sen : tmp) {
                    result.add(str+" "+sen);
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Question0140 q = new Question0140();
        String s = "catsanddog";
        String[] wordDict = {"cat","cats","and","sand","dog"};
        q.wordBreak(s, Arrays.stream(wordDict).toList());
    }
}
