package hard;

/**
 * @author yangke
 * @projectName LeetCodeJava
 * @date 11/11/21
 */
public class Question0044 {
    /**
     * QUESTION 44
     * Given an input string (s) and a pattern (p), implement wildcard pattern matching with support for '?' and '*' where:
     *
     * '?' Matches any single character.
     * '*' Matches any sequence of characters (including the empty sequence).
     * The matching should cover the entire input string (not partial).
     */
    public boolean isMatch(String s, String p) {
        char[] sArray = s.toCharArray();
        int sl = s.length();
        char[] pArray = p.toCharArray();
        int pl = p.length();

        int count = 0;
        for (char c : pArray) {
            if (c == '*'){
                count ++;
            }
        }

        return isMatch(0, sArray, 0, pArray, sl- pl + count);

    }

    private boolean isMatch(int i, char[] sArray, int j, char[] pArray, int add){
        System.out.printf("i:%d, j:%d, add:%d\n", i, j, add);
       if (i > sArray.length - 1){
           for (int k = j; k < pArray.length; k++){
               if (pArray[k] != '*'){
                   return false;
               }
           }
           return true;
       }

       if (j > pArray.length - 1){
           return false;
       }

        char sc = sArray[i];
        char pc = pArray[j];
        if (sc == pc || pc == '?') {
            return isMatch(i+1, sArray, j+1, pArray, add);
        }

        if (pc == '*') {
            if (add <= 0 ){
                return isMatch(i, sArray, j+1, pArray, add);
            }
            if (j+1 < pArray.length && pArray[j+1] == '*') {
                return isMatch(i, sArray, j+1, pArray, add);
            }
            return isMatch(i, sArray, j+1, pArray, add) ||
                    isMatch(i+1, sArray, j+1, pArray, add-1) ||
                    isMatch(i+1, sArray, j, pArray, add-1);
        }
        return false;
    }

    public static void main(String[] args){
        Question0044 q = new Question0044();
        boolean r = q.isMatch("abbabaaabbabbaababbabbbbbabbbabbbabaaaaababababbbabababaabbababaabbbbbbaaaabababbbaabbbbaabbbbababababbaabbaababaabbbababababbbbaaabbbbbabaaaabbababbbbaababaabbababbbbbababbbabaaaaaaaabbbbbaabaaababaaaabb", "**aa*****ba*a*bb**aa*ab****a*aaaaaa***a*aaaa**bbabb*b*b**aaaaaaaaa*a********ba*bbb***a*ba*bb*bb**a*b*bb");
        System.out.println(r);
    }
}
