package UnionFind;

/**
 * https://www.1point3acres.com/bbs/thread-940851-1-1.html
 *
 * 给定一个二维点列表，如果任何两个点的距离（直线）<= k，则将它们组合在一起。例如。 [P1,P2,P3]，P1 到 P2 <=k，P2 到 p3<=k，p1 到 p3>k。他们仍然在同一组中。 （距离关系是可链接的）请问你能找到多少组？
 * 这题感觉N^2没有在优化的空间了
 * 直接BFS做的，follow up讨论了一下优化，好像没法在优化了。
 */
public class UnionFind50004FindGroupByDistance {

    static class UF {
        private static int N; // number of points
        private static int K; // distance to group together
        private static int[] parents;
        private static int[] levels;
        private static int count;
        public int getGroupByDistance(int[][] points, int k) {
            this.N = points.length;
            this.K = k;
            // Init the Union Find
            this.parents = new int[N];
            this.levels = new int[N];
            this.count = N;
            for (int i = 0; i < N; ++i) {
                parents[i] = i; // parent is its own
                levels[i] = 1; // all in level 1
            }

            for (int i = 0; i < N - 1; ++i) {
                for (int j = i + 1; j < N; ++j) {
                    int[] point1 = points[i];
                    int[] point2 = points[j];
                    int parentOf1 = getParent(i);
                    int parentOf2 = getParent(j);
//                    System.out.println(i + " parents " + parentOf1 + " levels " + levels[i]);
//                    System.out.println(j + " parents " + parentOf2 + " levels " + levels[j]);
                    if (ifTwoPointsBelongSameGroup(point1, point2) && parentOf1 != parentOf2) {
                        // Put them in same group
                        if (levels[i] > levels[j]) {
                            parents[j] = parentOf1;
                        } else if (levels[i] < levels[j]){
                            parents[i] = parentOf2;
                        } else {
                            parents[i] = parentOf2;
                            levels[i] ++; //这个不能加错, 加错了就成为pa=b pa=c 但b和c没有连起来
                        }
//                        System.out.println("-----");
//                        System.out.println("-Not same parents, make them");
//                        System.out.println("-" + i + " parents " + getParent(i) + " levels " + levels[i]);
//                        System.out.println("-" + j + " parents " + getParent(j) + " levels " + levels[j]);
//                        System.out.println("-----");
                        count --;
                    }
                }

            }
            return count;
        }
        private int getParent(int i) {
//            int parent = parents[id];
//            while (parent != parents[parent] ) {
//                parent = parents[parent];
//            }
//            return parent;
            if (parents[i] != i) {
                parents[i] = getParent(parents[i]); // Path compression 比上面的写法好
            }
            return parents[i];
        }
        private boolean ifTwoPointsBelongSameGroup(int[] a, int[] b) {
            double distance = Math.sqrt((b[1] - a[1])*(b[1] - a[1]) + (b[0] - a[0])*(b[0] - a[0]));
            boolean result = true;
            if (distance > K) { //注意这里用double和k比较, 否则ceiling不准确
//                System.out.println(a[0] + " " + a[1] + ", " + b[0] + " " + b[1] + " got diff group");
                result = false;
            }
            return result;
        }
    }
    public static void main(String[] args) {
        UF solution = new UF();
        int[][] points = {
                {1,5},{5,1},{1,1},{4,4},
                {5,5},
                {10,10}
        };
        int k = 4;
        printResult(1, points, k);
        int[][] points2 = {
                {1,5},{5,1},{1,1},{4,4},
                {5,5}, {7,7}, {8,8},
                {10,10}
        };
        printResult(2, points2, k);
        int[][] points3 = {
                {1,1},
                {5,5}, {7,7}, {8,8},
                {10,10}
        };
        printResult(3, points3, k);

        int[][] points4 = {
        };
        printResult(4, points4, k);


    }
    private static void printResult(int test, int[][] points, int k) {
        UF solution = new UF();
        int answer = solution.getGroupByDistance(points, k);
        System.out.println("Test #" + test + " answer=" + answer);
    }
}
