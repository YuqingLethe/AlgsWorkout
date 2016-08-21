package String;

public class String58LengthOfLastWord {
	/**
	 * Method 1: <br>
	 * Store the string to array using split()
	 * @param s
	 * @return
	 */
	public static int lengthOfLastWord(String s) {
		
	    String[] strs = s.split(" ");
	    if (strs.length == 0)
	        return 0;
	    System.out.println(strs[strs.length - 1]);
	    return strs[strs.length - 1].length();
	}
	/**
	 * Method 2: <br>
	 * Find the begin letter of the last word, 
	 * and start to count its length.
	 * @param s
	 * @return
	 */
	public static int lengthOfLastWord2(String s) {
		if (s.length() == 0)
			return 0;
		int index = s.length() - 1;
		int count = 0; //count the word length
		//Find the begin letter of the last word
		while (index >= 0 && s.charAt(index) == ' ') 
			index--;
//		if (index == -1) //all blanks
//	    	return 0;
		//Start to count the length of the last word
	    while (index >= 0 && s.charAt(index) != ' ') {
	    	count++;
	        index--;
	    }
	    return count;
	}
	
	public static void main(String[] args) {
		String s = " ";
		System.out.println(lengthOfLastWord2(s));
	}
	
}
