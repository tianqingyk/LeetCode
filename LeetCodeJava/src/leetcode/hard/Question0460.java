package leetcode.hard;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 2022/2/12
 */
public class Question0460 {

    /**
     * 460. LFU Cache
     */

    /**
     * Solution
     * Runtime: 137 ms, faster than 34.60% of Java online submissions for LFU Cache.
     * Memory Usage: 128.4 MB, less than 88.40% of Java online submissions for LFU Cache.
     */
    class Pair{
        int key;
        int count;
        int id;
        Pair(int key, int count, int id){
            this.key = key;
            this.count = count;
            this.id = id;
        }

        public void update(int id){
            count++;
            this.id = id;
        }
    }

    int capacity;
    int id = 0;
    Map<Integer, Integer> cache = new HashMap<>();

    Map<Integer, Pair> pairMap = new HashMap<>();
    PriorityQueue<Pair> countQueue = new PriorityQueue<>((a,b) -> {
        int cmp = a.count - b.count;
        if(cmp == 0) cmp = a.id - b.id;
        return cmp;
    });

    public Question0460(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        if(capacity == 0 || !cache.containsKey(key)) return -1;
        updateQueue(key, ++id);
        return cache.get(key);
    }

    public void put(int key, int value) {
        if(capacity == 0) return;
        id++;
        if(cache.containsKey(key)) {
            cache.put(key, value);
            updateQueue(key, id);
            return;
        }

        if(cache.size() >= capacity){
            Pair removePair = countQueue.poll();
            pairMap.remove(removePair.key);
            cache.remove(removePair.key);
        }

        Pair pair = new Pair(key, 1, id);

        cache.put(key, value);
        pairMap.put(key, pair);
        countQueue.offer(pair);
        return;
    }

    private void updateQueue(int key, int id){
        Pair pair =  pairMap.get(key);
        countQueue.remove(pair);
        pair.update(id);
        countQueue.offer(pair);
    }
}
