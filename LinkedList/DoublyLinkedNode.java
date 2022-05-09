package LinkedList;

public class DoublyLinkedNode {
    public int val;
    public DoublyLinkedNode left;
    public DoublyLinkedNode right;

    public DoublyLinkedNode() {}

    public DoublyLinkedNode(int _val) {
        val = _val;
    }

    public DoublyLinkedNode(int _val,DoublyLinkedNode _left,DoublyLinkedNode _right) {
        val = _val;
        left = _left;
        right = _right;
    }
}
