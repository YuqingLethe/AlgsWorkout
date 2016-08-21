package LinkedList;

public class LL19RemoveNthNodeFromEndOfListVersion2 {
	public static class ListNode {
		 int val;
		 ListNode next;
		 ListNode(int x) { val = x; }
	}
	/**
	 * This is another method. The interval between a faster pointer 
	 * and a slower pointer.
	 * 
	 * 1. Create a faster pointer and a slower
	 * one whose interval between the two is n + 1.
	 * 2. Delete the slower.next element if faster pointer reaches
	 * the end.
	 * @param head
	 * @param n
	 * @return
	 */
	public static ListNode removeNthFromEnd2(ListNode head, int n) {
		//Create the two pointers
		ListNode faster = head;
		ListNode slower = head;
		for (int i = 0; i < n; i++) {
			faster = faster.next;
		}
		//Find the to-be-delete element (slower)
		if (faster == null) {//delete the head		
				head = head.next;
		} else  {//not delete head
			while (faster.next != null) {		
				faster = faster.next;
				slower = slower.next;
			}
			slower.next = slower.next.next;
		}
		return head;
	}
	public static void main (String[] args) {
		ListNode a = new ListNode(1);
		ListNode b = new ListNode(2);
		ListNode c = new ListNode(3);
		ListNode d = new ListNode(4);
		ListNode e = new ListNode(5);
		a.next = b;
		b.next = c;
		c.next = d;
		d.next = e;
		e.next = null;
		ListNode x = removeNthFromEnd2(a, 1);
		//ListNode x = reverse(a);
		while (x != null) {
			System.out.println(x.val);
			x = x.next;
		}
		
	}
}
