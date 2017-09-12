package String;

public class String125ValidPalindrome {
	public static boolean isPalindrome(String s) {
        /*
        //Method 1.1 12ms      s.charAt(index) AND Character methods; 
        if (s.length() == 0 || s.length() == 1)
            return true;
        int front = 0;
        int end = s.length() - 1;
        while (front < end) {
            while (!(Character.isDigit(s.charAt(front)) || Character.isLetter(s.charAt(front))) && front < end) {
                front++;//This place,use "if, continue", we don't need the 'front<end' judegement
            }
            while (!(Character.isDigit(s.charAt(end)) || Character.isLetter(s.charAt(end))) && front < end) {
                end--;
            }
            if (Character.toLowerCase(s.charAt(front)) != Character.toLowerCase(s.charAt(end)))
                return false;
            front++;
            end--;
        }
        return true;
        
        //Method 1.2  13ms      s.charAt(index);Optimize by 'continue' 
        if (s.length() == 0 || s.length() == 1)
            return true;
        int front = 0;
        int end = s.length() - 1;
        while (front < end) {
            if (!(Character.isDigit(s.charAt(front)) || Character.isLetter(s.charAt(front)))) {
                front++;
                continue;
            }
            if (!(Character.isDigit(s.charAt(end)) || Character.isLetter(s.charAt(end)))) {
                end--;
                continue;
            }
            if (Character.toLowerCase(s.charAt(front)) != Character.toLowerCase(s.charAt(end)))
                return false;
            front++;
            end--;
        }
        return true;*/
        
        //Method2: Compare by char's ASCII value
        if (s.length() == 0)
        	return true;
        char[] ca = s.toCharArray();
        int head = 0;
        int tail = ca.length - 1;
        while (head < tail) {
        	if (notAlphaNumeric(ca[head])) {
        		head++;
        		continue;
        	}
        	if (notAlphaNumeric(ca[tail])) {
        		tail--;
        		continue;
        	}
        	if (ca[head] < 97 && ca[head] >= 65) {
    			ca[head] += 32;
    		}
        	if (ca[tail] < 97 && ca[tail] >= 65) {
    			ca[tail] += 32;
    		}
        	if (ca[head] != ca[tail]) {
        		return false;
        	} else {
        		head++;
        		tail--;
        	}	
        }
        return true;
    }

	public class SolutionLintcode {
		public boolean isPalindrome(String s) {
			// Write your code here
			int i = 0, j = s.length() - 1;
			while(i < j) {
				char left = s. charAt(i);
				char right = s.charAt(j);

				if (!Character.isLetter(left) && !Character.isDigit(left)) { //9/12应该用&&啊低级错误
					i++;
					continue;
				}
				if (!Character.isLetter(right) && !Character.isDigit(right)) {
					j--;
					continue;
				}
				if (Character.toLowerCase(left) != Character.toLowerCase(right)) {
					return false;
				}
				i++;
				j--;
			}
			return true;
		}
	}
	
	private static boolean notAlphaNumeric(char x) {
		if ((x >= 48 && x <= 57) || (x >= 65 && x <= 90) || (x >= 97 && x <= 122)) {
			return false;
		} else {
			return true;
		}
	}
	
	public static void main(String[] args) {
		String s1 = "A man, a [plan, a canal: Panama[";
		String s2 = "[Race a car[";
		System.out.println(isPalindrome(s2));
	}
}
