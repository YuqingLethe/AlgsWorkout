package Tree;

import java.util.*;

/**
 * Compute the total number of operations required to convert one N-ary tree to another.
 class TreeNode {
    String key;
    int value;
    List<TreeNode> children;
 }
 */

// ** Tree-1 **
//           a(1)
//         /       \
//      b(2)       c(3)
//     /     \         \
//   d(4)    e(5)      f(6)


//  ** Tree-2 **
//         a(1)
//             \
//            c(3)
//                \
//                f(66)

public class Dasher_MinimalOperationsToMakeTreesSame {
    class Node {
        String key;
        int value;
        List<Node> children;

        public Node() {}

        public Node(String key, int value) {
            this.key = key;
            this.value = value;
            children = new ArrayList<Node>();
        }
    }

    static class mySolution {
        public static int countOperations(Node root1, Node root2) {
            int result = 0;
            if (root1 == null && root2 == null) {
                return result;
            }
            Queue<Node> q1 = new LinkedList<>();
            Queue<Node> q2 = new LinkedList<>();
            q1.add(root1);
            q2.add(root2);
            while (!q1.isEmpty() || !q2.isEmpty()) {
                // 注意這裏應該先判斷是否爲空再poll
                Node curr1 = q1.isEmpty() ? null : q1.poll();
                Node curr2 = q2.isEmpty() ? null : q2.poll();

                if (curr1 == null) {
                    result = getNumberOfChildren(curr2) + 1;
                }

            }
            return result;
        }

        private static int getNumberOfChildren(Node root) {
            return root.children == null ? 0: root.children.size();
        }
    }

    /**
     * Crib solutino and code
     */
    public static class Solution {

        public static int countOperations(Node root1, Node root2) {
            int totalOperations = 0;

            if (root1 == null && root2 == null) {
                return totalOperations;
            }

            Queue<Node> queue1 = new LinkedList<Node>();
            Queue<Node> queue2 = new LinkedList<Node>();
            queue1.add(root1);
            queue2.add(root2);

            while (!queue1.isEmpty() || !queue2.isEmpty()) {

                Node node1 = queue1.isEmpty() ? null : queue1.poll();
                Node node2 = queue2.isEmpty() ? null : queue2.poll();

                if (node1 == null) {
                    totalOperations += getTotalNodes(node2);
                    return totalOperations;
                } else if (node2 == null) {
                    totalOperations += getTotalNodes(node1);
                    return totalOperations;
                }

                if (node1.key == node2.key && node1.value != node2.value) {
                    totalOperations++;

                    if (node2.children.size() != 0) {
                        totalOperations += getTotalNodes(node2);
                    }
                } else if (node1.key != node2.key) {
                    totalOperations++;

                    if (node1.children.size() != 0) {
                        totalOperations += getTotalNodes(node1);
                    }
                } else { // Everything matches (i.e. key + value)
                    if (node1 != null && node1.children.size() != 0) {
                        queue1.addAll(node1.children);
                    }
                    if (node2 != null && node2.children.size() != 0) {
                        queue2.addAll(node2.children);
                    }
                }
            }
            return totalOperations;
        }

        private static int getTotalNodes(Node node) {
            int numOfNodes = 1; // currentNode for the root input node

            for (Node currentNode: node.children) {
                // Default 1 for current processing Node if no childs
                numOfNodes += currentNode.children.size() != 0 ? currentNode.children.size() : 1;
            }

            return numOfNodes;
        }
    }
    public void main(String[] args) {
        // Tree-1
        Node nodeD = new Node("d", 4);
        Node nodeE = new Node("e", 5);
        Node nodeF = new Node("f", 6);

        Node nodeB = new Node("b", 2);
        nodeB.children = Arrays.asList(nodeD, nodeE);

        Node nodeC = new Node("c", 3);
        nodeC.children = Arrays.asList(nodeF);

        Node root1 = new Node("a", 1);
        root1.children = Arrays.asList(nodeB, nodeC);

        // Tree-2
        Node nodeC2 = new Node("c", 6);
        nodeC2.children = Arrays.asList(new Node("f", 66));
        Node root2 = new Node("a", 1);
        root2.children = Arrays.asList(nodeC2);

        System.out.println(Solution.countOperations(root1, root2));
    }
}
