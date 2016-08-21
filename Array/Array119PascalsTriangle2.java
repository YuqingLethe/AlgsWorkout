package Array;

import java.util.ArrayList;
import java.util.List;

public class Array119PascalsTriangle2 {
	public static List<Integer> getRow(int rowIndex) {
        List<Integer> prev = new ArrayList<Integer>(); //previous row
        List<Integer> curr = new ArrayList<Integer>(); //current row
        for (int i = 0; i <= rowIndex; i++) {
        	for (int j = 0; j <= i; j++) {//build the current row
        		if (j == 0 || j == i) {
        			curr.add(1);
        		} else {
        			curr.add(prev.get(j) + prev.get(j - 1));
        		}
        	}
        	if (i != rowIndex) {//build the previous row
        		prev = curr;
        		curr = new ArrayList<Integer>();;
        	} else {
        		break;
        	}
        }
        return curr;
    }
	public static void main (String[] args) {
		List<Integer> result = new ArrayList<Integer>();
		result = getRow(3);
		for (int x : result)
			System.out.print(x + " ");
	}
}
