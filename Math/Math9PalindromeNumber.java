package Math;

public class Math9PalindromeNumber {
    /**
     * Runtime: 191ms   10/22/2016中心思想是，v储存后半部分，x储存前半部分，
     * 比如12321在while循环之后v=123 x=12
     * 如果位数为奇数就会落到第二个if(v>x)这里从而把中间那个数去掉，所以v=12
     * https://discuss.leetcode.com/topic/40845/9-ms-java-beats-99-5-java-solutions-easy-to-understand/2
     * 巧妙在用了一个循环把后半部分的数倒过来
     */
    public boolean isPalindromeGetLatterHalf(int x) {
        if (x < 0) return false;
        if(x<10) return true;
        if(x%10==0) return false;
        if(x<100&&x%11==0) return true;
        if(x<1000&&((x/100)*10+x%10)%11==0) return true;

        int v;

        //actual logic
        v=x%10;
        x=x/10;
        while(x-v>0)
        {
            v=v*10+x%10;
            x/=10;
        }
        if(v>x){v/=10;}
        return v==x?true:false;
    }

    /**
     * Runtime: 200ms   Use:     10/22/2016
     * To avoid overflow of reversed number, leave the first digit
     * https://discuss.leetcode.com/topic/9477/o-1-space-o-lgn-time-java-solution-no-overflow-risk/2
     */
    public boolean isPalindromeGetReverseNumber(int x) {
        if (x < 0) return false;
        if (x < 10) return true;
        int r = 0; //reversed number
        int o = x; //original number

        while(o > 9) {
            int temp = o%10;
            r = r*10 + temp;
            o /= 10;
        }
        return r == x/10 && o == x%10;
    }
}
