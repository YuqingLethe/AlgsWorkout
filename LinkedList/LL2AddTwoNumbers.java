package LinkedList;

public class LL2AddTwoNumbers {
    public ListNode Jan2022(ListNode l1, ListNode l2) {
        ListNode dummy1 = l1;
        ListNode dummy2 = l2;
        ListNode result0 = new ListNode(0);
        ListNode dummy3 = result0;
        int beyond10 = 0;
        while (dummy1 != null || dummy2 != null || beyond10 == 1) {
            int sum = beyond10;
            if (dummy1 != null) {
                sum += dummy1.val;
                dummy1 = dummy1.next;
            }
            if (dummy2 != null) {
                sum += dummy2.val;
                dummy2 = dummy2.next;
            }
            if (sum > 9) {
                sum = sum - 10;
                beyond10 = 1;
            } else {
                beyond10 = 0;
            }
            dummy3.next = new ListNode(sum);
            dummy3 = dummy3.next;
        }
        return result0.next;
    }
}
