package hard;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 2021/12/27
 */
public class Question0158 {

    /**
     * 158. Read N Characters Given read4 II - Call Multiple Times
     *
     */

    /**
     * The read4 API is defined in the parent class Reader4.
     *     int read4(char[] buf4);
     */

    public class Solution extends Reader4 {
        /**
         * @param buf Destination buffer
         * @param n   Number of characters to read
         * @return    The number of actual characters read
         */

        /**
         * Solution 1
         * Runtime: 0 ms, faster than 100.00% of Java online submissions for Read N Characters Given read4 II - Call Multiple Times.
         * Memory Usage: 37.6 MB, less than 44.34% of Java online submissions for Read N Characters Given read4 II - Call Multiple Times.
         */
        char[] buf4 = new char[4];
        int end4 = 0;
        int index4 = 0;
        public int read(char[] buf, int n) {
            int result = 0;
            while(result < n){
                if(end4 <= index4){
                    index4 =0;
                    end4 = read4(buf4);
                }
                if(end4 == 0) break;

                while(end4 > index4 && result < n){
                    buf[result++] = buf4[index4++];
                }
            }
            return result;
        }
    }
}
class Reader4{
    public int read4(char[] buf4){
        return 0;
    }
}