package amazon.oa;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 3/11/2022
 */
public class Question0019 {

    /**
     * 19. Top K Frequent Words
     *
     * Given a non-empty list of words, return the k most frequent elements. Your answer should be sorted by frequency
     * from highest to lowest. If two words have the same frequency, then the word with the lower alphabetical order comes
     * first.
     */
    class Pair {
        String word;
        long frequence;
        Pair(String word, long frequent){
            this.word = word;
            this.frequence = frequent;
        }
    }
    public String[] topKFrequentWords(String[] words, int k) {
        PriorityQueue<Pair> queue = new PriorityQueue<>((a, b) -> {
            int cmp = Long.compare(b.frequence, a.frequence);
            if (cmp == 0) cmp = a.word.compareTo(b.word);
            return cmp;
        });

        Arrays.stream(words).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .forEach((key,v) -> queue.offer(new Pair(key,v)));

        String[] result = new String[k];
        for (int i = 0; i < k; i++) {
            result[i] = queue.poll().word;
        }
        return result;
    }

    public static void main(String[] args) {
        Question0019 q = new Question0019();
        Arrays.stream(q.topKFrequentWords(new String[]{"i", "love", "leetcode", "i", "love", "coding"}, 2)).forEach(System.out::println);
        Arrays.stream(q.topKFrequentWords(new String[]{"the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"}, 4)).forEach(System.out::println);
    }
}
