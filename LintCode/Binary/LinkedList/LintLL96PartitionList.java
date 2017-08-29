package LintCode.Binary.LinkedList;

/**
 * Created by Administrator on 2017/8/28.
 */
public class LintLL96PartitionList {
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int val) {
            this.val = val;
            this.next = null;
        }
    }
    /**
     * Unknown when
     */
    public class Solution {
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
}
