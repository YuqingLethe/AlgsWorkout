 package Math;

public class Math171ExcelSheetColumnNumber {
    //Runtime: 2ms  O(n) 9/28/2016
    public int titleToNumberByChar(String s) {
        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            result = result*26 + s.charAt(i) - 64;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println('A' - 1);
    }
}
