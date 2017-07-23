package LintCode.Binary.Heap;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by Administrator on 2017/7/23.
 */

public class LintHeap612KClosestPoints {
    class Point {
        int x;
        int y;
        Point() { x = 0; y = 0; }
        Point(int a, int b) { x = a; y = b; }
    }

    /**
     * 2017/7/23
     * 我的距离结算方法和答案不一样, 因此没有过全部的test, 其他都是对的
     */
    public Point[] kClosest(Point[] points, Point orig, int k) {
        if (points == null || points.length == 0) {
            return new Point[0];
        }
        //origin在comparator中declare需要在这里声明一下, 或者变成global变量
        final Point origin = orig;
        PriorityQueue<Point> pq = new PriorityQueue<>(k, new Comparator<Point>(){
            @Override
            public int compare(Point a, Point b) {
                int diff = Math.abs(a.x - origin.x) + Math.abs(a.y - origin.y)
                        - Math.abs(b.x - origin.x) - Math.abs(b.y - origin.y);
//                int diff = Math.abs(a.x - origin.x)*Math.abs(a.x - origin.x)
//                        + Math.abs(a.y - origin.y)*Math.abs(a.y - origin.y)
//                        - Math.abs(b.x - origin.x)*Math.abs(b.x - origin.x)
//                        - Math.abs(b.y - origin.y)*Math.abs(b.y - origin.y);
                if (diff == 0) {
                    return a.x - b.x;
                }
                if (diff == 0) {
                    return a.y - b.y;
                }
                return diff;
            }
        });
        for (int i = 0; i < points.length; i++) {
            pq.offer(points[i]);
        }

        int len = Math.min(k, pq.size());
        Point[] ans = new Point[len];
        for (int i = 0; i < len; i++) {
            ans[i] = pq.poll();
        }
        return ans;
    }
}
