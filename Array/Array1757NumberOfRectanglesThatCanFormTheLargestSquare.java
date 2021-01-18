package Array;

import java.util.HashMap;

public class Array1757NumberOfRectanglesThatCanFormTheLargestSquare {
	/**
	 * 20min 20210118 主要是想到，不是最大的那个square就不需要统计个数了
	 *
	 * Runtime: 2 ms, faster than 80.00% of Java online submissions for Number Of Rectangles That Can Form The Largest Square.
	 * Memory Usage: 39.5 MB, less than 60.00% of Java online submissions for Number Of Rectangles That Can Form The Largest Square.
	 * @param rectangles
	 * @return
	 */
	public int countGoodRectangles(int[][] rectangles) {
		if (rectangles == null) {
			return 0;
		}
		int maxLen = 0;
		HashMap<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < rectangles.length; i ++) {
			int minSide = rectangles[i][0] > rectangles[i][1] ? rectangles[i][1] : rectangles[i][0];
			if (minSide >= maxLen) { //这一步是关键
				maxLen = minSide;
				if (map.containsKey(minSide)) {
					map.put(minSide, map.get(minSide) + 1);
				} else {
					map.put(minSide, 1);
				}
			}
		}
		return map.get(maxLen);
	}
	
	public static void main(String[] args) {

	}
}
