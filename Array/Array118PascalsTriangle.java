package Array;

import java.util.ArrayList;
import java.util.List;

public class Array118PascalsTriangle {
	public static List<List<Integer>> generate(int numRows) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		//create the following rows		
        for (int i = 0; i < numRows; i++) {
        	List<Integer> newrow = new ArrayList<Integer>();
        	for (int j = 0; j < i + 1; j++) {
        		if (j == 0 || j == i) {
        			newrow.add(1);
        		} else {
        			List<Integer> lastrow = result.get(i - 1);
        			newrow.add(lastrow.get(j) + lastrow.get(j - 1));
        		}       		
        	}	
        	result.add(newrow);
        }
        return result;
        
    }
	
	public static void main (String[] args) {
		List<List<Integer>> result = generate(9);
		
		for (int i = 0; i < result.size(); i++) {
			System.out.println("The " + i + "th row is: ");
			List<Integer> row = result.get(i);
			for (int j : row)
				System.out.print(j + " ");
			System.out.println();
		}
		
	}
}
