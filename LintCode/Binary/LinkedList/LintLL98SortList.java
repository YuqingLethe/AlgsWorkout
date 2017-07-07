package LintCode.Binary.LinkedList;

/**
 * Created by Administrator on 2017/7/7.
 */
public class LintLL98SortList {
    /**
     * Merge Sort
     */
    public class Solution {
        public ListNode sortList(ListNode head) {
            if (head == null || head.next == null) {
                return head;
            }
            ListNode middle = findMiddle(head);
            ListNode rightList = sortList(middle.next);
            middle.next = null;
            ListNode leftList = sortList(head);

            return merge(leftList, rightList);
        }

        public ListNode merge(ListNode head1, ListNode head2) {
            ListNode dummy = new ListNode(0);
            ListNode pointD = dummy;
            ListNode point1 = head1; //如果head可以移动的话, 可以直接用head1和head2
            ListNode point2 = head2;
            while(point1 != null && point2 != null) { //又错用成了||
                if (point1.val <= point2.val) {
                    pointD.next = point1;
                    point1 = point1.next;
                } else {
                    pointD.next = point2;
                    point2 = point2.next;
                }
                pointD = pointD.next;
            }
            if (point1 != null) {
                pointD.next = point1;
            }
            if (point2 != null) {
                pointD.next = point2;
            }
            return dummy.next;
        }

        private ListNode findMiddle(ListNode head) {
            ListNode fast = head.next; //求中间, fast要在第二个节点开始,方能保证return middle
            ListNode slow = head;
            while(fast != null && fast.next != null) {
                fast = fast.next.next;
                slow = slow.next;
            }
            return slow;
        }
    }

}
