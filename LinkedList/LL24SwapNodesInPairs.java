package LinkedList;

public class LL24SwapNodesInPairs {
	public static class ListNode {
		 int val;
		 ListNode next;
		 ListNode(int x) { val = x; }
	}
	
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
	
	public static void main (String[] args) {
		ListNode a = new ListNode(1);
		ListNode b = new ListNode(2);
//		ListNode c = new ListNode(3);
//		ListNode d = new ListNode(4);
//		ListNode e = new ListNode(5);
		a.next = b;b.next = null;
//		b.next = c;c.next = d;d.next = e;e.next = null;
		
		ListNode x = swapPairs(a);
		while (x != null) {
			System.out.println(x.val);
			x = x.next;
		}
		
	}
}
