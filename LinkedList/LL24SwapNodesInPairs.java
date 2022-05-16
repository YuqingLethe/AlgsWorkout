package LinkedList;

public class LL24SwapNodesInPairs {
	static class July2017 {
		public static ListNode swapPairs(ListNode head) {
			if(head == null) return null;
			if(head.next == null) return head;
			ListNode first = head; //the first element of the pair
			ListNode second = first.next; //the second element of the pair
			head = head.next; //change the head of the List
			while (first != null && first.next != null ) {
				//swap the oddth and eventh pairs
				second = first.next;
				first.next = second.next;
				second.next = first;
				//move the oddth element to the next element for the judgment
				first = first.next;
				// change the 'next' pointer of the eventh element if possible
				if (first != null && first.next != null ) {
					second = second.next;
					second.next = first.next;
				}
			}
			return head;
		}
	}

	class May2022 {
		public ListNode swapPairs(ListNode head) {
			ListNode dummy = new ListNode(0);
			dummy.next = head;
			ListNode curr = dummy;
			while (curr.next != null && curr.next.next != null) {
				ListNode right = curr.next.next;
				curr.next.next = right.next;
				right.next = curr.next;
				curr.next = right;
				curr = curr.next.next; // move two nodes
			}
			return dummy.next;
		}
	}
	
	public static void main (String[] args) {
		ListNode a = new ListNode(1);
		ListNode b = new ListNode(2);
//		ListNode c = new ListNode(3);
//		ListNode d = new ListNode(4);
//		ListNode e = new ListNode(5);
		a.next = b;b.next = null;
//		b.next = c;c.next = d;d.next = e;e.next = null;
		
		ListNode x = July2017.swapPairs(a);
		while (x != null) {
			System.out.println(x.val);
			x = x.next;
		}
		
	}
}
