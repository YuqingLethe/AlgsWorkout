package BitManipulation;

public class CC150Ch5BM53 {
    //USE: 4hr 9/22/2016
    private static void print(char[] ca) {
        for (int i = 0; i < ca.length; i++) {
            System.out.print(ca[i]);
        }
        System.out.println();
    }

    public static int largestWithSameNumberOf1bits (int n) {
        if (n == 0) { return 1; }

        char[] ca = Integer.toBinaryString(n).toCharArray();
        char[] result = ca;

        //Find the largest
        int index = -1;
        int lg = 0;
        System.out.print("Original = ");
        print(ca);
        for (int i = ca.length - 1; i > 0; i--) {
            if (ca[i] == '1' && ca[i - 1] == '0') {
                index = i;
                break;
            }
        }
        if (index != -1) {
            char temp = result[index];
            result[index] = result[index - 1];
            result[index - 1] = temp;
            System.out.print("Largest Result = ");
            print(result);
            lg = Integer.parseInt(String.valueOf(result), 2);
        } else {
            String s;
            if (ca[ca.length - 1] == '1') {
                result[result.length - 1] = '0';
                s = String.valueOf(result) + "1";
            } else {
                result[result.length - 1] = '1';
                s = String.valueOf(result) + "1";
            }
            System.out.print("Largest Result of whole 1 = " + s);
            System.out.println();
            lg = Integer.parseInt(s, 2);
        }
        return lg;
    }
    public static int smallest(int n) {
        if (n == 0) { return -1; }
        char[] ca = Integer.toBinaryString(n).toCharArray();
        char[] result = ca;

        //Find the smallest
        int index = -1;
        int sm = 0;
        System.out.print("Original = ");
        print(ca);
        for (int i = ca.length - 1; i > 0; i--) {
            if (ca[i] == '0' && ca[i - 1] == '1') {
                index = i;
                break;
            }
        }
        if (index != -1) {
            char temp = result[index];
            result[index] = result[index - 1];
            result[index - 1] = temp;
            sm = Integer.parseInt(String.valueOf(result), 2);
        } else {
            String s;
            if (ca[ca.length - 1] == '1') {
                System.out.println("Cannot find the smallest for whole 1 of a binary number");
            } else {
                System.out.println("ERROR w/ Smallest");
            }
        }
        System.out.print("Smallest Result = ");
        print(result);
        return sm;
    }
    public static void main(String[] args) {
        System.out.println("============= Largest =================");
        int x = largestWithSameNumberOf1bits(2);
        System.out.println("Largest = " + x);
        System.out.println("============= Smallest =================");
        int y = smallest(3);
        System.out.println("Smallest = " + y);
    }
}
