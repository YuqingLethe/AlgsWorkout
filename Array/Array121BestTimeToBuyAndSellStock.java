package Array;

public class Array121BestTimeToBuyAndSellStock {
	/**
	 * 2016 Crib answer solution 2: Updating maximum profit in one loop
	 */
	static class LinearSolution {
		public int maxProfit(int[] prices) {
			if (prices.length == 0 || prices.length == 1) {return 0;}
			int lowestPrice = prices[0];
			int maxInterval = 0;

			for(int i = 1; i < prices.length; i++){
				int dif = prices[i]-lowestPrice;
				maxInterval = Math.max(dif, maxInterval);
				lowestPrice = Math.min(lowestPrice, prices[i]);
			}
			return maxInterval;
		}

	}

	/**
	 * 不如上面简介但比它直观
	 */
	class Nov2022 {
		public int maxProfit(int[] prices) {
			if (prices == null || prices.length <= 1) { //注意可以是<=1 不用==0 虽然结果没错
				return 0;
			}
			int minPrice = prices[0];
			int maxProfit = 0;
			for (int i = 0; i < prices.length; ++i) {
				if (minPrice > prices[i]) {
					minPrice = prices[i];
				} else if (prices[i] - minPrice > maxProfit) { // 需要用else保证maxProfit always calculated in days after buy
					maxProfit = prices[i] - minPrice;
				}
			}
			return maxProfit;
		}
	}


	public static void main(String[] args) {
		LinearSolution solution = new LinearSolution();
		int[] a = new int[] {100,99,88,77,66};
		//100,12,3,25,3,5
		System.out.println(solution.maxProfit(a));
	}
}
