package String;

import java.util.ArrayList;

/**
 * Created by XiaoMi on 2016/9/1.
 */
public class StringM12IntegerToRoman {
    /**
     * Runtime: 8ms Use: 1.5hr 9/1/2016
     * @param num
     * @return
     */
    private static String intToRoman(int num) {
        StringBuilder result = new StringBuilder();
        if (num/1000 > 0) {//Thousands
            for (int i = 0; i < num/1000; i++) {
                result.append('M');
            }
            num = num%1000;
        }
        if (num/100> 0) {//hundreds
            result.append(intToStringByDigit2(num/100, 'M'));
            num %= 100;
        }
        if (num/10 > 0) {//tens
            result.append(intToStringByDigit2(num/10, 'C'));
            num %= 10;
        }
        if (num > 0) {//digits
            result.append(intToStringByDigit2(num/1, 'X'));
        }
        return result.toString();
    }

    private static int[] intToArray(int num) {
        char[] numCharArray = Integer.toString(num).toCharArray();

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
    //Runtime change to 9ms by using this function. 9/1/2016
    private static String intToStringByDigit2(int n, char c) {
        char[] rule = new char[3];
        if (c == 'M') {
            rule[0] = 'M';rule[1] = 'D';rule[2] = 'C';
        } else if (c == 'C') {
            rule[0] = 'C';rule[1] = 'L';rule[2] = 'X';
        } else if (c == 'X') {
            rule[0] = 'X';rule[1] = 'V';rule[2] = 'I';
        }

        StringBuilder resultSB = new StringBuilder();
        if (n == 9) {
            resultSB.append(rule[2]);
            resultSB.append(rule[0]);
        } else if (n == 4) {
            resultSB.append(rule[2]);
            resultSB.append(rule[1]);
        } else {
            if (n >= 5) {
                resultSB.append(rule[1]);
                n -= 5;
            }
            while (n != 0) {
                resultSB.append(rule[2]);
                n--;
            }
        }
        return resultSB.toString();
    }

    //TODO: BY descrete answers like C[], M[], X[], I[]

    /**
     * https://discuss.leetcode.com/topic/12384/simple-solution Answer is here
     * @param num
     * @return
     */
    private static String intToRomanByDiscreteAnswer(int num) {
        return "a";
    }
    public static void main (String[] args) {
        System.out.println(intToStringByDigit2(4, 'C'));
        System.out.println(intToRoman(1437));

    }

}
