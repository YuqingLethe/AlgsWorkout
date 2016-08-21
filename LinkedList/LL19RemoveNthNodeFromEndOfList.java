package LinkedList;

public class LL19RemoveNthNodeFromEndOfList {
	public static class ListNode {
		 int val;
		 ListNode next;
		 ListNode(int x) { val = x; }
	}
	/**
	 * This is the version 1 using reverse list. First, reverse the
	 * list, then delete the Nth element, and then reverse back.
	 * @param head
	 * @param n
	 * @return
	 */
	public static ListNode removeNthFromEnd(ListNode head, int n) {
        if (head.next == null) {
        	return null;
        }
        //Reverse the list
		ListNode reverseHead = reverse(head);
		
		//count the Nth element and delete
		ListNode curr = reverseHead;
		if (n == 1) {//if delete the first element
			reverseHead = reverseHead.next;
		}
		for (int i = 0; i < n - 2; i++) {
			curr = curr.next;
		}
		curr.next = curr.next.next;
		
		//reverse back		
		return reverse(reverseHead);
    }
	
	private static ListNode reverse (ListNode head) {
		//Reverse the list
		ListNode curr = head; //point of devision between reversed part and remaining part
		ListNode nexthead = curr.next;//point to the first element of remaining list
		while (curr.next != null) {
			nexthead = curr.next;
			curr.next = nexthead.next;
			nexthead.next = head;
			head = nexthead;
			
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
		b.next = null;
		c.next = d;
		d.next = e;
		e.next = null;
		ListNode x = removeNthFromEnd(a, 1);
		//ListNode x = reverse(a);
		while (x != null) {
			System.out.println(x.val);
			x = x.next;
		}
		
	}
}
