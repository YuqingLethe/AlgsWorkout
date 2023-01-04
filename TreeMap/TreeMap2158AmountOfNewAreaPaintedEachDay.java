package TreeMap;

import java.util.Map;
import java.util.TreeMap;

public class TreeMap2158AmountOfNewAreaPaintedEachDay {
    /**
     * Crib answer Jan 2023
     * https://leetcode.com/problems/amount-of-new-area-painted-each-day/solutions/1784268/java-treemap-merge-intervals-easy-to-understand-beats-95-83/
     * 很好的treemap的interface练习题目
     */
    class TreeMap_MergeIntervals {
        public int[] amountPainted(int[][] paint) {
            final int n = paint.length;
            int[] result = new int[n];
            TreeMap<Integer, Integer> intervals = new TreeMap<>();

            for (int i = 0; i < n; ++i) {
                int startT = paint[i][0];
                int endT = paint[i][1];
                int move = endT - startT;
                int newStartT = startT; //这两个是必须的, 应对beforeStart或afterStart为null的情况
                int newEndT = endT;

                Map.Entry<Integer, Integer> beforeStart = intervals.floorEntry(startT);
                if (beforeStart != null) {
                    if (beforeStart.getValue() >= endT) {
                        continue;
                    }
                    if (beforeStart.getValue() >= startT) {
                        move -= beforeStart.getValue() - startT;
                        intervals.remove(beforeStart.getKey());
                        newStartT = beforeStart.getKey(); //注意是key不是value, 这种题细节错了debug麻烦.
                    }
                    // beforeStart.getValue() < startT, proceed to handle the ceiling sections.
                    //注意beforeStart.getValue() < startT这种情况要继续往下走, 不能跳出循环
                }


                //注意这里容易想错, 不是找endT的ceiling. 虽然这样也可以, 但也许要在两个interval之间循环, 而且有remove的话比较麻烦. 这种情况应该直接考虑动态赋值, 所以用while找startT的ceiling
                // 我们只要找startT的ceiling, 然后每次处理这一块就行, 一直到endT处理完毕.
                Map.Entry<Integer, Integer> afterStart = intervals.ceilingEntry(startT);
                while (afterStart != null && afterStart.getKey() <= endT) {
                    move -= Math.min(endT, afterStart.getValue()) - afterStart.getKey();
                    intervals.remove(afterStart.getKey());
                    newEndT = Math.max(endT, afterStart.getValue());
                    afterStart = intervals.ceilingEntry(startT); //这里查找下一个的是startT的ceiling
                }

                intervals.put(newStartT, newEndT);
                result[i] = move;
                // System.out.println(intervals.toString());
            }
            return result;

        }
    }
}
