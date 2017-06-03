package LintCode.Binary;

/**
 * Created by Administrator on 2017/5/31.
 */
public class LintBianry159FindMinimumInRotatedSortedArray {
    //本题实际考察的是最坏情况的判断!!
    // 关联题目LintBinary39
    /**
     * @param nums: a rotated sorted array
     * @return: the minimum number in the array
     */
    public int findMin(int[] nums) {
        // 首次写13min提交 一次ac
        int lo = 0;
        int hi = nums.length - 1;
        if (hi == 0 || nums[hi] > nums[0]) {
            return nums[0];
        }
        while (lo + 1 < hi) {
            int mid = lo + (hi - lo) / 2;
            if (mid > 0 && nums[mid - 1] > nums[mid]) {
                return nums[mid];
            } else if (mid > 0 && nums[mid - 1] < nums[mid] //想复杂了, 其实可以和最后的元素对比, 看findMin2把
                    && nums[mid] < nums[0]) {
                hi = mid - 1;
            } else { //自我感觉这个else用的不错
                lo = mid;
            }
        }
        return nums[hi];
    }

    public int findMinWrongAnswer(int[] nums) {
        int len = nums.length - 1;
        int lo = 0;
        int hi = len;
        if (hi == 0 || nums[hi] > nums[0]) {
            return nums[0];
        }
        while (lo + 1 < hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] <= nums[len - 1]) {
                hi = mid;
            } else if (nums[mid] > nums[len - 1]) {
                lo = mid; //如果只有这两个情况, 那么当edge case {3,3,0,3,3,3,3}时候, hi就会到第二个3那里, 出错.
                //所以应该像答案给的那样, 如果和末元素相等, hi--
            }
        }
        return nums[hi];
    }

    /**
     * 九章算法的答案
     */
    public int findMinAnswer(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int start = 0, end = nums.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == nums[end]) {
                // if mid equals to end, that means it's fine to remove end
                // the smallest element won't be removed
                end--;
            } else if (nums[mid] < nums[end]) {
                end = mid;
                // of course you can merge == & <
            } else {
                start = mid;
                // or start = mid + 1
            }
        }

        if (nums[start] <= nums[end]) {
            return nums[start];
        }
        return nums[end];
    }


    //二分法其实是fake 二分法

    public int findMinStraight(int[] num) {
        //  这道题目在面试中不会让写完整的程序
        //  只需要知道最坏情况下 [1,1,1....,1] 里有一个0
        //  这种情况使得时间复杂度必须是 O(n)
        //  因此写一个for循环就好了。
        //  如果你觉得，不是每个情况都是最坏情况，你想用二分法解决不是最坏情况的情况，那你就写一个二分吧。
        //  反正面试考的不是你在这个题上会不会用二分法。这个题的考点是你想不想得到最坏情况。
        int min = num[0];
        for (int i = 1; i < num.length; i++) {
            if (num[i] < min)
                min = num[i];
        }
        return min;
    }
}
