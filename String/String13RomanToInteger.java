package String;

/**
 * Created by XiaoMi on 2016/9/1.
 */
public class String13RomanToInteger {
    /**
     * Runtime: 10ms Use: 22min 9/1/2016 one-time pass
     * @param s
     * @return
     */
    private static int romanToInt(String s) {
        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            if (i == s.length() - 1) {
                result += returnInt(s.charAt(i));
            } else if ( returnInt(s.charAt(i)) < returnInt(s.charAt(i + 1))) {//when it is 9, 90, 900
                result += returnInt(s.charAt(i + 1)) - returnInt(s.charAt(i++));
            } else { //when left larger than right
                result += returnInt(s.charAt(i));
            }
        }
        return result;

    }
    //Compare the charaters if the left is larger than the right
    private static boolean aIsLarger(char a, char b) {
        StringBuilder romanSB = new StringBuilder("IVXLCDM");
        if (romanSB.indexOf(Character.toString(a)) > romanSB.indexOf(String.valueOf(b))) {
            return true;
        } else {
            return false;
        }
    }

    private static int returnInt(char a) {
        switch(a) {
            case 'I': return 1;
            case 'V': return 5;
            case 'X': return 10;
            case 'L': return 50;
            case 'C': return 100;
            case 'D': return 500;
            case 'M': return 1000;
            default: return 99999;
        }
    }

    public static void main(String[] args) {
        System.out.println(romanToInt("MMMCCCXXXIII"));
    }
}
