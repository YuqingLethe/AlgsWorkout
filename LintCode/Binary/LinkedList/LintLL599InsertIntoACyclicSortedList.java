package LintCode.Binary.LinkedList;

/**
 * Created by Administrator on 2017/7/21.
 */
public class LintLL599InsertIntoACyclicSortedList {
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
