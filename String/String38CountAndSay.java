package String;

/**
 * Created by XiaoMi on 2016/8/31.
 */
public class String38CountAndSay {
    //Runtime 12ms   8/31/2016 Use: 1hr
    private static String countAndSay(int n) {
        StringBuilder resultSB = new StringBuilder("1");
        System.out.println("Should be 1=" + resultSB);
        StringBuilder tempSB;

        for (int i = 0; i < n - 1; i++) {
            tempSB = new StringBuilder();
            //calculate length of same number
            for (int j = 0; j < resultSB.length(); j++) {
                char tempChar = resultSB.charAt(j);
                int count = 0; //Count of the number of same tempChar
                while(j < resultSB.length() && tempChar == resultSB.charAt(j)) {
                    j++;
                    count++;
                }
                tempSB = tempSB.append((char)(count + 48));
                tempSB = tempSB.append(tempChar);
                j--;
            }
            resultSB = tempSB;
            System.out.println(tempSB.toString());
        }
        return resultSB.toString();
    }


    public static void main(String[] args) {

        System.out.println(countAndSay(5));
    }
}
