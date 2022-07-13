package Math;

/**
 * Created by Administrator on 2016/11/7.
 */
public class Math223RectangleArea {
    /**
     * Runtime: 4ms 11/7/2016
     * Rectangle 1 + Rectangle 2 - overlapping area
     * One hint to get overlapping area
     * https://discuss.leetcode.com/topic/15733/my-java-solution-sum-of-areas-overlapped-area
     */
    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        int result = (C - A)*(D - B) + (G - E)*(H - F);
        if (E < C && G > A && F< D && H > B) {//Make sure have overlapping
            int left = Math.max(A, E);
            int right = Math.min(C, G);
            int bottom = Math.max(B, F);
            int top = Math.min(D, H);
            result -= (right - left)*(top - bottom);
        }
        return result;
    }

    /**
     * Optimize the previous version by verify if right > left
     * But considering the int limit (min - max) use following method
     */
    public int computeArea2(int A, int B, int C, int D, int E, int F, int G, int H) {
        int x = Math.min(G, C) > Math.max(E, A) ? (Math.min(G, C) - Math.max(E, A)) : 0;
        int y = Math.min(D, H) > Math.max(B, F) ? (Math.min(D, H) - Math.max(B, F)) : 0;
        return (D - B) * (C - A) + (G - E) * (H - F) - x * y;
    }

}
