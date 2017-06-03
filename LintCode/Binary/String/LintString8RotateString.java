package LintCode.Binary.String;

/**
 * Created by Administrator on 2017/6/2.
 */
public class LintString8RotateString {
    /**
     * @param str: an array of char
     * @param offset: an integer
     * @return: nothing
     */
    public void rotateString(char[] str, int offset) {
        if (str != null && str.length > 0) { //见答案, 更清晰的方法处理无返回值方法的边界情况

            if (offset > str.length) { //完全忘记了溢出情况, 很不应该!!
                offset %= str.length;
            }

            for (int i = 0; i < offset; i++) {
                char tmp = str[str.length - 1]; //每次都是储存最后一个, 和offset没关系哦
                for (int j = str.length - 1; j > 0; j--) {
                    str[j] = str[j - 1];
                }
                str[0] = tmp;
            }
        }
    }

    public void rotateStringJiuzhangAnswer(char[] str, int offset) {
        // write your code here
        if (str == null || str.length == 0)
            return;

        offset = offset % str.length;
        reverse(str, 0, str.length - offset - 1);
        reverse(str, str.length - offset, str.length - 1);
        reverse(str, 0, str.length - 1);
    }

    private void reverse(char[] str, int start, int end) {
        for (int i = start, j = end; i < j; i++, j--) {
            char temp = str[i];
            str[i] = str[j];
            str[j] = temp;
        }
    }
}
