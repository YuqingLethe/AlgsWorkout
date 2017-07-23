package LintCode.Binary.TwoPointers;

/**
 * Created by Administrator on 2017/7/23.
 */
public class LintTP143SortColorIIRainbowSort {
    /**
     * mergeSort + quickSort 答案版本
     */
    class Solution {
        public void sortColors2(int[] colors, int k) {
            if (colors == null || colors.length <= 1) {
                return;
            }
            rainbowSort(colors, 0, colors.length - 1, 1, k);
        }
        public void rainbowSort(int[] colors,
                                int left,
                                int right,
                                int colorFrom,
                                int colorTo) {
            if (left >= right) {
                return;
            }
            if (colorFrom >= colorTo) {
                return;
            }
            int colorMid = colorFrom + (colorTo - colorFrom) / 2;
            int l = left; //不能直接用left和right这两个后面recursion还得用
            int r = right;
            while(l < r) {
                if (colors[l] <= colorMid) {
                    l ++;
                } else if (colors[r] > colorMid) {
                    r --;
                } else {
                    int tmp = colors[r];
                    colors[r] = colors[l];
                    colors[l] = tmp;
                    l ++;
                    r --;
                }
            }
            rainbowSort(colors, left, r, colorFrom, colorMid);
            rainbowSort(colors, l, right, colorMid + 1, colorTo);
        }
    }
}
