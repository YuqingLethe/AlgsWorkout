package LinkedList;
/**
 * Write a function to delete a node (except the tail) 
 * in a singly linked list, given only access to that node.
 *
 * Supposed the linked list is 1 -> 2 -> 3 -> 4 
 * and you are given the third node with value 3, 
 * the linked list should become 1 -> 2 -> 4 after calling your function.
 * @author yoki
 *
 */
public class LL237DeleteNodeInALinkedList {
	public static class ListNode {
		 int val;
		 ListNode next;
		 ListNode(int x) { val = x; }
	}
	
	public static void deleteNode(ListNode node) {
		//As we only know the tobe deleted node,
		//we should delete the next node and exchange val
		ListNode temp = node.next;
		node.val = temp.val;
		node.next = temp.next;
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
		
		deleteNode(d);
		ListNode x = a;
		while (x != null) {
			System.out.println(x.val);
			x = x.next;
		}
		
	}
}
