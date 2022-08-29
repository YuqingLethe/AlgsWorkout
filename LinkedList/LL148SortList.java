package LinkedList;

/**
 * Given the head of a linked list, return the list after sorting it in ascending order.

 * Example 1:
 *
 *
 * Input: head = [4,2,1,3]
 * Output: [1,2,3,4]
 *
 * Example 2:
 *
 * Input: head = [-1,5,3,4,0]
 * Output: [-1,0,3,4,5]
 *
 * Example 3:
 *
 * Input: head = []
 * Output: []
 *
 * Constraints:
 *
 * The number of nodes in the list is in the range [0, 5 * 104].
 * -105 <= Node.val <= 105
 */

import static LinkedList.ListNode.*;

/**
 * Lint98 LintLL98SortList LintCode98
 */
public class LL148SortList {

    class MergeSort_TopDown_Aug2022 {
        public ListNode sortList(ListNode head) {
            if (head == null || head.next == null) {
                return head;
            }
            ListNode mid = getMidAndSplit(head);
            ListNode left = sortList(head);
            ListNode right = sortList(mid);
            return merge(left, right);
        }
        private ListNode getMidAndSplit(ListNode head) {
            ListNode fast = head;
            ListNode midPrev = null;
            while (fast != null && fast.next != null) {
                if (midPrev == null) {
                    midPrev = head;
                } else {
                    midPrev = midPrev.next;
                }
                fast = fast.next.next;
            }
            ListNode mid = midPrev.next;
            midPrev.next = null;
            return mid;
        }
        private ListNode merge(ListNode one, ListNode two) {
            ListNode dummyHead = new ListNode(0);
            ListNode tail = dummyHead;

            while (one != null && two != null) {
                if (one.val < two.val) {
                    tail.next = one;
                    one = one.next;
                    tail = tail.next;
                } else {
                    tail.next = two;
                    two = two.next;
                    tail = tail.next;
                }

            }
            if (one != null) {
                tail.next = one;
            } else {
                tail.next = two;
            }
            return dummyHead.next;
        }

    }
    public class MergeSort_TopDown_2017 {
        public ListNode sortList(ListNode head) {
            if (head == null || head.next == null) {
                return head;
            }
            ListNode middle = findMiddle(head);
            ListNode rightList = sortList(middle.next); // 因为helper函数并没有cut只是找到中点
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
    /**
     * Quick Sort快速排序宗旨要明了啊, 是根据mid大小分别在左右,
     * 而不在乎怎样实现, 到底是两指针左右向中间靠近, 还是直接copy.....
     * Aug 2017
     */
    public class SolutionQuickSort_Lint98 {

        public ListNode sortList(ListNode head) {
            // write your code here
            if (head == null || head.next == null) {
                return head;
            }
            ListNode mid = findMid(head);

            ListNode leftDummy  = new ListNode(0), leftTail  = leftDummy;
            ListNode rightDummy = new ListNode(0), rightTail = rightDummy;
            ListNode midDummy   = new ListNode(0), midTail   = midDummy;

            //quick sort的主旨是以mid为分割, 左边全部小于mid, 右边全部大于mid, 而不在乎怎样比较怎样实现的这个步骤
            while(head != null) {
                if (head.val < mid.val) {
                    leftTail.next = head;
                    leftTail = leftTail.next;
                } else if (head.val > mid.val) {
                    rightTail.next = head;
                    rightTail = rightTail.next;
                } else {
                    midTail.next = head;
                    midTail = midTail.next;
                }
                head = head.next;

            }
            leftTail.next = null;
            rightTail.next = null;
            midTail.next = null;

            ListNode left = sortList(leftDummy.next);
            ListNode right = sortList(rightDummy.next);

            return concate(left, midDummy.next, right);
        }


        private ListNode findMid(ListNode head) {
            ListNode fast = head.next;
            ListNode slow = head;
            while(fast != null && fast.next != null) {
                fast = fast.next.next;
                slow = slow.next;
            }
            return slow;
        }

        private ListNode getTail(ListNode head) {
            if (head == null) {
                return null;
            }
            ListNode tail = head;
            while(tail.next != null) {
                tail = tail.next;
            }
            return tail;
        }
        private ListNode concate(ListNode left, ListNode mid, ListNode right) {
            /*
            //这么写有bug, 因为这三个节点有可能是null, 直接next会出错
            ListNode dummy = new ListNode(0);
            ListNode leftTail = getTail(left);
            ListNode midTail = getTail(mid);


            dummy.next = left;
            leftTail.next = mid;
            midTail.next = right;
            */
            ListNode dummy = new ListNode(0);
            ListNode tail = dummy;
            tail.next = left; tail = getTail(tail);
            tail.next = mid;  tail = getTail(mid);
            tail.next = right;

            return dummy.next;
        }
    }

    /**
     * Copy SolutionQuickSort_Lint98
     */
    static class QuickSort_Aug2022 {
        public ListNode sortList(ListNode head) {
            if (head == null || head.next == null) {
                return head;
            }
            ListNode mid = findMid(head);

            ListNode leftDummyHead = new ListNode(0), leftTail = leftDummyHead;
            ListNode rightDummyHead = new ListNode(0), rightTail = rightDummyHead;
            ListNode midDummyHead = new ListNode(0), midTail = midDummyHead;

            ListNode curr = head;
            while (curr != null) { // 注意在while里面找下一个的话, while循环判断当前而不是curr.next != null
                if (curr.val < mid.val) {
                    leftTail.next = curr;
                    leftTail = leftTail.next;
                } else if (curr.val > mid.val) {
                    rightTail.next = curr;
                    rightTail = rightTail.next;
                } else {
                    midTail.next = curr;
                    midTail = midTail.next;
                }
                curr = curr.next;
            }
            leftTail.next = null;
            rightTail.next = null;
            midTail.next = null;

            ListNode leftHead = sortList(leftDummyHead.next);
            ListNode rightHead = sortList(rightDummyHead.next);

            return concat(leftHead, midDummyHead.next, rightHead);

        }

        private ListNode findMid(ListNode head) {
            if (head == null || head.next == null) {
                return head;
            }
            ListNode fast = head;
            while (fast.next != null && fast.next.next != null) {
                fast = fast.next.next;
                head = head.next;
            }
            return head.next;
        }

        public ListNode concat(ListNode left, ListNode mid, ListNode right) {
            ListNode dummyHead = new ListNode(0); // In case left is null
            ListNode curr = dummyHead;
            if (left != null) { //或者用一个getTail()函数代替
                curr.next = left;
                while (left.next != null) {
                    left = left.next;
                }
                curr = left;
            }
            if (mid != null) {
                curr.next = mid;
                while (mid.next != null) {
                    mid = mid.next;
                }
                curr = mid;
            }
            curr.next = right;
            return dummyHead.next;
        }
    }

    public static void main(String[] args) {
        ListNode list1 = listBuilder(new int[]{-1,5,3,4,0});
        ListNode list2 = listBuilder(new int[]{99});
        ListNode list3 = listBuilder(new int[]{0});
    }
}
