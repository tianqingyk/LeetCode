import java.util.LinkedList;
import java.util.ListIterator;
import java.util.TreeSet;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 3/16/2022
 */
public class Test {

    public static void main(String[] args) {
        TreeSet<Integer> treeSet = new TreeSet<>();

        treeSet.add(1);
        treeSet.add(4);
        treeSet.add(5);

        System.out.println(treeSet.higher(4));
        System.out.println(treeSet.lower(4));
    }
}
