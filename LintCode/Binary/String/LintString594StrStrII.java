package LintCode.Binary.String;

/**
 * Created by Administrator on
 * Implement strStr function in O(n + m) time.
 strStr return the first index of the target string in a source string. The length of the target string is m and the length of the source string is n.
 If target does not exist in source, just return -1.
 Example
 Given source = abcdef, target = bcd, return 1.
 */
public class LintString594StrStrII {
    int BASE = 100000;
    /** 2017/6/6.接近小视频的答案了. 请妥善保管这段代码
     * @param source a source string
     * @param target a target string
     * @return an integer as index
     */
    public int strStr2RobinKarp(String source, String target) {
        if (source == null || target == null) {
            return -1;
        }
        int m = target.length();
        if (m == 0) {
            return 0;
        }
        //targetCode and power = pow(31, m);
        int targetCode = 0;
        int power = 1; //Power == 1啊同学
        for (int i = 0; i < m; i++) {
            targetCode = (targetCode * 31 + target.charAt(i))%BASE;
            power = power * 31 % BASE; //忘记模base啦
        }

        //hashCode
        int hashCode = 0;
        for (int i = 0; i < source.length(); i++) {
            hashCode = (hashCode * 31 + source.charAt(i))%BASE;
            //不要忘了起始时候建立第一个substring, 不需要减去, 当i == m -1时需要去比较targetCode了!
            if (i < m - 1) {
                continue;
            }
            if (i >= m) { //竟然忘了这个条件. 不能只有上面i<m的条件,
                hashCode = hashCode - (source.charAt(i - m) * power)%BASE;
                if (hashCode < 0) {
                    hashCode += BASE;
                }
            }

            if (hashCode == targetCode
                    && source.substring(i - m + 1, i + 1).equals(target)) {
                return i - m + 1;
            }
        }
        return -1;
    }
}
