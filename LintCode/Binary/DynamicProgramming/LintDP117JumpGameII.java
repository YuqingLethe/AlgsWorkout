package LintCode.Binary.DynamicProgramming;

/**
 * Created by Administrator on 2017/7/17.
 */
public class LintDP117JumpGameII {
    /**
     * DP方法2017/7/17.
     * 吸取上一个教训, 把min每个节点表示想清楚了, 一次AC
     */
    public int jumpDP(int[] A) {
        // write your code here
        if (A == null || A.length == 0) {
            return -1;
        }

        int[] min = new int[A.length];
        min[0] = 0;
        for (int i = 0; i < A.length; i++) {
            if (i > 0 && min[i] == 0) {
                return -1;
            }

            int jump = A[i];
            for (int j = 1; j <= jump; j++) {
                if (min[i + j] == 0) {
                    min[i + j] = min[i] + 1;
                } else {
                    min[i + j] = Math.min(min[i + j], min[i] + 1);
                }
                if (i + j == A.length - 1) { //勿忘
                    return min[i + j];
                }
            }
//            System.out.println(i + "=" + min[i]);
        }
        return min[A.length - 1];
    }
}
