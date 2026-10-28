package HashMap;

import java.util.*;

public class HM49GroupAnagrams {
    /**
     * 2026 gogle了语法一遍过，极致的syntax考验，遍历map 遍历array，熟用map，String和Array
     */
    class Solution2026 {
        public List<List<String>> groupAnagrams(String[] strs) {
            Map<String, ArrayList<Integer>> map = new HashMap<>(); // sorted string - list of indexses of annagrams
            for (int i = 0; i < strs.length; i ++) {

                // Sort by characters and to String
                char[] charArray = strs[i].toCharArray();
                Arrays.sort(charArray); //其实用Sorted Set更好
                String s = new String(charArray);

                // Add to map
                if (map.containsKey(s)) {
                    map.get(s).add(i);
                } else {
                    ArrayList<Integer> idxArray = new ArrayList<>();
                    idxArray.add(i);
                    map.put(s, idxArray);
                }
            }


            // Convert to result
            List<List<String>> result = new ArrayList<>();
            for (ArrayList<Integer> indexes : map.values()) {
                List<String> stringArray = new ArrayList<>();
                for (Integer idx : indexes) {
                    stringArray.add(strs[idx]);
                }
                result.add(stringArray);
            }
            return result;
        }
    }
}
