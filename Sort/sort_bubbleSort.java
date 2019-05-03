package Sort;

import Utility.Print;

public class sort_bubbleSort extends Print {
    public void bubbleSortBetter(int[] nums) {
        boolean allSorted = false;
        for (int i = 0; i < nums.length && !allSorted; i ++) {
            allSorted = true;
            for (int j = nums.length - 1; j > i; j --) {
                if (nums[j] < nums[j - 1]) {
                    int tmp = nums[j];
                    nums[j] = nums[j - 1];
                    nums[j - 1] = tmp;
                    allSorted = false;
                    System.out.println("执行了一次j循环 i = " + i + " , j = " + j);
                }
            }
            System.out.println("执行了一次i循环, i = " + i);
        }
    }

    private void wrongAnswer1(int[] nums) {
        boolean allSorted = true;
        for (int i = 0; i < nums.length; i ++) {
            // 想清楚allSorted的优化究竟优化的哪一步
            for (int j = nums.length - 1; j > i && allSorted; j--) {
                allSorted = false;
                if (nums[j] < nums[j - 1]) {
                    int tmp = nums[j];
                    nums[j] = nums[j - 1];
                    nums[j - 1] = tmp;
                    allSorted = true;
                    System.out.println("执行了一次j循环 i = " + i + " , j = " + j);
                }
            }
            System.out.println("执行了一次i循环");
        }
    }

    public static void main(String[] args) {
        int[] roudai = {4,3,1,2};
        //     int[] roudai = {1,2,3,4};
        Print.printArray(roudai);
        sort_bubbleSort bubble = new sort_bubbleSort();
        bubble.bubbleSortBetter(roudai);
        Print.printArray(roudai);
    }
}



