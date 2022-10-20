package LinkedList;

public class LL142LinkedListCycleII {
    /**
     * Given the head of a linked list, return the node where the cycle begins. If there is no cycle, return null.
     *
     * There is a cycle in a linked list if there is some node in the list that can be reached again by continuously following the next pointer. Internally, pos is used to denote the index of the node that tail's next pointer is connected to (0-indexed). It is -1 if there is no cycle. Note that pos is not passed as a parameter.
     *
     * Do not modify the linked list.
     *
     * Example 1:
     * Input: head = [3,2,0,-4], pos = 1
     * Output: tail connects to node index 1
     * Explanation: There is a cycle in the linked list, where tail connects to the second node.
     *
     * Example 2:
     * Input: head = [1,2], pos = 0
     * Output: tail connects to node index 0
     * Explanation: There is a cycle in the linked list, where tail connects to the first node.
     *
     * Example 3:
     * Input: head = [1], pos = -1
     * Output: no cycle
     * Explanation: There is no cycle in the linked list.
     *
     *
     * Constraints:
     *
     * The number of the nodes in the list is in the range [0, 104].
     * -105 <= Node.val <= 105
     * pos is -1 or a valid index in the linked-list.
     */
    public static class LintLL103LinkedListCycleII {

        /**
         * 可以推导的数学公式 第一个while的条件很好, 比冗余版本相比减少了一次判断 July2017
         *
         * 2022 Oct依旧忘了数学公式, 不会做后半部分
         *
         */
        public class Solution {

            public ListNode detectCycle(ListNode head) {
                if (head == null || head.next==null) {
                    return null;
                }

                ListNode fast, slow;
                fast = head.next;
                slow = head;
                while (fast != slow) {
                    if(fast==null || fast.next==null)
                        return null;
                    fast = fast.next.next;
                    slow = slow.next;
                }

                while (head != slow.next) {
                    head = head.next;
                    slow = slow.next;
                }
                return head;
            }
        }
        /**
         * 冗余版本
         */
        public class SolutionRongYu {
            public ListNode detectCycle(ListNode head) {
                // write your code here
                ListNode fast = head;
                ListNode slow = head;
                while (fast != null && fast.next != null) {
                    fast = fast.next.next;
                    slow = slow.next;
                    if (fast == slow) {
                        break;
                    }
                }
                //这里不能忘了要判断fast.next == null 要和前面的进入(结束)while条件对应
                if (fast == null || fast.next == null) {
                    return null;
                }
                fast = head;
                while (fast != slow) {
                    fast = fast.next;
                    slow = slow.next;
                }
                return fast;
            }
        }

    }
}
