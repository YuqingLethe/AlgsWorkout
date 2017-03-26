package Array;

public class Array122BestTimeToBuyAndSellStock2 {
    /**
     * Runtime: 2ms Use: 10min 3/26/2017
     * Minimum transaction times
     */
    public int maxProfit(int[] prices) {
        int l = 0, h = 1;
        int ans = 0;
        while(h < prices.length) {
            while (prices[h - 1] <= prices[h]) {
                h++;
            }
            ans += prices[h - 1] - prices[l];
            l = h;
            h++;
        }
        return ans;
    }

    /**
     * runtime: 2ms Use: 2min  3.26.2017
     * Buy and sell stock everyday if profitable
     */
    public int maxProfit2(int[] prices) {
        int ans = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            if (prices[i] < prices[i + 1]) ans += prices[i + 1] - prices[i];
        }
        return ans;
    }


}
