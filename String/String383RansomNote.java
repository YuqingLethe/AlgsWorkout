package String;

import java.util.HashMap;

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

    public boolean canConstruct2026(String ransomNote, String magazine) {
        if (ransomNote == null || ransomNote.length() == 0) {
            return true;
        }
        if (magazine == null || ransomNote.length() > magazine.length()) {
            return false;

        }
        HashMap<Character, Integer> map = new HashMap<>();

        // Build hashmap for Magazine
        for (int i = 0; i < magazine.length(); i ++) {
            Character magChar = magazine.charAt(i);
            map.merge(magChar, 1, (oldValue, newValue) -> oldValue + 1);
        }

        // Decrease map values if found in RansomNote
        for (int i = 0; i < ransomNote.length(); i ++) {
            Character c = ransomNote.charAt(i);
            if (map.get(c) == null) {
                return false;
            }
            map.put(c, map.get(c) - 1);
            if (map.get(c) == 0) { // If Ransom used all letter from magazine, remove from hashmap
                map.remove(c);
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(canConstruct("", ""));
    }
}
