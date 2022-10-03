package PriorityQueue;

import java.util.Arrays;

public class PQ253MeetingRoomsII {
    /**
     * Oct 2022 60min Crib Answer
     * https://leetcode.com/problems/meeting-rooms-ii/solutions/67855/explanation-of-super-easy-java-solution-beats-98-8-from-pinkfloyda/
     *
     * 主要没想到的点是:
     * 1. 可以转换思路, 比如所有interval都占一个room, 什么情况可以减少一个room?
     * 2. 就是end < start的时候
     * 3. 所有interval占一个room ==> 所有的start比end早就占一个room, 否则就节省一个room
     */
    class ChronologicalOrdering {
        public int minMeetingRooms(int[][] intervals) {
            final int N = intervals.length;
            int[] start = new int[N];
            int[] end = new int[N];

            for (int i = 0; i < N; ++i) {
                start[i] = intervals[i][0];
                end[i] = intervals[i][1];
            }

            Arrays.sort(start);
            Arrays.sort(end);

            int room = 0;
            int endIdx = 0;
            for (int i = 0; i < N; ++i) {
                if (start[i] < end[endIdx]) {
                    room ++;
                } else {
                    endIdx ++;
                }
            }
            return room;
        }
    }
}
