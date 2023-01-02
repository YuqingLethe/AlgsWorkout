package UnionFind;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UnionFind50006CirclesBelongInSingleGroup {
    static class Circle {
        int x;
        int y;
        int radius;
        Circle(int a, int b, int c) {
            this.x = a;
            this.y = b;
            this.radius = c;
        }
    }

    static class UnionFindSolution {

        class UnionFind {
            private int[] parents;
            private int[] levels; // did not use
            private int count;
            UnionFind(int n) {
                this.count = n;
                this.parents = new int[n];
                this.levels = new int[n];
                for (int i = 0; i < n; ++i) {
                    parents[i] = i;
                    levels[i] = 1;
                }
            }
            public void union(int a, int b) {
                parents[b] = getParent(a);
                count --;
            }
            public int getParent(int x) {
                int parent = parents[x];
                while (parent != parents[parent]) {
                    parent = parents[parent];
                }
                return parent;
            }
            public int getCount() {
                return count;
            }
        }
        public boolean belongToSameGroup(List<Circle> list) {
            final int N = list.size();
            Collections.sort(list, (a, b) -> ((b.x - a.x)^2 + (b.y - a.y)^2));
            UnionFind uf = new UnionFind(N);
            int prev = 0;
            for (int i = 1; i < N; ++i) {
                if (isCircleConnected(list.get(prev), list.get(i))) {
                    uf.union(prev, i);
                }
                prev = i;
            }
            System.out.println("total count" + uf.getCount());
            return uf.getCount() == 1;

        }
        private boolean isCircleConnected(Circle a, Circle b) {
//            System.out.println(a.x + " " + a.y + " | " + b.x + " " + b.y);
            double distBetweenCenters = Math.sqrt((b.x - a.x)*(b.x - a.x) + (b.y - a.y)*(b.y - a.y));
            return a.radius + b.radius >= distBetweenCenters;
        }
    }

    public static void main(String[] args) throws IOException {
        Circle a = new Circle(-1,-1,1);
        Circle b = new Circle(1,1,3);
        Circle c = new Circle(2,2,2);
        Circle d = new Circle(10,10,1);
        List<Circle> list = new ArrayList<>();
        list.add(a);
        list.add(b);
        list.add(c);
        list.add(d);

        UnionFindSolution solution = new UnionFindSolution();
        boolean result = solution.belongToSameGroup(list);
        System.out.println(result);
    }
}
