package LinkedList;


public class LL206ReverseLinkedList {


	static class Iterative {
		/**
		 * April2022
		 * 没有2017版本的neat但是更易懂!
		 *
		 * 怎么解决reverse list最后node指向null? 一开始补足个null就行了
		 */
		public ListNode reverseList_April2022(ListNode head) {
			if (head == null) {
				return null;
			}
			ListNode dummy = new ListNode(0);
			ListNode originalHead = head;
			ListNode originalNext = originalHead.next;

			while (originalHead != null) {
				originalNext = originalHead.next;
				originalHead.next =dummy.next;
				dummy.next = originalHead;
				originalHead = originalNext;

			}
			return dummy.next;
		}
		public static ListNode reverseList_July2017(ListNode head) {
			if (head == null) return null;
			//Always point to the start of the remaining unchanged list
			ListNode front = head;
			//point to the element gonna change its position
			ListNode rear = head.next;
			while (rear != null) {
				front.next = rear.next;
				rear.next = head;
				head = rear;
				rear = front.next;
			}
			return head;
		}
	}

	/**
	 * 这个有点绕, crib answer april 2022
	 * 主要理解的点:
	 * 那个P永远指向最后的节点 5. 每次recursive变动只是让新加入reverselist的node next变化, 并且originalList删掉最后那个节点
	 *
	 */
	class Recursive {
		public ListNode reverseList(ListNode head) {
			if (head == null || head.next == null) {
				return head;
			}
			ListNode p = reverseList(head.next);
			head.next.next = head;
			head.next = null;
			return p;
		}
	}
	
	public static void main (String[] args) {
		ListNode a = new ListNode(1);
//		ListNode b = new ListNode(2);
//		ListNode c = new ListNode(3);
//		ListNode d = new ListNode(4);
//		ListNode e = new ListNode(5);
//		a.next = b;
//		b.next = c;
//		c.next = d;
//		d.next = e;e.next = null;
		a.next = null;
		ListNode x = Iterative.reverseList_July2017(a);
		while (x != null) {
			System.out.println(x.val);
			x = x.next;
		}
		
	}
}
