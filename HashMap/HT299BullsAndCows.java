package HashMap;

import java.util.*;

/**
 * Created by Administrator on 2016/11/12.
 */
public class HT299BullsAndCows {
    /**
     * Runtime: 52ms  Use: 10min
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
     * Runtime:  Use:
     * Put secret in HashMap, and guess in LinkedLIst
     * Find the bulls and delete them in both HashMap and HashSet
     * Cannot use same method fow cows, because cannot remove element in hashMap by values. Must by keys
     *
     */
    public static String getHintByMapAndLinkedList(String secret, String guess) {
        HashMap<Integer, Character> map = new HashMap<>();
        LinkedList<Character> guessArr = new LinkedList<Character>();
        for (Integer i = 0; i < secret.length(); i++) {
            map.put(i, secret.charAt(i));
        }
        for (char c : guess.toCharArray()) {
            guessArr.add(c);
        }
        int A = 0, B = 0;
        //Calculate Bulls
        Integer k = 0; //index of guessArr
        while (k < guess.length()) {
            if (map.get(k) == null) System.out.println("ERROR");
            if (map.get(k) == guessArr.get(k)) {
                A++;
                map.remove(k);
                guessArr.remove(k);
                System.out.println("REMOVE " + k);
            } else {
                k++;
            }
        }
        //Calculate Cows. In order to remove the element, have to iterate map to find the key.
        int i = 0; //index of guessArr
        while (i < guessArr.size()) {
            Character c = guessArr.get(i);
            if (map.containsValue(c)) {
                B++;
                for (int j = 0; j < secret.length(); j++) {//remove the element from map by the value c
                    if (map.containsKey(j) && map.get(j) == c) {
                        map.remove(j);
                    }
                }
            }
            i++;
        }
        return A + "A" + B + "B";
    }

    /**
     * RUntime:
     * By making a int[10] to store frequency of numbers, we can save one pass
     * https://discuss.leetcode.com/topic/38600/my-3ms-java-solution-may-help-u
     */
    public static String getHintByFrequency(String secret, String guess) {
        int A = 0, B = 0;
        return A + "A" + B + "B";
    }

    public static void main(String[] args) {
        char c = '4';
        char d = '4';
        System.out.println(c == d);
        String secret = "1234";
        String guess = "1532";
        System.out.println(getHintByMapAndLinkedList(secret, guess));
    }
}
