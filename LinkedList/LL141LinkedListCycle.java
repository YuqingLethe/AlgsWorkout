package LinkedList;

import static LinkedList.ListNode.listBuilder;

/**
 * Given head, the head of a linked list, determine if the linked list has a cycle in it.
 *
 * There is a cycle in a linked list if there is some node in the list that can be reached again by continuously following the next pointer. Internally, pos is used to denote the index of the node that tail's next pointer is connected to. Note that pos is not passed as a parameter.
 *
 * Return true if there is a cycle in the linked list. Otherwise, return false.
 *
 *
 *
 * Example 1:
 * Input: head = [3,2,0,-4], pos = 1
 * Output: true
 * Explanation: There is a cycle in the linked list, where the tail connects to the 1st node (0-indexed).
 *
 * Example 2:
 * Input: head = [1,2], pos = 0
 * Output: true
 * Explanation: There is a cycle in the linked list, where the tail connects to the 0th node.
 *
 * Example 3:
 * Input: head = [1], pos = -1
 * Output: false
 * Explanation: There is no cycle in the linked list.
 *
 *
 * Constraints:
 * The number of the nodes in the list is in the range [0, 104].
 * -105 <= Node.val <= 105
 * pos is -1 or a valid index in the linked-list.
 */
public class LL141LinkedListCycle {
    /**
     * Runtime: 1ms Use: 23min Nov2016
     */
    public static boolean hasCycleTwoPointers(ListNode head) {
        if (head == null || head.next == null) return false;
        ListNode slow = head, fast = head.next.next;
        while(fast != null && slow != null && slow.next != null && fast.next != null && fast.next.next != null) {
            if (fast == slow) return true;
            fast = fast.next.next;
            slow = slow.next;
        }
        return false;
    }

    public static boolean hasCycleTwoPointers2(ListNode head) {
        ListNode slow = head, fast = head;
        while(fast != null && fast.next != null) {
            fast = fast.next.next;
            if (fast == slow) return true;
            slow = slow.next;
        }
        return false;
    }

    public static void main(String[] args) {
        ListNode ln1 = new ListNode(4);
        ListNode ln2 = new ListNode(2);
        ListNode ln3 = new ListNode(3);
        ln1.next = ln2;
        ln2.next = ln3;
//        ln3.next = ln1;

        int[] nums = {7032,1};
        ListNode head = listBuilder(nums);
        System.out.println(ln3.next);
        System.out.println(hasCycleTwoPointers2(ln2));
    }

    /**
     * Created by Administrator on 2017/8/29.
     */
    public static class LintLL102LinkedListCycle {
        /**
         * 2017/7/7 这样写corner case自动覆盖了
         */
        public boolean hasCycle(ListNode head) {
            ListNode fast = head;
            ListNode slow = head;
            while(fast != null && fast.next != null) {
                fast = fast.next.next;
                slow = slow.next;
                if (fast == slow) {
                    return true;
                }
            }
            return false;
        }
    }
}
