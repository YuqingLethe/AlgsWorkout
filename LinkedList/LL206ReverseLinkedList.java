package LinkedList;


public class LL206ReverseLinkedList {
	private static class ListNode {
		 int val;
		 ListNode next;
		 ListNode(int x) { val = x; }
	}
	public static ListNode reverseList(ListNode head) {
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
        
        //recursive method:
        
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
		ListNode x = reverseList(a);
		while (x != null) {
			System.out.println(x.val);
			x = x.next;
		}
		
	}
}
