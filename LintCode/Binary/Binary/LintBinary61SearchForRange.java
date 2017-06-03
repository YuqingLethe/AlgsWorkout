package LintCode.Binary.Binary;

/**
 * Created by Administrator on 2017/5/31.
 */
public class LintBinary61SearchForRange {
    /**
     *@param A : an integer sorted array
     *@param target :  an integer to be inserted
     *return : a list of length 2, [index1, index2]
     */
    public int[] searchRange(int[] A, int target) {
        int[] ans = new int[2];
        ans[0] = -1; //还可以这么赋值: bound[0] = bound[1] = -1;
        ans[1] = -1;
        if (A.length == 0) {
            return ans;
        }
        if (A[A.length - 1] < target || A[0] > target) {
            return ans;
        }

        //Find the left boundry
        int lo = 0;
        int hi = A.length - 1;
        while (lo + 1 < hi) { //这个发生的条件是, lo范围是0到N-2, hi的范围是1到N-1
            int mid = lo + (hi - lo) / 2;
            if (A[mid] >= target) {
                hi = mid;
            } else {
                lo = mid;
            }
        }
        //以下这一块其实还能更明确, 分别比较A[lo] 和A[hi]和target大小, 相当就赋值, 都不相等就return -1-1
        /*
        if (A[hi] == target) {
            ans[0] = hi;
        } else if (A[lo] == target) {
            ans[0] = lo;
        } else {
            return ans;
        }
         */
        if (A[hi] != target) {//而不仅仅是A[hi] > target而已. hi应该定位到相等, 如果没有相等则不存在
            return ans;
        } else if (A[lo] == target) { //忘记了{5,5,5} target = 5的情况
            ans[0] =  lo;
        } else {
            ans[0] = hi;
        }
        //Find the right boundry
        lo = hi;
        hi = A.length - 1;
        while (lo + 1 < hi) {
            int mid = lo + (hi - lo) / 2;
            if (A[mid] > target) {
                hi = mid;
            } else {
                lo = mid;
            }
        }
        //这一块其实还能更明确, 分别比较A[lo] 和A[hi]和target大小, 相当就赋值, 都不相等就return -1-1
        if (A[hi] == target) {
            ans[1] = hi;
        } else {
            ans[1] = lo;
        }
        return ans;
    }
    //Some great test cases:
    public static void main(String[] args) {
        int[] test1 = {5,5,5,5};
        int target1 = 5;
        int[] test2 = {1,3,4,5};
        int target2= 8;
    }
}
