package Array;

public class Array11ContainerWithMostWater {
    /**
     * Runtime: 8ms 2017/2/2
     */
    public static int maxArea(int[] height) {
        int i = 0, j = height.length - 1;
        int currHeight = Math.min(height[i], height[j]);
        int ans = (j - i) * currHeight;
        while (i < j) {
            while (i < j && height[i] <= currHeight) {
                i++;
            }
            while (j > i && height[j] <= currHeight) {
                j--;
            }
            if (i >= j) break;
            currHeight = Math.min(height[i], height[j]);
            ans = Math.max(ans, (j - i)*currHeight);
        }
        return ans;
    }
    public static void main(String[] args) {
        int[] height = {1,2,3,2};
        System.out.println(maxArea(height));
    }
}
