package String;

/**
 * Created by Yoki on 2016/4/23.
 */
public class String344ReverseString {

    /**
     * Method 1: 3ms <br>
     *     Exchange in char array;
     * @param s
     * @return
     */
    public static String reverseString(String s) {
        int i = 0; //pointer from left
        int j = s.length() - 1; //pointer from right
        char[] ca = s.toCharArray();
        while (i < j) {
            char temp = ca[i];
            ca[i] = ca[j];
            ca[j] = temp;
            i++;
            j--;
        }
        return String.valueOf(ca);
    }

    /**
     * Method 2: 12ms <br>
     * StringBuffer and substring.
     * @param s The input string
     * @return the reversed string
     */
    private static String reverseString2(String s) {
        int n = s.length();
        if ( n == 0)
            return s;
        StringBuilder strb = new StringBuilder(s);
        int i = n - 2; //pointer for copy
        int j = n; //pointer for the result array
        while (i >= 0) {
            strb.insert(j, s.substring(i, i + 1));
            i--;
            j++;
        }
        return strb.substring(n - 1);
    }

    public static void main(String[] args) {
        String s = "";
        System.out.println(reverseString2(s));
    }
}
