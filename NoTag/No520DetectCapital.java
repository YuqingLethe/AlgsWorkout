package NoTag;

public class No520DetectCapital {
    /**
     * Use: 20min Runtime: 33ms 20170218
     */
    public static boolean detectCapitalUse(String word) {
        if (word.length() == 1) return true;
        if (Character.isUpperCase(word.charAt(0)) == false && Character.isUpperCase(word.charAt(1)))
            return false;

        int allUpper = 0;
        if (Character.isUpperCase(word.charAt(0)) && Character.isUpperCase(word.charAt(1)))
            allUpper = 2;

        for (int i = 2; i < word.length(); i++) {
            if (Character.isUpperCase(word.charAt(i))) {
                allUpper++;
            }
        }
        return allUpper == word.length() || allUpper == 0;
    }

    public static void main(String[] args) {
        String word = "USA";
        System.out.println(detectCapitalUse(word));
    }
}
