package NoTag;

import java.util.HashSet;
import java.util.Set;

public class No526BeautifulArrangement {
    /**
     * Runtime:    Use:  20170218
     */
    public static int countArrangement(int N) {
        int ans = 1;
        for (int i = 1; i <= N; i++) { //Iterate every number in the array
            int count = 0;
            for (int j = N; j > 0; j--) { //Iterate every possibility number
                if (j%i == 0 && i%j == 0) {
                    count++;
                }
            }
            ans *= count;
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(countArrangement(2));
    }
}
