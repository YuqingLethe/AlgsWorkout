package String;

public class String14LongestCommonPrefix {
    /**
     * Runtime: 13ms Use: 10min  11/13/2016
     */
    public String longestCommonPrefixHorizontal1(String[] strs) {
        if (strs.length == 0) return "";
        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++) {
            int pointer = 0;
            while (pointer < Math.min(strs[i].length(), prefix.length())) {
                if (prefix.charAt(pointer) != strs[i].charAt(pointer)) {
                    break;
                }
                pointer++;
            }
            prefix = prefix.substring(0, pointer);
        }
        return prefix;
    }

    /**
     * Runtime: 11ms Use: 2min The approach given by tutorial
     */
    public String longestCommonPrefixHorizontal2(String[] strs) {
        if (strs.length == 0) return "";
        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++) {
            while (strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.length() == 0) return "";
            }
        }
        return prefix;
    }

    /**
     * Runtime: 11ms Use: 5min 11/13/2016
     * TODO: Try the method in tutorial, don't use minLen
     */
    public String longestCommonPrefixVertical(String[] strs) {
        if (strs == null) return "";
        int minLen = strs[0].length();
        for (String str : strs) {
            if (str.length() < minLen) minLen = str.length();
        }
        String prefix = "";
        for (int i = 0; i < minLen; i++) {
            char c = strs[0].charAt(i);
            for (String str: strs) {
                if (str.charAt(i) != c) return prefix;
            }
            prefix += c;
        }
        return prefix;
    }

    /**
     * Runtime: 10ms  Use: 20min 11/13/2016
     *
     */
    public String longestCommonPrefixBinarySearch(String[] strs) {
        if (strs.length == 0) return "";
        int start = 1, end = strs[0].length();
        while(start <= end) {
            int mid = start + (end - start)/2;
            if (isCommonPrefix(strs, mid)) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        if (end == 0) return "";
        else return strs[0].substring(0, start - 1);
    }
    private boolean isCommonPrefix (String[] strs, int len) {
        String prefix = strs[0].substring(0, len);
        for (int i = 1; i < strs.length; i++) {
            if (len > strs[i].length() || !strs[i].substring(0, len).equals(prefix))
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        String myName = "halftime";
        myName = myName.substring(0,4)+'x'+myName.substring(5);
        char c = 'x';
        String s = "" + c;
        System.out.println(s);
    }
}
