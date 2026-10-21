package LinkedList;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
// TODO: check LintCode 134 LRU cache 要求是不是不需要map替换？e.g. size=2, put 1,1 put 2,1 put 2.3 get 2 答案应该是3
public class DLL146LRUCache {

    /**
     * 题目设置的坑：不一定都要删除，需要判断size>capacity才执行删除
     * 添加node的时候注意, 判断是否有相同的key，如果有，删除map，删除list，再判断是否溢出
     */
    class LRUCache {
        class ListNode {
            int k;
            int v;
            ListNode prev;
            ListNode next;
            ListNode(int k, int v) {
                this.k = k;
                this.v = v;
                this.prev = null;
                this.next = null;
            }
        }
        private Map<Integer, ListNode> dic;
        private final int maxSize;
        private ListNode head;
        private ListNode tail;

        public LRUCache(int capacity) {
            this.maxSize = capacity;
            this.dic = new HashMap<>();
            this.head = new ListNode(-1, -1);
            this.tail = new ListNode(-1, -1);
            this.head.next = this.tail;
            this.tail.prev = this.head;
        }

        public int get(int key) {
            if (!dic.containsKey(key)) {
                return -1;
            }
            ListNode curr = dic.get(key);
            remove(curr);
            add(curr);
            return curr.v;
        }

        private void remove(ListNode node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }

        private void add(ListNode node) {
            ListNode endPrev = this.tail.prev; // 因为head和tail相连，tail前面总是有东西
            endPrev.next = node;
            node.prev = endPrev;
            tail.prev = node;
            node.next = tail;
        }

        public void put(int key, int value) {
            if (dic.containsKey(key)) {
                ListNode old = dic.get(key);
                remove(old);
            }
            ListNode newNode = new ListNode(key, value);
            add(newNode);
            dic.put(key, newNode);

            if (dic.size() > maxSize) { //要随时记得两个表，一个dic一个map
                ListNode toBeDeleted = head.next;
                dic.remove(toBeDeleted.k);
                remove(toBeDeleted);
            }

        }
    }


    /**
     * 这个直接用LinkedHashMap，注意override removeEldestEntry
     * 我不知道这个loadFactor还有意义么
     */
    class LRUCacheLinkedHashMap {
        int capacity;
        LinkedHashMap<Integer, Integer> map;

        public LRUCacheLinkedHashMap(int capacity) {
            this.capacity = capacity;
            map = new LinkedHashMap<>(5, 0.75f, true) {
                @Override
                protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
                    return size() > capacity;
                }
            };


        }
        public int get(int key) {
            return map.getOrDefault(key, -1);
        }
        public void put(int key, int value) {
            map.put(key, value);
        }
    }

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
}
