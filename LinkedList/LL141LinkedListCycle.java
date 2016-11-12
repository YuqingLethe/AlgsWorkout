package LinkedList;

import static LinkedList.ListNode.listBuilder;

/**
 * Created by Administrator on 2016/11/12.
 */
public class LL141LinkedListCycle {
    /**
     * Runtime: 1ms Use: 23min
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
}
