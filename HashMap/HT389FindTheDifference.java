package HashMap;

public class HT389FindTheDifference {
    /**
     * Runtime: 6ms Use: 45min 用了一个异或的性质
     * each letter appear twice which leads to zero in the end except for the adding letter
     * Same with bits
     * https://discuss.leetcode.com/topic/61149/7ms-java-solution
     */
    public static char findTheDifferenceByBit(String s, String t) {
        char[] st=(s+t).toCharArray();
        if(st.length==0) return 0;
        int i;
        for(i=st.length-1;i>0;i--){
            st[i-1]=(char)((int)st[i]^(int)st[i-1]);
        }
        return st[i];
    }

    /**
     * Runtime: 8ms Use: 10min
     * Very smart method, get from discussion
     * @param s
     * @param t
     * @return
     */
    public static char findTheDifferenceByAddMinus(String s, String t) {
        int diff = 0;
        for (int i = 0; i < s.length(); i++) {
            diff += t.charAt(i);
            diff -= s.charAt(i);
        }
        diff += t.charAt(t.length() - 1);
        return (char) diff;
    }

    public static void main(String[] args) {
        String s = "ab";
        String t = "aeb";
        System.out.println(findTheDifferenceByAddMinus(s, t));
    }
}
