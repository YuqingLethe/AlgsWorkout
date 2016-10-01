package BitManipulation;

/**
 * 5.3 Give a positive integer, print the next smallest and the next largest number
 * that have the same number of 1 bits in their binary representations.
 */
public class CC150Ch5BM53 {

    /**
     * USE: 4hr 9/22/2016 by Char Array
     */
    private static void print(char[] ca) {
        for (char c : ca) {
            System.out.print(c);
        }
        System.out.println();
    }

    public static int getNextByArray (int n) {
        if (n == 0) { return 1; }

        char[] ca = Integer.toBinaryString(n).toCharArray();
        char[] result = ca;

        //Find the largest
        int index = -1;
        int lg;
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
            lg = Integer.parseInt(s, 2);
        }
        return lg;
    }
    public static int getPrevByArray(int n) {
        if (n == 0) { return -1; }
        char[] ca = Integer.toBinaryString(n).toCharArray();
        char[] result = ca;

        //Find the smallest
        int index = -1;
        int sm = 0;
        for (int i = ca.length - 1; i > 0; i--) {
            if (ca[i] == '0' && ca[i - 1] == '1') {
                index = i;
                break;
            }
        }
        //TODO:　Algs wrong
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
        return sm;
    }

    /**
     * Use the solution from the book
     * Use: 1hr 9/30/2016
     */
    public static int getNext(int n) {
        //Compute c0 and c1
        int temp = n;
        int c0 = 0, c1 = 0;
        while((temp&1) == 0 && temp != 0) {
            c0++;
            temp = temp >> 1;
        }
        while((temp&1) == 1 && temp != 0) {
            c1++;
            temp >>= 1;
        }
        if (temp == 0) { return -1; } //There is no bigger one;

        int p = c0 + c1;

        //Build the mask
        int a = ~((1 << p) - 1);
        n &= a; //Make p bits from the end to 0
        n |= 1 << p; //Flip the p bit
        int b = (1 << c1 - 1) - 1; //add c1 - 1 bits 1 in the end
        n |= b;
        return n;
    }

    public static int getPrev(int n) {
        int temp = n;
        int c0 = 0, c1 = 0, p;
        while((temp&1) == 1 && temp != 0) {
            c1++;
            temp >>= 1;
        }
        if (temp == 0) return -1; //error no smaller one
        while((temp&1) == 0 && temp!= 0) {
            c0++;
            temp >>= 1;
        }
        p = c0 + c1;

        int a = ~((1 << p + 1) - 1);
        n &= a; //clears rightmost p+1 bits
        int b = (1 << c1 + 1) - 1 << c0 - 1;
        n |= b;
        return n;
    }


    public static void main(String[] args) {
        int n = Integer.parseInt("10011110000011", 2); //13948 for getNext()
        int x = getNextByArray(n);
        int y = getPrevByArray(n);

        System.out.printf("%10s %10d = %32s ", "Original", n, Integer.toBinaryString(n));
        System.out.println();
        System.out.printf("%10s %10d = %32s ", "Next", x, Integer.toBinaryString(x));
        System.out.println();
        System.out.printf("%10s %10d = %32s ", "Previous", y, Integer.toBinaryString(y));
    }
}
