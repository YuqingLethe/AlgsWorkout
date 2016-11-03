package BinarySearch;

public class Binary374GuessNumberHigherOrLower {
    /**
     * Runtime: 1ms   11/2/2016
     * Attention: "i + (j - i) / 2" is faster than "(i + j) / 2" because the latter one could have integer overflow
     * (becoming negative). that could result infinite loop
     * @param n
     * @return
     */
    public int guessNumber(int n) {
        int low = 0, high = n;
        while(low <= high) {
            int mid = low + (high - low)/2;
            if (guess(mid) == 1) {
                low = mid + 1;
            } else if (guess(mid) == -1) {
                high = mid - 1;
            }else {
                return mid;
            }
        }
        return -1;
    }

    private static int guess(int n) {
        int my = 2126753390;
        if(n < my) return 1;
        else if(n > my) return -1;
        else  return 0;
    }
}
