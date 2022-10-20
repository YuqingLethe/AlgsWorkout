package LinkedList;

import java.util.HashMap;

public class LL138CopyListWithRandomPointer {
    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
    /**
     * Created by Administrator on 2017/7/7.
     */
    public static class LintLL105CopyListWithRandomPointer {
        class RandomListNode {
            int label;
            RandomListNode next, random;
            RandomListNode(int x) { this.label = x; }
        }

        public class SetNextFirstLoop_RandomSecond {
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

    /**
     * Oct 2022 20min
     */
    class CreateNodeFirstLoop_SetNextRandomSecond {
        public Node copyRandomList(Node head) {
            HashMap<Node, Node> hm = new HashMap<>();

            // Deep copy all nodes and save to hashmap
            Node oldIdx = head;
            while (oldIdx != null) {
                Node curr = new Node(oldIdx.val);
                hm.put(oldIdx, curr);
                oldIdx = oldIdx.next;
            }

            // Link new node
            Node dummyHead = new Node(0);
            Node prev = dummyHead;
            oldIdx = head;
            while (oldIdx != null) {
                Node curr = hm.get(oldIdx);
                if (oldIdx.random == null) {
                    curr.random = null;
                } else {
                    curr.random = hm.get(oldIdx.random);
                }

                prev.next = curr;
                prev = curr;
                oldIdx = oldIdx.next;
            }
            prev.next = null;
            return dummyHead.next;
        }
    }
}
