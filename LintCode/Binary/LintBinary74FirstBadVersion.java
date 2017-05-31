package LintCode.Binary;

/**
 * Created by Administrator on 2017/5/31.
 */

/**
 * public class SVNRepo {
 *     public static boolean isBadVersion(int k);
 * }
 * you can use SVNRepo.isBadVersion(k) to judge whether
 * the kth code version is bad or not.
 */

 class SVNRepo {
      public static boolean isBadVersion(int k) {
          int firstBadVersion = 5;
          if (k >= firstBadVersion) {
              return true;
          } else {
              return false;
          }
      }
  }

public class LintBinary74FirstBadVersion {
    /** 写了很久, 得30min
     * @param n: An integers.
     * @return: An integer which is the first bad version.
     */
    public int findFirstBadVersion(int n) {
        int lo = 1;
        int hi = n;
        if (lo == hi) {
            if (SVNRepo.isBadVersion(lo)) {
                return lo;
            } else {
                return 0;
            }
        }
        while (lo + 1 < hi) {
            int mid = lo + (hi - lo) / 2;
            if (SVNRepo.isBadVersion(mid)) {
                hi = mid;
            } else {
                lo = mid;//有个问题, 万一mid == Integer.MAX_VALUE就边界了,. lo溢出. 因此还不能乱+1呢
            }
        }
        if (SVNRepo.isBadVersion(lo)) {//忘记第一个就是bad version的情况. 而且必须先if这个lo, 不能先if hi
            return lo;
        } else if (SVNRepo.isBadVersion(hi)) {
            return hi;
        } else {
            return 0;
        }
    }
}
