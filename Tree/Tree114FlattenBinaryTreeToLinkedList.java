package Tree;

import java.util.Stack;

public class Tree114FlattenBinaryTreeToLinkedList {
    /**
     * March 2022 忘記移動完把做子樹設爲null了. 和leetcode answer一樣
     */
    static class IterativeO1 {
        public static void flatten(TreeNode root) {
            TreeNode curr = root;
            while (curr != null) {
                TreeNode.printTreeByPreorderTraversal(curr);
                // If the node has a left child
                if (curr.left != null) {
                    // Find the rightmost node of the left tree and link with the Right tree
                    TreeNode left = curr.left;
                    TreeNode rightmost = left;
                    while (rightmost.right != null) {
                        rightmost = rightmost.right;
                    }
                    // rewire the connections
                    TreeNode currRight = curr.right;
                    curr.right = left;
                    rightmost.right = currRight;
                }
                // move on to the right side of the tree
                curr.left = null; //忘記移動完把做子樹設爲null了
                curr = curr.right;
            }
        }

        /**
         * Recursion Divide and Conquer还有许多小细节不熟练 2017/6/18.
         * 细节1: helper考虑没有左子树节点的特殊处理
         * 细节2: 叶子节点的处理, return
         * 细节3: root.right在这道里面不需要单独储存, 可以直接连接到leftLast上
         */
        public void June2017(TreeNode root) {
            helperJune2017(root);
        }
        //Return the last node of the flattened tree
        private TreeNode helperJune2017(TreeNode root) {
            if (root == null) {
                return null;
            }
            TreeNode leftLast = helperJune2017(root.left);
            TreeNode rightLast = helperJune2017(root.right);
            if (leftLast != null) {
                leftLast.right = root.right;
                //以下两行虽然root没有左子树时候执行不出错, 但root无左子树时不需要执行, 所以放在if里面
                root.right = root.left;
                root.left = null;
            }

            if (rightLast != null) { //不要忘了return时候rightLast不存在则需要return leftLast
                return rightLast;
            }
            if (leftLast != null) {
                return leftLast;
            }

            return root;//leftlast rigihtLast都不存在, return root....
        }

        /**
         * 單個Recursion Traverse 2017/6/16.
         */
        public void flattenSingleRecursion(TreeNode root) {
            if (root == null) {
                return;
            }
            if (root.left != null) {
                if (root.right == null) {
                    root.right = root.left;
                    root.left = null;
                } else {
                    TreeNode tmp = root.right;
                    root.right = root.left;
                    root.left = null;
                    TreeNode goRightmost = root.right;
                    while (goRightmost.right != null) {
                        goRightmost = goRightmost.right;
                    }
                    goRightmost.right = tmp;
                }
            }
            flattenSingleRecursion(root.right); //Flattten right是无论有没有左子节点都要做的
        }

        public static void main (String[] args) {
            Integer[] array = {1,2,5,3,4,null,6};
            TreeNode root = TreeNode.createTreeByArray(array);
            flatten(root);
        }
    }

    /**
     * March 2022炒了答案 還要重做
     */
    class RecursiveLeetcodeAnswer {
        private TreeNode flattenTree(TreeNode node) {
            // Handle the null scenario
            if (node == null) {
                return null;
            }
            // For a leaf node, we simply return the
            // node as is.
            if (node.left == null && node.right == null) {
                return node;
            }

            //Recursively flatten the left subtree
            TreeNode leftTail = this.flattenTree(node.left);
            // Recursively flatten the right subtree
            TreeNode rightTail = this.flattenTree(node.right);
            // If there was a left subtree, we shuffle the connections
            // around so that there is nothing on the left side
            // anymore.
            if (leftTail != null) {
                leftTail.right = node.right;
                node.right = node.left;
                node.left = null;
            }
            // We need to return the "rightmost" node after we are
            // done wiring the new connections.
            return rightTail == null ? leftTail : rightTail;
        }

        public void flatten(TreeNode root) {
            this.flattenTree(root);
        }
    }

    class StackIterativeLeetcodeAnswer {
        /**
         * Iterative方法 2017/6/16.
         * http://www.jiuzhang.com/solutions/flatten-binary-tree-to-linked-list/
         */
        public void flattenIterative(TreeNode root) {
            if (root == null) {
                return;
            }
            Stack<TreeNode> s = new Stack<>();
            s.push(root);
            while(!s.empty()) {
                TreeNode tn = s.pop();
                if (tn.right != null) {
                    s.push(tn.right);
                }
                if (tn.left != null) {
                    s.push(tn.left);
                }
                tn.left = null;
                if (s.empty()) { //这个判断要是忘了, 之后s.peek()就会出错, 因为若为空, 用这个peek就会返回EmptyStackException
                    tn.right = null;
                } else {
                    tn.right = s.peek();//这一步无论有没有子节点, 都要和stack里面的TreeNode连上, 不然就断啦
                }
            }
        }

        /**
         * 感覺這個太抽象了, skip. March 2022
         */
        class Pair<K, V> {
            K key;
            V value;
            Pair(K a, V b) {
                this.key = a;
                this.value = b;
            }
            public K getKey() {
                return this.key;
            }
            public V getValue() {
                return this.value;
            }
        }

        /**
         * Leetcode Answer
         */
        public void flatten(TreeNode root) {
            // Handle the null scenario
            if (root == null) {
                return;
            }
            int START = 1, END = 2;
            TreeNode tailNode = null;
            Stack<Pair<TreeNode, Integer>> stack = new Stack<Pair<TreeNode, Integer>>();
            stack.push(new Pair<TreeNode, Integer>(root, START));

            while (!stack.isEmpty()) {

                Pair<TreeNode, Integer> nodeData = stack.pop();
                TreeNode currentNode = nodeData.getKey();
                int recursionState = nodeData.getValue();

                // We reached a leaf node. Record this as a tail
                // node and move on.
                if (currentNode.left == null && currentNode.right == null) {
                    tailNode = currentNode;
                    continue;
                }

                // If the node is in the START state, it means we still
                // haven't processed it's left child yet.
                if (recursionState == START) {
                    // If the current node has a left child, we add it
                    // to the stack AFTER adding the current node again
                    // to the stack with the END recursion state.
                    if (currentNode.left != null) {
                        stack.push(new Pair<TreeNode, Integer>(currentNode, END));
                        stack.push(new Pair<TreeNode, Integer>(currentNode.left, START));
                    } else if (currentNode.right != null) {
                        // In case the current node didn't have a left child
                        // we will add it's right child
                        stack.push(new Pair<TreeNode, Integer>(currentNode.right, START));
                    }
                } else {
                    // If the current node is in the END recursion state,
                    // that means we did process one of it's children. Left
                    // if it existed, else right.
                    TreeNode rightNode = currentNode.right;

                    // If there was a left child, there must have been a leaf
                    // node and so, we would have set the tailNode
                    if (tailNode != null) {
                        // Establish the proper connections.
                        tailNode.right = currentNode.right;
                        currentNode.right = currentNode.left;
                        currentNode.left = null;
                        rightNode = tailNode.right;
                    }

                    if (rightNode != null) {
                        stack.push(new Pair<TreeNode, Integer>(rightNode, START));
                    }
                }
            }
        }
    }

}
