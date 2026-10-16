package DynamicProgramming;

import java.util.Arrays;

public class DP435NonOverlappingIntervals {

    /**
     * 这是一个非典型DP题目，主要思想就是时间结束越早，天然后面removal数量越少。而且我们只看留下的个数。
     * 所以只要有overlap的，直接剩一个就行，而剩的那个是end最小或者meeting end最早的，就够了。
     *
     * 这是Leetcode 252MeetingRoom的变种
     */
    class Solution {
        public int eraseOverlapIntervals(int[][] intervals) {
            if (intervals == null || intervals.length <= 1) {
                return 0;
            }
            Arrays.sort(intervals, (a, b) -> Integer.compare(a[1], b[1]));
            int numOfRemoval = 0;
            int currEnd = intervals[0][1];
            for (int i = 1; i < intervals.length; i ++) {
                if (currEnd > intervals[i][0]) {
                    numOfRemoval ++;
                } else {
                    currEnd = intervals[i][1];
                }
            }
            return numOfRemoval;
        }
    }
}
