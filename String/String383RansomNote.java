package String;

/**
 * Created by XiaoMi on 2016/9/1.
 */
public class String383RansomNote {
    //Runtime: 38ms 9/1/2016 use: 10min Pass in the first time!
    private static boolean canConstruct(String ransomNote, String magazine) {
        StringBuilder magazineSB = new StringBuilder(magazine);
        for ( int i = 0; i < ransomNote.length(); i++) {
            char curr = ransomNote.charAt(i);
            boolean foundFlag = false;
            //Search the magazine and deleted the existing char
            for (int j = 0; j < magazineSB.length(); j++) {
                if (curr == magazineSB.charAt(j)) {
                    magazineSB = magazineSB .deleteCharAt(j);
                    foundFlag = true;
                    break;
                }
            }
            if (!foundFlag) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(canConstruct("", ""));
    }
}
