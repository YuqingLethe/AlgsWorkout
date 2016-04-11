package happynumber;

public class Array121BestTimeToBuyAndSellStock {
	public static int maxProfit(int[] prices) {
		if (prices.length == 0 || prices.length == 1) {return 0;}
		
		/*
		//Method 1: Sort prices with date and find the maximum difference
		//Exceed time limits
        int[] days = new int[prices.length];
        //create the days array
        days[0] = 0;
        //sort the prices array with days array
        for (int i = 1; i < prices.length; i++) {
        	days[i] = i; //create the days array
        	for (int j = i; j > 0 && less(prices[j], prices[j-1]); j--) {
        		exchange (prices, days, j, j - 1);
        	}
        }
        for (int i = 0; i < prices.length; i++) {
        	System.out.print(prices[i] + " ");
        }
        System.out.println();
        for (int i = 0; i < days.length; i++) {
        	System.out.print(days[i] + " ");
        }
        System.out.println();
        //find the maximum differences
        int min = 0, max = prices.length - 1;
        while (days[min] > days[max] ) {
        	min++;
        }
        if (min == max) {
	        min = 0;
	        max = prices.length - 1;
	        while (days[min] > days[max]) {
	        	max--;
	        }
        }
        
        return prices[max] - prices[min];

        */
		//Method 2: Updating maximum profit in one loop
		int lowestPrice = prices[0];
	    int maxInterval = 0;

	    for(int i = 1; i < prices.length; i++){
	        int dif = prices[i]-lowestPrice;
	        maxInterval = Math.max(dif, maxInterval);
	        lowestPrice = Math.min(lowestPrice, prices[i]);
	    }

	    return maxInterval;
	    
    }
	private static void exchange(int[] a, int[] b, int i , int j) {
		int x = a[i];
		a[i] = a[j];
		a[j] = x;
		int y = b[i];
		b[i] = b[j];
		b[j] = y;
	}
	private static boolean less(int v, int w) {
		if (v < w)
			return true;
		else 
			return false;
		
	}
	
	public static void main(String[] args) {
		int[] a = new int[] {100,99,88,77,66};
		//100,12,3,25,3,5
		System.out.println(maxProfit(a));
	}
}
