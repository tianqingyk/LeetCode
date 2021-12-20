package hard;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 2021/12/20
 */
public class Question0068 {

    /**
     * QUESTION 68 Text justification
     * <p>
     * Given an array of strings words and a width maxWidth, format the text such that each line has exactly maxWidth characters and is fully (left and right) justified.
     * <p>
     * You should pack your words in a greedy approach; that is, pack as many words as you can in each line. Pad extra spaces ' ' when necessary so that each line has exactly maxWidth characters.
     * <p>
     * Extra spaces between words should be distributed as evenly as possible. If the number of spaces on a line does not divide evenly between words, the empty slots on the left will be assigned more spaces than the slots on the right.
     * <p>
     * For the last line of text, it should be left-justified and no extra space is inserted between words.
     */

    /**
     * Solution 1
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Text Justification.
     * Memory Usage: 37.1 MB, less than 97.61% of Java online submissions for Text Justification.
     * @param words
     * @param maxWidth
     * @return
     */
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> result = new ArrayList<>();

        int amountLength = 0;
        int wordNumber = 0;
        int begin = 0;

        for (int i = 0; i < words.length; i++) {

            String word = words[i];
            amountLength += word.length();
            wordNumber ++;
            int tmp = amountLength + wordNumber - 1;
            if (tmp < maxWidth){
                continue;
            }

            if (tmp == maxWidth){
                StringBuilder builder = new StringBuilder();
                for(int j = begin; j <= i; j++){
                    builder.append(words[j]).append(" ");
                }
                builder.deleteCharAt(builder.length()-1);
                result.add(builder.toString());

                //reset tmp data
                amountLength = 0;
                wordNumber = 0;
                begin = i+1;
            }

            if (tmp > maxWidth){
                amountLength -= word.length();
                wordNumber --;
                int space = maxWidth - amountLength;
                int extra = 0;
                int reminder = 0;
                int end = space;
                if (wordNumber > 1){
                    extra = space/(wordNumber - 1);
                    reminder = space%(wordNumber - 1);
                    end = 0;
                }

                StringBuilder builder = new StringBuilder();
                for(int j = begin; j < i-1; j++){
                    builder.append(words[j]);
                    for (int k = 0; k < extra; k++){
                        builder.append(" ");
                    }
                    if (reminder > 0){
                        reminder --;
                        builder.append(" ");
                    }
                }
                builder.append(words[i-1]);
                for (int k = 0; k < end; k++){
                    builder.append(" ");
                }

                result.add(builder.toString());

                //reset tmp data
                amountLength = word.length();
                wordNumber = 1;
                begin = i;
            }
        }


        if (amountLength > 0){
            StringBuilder builder = new StringBuilder();
            for(int j = begin; j < words.length; j++){
                builder.append(words[j]).append(" ");
            }
            builder.deleteCharAt(builder.length()-1);
            for (int k = 0; k < maxWidth - amountLength - wordNumber + 1;k++){
                builder.append(" ");
            }
            result.add(builder.toString());
        }

        return result;
    }

    public static void main(String[] args) {
        Question0068 q = new Question0068();
        String[] testWords = {"What","must","be","acknowledgment","shall","be"};
        System.out.println(q.fullJustify(testWords, 16));
    }
}
