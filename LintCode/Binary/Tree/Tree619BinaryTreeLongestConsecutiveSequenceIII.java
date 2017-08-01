package LintCode.Binary.Tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/31.
 */
public class Tree619BinaryTreeLongestConsecutiveSequenceIII {
    public class MultiTreeNode {
        int val;
        List<MultiTreeNode> children;
        MultiTreeNode(int x) {
            val = x;
        }
    }
    public class Solution {
        /**
         * 2017/7/31 虽然和II差不多, 但理解的还是有错误
         */
        public int longestConsecutive3(MultiTreeNode root) {
            ResultType rt = helper(root);
            return rt.max_len;
        }

        private ResultType helper(MultiTreeNode root) {
            if (root == null) {
                return new ResultType(0,0,0);
            }
            int down = 0;
            int up = 0;
            int len = 1; //注意这个len起始值应该是1
            List<MultiTreeNode> mtnList = new ArrayList<MultiTreeNode>(root.children);
            for (int i = 0; i < mtnList.size(); i++) {
                MultiTreeNode crt = mtnList.get(i);
                ResultType crtRT = helper(crt);
                if (crt.val + 1 == root.val) {
                    up = Math.max(up, crtRT.max_up + 1);
                }
                if (crt.val - 1 == root.val) {
                    down = Math.max(down, crtRT.max_down + 1);
                }
                len = Math.max(len, crtRT.max_len); //只要得到children里面的最大值即可, 后来比较
            }
            len = Math.max(len, down + up + 1);
            return new ResultType(len, down, up);

        }

        class ResultType {
            int max_len;
            int max_down;
            int max_up;
            public ResultType(int a, int b, int c) {
                this.max_len = a;
                this.max_down = b;
                this.max_up = c;
            }
        }
    }
}
