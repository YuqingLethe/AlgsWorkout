package LinkedList;

public class LL86PartitionList {
    /**
     * Created by Administrator on 2017/8/28.
     */
    public static class LintLL96PartitionList {
        public ListNode partition(ListNode head, int x) {
            if (head == null) {
                return null;
            }
            ListNode smaller = new ListNode(0);
            ListNode notsmaller = new ListNode(0);
            ListNode smallerIdx = smaller;
            ListNode notsmallerIdx = notsmaller;
            while(head != null)  {
                if (head.val < x) {
                    smallerIdx.next = head;
                    smallerIdx = head;
                } else {
                    notsmallerIdx.next = head;
                    notsmallerIdx = head;
                }
                head = head.next;
            }
            notsmallerIdx.next = null; //这句话不能忘, 很重要
            smallerIdx.next = notsmaller.next;
            return smaller.next;
        }
    }

    /**
     * May 2022
     */
    class TwoPointers {
        public ListNode partition(ListNode head, int x) {
            ListNode dSmall = new ListNode(0);
            ListNode dLarge = new ListNode(0);
            ListNode small = dSmall;
            ListNode large = dLarge;
            ListNode idx0 = head;
            while (idx0 != null) {
                if (idx0.val < x) {
                    small.next = idx0;
                    small = small.next;
                } else {
                    large.next = idx0;
                    large = large.next;
                }
                idx0 = idx0.next;
            }
            small.next = dLarge.next;
            large.next = null;
            return dSmall.next;

        }
    }
}
