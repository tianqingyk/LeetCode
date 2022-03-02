package google.medium;

import java.util.*;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 2022/3/2
 */
public class Question0146 {

    /**
     * 146. LRU Cache
     */


    /**
     * Solution 1
     * Time Limit Exceeded
     */
    class LRUCache {

        private int capacity;

        public LRUCache(int capacity) {
            this.capacity = capacity;
        }

        Queue<Integer> keyQueue = new LinkedList<>();
        Map<Integer, Integer> map = new HashMap<>();

        public int get(int key) {
            if (!map.containsKey(key)) return -1;
            keyQueue.remove(key);
            keyQueue.add(key);
            return map.get(key);
        }

        public void put(int key, int value) {
            if (map.containsKey(key)) {
                keyQueue.remove(key);
            }
            if (keyQueue.size() >= capacity) map.remove(keyQueue.poll());
            keyQueue.add(key);
            map.put(key, value);
        }
    }

    /**
     * Solution 2 Copy From Solution
     * Ordered dictionary
     */
    class LRUCache2 extends LinkedHashMap<Integer, Integer>{
        private int capacity;

        public LRUCache2(int capacity) {
            super(capacity, 0.75F, true);
            this.capacity = capacity;
        }

        public int get(int key) {
            return super.getOrDefault(key, -1);
        }

        public void put(int key, int value) {
            super.put(key, value);
        }

        @Override
        protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
            return size() > capacity;
        }
    }

    /**
     * Solution 3 Copy From Solution
     * Hashmap + DoubleLinkedList
     */

    public class LRUCache3 {

        class DLinkedNode {
            int key;
            int value;
            DLinkedNode prev;
            DLinkedNode next;
        }

        private void addNode(DLinkedNode node) {
            /**
             * Always add the new node right after head.
             */
            node.prev = head;
            node.next = head.next;

            head.next.prev = node;
            head.next = node;
        }

        private void removeNode(DLinkedNode node){
            /**
             * Remove an existing node from the linked list.
             */
            DLinkedNode prev = node.prev;
            DLinkedNode next = node.next;

            prev.next = next;
            next.prev = prev;
        }

        private void moveToHead(DLinkedNode node){
            /**
             * Move certain node in between to the head.
             */
            removeNode(node);
            addNode(node);
        }

        private DLinkedNode popTail() {
            /**
             * Pop the current tail.
             */
            DLinkedNode res = tail.prev;
            removeNode(res);
            return res;
        }

        private Map<Integer, DLinkedNode> cache = new HashMap<>();
        private int size;
        private int capacity;
        private DLinkedNode head, tail;

        public LRUCache3(int capacity) {
            this.size = 0;
            this.capacity = capacity;

            head = new DLinkedNode();
            // head.prev = null;

            tail = new DLinkedNode();
            // tail.next = null;

            head.next = tail;
            tail.prev = head;
        }

        public int get(int key) {
            DLinkedNode node = cache.get(key);
            if (node == null) return -1;

            // move the accessed node to the head;
            moveToHead(node);

            return node.value;
        }

        public void put(int key, int value) {
            DLinkedNode node = cache.get(key);

            if(node == null) {
                DLinkedNode newNode = new DLinkedNode();
                newNode.key = key;
                newNode.value = value;

                cache.put(key, newNode);
                addNode(newNode);

                ++size;

                if(size > capacity) {
                    // pop the tail
                    DLinkedNode tail = popTail();
                    cache.remove(tail.key);
                    --size;
                }
            } else {
                // update the value.
                node.value = value;
                moveToHead(node);
            }
        }
    }

}
