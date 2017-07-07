package LintCode.Binary.Array;

/**
 * Created by Administrator on 2017/7/7.
 */
public class LintArray64MergeSortedArray {
    /**
     * 2017/7/7
     * 还说这么简单的题, 一口气bug free 结果debug了超级久
     * Failed case: [4,5,6], 3, [], 0
     * */
    class SolutionLeftToRight {

        private int firstEmpty = 0; //后来发现根据题意, 给了m, 所以不用求
        public void mergeSortedArray(int[] A, int m, int[] B, int n) {
            if (B == null || B.length == 0) {
                return;
            }
            setFirstEmpty(A, m);
            int a = 0;
            int b = 0;
            while(b < n ) {
//            System.out.println("Before: a=" + a + " b=" + b + " firstEmpty=" + firstEmpty);
                if (a == firstEmpty) {
                    A[a] = B[b];
                    firstEmpty++;
                    b++;
                } else if(A[a] > B[b]) { //这里必须用else if,否则a b可能会溢出
                    moveRightOneSpace(A, a);
                    firstEmpty++;
                    A[a] = B[b];
                    b++; //这里b有可能不增加, 因此考虑B.length == 0的情况
                }

                a++;
//            System.out.println("AFTER: a=" + a + " b=" + b + " firstEmpty=" + firstEmpty);
//          print(A);
            }
        }
        private void setFirstEmpty(int[] A, int m) {
            if (A == null || A.length == 0) {
                return;
            }
            int count = 0;
            for (int i = 0; i < m; i++) {
                if (A[i] > A[i + 1]) {
                    firstEmpty = i + 1;
                }
            }
        }
        private void moveRightOneSpace(int[] A, int k) {
            for (int i = firstEmpty; i > k; i--) {
                A[i] = A[i - 1];
            }
        }
        private void print(int[] A) {
            for (int i = 0; i < firstEmpty; i++) {
                System.out.print(A[i] + " ");
            }
            System.out.println();
        }
    }
    /**
     * 答案的方法 特别简单, 唉.
     */
    class SolutionRightToLeft {

        public void mergeSortedArray(int[] A, int m, int[] B, int n) {
            //Find the length of array A
            int idx = m + n - 1;
            int i = m - 1;
            int j = n - 1;

            while(i >= 0 && j >= 0) {
                if (A[i] <= B[j]) {
                    A[idx--] = B[j--];
                } else {
                    A[idx--] = A[i--];
                }
            }
            while(i >= 0) {
                A[idx--] = A[i--];
            }
            while(j >= 0) {
                A[idx--] = B[j--];
            }
        }
    }
}
