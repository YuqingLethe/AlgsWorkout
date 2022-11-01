package BackTracking;

import java.util.HashSet;
import java.util.Set;

/**
 * MovieList size = N
 * N*N-1*N-2..... N!
 * O(N!)
 */
public class BC30001Luting_MovieScheduling {

    static class BackTracking_OptimizeShowTime {
        private int globalMin;
        private static int movieNum;
        private static int[] movieList;
        private final int interval = 5;

        public int getMinDurationInTheater(int[] movieList) {
            this.movieNum = movieList.length;
            this.movieList = movieList;
            this.globalMin = Integer.MAX_VALUE;

            Set<Integer> seenMovies = new HashSet<>(); // Mark moves already seen.
            helper(seenMovies, 0);
            return globalMin;
        }
        private int nextStartTime (int movieID, int fromTimeStamp) {
            int result = 0;
            int movieTime = movieList[movieID];
            int roundCount = fromTimeStamp/(movieTime + interval);
            int prevCycleEndTime = roundCount * (movieTime + interval);
            if (prevCycleEndTime == fromTimeStamp) {
                return prevCycleEndTime;
            }
            return prevCycleEndTime + movieTime + interval;
        }

        private void helper (Set<Integer> seenMovies, int currTime) {
            if (seenMovies.size() == movieNum) {
                globalMin = Math.min(globalMin, currTime);
                return;
            }
            for (int i = 0; i < movieNum; ++i) {
                if (seenMovies.contains(i)) {
                    continue;
                }
                int currMovieNextStartTime = nextStartTime(i, currTime);
                int currMoveEndTime = currMovieNextStartTime + movieList[i];
                if (currMoveEndTime > globalMin) {
                    continue;
                }
                seenMovies.add(i);
                helper(seenMovies, currMoveEndTime);
                seenMovies.remove(i);
            }

        }
    }


    public static void main(String[] args) {
        int[] movieList = {10, 100, 1000};
        BackTracking_OptimizeShowTime solution = new BackTracking_OptimizeShowTime();
        int result = solution.getMinDurationInTheater(movieList);
        System.out.println(result);
    }
}
