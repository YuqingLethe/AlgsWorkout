package DynamicProgramming;

import java.util.HashMap;

public class DP62UniquePaths {
    /**
     * Created by Administrator on 2017/7/17.
     */
    public class LintDP114UniquePath {
        /**
         * 第一行第一列都是1 2017/7/17
         */
        public int uniquePaths(int m, int n) {
            // write your code here
            if (m == 0 && n == 0) {
                return 0;
            }

            int[][] f = new int[n][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (i == 0 || j == 0) {
                        f[i][j] = 1;
                        continue;
                    }
                    f[i][j] = f[i][j - 1] + f[i - 1][j];
                }
            }
            return f[n - 1][m - 1];
        }
    }

    /**
     * ExceedTImeLImit估计是build record和查找太慢了，用int【】【】就好了
     * 但是熟悉Java16的Record和equals用法，以及紧凑构造函数Compact Constructor很有用
     */
    class Grind752026 {
        public record GridSize(int first, int second) {
            // Java 16+ 紧凑构造函数：没有参数列表，专门用来校验或转换初始化数据
            // 不需要构造equals函数，只要改变存储一直让小的在前面就起到了相同作用
            public GridSize {
                if (first > second) {
                    int temp = first;
                    first = second;
                    second = temp;
                }
            }

            //如果非要写equals的话注意hash也得写
            @Override
            public boolean equals(Object o) {
                // 1. Check if they are the exact same instance in memory
                if (this == o) return true;

                // 2. Check if the other object is an instance of GridSize
                if (!(o instanceof GridSize other)) return false; // Uses Java 16+ Pattern Matching

                // 3. Custom logic: true if (m==m and n==n) OR (m==n and n==m)
                return (this.first == other.first && this.second == other.second) ||
                        (this.first == other.second && this.second == other.first);
            }

            @Override
            public int hashCode() {
                // Order-independent hashing: 2 + 3 yields the exact same result as 3 + 2
                return Integer.hashCode(first) + Integer.hashCode(second);
            }
        }

        public int uniquePaths(int m, int n) {
            HashMap<GridSize, Integer> pathResults = new HashMap<>();
            return uniquePaths(m, n, pathResults);
        }
        private int uniquePaths(int m, int n, HashMap<GridSize, Integer> pathResults) {
            GridSize curr = new GridSize(m, n);
            if (pathResults.containsKey(curr)) {
                return pathResults.get(curr);
            }
            if (m == 1 && n == 1) {
                pathResults.put(curr, 1);
                return 1;
            }
            if (m == 1 || n == 1) {
                pathResults.put(curr, 1);
                return 1;
            }
            int result = 0;
            if (m > 1) {
                result += uniquePaths(m - 1, n, pathResults);
            }
            if (n > 1) {
                result += uniquePaths(m, n - 1, pathResults);
            }
            return result;
        }
    }
}
