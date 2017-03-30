package BinarySearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Binary392IsSubsequence {
    /**
     * Runtime: 70ms  Use: 2min  3/30/2017
     */
    public boolean isSubsequenceGreedy(String s, String t) {
        if(s.length() == 0) return true;
        int idx = 0; //index of s
        for (int i = 0; i < t.length(); i++) {
            if (t.charAt(i) == s.charAt(idx)) {
                idx++;
            }
            if (idx == s.length())
                return true;
        }
        return false;
    }

    /**
     * TODO: try it
     * Optimize by (1) s t size  (2) length (3) _String.indexOf
     * _String.indexOf:
     * Returns the index within this string of the first occurrence of the specified character,
     * starting the search at the specified index.
     * https://discuss.leetcode.com/topic/83933/my-2ms-java-solution
     */
    public boolean isSubsequenceGreedyOptimize(String s, String t) {
        return false;
    }

    public boolean isSubsequenceGreedyRecursive(String s, String t) {
        return false;
    }

    public static boolean isSubsequenceBinaryHM(String s, String t) {

        //Build the map where key is the character, list are the positions in String t.
        HashMap<Character, List<Integer>> hm = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            if (!hm.containsKey(c)) {
                hm.put(c, new ArrayList<Integer>()); //instantiate the List
            }
            hm.get(t).add(i);
        }

        int idx = -1;
        for (int i = 0; i < s.length(); i++) {
            List tmp;
            char c = s.charAt(i);
            if (hm.containsKey(c)) {
                tmp = hm.get(c);
                idx = binaryReturnIndex(tmp, idx + 1);
                if (idx == -1) return false;
            } else {
                return false;
            }
        }
        return true;
    }
    //Return the index of String t, from List l elements
    private static int binaryReturnIndex(List l, int target) {
        int i = 0, j = l.size();
        while(i <= j) {
            int mid = (j - i)/2 + i;
            if (target < mid)
                j = mid - 1;
            else if (target > mid)
                i = mid + 1;
            else
                return mid;
        }
        return -1;
    }


    //https://discuss.leetcode.com/topic/60450/java-clean-dp-solution
    public boolean isSequenceDP (String s, String t) {
        return false;
    }

    public static void main(String[] args) {
        String s = "abc";
        String t = "bahbgdca";
        System.out.println(isSubsequenceBinaryHM(s, t));
    }
}