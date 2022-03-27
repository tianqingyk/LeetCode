import com.sun.jdi.Value;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.util.*;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 3/16/2022
 */
public class Test {

    public void addNode(Node node) {
        Node after = first.after;

        first.after = node;
        node.before = first;

        node.after = after;
        after.before = node;

    }

    public void updateNode(Node node) {
        removeNode(node);
        addNode(node);
    }
    public static void main(String[] args) {
        Test cache = new Test(2);
        cache.put(1,1);
        cache.put(2,2);
        cache.get(1);
        cache.put(3,3);
        cache.get(2);
    }
}