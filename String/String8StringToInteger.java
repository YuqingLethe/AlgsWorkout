package String;
public class String8StringToInteger {
    /**
     * Not pass 5/23/2016
     */
    private static int myAtoi(String str) {
        char[] ca = str.toCharArray();
        int result = 0; //store the Integer result
        int index = 0; //the index of ca
        byte negFlag = 0; //if negative
        if(ca.length == 0)
            return 0;
        while(index < ca.length) {
            if (Character.isDigit(ca[index])) { //cal result with digits
                if (result == 0 && ca[index] == '0') {
                } else {
                    result = result * 10 + ca[index] - '0';
                }
            } else if (result == 0){ //deal with symbols before digits
                if (index == 0) {
                    if (ca[index] == '-') {
                        negFlag = 1;
                    } else if (!(ca[index] == ' ' || ca[index] == '+')) {
                    }
                } else {
                    return 0;
                }
            }
            index++;
        }
        if (negFlag == 1)
            result = 0 - result;
        return result;
    }

    /**
     * Runtime: 52ms   Use: 85min  10/22/2016
     */
    private static int myAtoiByIf(String str) {
        if (str == null || str.equals("")) return 0;
        char[] ca = str.toCharArray();
        int i = 0; //index of charArray ca
        boolean neg = false;
        double result = 0;

        while (i < ca.length) {
            if (Character.isDigit(ca[i])) {
                result = result*10 + ca[i] - '0';
                if (i < ca.length - 1 && !Character.isDigit(ca[i + 1])) break;
                i++;
                continue;
            }
            if (ca[i] != '+' && ca[i] != '-' && ca[i] != ' ') return 0;

            if (ca[i] == ' ') {
                i++;
                continue;
            }
            if (ca[i] == '-' || ca[i] == '+') {
                if (i < ca.length - 1) {
                    if (Character.isDigit(ca[i + 1])) {
                        if (ca[i] == '-') { neg = true; }
                        i++;
                        continue;
                    } else {
                        break;
                    }
                }
            }
        }

        //OutOfBounds
        if (neg) {
            if (-result < Integer.MIN_VALUE)
                return Integer.MIN_VALUE;
            return (int) -result;
        } else {
            if (result > Integer.MAX_VALUE)
                return Integer.MAX_VALUE;
            return (int) result;
        }
    }

    /**
     * Optimization of myAtoiByIf()
     * Runtime: 42ms Use: 15min 10/22/2016
     * https://discuss.leetcode.com/topic/62614/my-java-solution
     */
    private static int myAtoiByTrim(String str) {
        if (str == null || str.length() == 0) return 0;
        str = str.trim();
        int i = 0; //index of charArray ca
        boolean neg = false;
        long result = 0;

        if (str.charAt(0) == '-' || str.charAt(0) == '+') {
            i++;
            if (str.charAt(0) == '-') neg = true;
        }
        while(i < str.length() && Character.isDigit(str.charAt(i))) {
            result = result*10 + str.charAt(i) - '0';
            i++;
            if (result > Integer.MAX_VALUE) {
                return neg ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            }
        }

        return (int) (neg ? -result : result);

    }

    /**
     * TODO: Use Count++ and -- to get rid of blank and invalid conditions
     * https://discuss.leetcode.com/topic/55866/3ms-java-really-easy-to-understand
     */
    private static int myAtoiByIf2(String str) {
        return 1;
    }
    public static void main(String[] args) {
        String s = "9223372036854775809";
        System.out.println(myAtoiByTrim(s));
    }
}
