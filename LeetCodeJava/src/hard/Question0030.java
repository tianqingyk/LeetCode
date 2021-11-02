package hard;

/**
 * @author yangke
 * @projectName LeetCodeJava
 * @date 9/23/21
 */

import com.sun.nio.sctp.SendFailedNotification;

import java.util.*;

/**
 * QUESTION 30
 * You are given a string s and an array of strings words of the same length.
 * Return all starting indices of substring(s) in s that is a concatenation of each word in words exactly once,
 * in any order, and without any intervening characters.
 * <p>
 * You can return the answer in any order.
 */
public class Question0030 {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();

        int L = s.length();
        int l = words[0].length();
        char[] chars = s.toCharArray();

        Map<String, List<Integer>> wordIndices = new HashMap<>();
        for (String word : words) {
            List list = new ArrayList<Integer>();
            wordIndices.put(word, list);
            char[] wordChars = word.toCharArray();
            for (int j = 0; j < L - l + 1; j++) {
                boolean isOk = true;
                for (int i = 0; i < l; i++) {
                    if (chars[j + i] != wordChars[i]) {
                        isOk = false;
                        break;
                    }
                }
                if (isOk) {
                    list.add(j);
                }
            }
        }

        Map<Integer, Map<String, List<Integer>>> tmpMap = new HashMap<>();
        for (Map.Entry<String, List<Integer>> entry : wordIndices.entrySet()) {
            String key = entry.getKey();
            if (entry.getValue().isEmpty()) {
                return result;
            }
            for (int index : entry.getValue()) {
                tmpMap.computeIfAbsent(index % l, v -> new HashMap<String, List<Integer>>())
                        .computeIfAbsent(key, v -> new ArrayList<Integer>()).add(index);
            }
        }

        List<Integer> tmpResult = new ArrayList<>();
        for (Map.Entry<Integer, Map<String, List<Integer>>> entry : tmpMap.entrySet()) {

            List<Integer> allIndices = new ArrayList<>();
            for (Map.Entry<String, List<Integer>> entry1 : entry.getValue().entrySet()) {
                allIndices.addAll(entry1.getValue());
            }
            Collections.sort(allIndices);

            for (int i = 0; i <= allIndices.size() - words.length; i++) {
                boolean isOk = true;
                for (int j = 0; j < words.length - 1; j++) {
                    if (allIndices.get(i) + l != allIndices.get(i + 1)) {
                        isOk = false;
                        break;
                    }
                }
                if (isOk) {
                    tmpResult.add(allIndices.get(i));
                }
            }
        }

        Arrays.sort(words);
        for (int index : tmpResult) {
            String[] tmpWords = new String[words.length];
            for (int i = 0; i < words.length; i++) {
                String str = "";
                for (int j = 0; j < l; j++) {
                    str += chars[index + i * l + j];
                }
                tmpWords[i] = str;
            }
            Arrays.sort(tmpWords);

            if (Arrays.equals(words, tmpWords)) {
                result.add(index);
            }
        }

        return result;
    }

    /**
     * Copy from Jianchao Li
     * @param s
     * @param words
     * @return
     */
    public List<Integer> findSubstring3(String s, String[] words) {
        List<Integer> results = new ArrayList<Integer>();
        Map<String, Integer> counts = new HashMap<String, Integer>();
        for (String word : words) {
            counts.put(word, counts.getOrDefault(word, 0) + 1);
        }

        int stringLength = s.length();
        int wordLength = words[0].length();
        int num = words.length;

        for (int i = 0; i < stringLength - wordLength * num + 1; i++) {
            Map<String, Integer> seen = new HashMap<String, Integer>();
            int j = 0;
            for (; j < num; j++) {
              String tmp = s.substring(i+j*wordLength, i+(j+1)*wordLength );
              if (counts.containsKey(tmp)) {
                  seen.put(tmp, seen.getOrDefault(tmp, 0) + 1);
                  if (seen.getOrDefault(tmp, 0) > counts.getOrDefault(tmp, 0)) { // the most important part of this method.
                      break;
                  }
              }else {
                  break;
              }
            }
            if (j == num) {
                results.add(i);
            }

        }
        return results;
    }

    public static void main(String[] args) {
        String s = "ababaab";
        String[] words = {"ab", "ba", "ba"};
        Question0030 q = new Question0030();
        System.out.println(q.findSubstring(s, words));
        System.out.println(q.findSubstring3(s, words));
    }
}
