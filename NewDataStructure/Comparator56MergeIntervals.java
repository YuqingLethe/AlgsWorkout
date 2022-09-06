package NewDataStructure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Comparator56MergeIntervals {
    class Solution {
        public int[][] merge(int[][] intervals) {
            if (intervals == null || intervals.length <= 1) {
                return intervals;
            }
            Arrays.sort(intervals, new Comparator<int[]>() {
                @Override
                public int compare(int[] a, int[] b) {
                    // Sort by start of interval in ascending order, and then by range in descending order
                    if (a[0] == b[0]) {
                        return (b[1] - b[0]) - (a[1] - a[0]);
                    }
                    return a[0] - b[0];
                }
            });

            List<int[]> list = new ArrayList<>();
            int rangeStart = intervals[0][0];
            int rangeEnd = intervals[0][1];
            for (int i = 1; i < intervals.length; ++i) {
                int currStart = intervals[i][0];
                int currEnd = intervals[i][1];
                if (currStart > rangeEnd) { // Push existing range to list and reset rangeStart and rangeEnd
                    int[] newRange = new int[]{rangeStart, rangeEnd};
                    list.add(newRange);
                    rangeStart = currStart;
                    rangeEnd = currEnd;
                }
                rangeEnd = currEnd > rangeEnd ? currEnd : rangeEnd;
                if (i == intervals.length - 1) { // If last one, push to list
                    int[] newRange = new int[]{rangeStart, rangeEnd};
                    list.add(newRange);
                }
            }

            // Convert List to int[][]
            int N = list.size();
            int[][] result = new int[N][2];
            for (int i = 0; i < N; ++i) {
                result[i] = list.get(i);
            }
            return result;
        }
    }
}
