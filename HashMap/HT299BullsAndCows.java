package HashMap;

import java.util.*;

/**
 * Created by Administrator on 2016/11/12.
 */
public class HT299BullsAndCows {
    /**
     * Runtime: 52ms  Use: 10min  11/12/2016
     */
    public static String getHintByArray(String secret, String guess) {
        char[] sarr = secret.toCharArray();
        char[] garr = guess.toCharArray();
        //Mark the bulls
        for (int i = 0; i < sarr.length; i++) {
            if (sarr[i] == garr[i]) {
                sarr[i] = 'a';
                garr[i] = 'a';
            }
        }
        //Mark the cows
        for (int i = 0 ;i < sarr.length; i++) {
            if (sarr[i] != 'a' && sarr[i] != 'b') {
                for (int j = 0; j < garr.length; j++) {
                    if (garr[j] != 'a' && garr[j] != 'b' && sarr[i] == garr[j]) {
                        garr[j] = 'b';
                        sarr[i] = 'b';
                    }
                }
            }
        }
        //Count and output
        int A = 0, B = 0;
        for (int i = 0; i < sarr.length; i++) {
            if (sarr[i] == 'a') A++;
            if (sarr[i] == 'b') B++;
        }
        return A + "A" + B + "B";
    }

    /**
     * Runtime: 200ms Use: 1.25 hr for false methods and 5 min for the right methods  11/12/2016
     * Put secret in HashMap, and guess in LinkedLIst
     * Find the bulls and delete them in both HashMap and HashSet
     * Cannot use same method fow cows, because cannot remove element in hashMap by values. Must by keys
     *
     */
    public static String getHintByMapsIndexAsKey(String secret, String guess) {
        HashMap<Integer, Character> smap = new HashMap<>();
        HashMap<Integer, Character> gmap = new HashMap<>();
        for (Integer i = 0; i < secret.length(); i++) {
            smap.put(i, secret.charAt(i));
        }
        for (Integer i = 0; i < secret.length(); i++) {
            gmap.put(i, guess.charAt(i));
        }
        int A = 0, B = 0; //number of bulls and cows
        //Get Bulls
        for (int i = 0; i < secret.length(); i++) {
            if (smap.get(i) == gmap.get(i)) {
                A++;
                smap.remove(i);
                gmap.remove(i);
            }
        }

        //Calculate Cows.
        for (int i = 0; i < secret.length(); i++) {
            if (smap.containsKey(i)) {
                Character c = smap.get(i);
                for (int j = 0; j < secret.length(); j++) {//remove the element from map by the value c
                    if (gmap.containsKey(j) && gmap.get(j) == c) {
                        B++;
                        gmap.remove(j);
                        smap.remove(i);
                        break;
                    }
                }
            }
        }
        return A + "A" + B + "B";
    }

    /**
     * Runtime: 3ms  Use: 8min  11/12/2016
     * By making a int[10] to store frequency of numbers, we can save one pass
     * https://discuss.leetcode.com/topic/38600/my-3ms-java-solution-may-help-u
     */
    public static String getHintByFrequency(String secret, String guess) {
        int A = 0, B = 0;
        int[] secretFreq = new int[10];
        int[] guessFreq = new int[10];
        for (int i = 0; i < secret.length(); i++) {
            if (secret.charAt(i) == guess.charAt(i)) {
                A++;
            } else {
                secretFreq[secret.charAt(i) - '0']++;
                guessFreq[guess.charAt(i) - '0']++;
            }
        }
        for (int i = 0; i < 10; i++) {
            B += Math.min(secretFreq[i], guessFreq[i]);
        }
        return A + "A" + B + "B";
    }

    /**
     * Runtime: 15ms  Use:  20min
     * Main Idea: B = ALL - A  from Stan?  Use one HashMap for frequency
     * My Post: https://discuss.leetcode.com/topic/67559/b-all-a-hashmap-one-pass-java-solution-with-explanation
     */
    public static String getHintByMapsFrequencyAsValue(String secret, String guess) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (char c : secret.toCharArray()) { //build the map
            map.put(c, map.containsKey(c) ? map.get(c) + 1 : 1);
        }

        int A = 0, all = 0; //number of bulls and total number of both-side existing chars
        for (int i = 0; i < guess.length(); i++) {
            Character c = guess.charAt(i);
            //Increment of bulls
            if (c == secret.charAt(i)) { //Attention: char to Character
                A++;
            }
            //Calculate the total frequency of all appearance of existing chars in guess (ALL)
            if (map.containsKey(c)) {
                all++;
                if (map.get(c) - 1 == 0) map.remove(c);
                else map.put(c, map.get(c) - 1);
            }
        }
        return A + "A" + (all - A) + "B";
    }

    public static void main(String[] args) {
        char c = '4';
        char d = '4';
        System.out.println(c == d);
        String secret = "1110";
        String guess =  "0111";
        System.out.println(getHintByMapsFrequencyAsValue(secret, guess));
    }
}
