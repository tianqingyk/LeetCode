package hard;

/**
 * @author yangke
 * @projectName LeetCodeJava
 * @date 9/23/21
 */

import java.time.chrono.IsoChronology;
import java.util.*;

/**
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

        //1.Find every word in the string and return index of its first letter
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

        //2. Put the same mod together
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

        //3. Put the same equal difference together
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

        //4.check the result.
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

    public List<Integer> findSubstring2(String s, String[] words) {
        List<Integer> result = new ArrayList<>();

        int L = s.length();
        int l = words[0].length();
        char[] chars = s.toCharArray();

        //1.Find every word in the string and return index of its first letter
        Set<Integer> wordIndicesSet = new HashSet<>();
        for (String word : words) {
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
                    wordIndicesSet.add(j);
                }
            }
        }

        List<Integer> tmpResult = new ArrayList<>();
        List<Integer> wordIndicesList = new ArrayList<>(wordIndicesSet);
        Collections.sort(wordIndicesList);
        for ( int j = 0; j < wordIndicesList.size(); j++){
            int index = wordIndicesList.get(j);
            boolean isOk = true;
            for (int i = 1; i < words.length;i++){
                int tmp = index+i*l;
                boolean isContain = false;
                for (int k = j+i; k < wordIndicesList.size(); k++) {
                    if (tmp == wordIndicesList.get(k)){
                        isContain = true;
                        break;
                    }
                    if (tmp < wordIndicesList.get(k)){
                        break;
                    }
                }
                if (!isContain){
                    isOk = false;
                    break;
                }
            }
            if (isOk){
                tmpResult.add(index);
            }
        }

        //3.check the result.
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


    public static void main(String[] args) {
        String s = "barfoofoobarthefoobarman";
        String[] words = {"bar","foo","the"};
        Question0030 q = new Question0030();
        System.out.println(q.findSubstring(s, words));
        System.out.println(q.findSubstring2(s, words));
    }
}
