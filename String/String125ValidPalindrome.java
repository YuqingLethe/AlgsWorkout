package String;

public class String125ValidPalindrome {
	public static boolean isPalindromeByCharacterBuiltIn(String s) {
		int i = 0, j = s.length() - 1;
		while(i < j) {
			char left = s. charAt(i);
			char right = s.charAt(j);
			if (!Character.isLetter(left) && !Character.isDigit(left)) { //应该用&&啊低级错误
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
		/*
			方法二: while里面套while
			 */
		return true;
	}

	/**
	 * 再次忘记比较时候的大小写区别
	 */
	public static boolean isPalindrome2019(String s) {
		char[] ca = s.toCharArray();
		int i = 0;
		int j = ca.length - 1;
		while (i < j) {
			System.out.println(i + " " + j + " => " + ca[i] + " " + ca[j]);
			if (notAlphaNumeric(ca[i])) {
				i ++;
			} else if (notAlphaNumeric(ca[j])) {
				j --;
			} else if (Character.toLowerCase(ca[i]) != Character.toLowerCase(ca[j])) {
				return false;
			} else {
				i ++;
				j --;
			}
			/*
			// 改变大小写的方法二
        	if (ca[head] < 97 && ca[head] >= 65) {
    			ca[head] += 32;
    		}
        	if (ca[tail] < 97 && ca[tail] >= 65) {
    			ca[tail] += 32;
    		}
			 */
		}
		return true;
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
		System.out.println(isPalindrome2019(s1));
		System.out.println(isPalindrome2019(s2));
	}
}
