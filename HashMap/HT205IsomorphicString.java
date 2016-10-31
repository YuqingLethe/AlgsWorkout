package HashMap;

import java.util.HashMap;

/**
 * Created by Administrator on 2016/10/31.
 */
public class HT205IsomorphicString {
    /**
     * Runtime: 20ms  Use 2min  10/31/2016
     */
    public boolean isIsomorphic(String s, String t) {
        if (s.length() == 0) return true;

        HashMap<Character, Character> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(t.charAt(i))) {
                if (map.get(t.charAt(i)) != s.charAt(i)) return false;
            } else if (map.containsValue(s.charAt(i))) {
                return false;
            } else {
                    map.put(t.charAt(i), s.charAt(i));
            }
        }
        return true;

    }
}
