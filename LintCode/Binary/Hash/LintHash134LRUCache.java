package LintCode.Binary.Hash;

import java.util.HashMap;
import java.util.Map;


/**
 * 题目设置的坑：不一定都要删除，需要判断size>capacity才执行删除
 * 添加node的时候注意, 判断是否有相同的key，如果有，删除map，删除list，再判断是否溢出。
 * 2026版本是先替换加入map再判断是否溢出
 * 2017版本是先加入再看是否溢出再
 */
class LRUCache2026 {
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

    public LRUCache2026(int capacity) {
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
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */

/**
 * Created by Administrator on 2017/7/13. 这个和上面思路一样，但是set可能题目要求不一样
 */
public class LintHash134LRUCache {
    public class LRUCache {
        private class Node{
            Node prev;
            Node next;
            int key;
            int value;

            public Node(int key, int value) {
                this.key = key;
                this.value = value;
                this.prev = null;
                this.next = null;
            }
        }

        Node head = new Node(-1, -1); //将head tail设为dummy node比直接指向有效值更方便
        Node tail = new Node(-1, -1);
        private HashMap<Integer, Node> hs = new HashMap<Integer, Node>();
        int capacity;

        // @param capacity, an integer
        public LRUCache(int capacity) {
            this.capacity = capacity;
            head.next = tail;
            tail.prev = head;
        }

        // @return an integer
        public int get(int key) {
            if (!hs.containsKey(key)) {
                return -1;
            }
            Node crt = hs.get(key);
            crt.prev.next = crt.next;
            crt.next.prev = crt.prev;
            insertToTail(crt);
            return crt.value;
        }

        public void set(int key, int value) {
            if (get(key) != -1) { //这想的很棒
                hs.get(key).value = value;
                return;
            }

            if (hs.size() == capacity) {
                hs.remove(head.next.key); //别忘了remove
                head.next = head.next.next; //head和tail没关系. 二者不连着
                head.next.prev = head;
            }
            Node newNode = new Node(key, value);
            hs.put(key, newNode);
            insertToTail(newNode);

        }

        private void insertToTail(Node n) {
            n.prev = tail.prev;
            tail.prev = n;
            n.next = tail;
            n.prev.next = n;
        }
    }
}
