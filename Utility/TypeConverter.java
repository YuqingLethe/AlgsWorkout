package Utility;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuqing on 7/31/22.
 */
public class TypeConverter {
    private int IntegerListToInt(List<Integer> list) {
        StringBuffer sb = new StringBuffer();
        for (Integer n : list) {
            sb.insert(0, n.toString());
        }
        return Integer.parseInt(sb.toString());
    }

    private List<Integer> convertByDigitsInReverseOrder(int n) {
        List<Integer> res = new ArrayList<>();
        while (n >= 10) {
            res.add(n % 10);
            n = n / 10;
        }
        res.add(n);
        return res;
    }

    private int[] integerToPrimitiveArray(int n) {
        String temp = Integer.toString(n);
        int[] newGuess = new int[temp.length()];
        for (int i = 0; i < temp.length(); i++)
        {
            newGuess[i] = temp.charAt(i) - '0';
        }
        return newGuess;
    }

    private int intArrayToInt(int[] nums) {
        StringBuilder strNum = new StringBuilder();
        for (int num : nums) {
            strNum.append(num);
        }
        int finalInt = Integer.parseInt(strNum.toString());
        // System.out.println(finalInt);
        return finalInt;
    }
}
