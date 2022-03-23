import com.sun.jdi.Value;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.util.*;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 3/16/2022
 */
public class Test {

    public static void main(String[] args) {
        int a = 5;
        for (int i = 0; i < 20; i++) {
            a *= 2;
            System.out.println(a & (a-1));
        }

    }
}
