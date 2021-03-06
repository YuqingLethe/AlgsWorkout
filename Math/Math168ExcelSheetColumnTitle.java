package Math;

/**
 * Created by Administrator on 2016/11/7.
 */
public class Math168ExcelSheetColumnTitle {
    /**
     * Runtime:  0ms 11/7/2016
     * Division 想到了加1沒想到減去1,直接被'A' - 1先入為主了
     * https://discuss.leetcode.com/category/176/excel-sheet-column-title
     */
    public static String convertToTitle(int n) {
        int num = n;
        String answer = "";

        while(num > 0) {
            num--;
            answer = Character.toString((char)('A' + num%26)) + answer;
            num /= 26;
        }
        return answer;
    }
    //TODO: Worth to do again

    /**
     * IT's a wrong answer, but shows how I think it at the second time
     * Bad case: 703 -> AAA
     */
    public static String convertToTitle2time(int n) {
        String answer = "";
        int mod = (n - 1)%26;
        int tmp = n - 1;
        while(tmp > 26) {
            tmp = tmp/26;
            answer = Character.toString((char) ('A' + tmp%26 - 1)) + answer;
        }

        answer += Character.toString((char) ('A' + mod));
        return answer;
    }

    public static void main(String[] args) {
        System.out.println(convertToTitle2time(28));
        System.out.println(convertToTitle2time(696));
    }
}
