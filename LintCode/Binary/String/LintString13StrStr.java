package LintCode.Binary.String;

/**
 * Created by Administrator on 2017/6/3.
 */
public class LintString13StrStr {
    /** 见Leetcode28ImplementStrStr()
     * Returns a index to the first occurrence of target in source,
     * or -1  if target is not part of source.
     * @param source string to be scanned.
     * @param target string containing the sequence of characters to match.
     */
    public int strStr(String source, String target) {
        //我自己觉得这两个if规整边界问题弄得不错
        if (target == null || source == null || source.length() < target.length()) {
            return -1;
        }
        if (target.length() == 0) {
            return 0;
        }
        for (int i = 0; i < source.length(); i++) {
            if (source.charAt(i) == target.charAt(0)
                    && source.length() - i >= target.length()) { //溢出问题不容忽视
                boolean find = true;
                for (int j = 1; j < target.length(); j++) {
                    if (source.charAt(i + j) != target.charAt(j)) {
                        find = false;
                        break;
                    }
                }
                if (find) {
                    return i;
                }
            }
        }
        return -1;
    }
}
