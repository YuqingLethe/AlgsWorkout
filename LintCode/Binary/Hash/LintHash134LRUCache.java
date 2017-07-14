package LintCode.Binary.Hash;

import java.util.HashMap;

/**
 * Created by Administrator on 2017/7/13.
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

        // @param key, an integer
        // @param value, an integer
        // @return nothing
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
