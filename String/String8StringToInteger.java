package String;
public class String8StringToInteger {
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
    public static void main(String[] args) {
        String s = "+-023";
        System.out.println(myAtoi(s));
    }
}
