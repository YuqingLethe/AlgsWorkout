package LinkedList;

public class LL708InsertIntoASortedCircularLinkedList {
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
        /**
         * 2017/7/21
         * if语句写的乱并且没有条理, 受不了了 很多failed case 2->2->2, 3
         * 看了答案发现答案逻辑清晰简单, 重新写
         *
         */
        public ListNode insertWrong(ListNode node, int x) {
            if (node == null) { //Failed: null, 4
                ListNode tmp = new ListNode(x);
                tmp.next = tmp;
                return tmp;
            }

            ListNode dummy = node;
            do {
                if ((dummy.val <= x && dummy.next.val >= x)
                        || (dummy.val <= x && dummy.next.val < dummy.val) //不能处理case: 0, 1 否则有重复val的节点无无法前进了
                        || (dummy.val >= dummy.next.val && dummy.next.val > x)
                        || (dummy.next == dummy) //Failed: 0, 1  特殊性就是
                        ) {
                    ListNode tmp = new ListNode(x);
                    tmp.next = dummy.next;
                    dummy.next = tmp;
                    return tmp;
                }
                dummy = dummy.next;
            } while (dummy != node);
            return null;
        }

        /**
         * 2017/7/21
         * 答案做法, 先讨论原LL的两种情况, 再根据这两种情况加上x的大小比较
         */
        public ListNode insert(ListNode node, int x) {
            if (node == null) {
                ListNode tmp = new ListNode(x);
                tmp.next = tmp;
                return tmp;
            }
            ListNode dummy = node;
            do {
                if (dummy.val <= x && x < dummy.next.val)  { //Failed: 30->50->2->2->3->5, 2 忘记了等号
                    break;
                }
                if (dummy.val > dummy.next.val && (x < dummy.next.val || x > dummy.val)) {
                    break;
                }
                dummy = dummy.next;
            } while (dummy != node);
            ListNode tmp = new ListNode(x);
            tmp.next = dummy.next;
            dummy.next = tmp;
            return tmp;
        }
    }
}
