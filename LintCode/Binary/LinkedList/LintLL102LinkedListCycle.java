package LintCode.Binary.LinkedList;

/**
 * Created by Administrator on 2017/8/29.
 */
public class LintLL102LinkedListCycle {
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
