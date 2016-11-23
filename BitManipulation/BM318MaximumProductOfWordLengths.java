package BitManipulation;

public class BM318MaximumProductOfWordLengths {
    /**
     * Runtime: TLE  Brute Force 20161121 Use: 5min
     */
    public static int maxProductBruteForce(String[] words) {
        int answer = 0;
        for (int i = 0; i < words.length - 1; i++) {
            for (int j = i + 1; j < words.length; j++) {
                boolean commonFlag = false;
                for (int k = 0; k < words[j].length(); k++) {
                    if (words[i].indexOf(words[j].charAt(k)) > -1) {
                        commonFlag = true;
                        break;
                    }
                }
                if(!commonFlag) {
                    answer = answer > words[i].length()*words[j].length() ?
                            answer : words[i].length()*words[j].length();
                }
            }
        }
        return answer;
    }

    /**
     *
     * @param words
     * @return
     */
    public static int maxProductByBit(String[] words) {
        int answer = 0;
        //TODO
        return answer;
    }
    public static void main(String[] args) {
        String s = "abcdz";
        String t = "efghz";
        int num = 0;
        for (char c : s.toCharArray()) {
            num |= 1 << (c - 'a');
            System.out.println(c + "-> "+ num);
        }
        System.out.println(Integer.toBinaryString(num));
        System.out.println(num);

         int hi = 0;
        for (char c : t.toCharArray()) {
            hi |= 1 << (c - 'a');
        }
        System.out.println(Integer.toBinaryString(hi));
        System.out.println(hi);
    }
}
