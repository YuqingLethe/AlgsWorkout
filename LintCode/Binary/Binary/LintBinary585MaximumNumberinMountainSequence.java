package LintCode.Binary.Binary;

import java.util.LinkedList;

/**
 * Created by Administrator on 2017/6/14.
 */
public class LintBinary585MaximumNumberinMountainSequence {
    /**
     * 这个方法通不过时间复杂度, 不过可以实现
     */
    public int mountainSequence(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        LinkedList<Integer> nodup = new LinkedList<>();
        nodup.add(nums[0]);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nodup.peekLast()) {
                nodup.add(nums[i]);
            }
        }
        int lo = 0;
        int hi = nodup.size() - 1;
        while (lo + 1 < hi) {
            int mid = lo + (hi - lo) / 2;
            if (nodup.get(mid) < nodup.get(mid + 1)) {
                lo = mid + 1;
            } else if (nodup.get(mid) > nodup.get(mid + 1)) {
                hi = mid;
            }
        }
        if (nodup.get(hi) > nodup.get(lo)) {
            return nodup.get(hi);
        } else {
            return nodup.get(lo);
        }
    }
}
