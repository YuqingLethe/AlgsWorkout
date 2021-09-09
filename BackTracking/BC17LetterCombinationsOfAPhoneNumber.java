package BackTracking;

import java.util.ArrayList;
import java.util.List;

public class BC17LetterCombinationsOfAPhoneNumber {
    /**
     * 爲什麼這次不有自主用了iterative而不是recursive? 沒有想清楚backtracking在這裏的具體運用.
     * 1. 善於運用全局變量, 減少出錯率
     * 2. 如果用charAt這種直接從0開始數的, 注意input爲空的時候!
     */
    class StandardBackTrackingSolution {
        private String[] pad = new String[]{"abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        private List<String> results = new ArrayList<>();
        private String phoneDigits;

        public List<String> letterCombinations(String digits) {
            if (digits.length() == 0) {
                return results;
            }
            ArrayList<ArrayList<Character>> list = new ArrayList<>();
            this.phoneDigits = digits;
            getCombo(0, new StringBuilder());

            return results;
        }

        private void getCombo(int p, StringBuilder path) {
            if (path.length() == phoneDigits.length()) {
                results.add(path.toString());
                return;
            }

            String possibleLetters = pad[phoneDigits.charAt(p) - '2'];
            for (char letter : possibleLetters.toCharArray()) {
                path.append(letter);
                getCombo(p + 1, path);
                path.deleteCharAt(path.length() - 1);
            }
        }
    }
}
