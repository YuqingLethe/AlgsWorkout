package String;


import java.util.ArrayList;


public class String68AddBinary {
    //Didn't pass a long string test
    //8/29/2016
    public static String addBinaryByDecimal(String a, String b) {
        Long decimalSum = Long.parseLong(a, 2) + Long.parseLong(b, 2);

        return Long.toBinaryString(decimalSum);
    }
    //Runtime: 12ms
    //8/29/2016
    public static String addBinaryByChar(String a, String b) {
        ArrayList<Integer> longArray = new ArrayList<>();
        ArrayList<Integer> shortArray = new ArrayList<>();
        String longString, shortString;
        if (a.length() > b.length()) {
            longString = a;
            shortString = b;
        } else {
            longString = b;
            shortString = a;
        }
        for (char c : longString.toCharArray()) {
            longArray.add(Character.getNumericValue(c));
        }
        for (char c : shortString.toCharArray()) {
            shortArray.add(Character.getNumericValue(c));
        }


        boolean increaseFlag = false;
        for (int j = longArray.size() - 1; j >= 0; j--) {
            //Only part of longArray need to added w/ the other array
            if (j >= longArray.size() - shortArray.size()) {
                longArray.set(j, longArray.get(j) + shortArray.get(j - longArray.size() + shortArray.size()));
            }
            if (longArray.get(j) > 1) {
                //Two cases when > 1, set itself
                if (longArray.get(j) == 2) {
                    longArray.set(j, 0);
                } else {
                    longArray.set(j, 1);
                }
                //Set the previous node
                if (j > 0) {
                    longArray.set(j - 1, longArray.get(j - 1) + 1);
                } else {
                    increaseFlag = true;
                }
            }
        }
        StringBuilder resultSB = new StringBuilder();
        for (int x : longArray) {
            resultSB.append(Integer.toString(x));
        }
        if (increaseFlag) {
            resultSB.insert(0, "1");
        }
        return resultSB.toString();
    }
    //TODO: BinaryMethod need
    public static void main(String[] args) {
        String resultByDecimal = addBinaryByDecimal("1011", "1");
//        String resultByChar = addBinaryByChar(
//                "10100000100100110110010000010101111011011001101110111111111101000000101111001110001111100001101",
//                "110101001011101110001111100110001010100001101011101010000011011011001011101111001100000011011110011"
//                );
        String resultByChar = addBinaryByChar("11", "1");
        System.out.println(resultByChar);
    }
}
