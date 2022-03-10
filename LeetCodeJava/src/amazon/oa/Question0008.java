package amazon.oa;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 3/10/2022
 */
public class Question0008 {

    /**
     * 8. Slowest Key Solution
     *
     * A newly designed keypad was tested, where a tester pressed a sequence of n keys, one at a time.
     *
     * You are given a string keysPressed of length n, where keysPressed[i] was the ith key pressed in
     * the testing sequence, and a sorted list releaseTime, where releaseTimes[i] was the time the ith key was released.
     * Both arrays are 0-indexed. The 0th key was pressed at the time 0, and every subsequent key was pressed at the exact
     * time the previous key was released.
     *
     * The tester wants to know the key of the keypress that had the longest duration. The ith keypress had a duration
     * of releaseTimes[i] - releaseTimes[i-1], and the 0th keypress had a duration of releaseTimes[0].
     *
     * Note that the same key could have been pressed multiple times during the test, and these multiple presses of the
     * same key may not have had the same duration.
     *
     * Return the key of the keypress that had the longest duration. If there are multiple such keypresses, return the
     * lexicographically largest key of the keypresses.
     */

    public char slowestKeySolution(int[] releaseTimes, String keysPressed) {
        int maxDuration = releaseTimes[0];
        char slowestChar = keysPressed.charAt(0);
        for (int i = 1; i < keysPressed.length(); i++) {
            int duration = releaseTimes[i] - releaseTimes[i-1];
            if (duration > maxDuration || (duration == maxDuration && keysPressed.charAt(i) > slowestChar)){
                maxDuration = duration;
                slowestChar = keysPressed.charAt(i);
                continue;
            }
        }
        return slowestChar;
    }

    public static void main(String[] args) {
        Question0008 q = new Question0008();
        q.slowestKeySolution(new int[]{12,23,36,46,62}, "spuda");
    }
}
