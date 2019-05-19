package String;

/**
 * Take Away:
 * 双指针比length - 1 - i找右边的强,准确.
 */
public class String344ReverseString {
    /**
     * Method 1: 3ms <br>
     *     Exchange in char array;
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

    /**
     * Write a function that takes a string as input and returns the string reversed.
     * Method 3: This is the Worst way to do it
     */
    public static String reverseString2019(String s) {
        char[] ca = s.toCharArray();
        for (int i = 0; i < ca.length / 2; i++) {
            char tmp = ca[i];
            ca[i] = ca[ca.length - 1 - i];
            ca[ca.length - 1 - i] = tmp;
        }
        String result = new String(ca);
        return result;
    }

    public static void main(String[] args) {
        String s = "abcd";
        String result = reverseString2019(s);
        System.out.println(result);
    }
}
