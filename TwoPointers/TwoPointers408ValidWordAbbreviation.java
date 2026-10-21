package TwoPointers;

/**
 * Fidelity的面经
 */
public class TwoPointers408ValidWordAbbreviation {

    /**
     * 别人的解法
     * @param word
     * @param abbr
     * @return
     */
    public boolean validWordAbbreviation(String word, String abbr) {
        if(word == null || abbr == null) return false;

        int i = 0, j = 0;

        while (i < word.length() && j < abbr.length()) {
            char c1 = word.charAt(i);
            char c2 = abbr.charAt(j);

            if (c1 == c2) { // Compare exact char
                i++;
                j++;
            } else if (Character.isDigit(c2) && c2 != '0') { // Get digit
                int skip = 0;
                while (j < abbr.length() && Character.isDigit(abbr.charAt(j))) { // get the whole number
                    skip = skip * 10 + (abbr.charAt(j) - '0');
                    j++;
                }


                i += skip;

            } else { // If char different or number start with 0
                return false;
            }
        }


        return i== word.length() && j == abbr.length(); // avoid substring
    }
}
