package String;
/**
 * Created by XiaoMi on 2016/4/24.
 */
public class String6ZigZagConversion {
    /**
     * Method 1: Time Limit Exceed <br>
     * Insert in the right position within blanks <br>
     *     Potential problem: concatenation of string[] to string
     */
    public static String convert(String s, int numRows) {
        if (numRows == 1)
            return s;
        char[] copys = s.toCharArray();
        char[][] result = new char[numRows][s.length()/2 + 1];
        int index = 0; //pointer of the char array
        int nthv = 0; //the column index of result, the Nth zigzag'v'
        while (index < s.length()) {
            int rowIndex = 0; //the index of rows
            while(rowIndex < numRows && index < s.length()) {//left column of 'V'
                result[rowIndex][nthv] = copys[index];
                index++;
                rowIndex++;
            }
            //right column of 'V'
            int i = 1; //the column of index of the result, in each 'V'
            rowIndex = numRows - 2;
            while (rowIndex > 0 && index < s.length()) {
                result[rowIndex--][nthv + i] = copys[index];
                index++;
            }
            nthv += numRows - 1;
        }
        String[] sa = new String[numRows];
        String stringResult = "";
        for (int i = 0; i < numRows; i++) {
            sa[i] = String.copyValueOf(result[i]);
            System.out.print(i + "th row: ");
            System.out.println(sa[i]);
            stringResult += sa[i];
        }
        return stringResult;

    }

    /**
     * Runtime: 51ms  11/13/2016
     *     No blanks. Use StringBuffer and append one-by-one
     *     Use flag to mark if going up or going down
     */
    public static String convertBruteForce(String s, int numRows) {
        if (numRows <= 1) return s;
        StringBuffer[] sb = new StringBuffer[numRows];
        for (int i = 0; i < numRows; i++) sb[i] = new StringBuffer();

        int mark = 0; //moving pointer of the stringBuffers
        boolean flag = false; //if reached to the last stringBuffer, reverse write
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            sb[mark].append(c);
            if (flag) {//obliquely up
                if (mark == 0) {
                    flag = false;
                    ++mark;
                } else {
                    --mark;
                }
            } else { //Vertically down
                if (mark == numRows - 1) {
                    --mark;
                    flag = true;
                } else {
                    ++mark;
                }
            }
        }
        for (int i = 1; i < sb.length; i++)
            sb[0].append(sb[i]);
        return sb[0].toString();
    }

    /**
     * TODO: Use one for loop for going down, and one for loop going up
     *  https://discuss.leetcode.com/topic/3162/easy-to-understand-java-solution
     *
     */
    public static String convertBruteForce2(String s, int numRows) {
        return s;
    }

    /**
     * Runtime: 126ms  Use: 10min  11/13/2016
     * A clearer math solution.
     * https://discuss.leetcode.com/topic/21196/a-10-lines-one-pass-o-n-time-o-1-space-accepted-solution-with-detailed-explantation/6
     */
    public static String convertByMath1(String s, int numRows) {
        if(numRows <= 1) return s;
        String result = "";
        int cycle = 2 * numRows - 2;//the size of a cycle(period)

        //Write the first row
        for (int i = 0; i < s.length(); i += cycle) {
            result += s.charAt(i);
        }
        for(int i = 1; i < numRows - 1; ++i) {
            for(int j = i; j < s.length(); j = j + cycle){
                result = result + s.charAt(j);
                int secondJ = (j - i) + cycle - i;
                if(secondJ < s.length()) {
                    result = result + s.charAt(secondJ);
                }
            }
        }
        //Write the last row
        for (int i = numRows - 1; i < s.length(); i += cycle) {
            result += s.charAt(i);
        }
        return result;
    }

    /**
     * Runtime: 106ms  11/13/2016
     * https://discuss.leetcode.com/topic/21196/a-10-lines-one-pass-o-n-time-o-1-space-accepted-solution-with-detailed-explantation
     * Optimize the convertByMath1()
     */
    public static String convertByMath2(String s, int numRows) {
        if (numRows <= 1) return s;
        int cycle = 2*numRows - 2;
        String result = "";
        for (int i = 0; i < numRows; i++) {
            for (int j = i; j < s.length(); j += cycle) {
                result += s.charAt(j);
                int secondJ = j + cycle - 2*i;
                if (i != 0 && i != numRows - 1 && secondJ < s.length()) {
                    result += s.charAt(secondJ);
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        String s = "ABCD";
//        System.out.println(convert("vucftpwctgtwmxnupycfgcuqunublmoiitncklefszbexrampetvhqnddjeqvuygpnkazqfrpjvoaxdpcwmjobmskskfojnewxgxnnofwltwjwnnvbwjckdmeouuzhyvhgvwujbqxxpitcvograiddvhrrdsycqhkleewhxtembaqwqwpqhsuebnvfgvjwdvjjafqzzxlcxdzncqgjlapopkvxfgvicetcmkbljopgtqvvhbgsdvivhesnkqxmwrqidrvmhlubbryktheyentmrobdeyqcrgluaiihveix",
//                247));
        System.out.println(convertByMath1(s, 2));
    }
}
