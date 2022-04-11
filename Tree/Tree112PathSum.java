package Tree;

import java.util.*;

public class Tree112PathSum {

    class Recursive {
        public boolean hasPathSum2021(TreeNode root, int targetSum) {
            //忘記corner case root == null
            if (root == null) {
                return false;
            }
            if (root.left == null && root.right == null && root.val == targetSum) {
                return true;
            }
            // 以下代碼完全可以優化的!! 只有true的時候return, false的時候繼續怎麼辦? 用OR!!!!
//         if (root.left != null) {
//             int sum = targetSum - root.val;
//             if(hasPathSum(root.left, sum)) {
//                 return true;
//             }
//         }
//         if (root.right != null) {
//             int sum = targetSum - root.val;
//             if (hasPathSum(root.right, sum)) {
//                 return true;
//             }
//         }
            return hasPathSum2021(root.left, targetSum - root.val) || hasPathSum2021(root.right, targetSum - root.val);
        }
        /**
         * 这是2020年的想法，思路很顺：1. 先写root node的逻辑，currSum是Sum减去root.val，左右子树两个if
         * 2. 再想leaf node的逻辑，如果当前val和sum相等
         * 漏掉了：如果root == null这个逻辑。在我的left note里面其实不会有这个，但不排除整个空树。
         */
        public boolean hasPathSum2020(TreeNode root, int sum) {
            if (root == null) {
                return false;
            }
            if (sum == root.val && root.left == null && root.right == null) {
                return true;
            }
            TreeNode currNode = root;
            int currSum = sum - currNode.val;

            if (currNode.left != null && hasPathSum2020(currNode.left, currSum)) {
                return true;
            } else if (currNode.right != null && hasPathSum2020(currNode.right, currSum)) {
                return true;
            }
            return false;
        }
    }
    class iteration {
        /**
         * 20211110
         * 1. 因爲targetSum根據node的去留改變, 所以直接也放在stack屬性裏面, 或者sum另外建立一個stack
         * 2. iteration即便DFS也要遇到node就把左右孩子都加進stack裏面
         */
        public boolean useStack(TreeNode root, int targetSum) {
            if (root == null) {
                return false;
            }
            // 因爲targetSum根據node的去留改變, 所以直接也放在stack屬性裏面, 或者sum另外建立一個stack
            Stack<TreeNode> nodeStack = new Stack<>();
            Stack<Integer> sumStack = new Stack<>();
            nodeStack.push(root);
            sumStack.push(targetSum);

            while (!nodeStack.isEmpty()) {
                TreeNode curr = nodeStack.pop();
                int sum = sumStack.pop();

                if (curr.left == null && curr.right == null && curr.val == sum) {
                    return true;
                }
                if (curr.left != null) {
                    nodeStack.push(curr.left);
                    sumStack.push(sum - curr.val);
                }
                if (curr.right != null) {
                    nodeStack.push(curr.right);
                    sumStack.push(sum - curr.val);
                }

            }
            return false;
        }

        /**
         * 2020
         * My Original Thoughts
         * https://leetcode.com/problems/path-sum/discuss/36519/My-Java-solutions-using-Recursion-and-Iteration-(Queue-and-Stack)
         *
         * 20200316又没写出来这个。写出来的思路有:
         * 1. 先判断叶子节点左右都是null的条件
         * 2. 需要一个TreeNode Stack来帮助trace父节点
         * 难点(没想明白的）
         * 1. 怎样得知我们已经遍历了左节点还是右节点？还需要一个boolean stack来告诉我们吗？
         *      答：并不用，要搞清楚每个stack到底装什么，每个节点判断完毕不是path就挨个pop，左右不用管，
         * 2. 什么是终结条件？
         *     答： 终结条件和中间的memory设计有关。但主旨是treenode里面还有，说明还有可能的path，所以不结束。
         * 3. 需要sum stack吗？还是只需要记录当前的值？
         *     答：sum stack的用法要根据node stack的用法来决定。
         *     如果解法A则必须用stack，因为每个sum stack存的是走这个path的substraction
         *     如果解法B则强烈建议用stack但不强制，因为不断地减加容易乱，不如和node stack一起pop push看得明白。
         */
        public boolean hasPathSumStack2020(TreeNode root, int sum) {
            if (root == null) {
                return false;
            }
            Stack<TreeNode> nodeStack = new Stack<TreeNode>();
            Stack<Integer> numbers = new Stack<Integer>();
            nodeStack.push(root);
            numbers.push(sum); //注意点A 注意这里，每个节点存的是当前节点及以下的和！

            while (!nodeStack.isEmpty()) {
                TreeNode currNode = nodeStack.pop();
                Integer currSum = numbers.pop() - currNode.val; //注意点A
                if (currSum == 0 && currNode.left == null && currNode.right == null) {
                    return true;
                }
                if (currNode.left != null) {
                    nodeStack.push(currNode.left);
                    numbers.push(currSum);//注意点A
                }
                if (currNode.right != null) {
                    nodeStack.push(currNode.right);
                    numbers.push(currSum);
                }
            }
            return false;

        }

