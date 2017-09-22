package LintCode.Binary.TwoPointers;

import java.util.Arrays;

/**
 * Created by yuqing on 9/21/17.
 */
public class LintTP382TriangleCount {
    /**
     * 9/21/2017
     */
    public class SolutionGreedy {

        public int triangleCount(int[] S) {
            if (S == null || S.length == 0) {
                return 0;
            }

            Arrays.sort(S);

            int count = 0;
            for (int i = 0; i < S.length - 2; i++) {
                for (int j = i + 1; j < S.length - 1; j++) {
                    for (int k = j + 1; k < S.length; k++) {
                        if (S[i] + S[j] > S[k]) {
                            count ++;
                        }
                    }
                }
            }

            return count;
        }
    }
}
