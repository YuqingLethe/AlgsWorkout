package String;

import java.util.ArrayList;

/**
 * Created by XiaoMi on 2016/9/1.
 */
public class StringM12IntegerToRoman {
    /**
     * Runtime: 9ms Use: 1.5hr 9/1/2016
     * @param num
     * @return
     */
    private static String intToRoman(int num) {
        StringBuilder result = new StringBuilder();
        if (num/1000 > 0) {
            for (int i = 0; i < num/1000; i++) {
                result.append('M');
            }
            num = num%1000;
        }
        if (num/100> 0) {
            result.append(intToStringByDigit(num/100, 'M'));
            num %= 100;
        }
        if (num/10 > 0) {
            result.append(intToStringByDigit(num/10, 'C'));
            num %= 10;
        }
        if (num > 0) {
            result.append(intToStringByDigit(num/1, 'X'));
        }
        return result.toString();
    }

    private static int[] intToArray(int num) {
        char[] numCharArray = Integer.toString(num).toCharArray();
        System.out.println(numCharArray);

        int[] result = new int[numCharArray.length];
        for (int i = 0; i < numCharArray.length; i++) {
            result[i] = Character.getNumericValue(numCharArray[i]);
        }
        return result;
        //TODO: Can you do it by calculation instead of string?
    }

    private static String intToStringByDigit(int n, char c) {
        char[] rule = new char[3];
        if (c == 'M') {
            rule[0] = 'M'; rule[1] = 'D'; rule[2] = 'C';
        }else if (c == 'C') {
            rule[0] = 'C'; rule[1] = 'L';rule[2] = 'X';
        } else if (c == 'X') {
            rule[0] = 'X';rule[1] = 'V';rule[2] = 'I';
        }

        StringBuilder resultSB = new StringBuilder();
        switch(n) {
            case 9:
                resultSB.append(rule[2]);
                resultSB.append(rule[0]);
                break;
            case 8:
                resultSB.append(rule[1]);
                resultSB.append(rule[2]);
                resultSB.append(rule[2]);
                resultSB.append(rule[2]);
                break;
            case 7:
                resultSB.append(rule[1]);
                resultSB.append(rule[2]);
                resultSB.append(rule[2]);
                break;
            case 6:
                resultSB.append(rule[1]);
                resultSB.append(rule[2]);
                break;
            case 5:
                resultSB.append(rule[1]);
                break;
            case 4:
                resultSB.append(rule[2]);
                resultSB.append(rule[1]);
                break;
            case 3:
                resultSB.append(rule[2]);
                resultSB.append(rule[2]);
                resultSB.append(rule[2]);
                break;
            case 2:
                resultSB.append(rule[2]);
                resultSB.append(rule[2]);
                break;
            case 1:
                resultSB.append(rule[2]);
                break;
            default:
        }
        return resultSB.toString();
    }

    public static void main (String[] args) {
        System.out.println(intToStringByDigit(4, 'C'));
        System.out.println(intToRoman(101));

    }

}
