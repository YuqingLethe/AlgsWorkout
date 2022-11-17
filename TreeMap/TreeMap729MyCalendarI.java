package TreeMap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;

/**
 * Questions:
 * 1. If start time is same with end time of another event, is that a valid booking?
 */
public class TreeMap729MyCalendarI {
    /**
     * Crib answer Nov 2022
     * 这个问题我没考虑到性质: 只有valid range才会被加入, 也就是treemap用start time排序足够了,
     * 因为valid time slot就保证了前面node的end time >= 后面node的start time
     *
     * 我一开始想着用Array排序, 我其实觉得也可以
     */
    class MyCalendar {
        TreeMap<Integer, Integer> tm;
        public MyCalendar() {
            tm = new TreeMap<>();
        }

        public boolean book(int start, int end) {
            Integer prevEvent = tm.floorKey(start);
            Integer nextEvent = tm.ceilingKey(start);
            if ((prevEvent == null || tm.get(prevEvent) <= start) && (nextEvent == null || nextEvent >= end)) {
                tm.put(start, end);
                Utility.PrintDataStructure.printTreeMapIntegers(tm);
                return true;
            }
            return false;
        }
    }

    class SortedList_BinarySearch {
        private List<int[]> calendar;
        public SortedList_BinarySearch() {
            calendar = new ArrayList<>();
        }

        public boolean book(int start, int end) {
            if (calendar.isEmpty()) {
                calendar.add(new int[]{start, end});
                return true;
            }
            Collections.sort(calendar, (a, b) -> (a[0] - b[0]));
            // printListInDoubleArray(calendar);
            // binary search find two slots
            int left = 0, right = calendar.size() - 1;
            while (left < right) {
                int mid = left + (right - left) / 2;
                if (calendar.get(mid)[0] < start) {
                    left = mid + 1;
                } else if (calendar.get(mid)[0] >= start) {
                    right = mid;
                } else {
                    return false;
                }
            }
            if (left > 0 && calendar.get(left)[0] > start) { //这一步自我纠正很重要, 不然binary search结果就乱了
                left --;
            }
            // System.out.println("left=" + left + " right=" + right);
            // 这一步好多判断啊, 不如treemap可以落在-1
            if ((calendar.get(left)[0] <= start && calendar.get(left)[1] > start) // 如果left开始的早结束的晚
                    || (calendar.get(left)[0] > start && calendar.get(left)[0] < end) // 如果left开始的晚但结束的早
                    || (left < calendar.size() - 1 && calendar.get(left + 1)[0] < end)) { /// 如果right开始的早
                return false;
            }
            calendar.add(new int[]{start, end});
            return true;
        }
        private void printListInDoubleArray(List<int[]> t) {
            for (int i = 0; i < t.size(); ++i) {
                System.out.print(t.get(i)[0] + "-" + t.get(i)[1] + ", ");
            }
            System.out.println();
        }
    }
    public static void main(String[] args) {
//["MyCalendar","book","book","book","book","book"]
//        [[],[37,50],[33,50],[4,17],[35,48],[8,25]]
    }
}
