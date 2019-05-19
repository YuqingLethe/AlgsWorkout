package String;

/**
 * Given a sentence of English, update the first letter of each word to uppercase.
 */
public class StringLint936CapitalizeFirstLetter {
    private static String capitalizeFirstLetter(String s) {
        String[] stringArray = s.split(" ");
        String result = "";
        for ( int i = 0; i < stringArray.length; i ++) {
            // substring是拿出部分string,所以不要只看0-1,替换完还要把后面的加上.
            stringArray[i] = stringArray[i].substring(0, 1).toUpperCase() + stringArray[i].substring(1);
            result = result + " " + stringArray[i]; //忘记连起来
        }
        return result;
    }
    public static void main (String[] args) {
        String s = "I don't know how to do it";
        String result = capitalizeFirstLetter(s);
        System.out.println(result);
    }
}