        /**
         * 同Stack一模一樣, 因爲是DFS
         */
        public boolean useQueue2021(TreeNode root, int targetSum) {
            if (root == null) {
                return false;
            }
            Queue<TreeNode> nodeQ = new LinkedList<>();
            Queue<Integer> sumQ = new LinkedList<>();
            nodeQ.add(root);
            sumQ.add(targetSum);

            while (!nodeQ.isEmpty()) {
                TreeNode curr = nodeQ.remove();
                Integer sum = sumQ.remove();
                if (curr.left == null && curr.right == null && curr.val == sum) {
                    return true;
                }
                if (curr.left != null) {
                    nodeQ.add(curr.left);
                    sumQ.add(sum - curr.val);
                }
                if (curr.right != null) {
                    nodeQ.add(curr.right);
                    sumQ.add(sum - curr.val);
                }
            }
            return false;
        }

    }

    /**
     * 20211110 第一思路竟然和2020年一樣, 感覺不需要左右節點都進stack.結果沒做出來.直到找到下面代碼纔知道那樣有多麻煩
     * https://leetcode.com/problems/path-sum/discuss/36440/non-recursive-using-two-stacks-java
     *
     */
    static class iterationLoadLeftChildOnly {
        public static void main(String[] args) {
            Integer[] tree = {5,4,8,11,null,13,4,7,3,null,null,null,1};
            int targetSum = 22;

            TreeNode root = TreeNode.createTreeByArray(tree);
            System.out.println(hasPathSum(root, targetSum));
        }
        public static boolean hasPathSum(TreeNode root, int targetSum) {
            if(root==null) return false;
            Stack<TreeNode> nodes = new Stack<TreeNode>();
            Stack<Integer> numbers = new Stack<Integer>();
            nodes.push(root);
            numbers.push(targetSum - root.val);
            TreeNode node = root;

            while(!nodes.isEmpty()){
                System.out.println("---- Start of while ----");
                print(nodes, numbers);
                if(node.left==null && node.right==null && numbers.peek()==0) return true;
                if(node.left!=null){
                    node=node.left;
                    nodes.push(node);
                    numbers.push(numbers.peek()-node.val);
                }
                else{
                    System.out.println("IN ELSE");
                    node=null;
                    while(node==null && !nodes.isEmpty()){
                        node=nodes.pop().right;
                        if(node!=null){
                            int temp= numbers.pop();
                            numbers.push(temp-node.val);
                            nodes.push(node);
                        }
                        else numbers.pop();
                        print(nodes, numbers);
                    }
                }
                System.out.println("---- End of while ---- ");
                if (node == null) {
                    System.out.println("node=null");
                } else {
                    System.out.println("node.val=" + node.val);
                }
                print(nodes, numbers);
            }
            return false;
        }

        private static void print(Stack<TreeNode> treeStack, Stack<Integer> integerStack) {
            TreeNode.printTreeNodeStack(treeStack);
            Helper.PrintDataStructure.printIntegerStack(integerStack);
        }
    }


    public static class Lint376BinaryTreePathSum_June2017 {
        /**
         * 2017/6/16 答案上处理方法有两个不同,大致思路是一样的 http://www.jiuzhang.com/solution/binary-tree-path-sum/
         * 一是用sum来比较和target大小
         * 二是左右节点在进入helper之前就先加入list里面了.
         * @param root the root of binary tree
         * @param target an integer
         * @return all valid paths
         */
        public List<List<Integer>> binaryTreePathSum(TreeNode root, int target) {
            List<List<Integer>> results = new ArrayList<>();
            if (root == null) {
                return results;
            }
            ArrayList<Integer> list = new ArrayList<>();
            helper(root, target, list, results);
            return results;
        }
        private void helper(TreeNode root,
                            int target,
                            ArrayList<Integer> list,
                            List<List<Integer>> results) {
            if (root.left == null && root.right == null) {
                //这个想法是对的, 不能等target为0再加入results, 那样左右节点会加两次
                if (target == root.val) {
                    list.add(root.val);
                    results.add(new ArrayList<Integer>(list));
                    list.remove(list.size() - 1); //每次add进list里面后都要删掉, 即便print进了results里面也要记得删除
                }
                return;
            }
            // if (target < root.val) {  //这个不能有, 万一节点有负数呢!!!!!
            //     return;
            // }
            list.add(root.val);
            if (root.left != null)  {
                helper(root.left, target - root.val, list, results);
            }
            if (root.right != null)  {
                helper(root.right, target - root.val, list, results);
            }
            list.remove(list.size() - 1);
        }
    }
}
