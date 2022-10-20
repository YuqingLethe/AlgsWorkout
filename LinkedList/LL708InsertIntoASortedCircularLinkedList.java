package LinkedList;

public class LL708InsertIntoASortedCircularLinkedList {
    class Node {
        public int val;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _next) {
            val = _val;
            next = _next;
        }
    };

    /**
     * Leetcode官方答案. 注意它insert和在环的末尾的处理
     */
    class LeetcodeSolution {
        public Node insert(Node head, int insertVal) {
            if (head == null) {
                Node newNode = new Node(insertVal, null);
                newNode.next = newNode;
                return newNode;
            }

            Node prev = head;
            Node curr = head.next;
            boolean toInsert = false;

            do {
                if (prev.val <= insertVal && insertVal <= curr.val) {
                    // Case 1).
                    toInsert = true;
                } else if (prev.val > curr.val) {
                    // Case 2).
                    if (insertVal >= prev.val || insertVal <= curr.val)
                        toInsert = true;
                }

                if (toInsert) {
                    prev.next = new Node(insertVal, curr);
                    return head;
                }

                prev = curr;
                curr = curr.next;
            } while (prev != head);

            // Case 3).
            prev.next = new Node(insertVal, curr);
            return head;
        }
    }

    /**
     * 30min参考了答案. 主要是3个case想清楚才行.
     */
    class Oct2022_ReferSolution {
        public Node insert(Node head, int insertVal) {
            if (head == null) {
                Node newNode = new Node(insertVal, null);
                newNode.next = newNode;
                return newNode;
            }
            Node curr = head;
            Node prev = head;
            do { //curr如果从head开始, 肯定要落在head上面. 因此结束条件用prev
                curr = curr.next;
                if (prev.val <= curr.val && prev.val <= insertVal && insertVal <= curr.val) {
                    break;
                }
                if (prev.val > curr.val && (insertVal < curr.val || insertVal > prev.val)) {
                    break;
                }
                if (curr == head && prev.val == curr.val && curr.val != insertVal) {
                    break;
                }

                prev = prev.next;
            } while (prev != head);

            // Node newNode = new Node(insertVal, curr);
            prev.next = new Node(insertVal, curr);
            return head;
        }
    }
    /**
     * Given a Circular Linked List node, which is sorted in ascending order, write a function to insert a value insertVal into the list such that it remains a sorted circular list. The given node can be a reference to any single node in the list and may not necessarily be the smallest value in the circular list.
     *
     * If there are multiple suitable places for insertion, you may choose any place to insert the new value. After the insertion, the circular list should remain sorted.
     *
     * If the list is empty (i.e., the given node is null), you should create a new single circular list and return the reference to that single node. Otherwise, you should return the originally given node.
     *
     * Example 1:
     * Input: head = [3,4,1], insertVal = 2
     * Output: [3,4,1,2]
     * Explanation: In the figure above, there is a sorted circular list of three elements. You are given a reference to the node with value 3, and we need to insert 2 into the list. The new node should be inserted between node 1 and node 3. After the insertion, the list should look like this, and we should still return node 3.
     *
     * Example 2:
     * Input: head = [], insertVal = 1
     * Output: [1]
     * Explanation: The list is empty (given head is null). We create a new single circular list and return the reference to that single node.
     *
     *  Example 3:
     * Input: head = [1], insertVal = 0
     * Output: [1,0]
     *
     *
     * Constraints:
     *
     * The number of nodes in the list is in the range [0, 5 * 104].
     * -106 <= Node.val, insertVal <= 106
     */
    public static class LintLL599InsertIntoACyclicSortedList {
        // 两个题不同. lintcode是加在前面, leetcode是加在后面, head不变
    }
    class July2017_JiuzhangChangeToBeLeetcodeCompatible {
        /**
         * 2017/7/21
         * 答案做法, 先讨论原LL的两种情况, 再根据这两种情况加上x的大小比较
         */
        public Node insert(Node node, int x) {
            if (node == null) {
                Node tmp = new Node(x);
                tmp.next = tmp;
                return tmp;
            }
            Node dummy = node;
            do {
                if (dummy.val <= x && x <= dummy.next.val)  { //Failed: 30->50->2->2->3->5, 2 忘记了等号
                    break;
                }
                if (dummy.val > dummy.next.val && (x < dummy.next.val || x > dummy.val)) {
                    break;
                }
                dummy = dummy.next;
            } while (dummy != node);
            Node tmp = new Node(x);
            tmp.next = dummy.next;
            dummy.next = tmp;
            return node;
        }
    }


}
