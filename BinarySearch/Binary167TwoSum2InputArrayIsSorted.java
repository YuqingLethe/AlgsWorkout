package BinarySearch;

import java.util.Arrays;

/**
 * Created by Administrator on 2016/11/20.
 */
public class Binary167TwoSum2InputArrayIsSorted {
    /**
     * Runtime: 1ms  Use: 17min  11/20/2016
     * purely two pointers
     */
    public static int[] twoSumByTwoPointers1(int[] numbers, int target) {
        int[] answer = new int[2];
        int i = 0, j = numbers.length - 1; //start and end pointers
        while(j >= 0 && i < numbers.length) {
            if (numbers[j] >= target) j--;
            else {
                long sum = numbers[i] + numbers[j]; //Overflow avoid
                if (sum > target) j--;
                else if (sum < target) i++;
                else {
                    answer[0] = Math.min(i, j) + 1;
                    answer[1] = Math.max(i, j) + 1;
                    return answer; //Should use break to be clearer
                }
            }
        }
        return answer;
    }
    //Reduce redundant consideration https://discuss.leetcode.com/topic/6229/share-my-java-ac-solution

    /**
     * Runtime: 2ms Optimize of twoSumByTwoPointers1()
     * Reduce redundant consideration https://discuss.leetcode.com/topic/6229/share-my-java-ac-solution
     */
    public static int[] twoSumByTwoPointers2(int[] numbers, int target) {
        int i = 0, j = numbers.length - 1;
        int[] answer = new int[2];
        while(i < j) {
            long sum = numbers[i] + numbers[j];
            if (sum > target) --j;
            if (sum < target) ++i;
            if (sum == target) {
                answer[0] = i + 1;
                answer[1] = j + 1;
                break;
            }
        }
        return answer;
    }

    /**
     * Runtime: 1ms  Use: 30min 11/20/2016
     * n
     */
    public static int[] twoSumByBinarySearchAndTwoPointers(int[] numbers, int target) {
        int[] answer = new int[2];
        int start = 0, end = numbers.length - 1;
        while(numbers[end] > target && start <= end) {
            int mid = start + (end - start)/2;
            if (numbers[mid] > target) end = mid - 1;
            if (numbers[mid] < target) start = mid + 1;
            if (numbers[mid] == target) {
                end = mid;
                break;
            }
        }
        start = 0;
        while(numbers[start] + numbers[end] != target) {
            if (numbers[start] + numbers[end] > target) --end;
            else ++start;
        }
        answer[0] = Math.min(start, end) + 1;
        answer[1] = Math.max(start, end) + 1;
        return answer;
    }

    /**
     * Other's    Runtime: 3ms
     * My own binarySearch() have TLE problem
     * https://discuss.leetcode.com/topic/6229/share-my-java-ac-solution/13
     */
    public static int[] twoSumByBinarySearch(int[] numbers, int target) {
        for (int i = 0, n = numbers.length; i < n - 1; i++) {
//            int j = Arrays.binarySearch(numbers, i + 1, n, target - numbers[i]);
            int j = binarySearch(numbers, i + 1, n, target - numbers[i]);
            if (j > 0) return new int[]{i + 1, j + 1};
        }
        return null;
    }
    //toIndex is exclusive to be searched
    private static int binarySearch(int[] numbers, int fromIndex, int toIndex, int target) {
        int start = fromIndex;
        int end = toIndex - 1;
        while (start <= end) {
            int mid = start + (end - start)/2;
            if (numbers[mid] < target) start = mid + 1;
            else if (numbers[mid] > target) end = mid - 1;
            else return mid;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] n = {0,0,4,5,7,8,9,19};
        int[] answer = twoSumByBinarySearch(n, 19);
        System.out.println(answer[0] + " " + answer[1]);

    }

}
