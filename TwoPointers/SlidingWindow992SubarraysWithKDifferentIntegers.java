package TwoPointers;

import java.util.*;

public class SlidingWindow992SubarraysWithKDifferentIntegers {
    /**
     * Dec 2022 self 这个可以把整个list print
     */
    class DoubleLoop_Timeout {
        private int[] nums;
        private int k;
        private List<List<Integer>> result;
        public int subarraysWithKDistinct(int[] nums, int k) {
            if (nums == null || nums.length == 0) {
                return 0;
            }
            this.nums = nums;
            this.k = k;
            int count = 0;
            // this.result = new ArrayList<>();
            // List<List<Integer>> result = new ArrayList<>();
            for (int i = 0; i < nums.length; ++i) {
                // List<Integer> dynamicList = new ArrayList<>();
                Map<Integer, Integer> map = new HashMap<>();
                // addToResult(i, dynamicList, freqMap);
                for (int j = i; j < nums.length; ++j) {
                    int curr = nums[j];
                    // System.out.println("Curr=" + curr);
                    map.put(curr, map.getOrDefault(curr, 0) + 1);
                    if (map.size() <= k) {
                        //    dynamicList.add(curr);
                        if (map.size() == k) {
                            // result.add(new ArrayList<>(dynamicList));
                            count ++;
                        }
                    } else if (map.size() > k) {
                        // removeFromMap(map, curr);
                        break;
                    }
                }
                // removeFromMap(map, nums[i]);
            }
            // return result.size();
            return count;
        }

        private void removeFromMap(Map<Integer, Integer> map, Integer key) {
            if (map.containsKey(key)) {
                map.put(key, map.get(key) - 1);
                if (map.get(key) == 0) {
                    map.remove(key);
                }
            }
            return;
        }
    }

    /**
     * Crib answer from https://leetcode.com/problems/subarrays-with-k-different-integers/solutions/234614/java-solution-using-two-pointers-and-hashmap/
     * Dec 2022
     */
    class DoubleLoop_Timeout_OnlyCount {
        public int subarraysWithKDistinct(int[] nums, int k) {
            int first = 0;
            int count = 0;
            int second = 0;
            Set<Integer> distinct = new HashSet<>();
            while (second < nums.length) {
                if (!distinct.contains(nums[second])) {
                    distinct.add(nums[second]);
                }
                if (distinct.size() > k) {
                    distinct.clear();
                    first ++;
                    second = first;
                    continue;
                }
                if (distinct.size() == k) {
                    count ++;
                    second ++;
                    if (second == nums.length) {
                        distinct.clear();
                        first ++;
                        second = first;
                    }
                    continue;
                }
                second ++;
            }
            return count;
        }

    }

    /**
     *  https://leetcode.com/problems/subarrays-with-k-different-integers/solutions/234614/java-solution-using-two-pointers-and-hashmap/
     *  Nevsanev's answer
     *  Dec 2022  TODO: not urgent, can do it one more time
     */
    class TreeSet_Map {

        public int subarraysWithKDistinct(int[] nums, int k) {
            int count = 0;
            Map<Integer, Integer> val2idx = new HashMap<>();
            TreeSet<Integer> index = new TreeSet<>();
            int left_boundary = -1;

            for (int i = 0; i < nums.length; ++i) {
                int currNum = nums[i];
                if (val2idx.containsKey(currNum)) { // update to latest index
                    index.remove(val2idx.get(currNum));
                    val2idx.put(currNum, i);
                    index.add(i);
                } else {
                    index.add(i);
                    val2idx.put(currNum, i);
                    if (index.size() > k) {
                        left_boundary = index.first(); // change to smallest index
                        index.remove(left_boundary);
                        val2idx.remove(nums[left_boundary]);
                    }
                }
                if (index.size() == k) {
                    System.out.println(val2idx.toString());
                    System.out.println(index.toString());
                    System.out.println("left_boundary=" + left_boundary);
                    count += index.first() - left_boundary; //这个地方很巧妙, 每次到distinct的最大值则计算所有符合条件的subarray的数量
                }
            }
            return count;
        }

    }

}
