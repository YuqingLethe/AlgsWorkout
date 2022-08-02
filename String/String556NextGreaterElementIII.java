package String;

/**
 * 這個思路做過， 但錯的一塌糊塗
 */
public class String556NextGreaterElementIII {
    class LinearSolution {
        public int nextGreaterElement(int n) {
            int[] num = integerToPrimitiveArray(n);
            // 答案方式： char[] a = ("" + n).toCharArray();
            int N = num.length;
            int i = N - 2;
            while (i >= 0 && num[i] >= num[i + 1]) {
                i--;
            }
            if (i < 0) {
                return -1;
            }

            // 注意這裏不能用下面這種，我們的目的是找比i位大的最末端，而不是
            // failed case 12 44 33 22
            // int j = i + 1;
            // while (j < N && num[j] >= num[i]) {
            //     j ++;
            // }
            int j = N - 1;
            while (j >= 0 && num[j] <= num[i]) {
                j --;
            }
            swap(num, i, j);
            reverse(num, i + 1, N - 1);
            try {
                return intArrayToInt(num);
                // 答案 return Integer.parseInt(new String(a));
            } catch (Exception e) {
                return -1;
            }
        }
        private void swap(int[] num, int aIdx, int bIdx) {
            int tmp = num[aIdx];
            num[aIdx] = num[bIdx];
            num[bIdx] = tmp;
        }
        private void reverse(int[] nums, int from, int to) {
            while (from < to) {
                swap(nums, from, to);
                from ++;
                to --;
            }

        }
        private int[] integerToPrimitiveArray(int n) {
            String temp = Integer.toString(n);
            int[] newGuess = new int[temp.length()];
            for (int i = 0; i < temp.length(); i++)
            {
                newGuess[i] = temp.charAt(i) - '0';
            }
            return newGuess;
        }
        private int intArrayToInt(int[] nums) {
            StringBuilder strNum = new StringBuilder();
            for (int num : nums) {
                strNum.append(num);
            }
            int finalInt = Integer.parseInt(strNum.toString());
            // System.out.println(finalInt);
            return finalInt;
        }
    }
    public static void main(String[] args) {
        int test1 = 2147483486; // result -1
        int test2 = 2147483476; // result 2147483647
    }
}
