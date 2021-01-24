package String;

/**
 * You are given a string time in the form of hh:mm, where some of the digits in the string are hidden (represented by ?).
 *
 * The valid times are those inclusively between 00:00 and 23:59.
 *
 * Return the latest valid time you can get from time by replacing the hidden digits.
 *
 *
 *
 * Example 1:
 *
 * Input: time = "2?:?0"
 * Output: "23:50"
 * Explanation: The latest hour beginning with the digit '2' is 23 and the latest minute ending with the digit '0' is 50.
 * Example 2:
 *
 * Input: time = "0?:3?"
 * Output: "09:39"
 * Example 3:
 *
 * Input: time = "1?:22"
 * Output: "19:22"
 */
public class String5661LatestTimeByReplacingHiddenDigits {
    /**
     * 20210123 Weekly context 20min
     * 忽略了一个条件！
     * @param time
     * @return
     */
    public String maximumTime(String time) {
        // 20min
        int h1 = time.charAt(0) == '?' ? '?' : time.charAt(0) - '0';
        int h2 = time.charAt(1) == '?' ? '?' : time.charAt(1) - '0';
        int m1 = time.charAt(3) == '?' ? '?' : time.charAt(3) - '0';
        int m2 = time.charAt(4) == '?' ? '?' : time.charAt(4) - '0';
        if (h1 == '?') {
            if (h2 != '?' && h2 > 3) { //这里之前忽略了！！！
                h1 = 1;
            } else{
                h1 = 2;
            }
        }
        if (h2 == '?') {
            if (h1 == 2) {
                h2 = 3;
            } else {
                h2 = 9;
            }
        }
        if (m1 == '?') {
            m1 = 5;
        }
        if (m2 == '?') {
            m2 = 9;
        }
        return Integer.toString(h1) + Integer.toString(h2) + ':' + Integer.toString(m1) + Integer.toString(m2);

    }

    public static void main(String[] args) {
        String s1 = "?0:15";
        String s2 = "?3:?0";
    }
}
