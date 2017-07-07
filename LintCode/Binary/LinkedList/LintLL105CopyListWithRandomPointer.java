package LintCode.Binary.LinkedList;

import java.util.HashMap;

/**
 * Created by Administrator on 2017/7/7.
 */
public class LintLL105CopyListWithRandomPointer {
    class RandomListNode {
        int label;
        RandomListNode next, random;
        RandomListNode(int x) { this.label = x; }
    }

    public class Solution {
        /**
         * @param head: The head of linked list with a random pointer.
         * @return: A new head of a deep copy of the list.
         */
        public RandomListNode copyRandomList(RandomListNode head) {
            if (head == null) {
                return null;
            }
            HashMap<RandomListNode, RandomListNode> hashmap = new HashMap<>();

            //Copy the nodes and build the new link
            RandomListNode idx = head;
            RandomListNode newPrev = new RandomListNode(0);
            while (idx != null) {
                RandomListNode crt = new RandomListNode(idx.label);
                newPrev.next = crt;
                hashmap.put(idx, crt);
                idx = idx.next;
                newPrev = newPrev.next;
            }

            //copy the random pointers
            idx = head;
            while (idx != null) {
                RandomListNode neighbor = idx.random;
                RandomListNode newNode = hashmap.get(idx);
                newNode.random = neighbor;
                idx = idx.next;
            }

            return hashmap.get(head);
        }
    }
}
