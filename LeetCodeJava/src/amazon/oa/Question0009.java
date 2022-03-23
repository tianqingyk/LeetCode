package amazon.oa;

import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.function.BiFunction;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 3/10/2022
 */
public class Question0009 {

    /**
     * 9. Five Star Seller/ Maximum Average Pass Raio Solution
     *
     * There is a school that has classes of students and each class will be having a final exam. You are given a 2D
     * integer array classes, where classes[i] = [passi, totali]. You know beforehand that in the ith class, there are
     * totali total students, but only passi number of students will pass the exam.
     *
     * You are also given an integer extraStudents. There are another extraStudents brilliant students that are guaranteed
     * to pass the exam of any class they assigned to. You want to assign each of the extraStudents students to a class in a
     * way that maximizes that average pass ratio across all the classes.
     *
     * The pass ratio of a class is equal to the number of students of the class that will pass the exam divided by the total
     * number of students of the class. The average pass ratio is the sum of pass ratios of all the class divided by the number
     * of the classes.
     *
     * Return the maximum possible average pass ratio after assigning the extraStudents students. Answers within 10^-5 of the
     * answer will be accepted.
     */

    class Pair{
        int x;
        int y;
        Pair(int x, int y){
            this.x = x;
            this.y = y;
        }
        public void addSelf(){
            this.x += 1;
            this.y += 1;
        }
    }

    public double maxAveragePassRaio(int[][] classes, int extraStudents) {
        BiFunction<Integer, Integer, Double> calculateIncreasing = (a,b) -> (double)(b-a)/(double)(b * (b+1));
        PriorityQueue<Pair> queue = new PriorityQueue<>((p1, p2) -> Double.compare(calculateIncreasing.apply(p2.x, p2.y), calculateIncreasing.apply(p1.x, p1.y)));

        for (int i = 0; i < classes.length; i++) {
            int[] cl = classes[i];
            queue.offer(new Pair(cl[0], cl[1]));
        }

        while (extraStudents > 0){
            extraStudents--;
            Pair p = queue.poll();
            p.addSelf();
            queue.offer(p);
        }

        double result = 0;
        Iterator<Pair> iterator = queue.iterator();
        while (iterator.hasNext()){
            Pair p = iterator.next();
            result += (double)p.x/(double) p.y;
        }

        return result/classes.length;
    }

    public static void main(String[] args) {
        Question0009 q = new Question0009();
        System.out.println(q.maxAveragePassRaio(new int[][]{{2,4}, {3,9}, {4,5}, {2,10}}, 4));
        System.out.println(q.maxAveragePassRaio(new int[][]{{1,2}, {3,5}, {2,2}}, 2));

    }
}
