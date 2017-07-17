package LintCode.Binary.DynamicProgramming;

/**
 * Created by Administrator on 2017/7/17.
 */
public class LintDP116JumpGame {
    /**
     * 好多failed case 主要还是条件情况没想全 2017/7/17
     */
    public boolean canJump(int[] A) {
        if (A == null || A.length == 0)  {
            return true;
        }
        int len = A.length;
        boolean[] reach = new boolean[len];
        for (int i = 0; i < len; i++) {
            if (i > 0 && !reach[i]) { //Failed  Case [0] 应该返回true 站在首元素上面意味着
                return false;
            }


            int value = A[i];
            for (int j = 1; j <= value; j++) { //failed case [1,0] 跳跃的步数, 应该从下一个开始记数
                if (i + j == len - 1) {
                    return true;
                }
                if (!reach[i + j]) {
                    reach[i + j] = true;
                }
            }
            System.out.println(i + "->" + reach[i]);

        }
        return true;
    }
    /**
     * Greedy 2017/7/17
     */
    public boolean canJumpGreedy(int[] A) {
        if (A == null || A.length == 0) {
            return false;
        }
        int maxIdx = 0;
        for (int i = 0; i < A.length; i++) {
            if (i > maxIdx) {
                return false;
            }
            maxIdx = Math.max(maxIdx, i + A[i]);
        }
        return true;
    }
}
