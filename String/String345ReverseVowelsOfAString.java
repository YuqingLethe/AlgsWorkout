package String;

import java.lang.String;

public class String345ReverseVowelsOfAString {
    public static  String reverseVowels(String s) {
        int i = 0; // pointer from left
        int j = s.length() - 1; // pointer from right
        char[] cs = s.toCharArray();
        while (i < j) {
            while (i < j && !isVowel(cs[i])) {
                i++;
            }
            while (i < j && !isVowel(cs[j])) {
                j--;
            }
            char temp = cs[i];
            cs[i] = cs[j];
            cs[j] = temp;
            i++;
            j--;
        }
        return String.valueOf(cs);
    }
    private static boolean isVowel(char c) {
        if (c == 'a' || c == 'A' || c == 'e' || c == 'E' || c == 'i'
                || c == 'I' || c == 'o' || c == 'O' || c == 'u' || c =='U')
            return true;
        else
            return false;
    }
    public static void main (String[] args) {
        String s = "e";
        System.out.println(reverseVowels(s));
        System.out.println(s);
    }
}
