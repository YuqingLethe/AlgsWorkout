package LinkedList;

public class LL148SortList {
    /**
     * Created by Administrator on 2017/7/7.
     */
    public static class LintLL98SortList {
        /**
         * Merge Sort
         */
        public class SolutionMergeSort {
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

        /**
         * Quick Sort快速排序宗旨要明了啊, 是根据mid大小分别在左右,
         * 而不在乎怎样实现, 到底是两指针左右向中间靠近, 还是直接copy.....
         */
        public class SolutionQuickSort {

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


    }
}
