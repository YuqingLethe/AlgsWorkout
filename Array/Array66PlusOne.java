package Array;

public class Array66PlusOne {
	public int[] plusOne (int[] digits) {
/*	        if(digits == null){ //is it necessary?
	            return null;
	        }
	        // check if it is all 9 boolean allNine = true;
	        for(int digit : digits){
	            if(digit!=9){
	                allNine = false;
	            }
	        }

	        if(allNine){
	            int result[] = new int[digits.length+1];
	            result[0] = 1;
	            return result;
	        }else {
	            for(int i=digits.length-1;i>=0;i--){
	                if(digits[i]<9){
	                    digits[i]++;
	                    break;
	                }else{
	                    digits[i]=0;

	                }
	            }
	        }
	        return digits;
	        */
		
		//The array plus one
		digits[digits.length - 1] += 1;
		for (int i = digits.length - 1; i >= 0; i--) {
			if (digits[i] == 10 && i != 0) {
				digits[i - 1] += 1;
				digits[i] = 0;
			}
			if (digits[i] == 10 && i == 0) {
				int[] newdigits = new int[digits.length + 1];
				newdigits[0] = 1;
				return digits = newdigits;
				
			}
		}
		return digits;
	    }

	public static void main(String[] args){
        Array66PlusOne tester = new Array66PlusOne();
        int[] input = {9, 9};
        int[] output = tester.plusOne(input);
        for(int i: output){
            System.out.print(i);
        }
    }
}
