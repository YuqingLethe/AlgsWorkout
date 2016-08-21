package String;
/**
 * Created by XiaoMi on 2016/4/24.
 */
public class String6ZigZagConversion {
    /**
     * Method 1: Time Limit Exceed <br>
     * Insert in the right position within blanks <br>
     *     Potential problem: concatenation of string[] to string
     * @param s
     * @param numRows
     * @return
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
     * Method 2: <br>
     *     No blanks. Use StringBuilder and append
     * @param
     * @param
     * @return
     */
 //   public static String convert2(String s, int numRows) {

 //   }
    public static void main(String[] args) {
        String s = "Apalindromeisaword,ddfdsf";
        System.out.println(convert("vucftpwctgtwmxnupycfgcuqunublmoiitncklefszbexrampetvhqnddjeqvuygpnkazqfrpjvoaxdpcwmjobmskskfojnewxgxnnofwltwjwnnvbwjckdmeouuzhyvhgvwujbqxxpitcvograiddvhrrdsycqhkleewhxtembaqwqwpqhsuebnvfgvjwdvjjafqzzxlcxdzncqgjlapopkvxfgvicetcmkbljopgtqvvhbgsdvivhesnkqxmwrqidrvmhlubbryktheyentmrobdeyqcrgluaiihveix",
                247));
    }
}
