package TwoPointers;

public class TwoPointers125ValidPalindrome {
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
	static class CharArray2019 {
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
	}

	/**
	 * 2026有点拎不清了，其实脸呢直接if更简单
	 */
	class WhileInsideWhile2026 {
		public boolean isPalindrome(String s) {
			int l = 0;
			int r = s.length() - 1;
			while (l < r) { //0 1-
				while (!validChar(s, l) && l < r) { //注意这里别忘了限定l大小否则死循环了
					l = l + 1;
				}
				while (!validChar(s, r) && l < r) {
					r = r - 1;
				}
				if (l < s.length() && r > 0 && !equals(s, l, r)) {
					return false;
				}
				l ++;
				r --;
			}
			return true;
		}
		private boolean validChar(String s, int i) { // 可以传入char或者index，涉及到大小写，index更方便
			if (i < 0 || i > s.length() - 1) { //既然改成了出参i就要判断i的范围
				return false;
			}
			char c = s.charAt(i);
			c = Character.toLowerCase(c);
			if ((c <= 'z' && c >= 'a') || (c <= '9' && c >= '0')) {
				return true;
			}
			return false;
		}
		private boolean equals(String s, int x, int y) {
			char a = Character.toLowerCase(s.charAt(x));
			char b = Character.toLowerCase(s.charAt(y));
			return a == b;
		}
	}
	
	public static void main(String[] args) {
		String s1 = "A man, a [plan, a canal: Panama[";
		String s2 = "[Race a car[";
		String s3 = ".,";
		System.out.println(CharArray2019.isPalindrome2019(s1));
		System.out.println(CharArray2019.isPalindrome2019(s2));
	}
}
