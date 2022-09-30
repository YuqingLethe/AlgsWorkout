package Sort;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class Sort973KClosestPointsToOrigin {
    class BuildInSort_Comparator {
        public int[][] kClosest(int[][] points, int k) {
            Arrays.sort(points, (a, b) -> (getEuclideanDistance(a) - getEuclideanDistance(b)));
            int[][] result = new int[k][2];
            for (int i = 0; i < k; ++i) {
                result[i] = points[i];
            }
            return result;
        }

        public int getEuclideanDistance(int[] point) {
            int a = point[0];
            int b = point[1];
            return a*a + b*b;
        }
    }

    class OrderlessMergeSort {
        private int[][] points;
        public int[][] kClosest(int[][] points, int k) {
            this.points = points;
            sort(0, points.length - 1, k);
            return Arrays.copyOfRange(points, 0, k);
        }
        private void sort(int i, int j, int K) {
            if (i >= j) {
                return;
            }
            int randomIdx = ThreadLocalRandom.current().nextInt(i, j);
            swap(i, randomIdx);
            int mid = partition(i, j);
            int leftLength = mid - i + 1;
            if (leftLength > K) {
                sort(i, mid - 1, K);
            } else if (leftLength < K) {
                sort(mid+ 1, j, K - leftLength);
            }
            //注意如果正好是K就直接退出, 因为前K个已经排好了
        }

        // private int partition(int i, int j) { 这个写法是错误的, 因为
        //     int originalLeft = i;
        //     int pivot = getEuclideanDistance(points[i]);
        //     i++;
        //     while (i < j) {
        //         if (getEuclideanDistance(points[i]) < pivot) {
        //             i ++;
        //         } else if (getEuclideanDistance(points[j]) > pivot) {
        //             j --;
        //         } else {
        //             swap(i, j);
        //         }

        //     }
        //     swap(originalLeft, j);
        //     return j;
        // }
        private int partition(int i, int j) {
            int oi = i;
            int pivot = getEuclideanDistance(points[i]);
            i++;

            while (true) {
                while (i < j && getEuclideanDistance(points[i]) < pivot)
                    i++;
                while (i <= j && getEuclideanDistance(points[j]) > pivot)
                    j--;
                if (i >= j) break;
                swap(i, j);
            }
            swap(oi, j);
            return j;
        }
        private void swap(int i, int j) {
            int x = points[i][0];
            int y = points[i][1];
            points[i][0] = points[j][0];
            points[i][1] = points[j][1];
            points[j][0] = x;
            points[j][1] = y;
        }

        private int getEuclideanDistance(int[] point) {
            return point[0]*point[0] + point[1]*point[1];
        }
    }
}
