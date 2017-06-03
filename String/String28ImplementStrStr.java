package String;

/**
 * Created by Administrator on 2017/6/3.
 */
public class String28ImplementStrStr {
    public int strStr(String haystack, String needle) {
        if (haystack == null || needle == null || haystack.length() < needle.length()) {
            return -1; //如果haystack和needle一方为null则返回-1
        }
        if (needle.length() == 0) {
            return 0;
        }
        int j;
        for (int i = 0;i < haystack.length(); i++) {
            if (haystack.length() - i < needle.length()) { //忘记的地方 溢出!
                return -1;
            }
            for (j = 0; j < needle.length(); j++) { //将j设置为方法变量而非局部变量, 可以用于之后的判断, 而不需要用boolean标记
                if (haystack.charAt(i + j) != needle.charAt(j)) {
                    break;
                }
            }
            if (j == needle.length()) {
                return i;
            }
        }
        return -1;
    }
}
