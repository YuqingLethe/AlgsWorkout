package LintCode.Binary.LinkedList;

/**
 * Created by Administrator on 2017/7/7.
 */
public class LintLL103LinkedListCycleII {

    /**
     * 可以推导的数学公式 第一个while的条件很好, 比冗余版本相比减少了一次判断
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
