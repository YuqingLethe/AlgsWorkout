package UnionFind;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuqing on 7/13/22.
 */
public class UnionFind305NumberOfIslandsII {
    /**
     * July 2022  120min 自己solution自己debug， 主要是想錯了， 這個unionFind的map是動態的， 應該建立動態的map
     * 我這個UF的功能更加完整
     *
     * 答案中把uf放主程序裏面了，因爲是動態的map， 其實path不需要優化
     */
    class Solution {
        class UnionFind {
            int[] parents;
            int[] ranks;
            int count;
            public UnionFind (int N) {
                parents = new int[N];
                ranks = new int[N];
                count = 0;
                for (int i = 0; i < N; ++i) {
                    parents[i] = -1;
                    ranks[i] = 0;
                }
                // printArray(parents, "parents");
                // printArray(ranks, "ranks");
            }
            public int find (int k) {
                if (parents[k] == -1) {
                    return -1;
                }
                if (parents[k] != k) {
                    parents[k] = find(parents[k]);
                }
                return parents[k];
            }
            public void addIsland(int k) {
                parents[k] = k;
                count++;
            }
            public void union(int a, int b) {
                int rootx = find(a);
                int rooty = find(b);
                if (rootx == rooty) {
                    return;
                }
                if (ranks[rootx] > ranks[rooty]) {
                    parents[rooty] = rootx;

                } else if (ranks[rootx] < ranks[rooty]) {
                    parents[rootx] = rooty;
                } else {
                    parents[rooty] = rootx;
                    ranks[rootx] ++;
                }
                --count;
                // printArray(parents, "parents");
            }
            public int getCount() {
                return count;
            }
        }
        public List<Integer> numIslands2(int m, int n, int[][] positions) {
            int[][] matrix = new int[m][n];
            List<Integer> result = new ArrayList<>();
            int N = m * n;
            UnionFind uf = new UnionFind(N);

            for (int i = 0; i < positions.length; ++i) {
                int r = positions[i][0];
                int c = positions[i][1];
                int self = r * n + c;
                uf.addIsland(self);

                int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
                for (int[] dir : dirs) {
                    int row = r + dir[0];
                    int col = c + dir[1];
                    int next = row * n + col;
                    if (row < 0 || row >= m || col < 0 || col >= n || uf.find(next) == -1) {
                        continue;
                    }
                    uf.union(next, self);
                }

                int parent = uf.find(self);
                if (parent != self) {
                    uf.union(self, parent);
                }
                result.add(uf.getCount());
            }
            return result;
        }
    }

    public static void main(String[] args) {

    }
}
