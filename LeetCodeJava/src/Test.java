import com.sun.jdi.Value;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 3/16/2022
 */
public class Test {

    public static void main(String[] args) {
        Apple a1 = new Apple("1",1);
        Apple a2 = new Apple("2",1);
        PriorityQueue<Apple> pq = new PriorityQueue<>((a,b)-> a.count - b.count);
        pq.offer(a1);
        pq.offer(a2);
        System.out.println(pq.peek().name);

        StringBuilder sb;

    }

}

class Apple{
    String name;
    int count;

    Apple(String name ,int count){
        this.name = name;
        this.count = count;
    }
}
