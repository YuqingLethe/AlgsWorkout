package BitManipulation;

public class CC150Ch5BM53 {
    public static void numbersWithSameNumberOf1bits (int n) {
        char[] ca = Integer.toBinaryString(n).toCharArray();
        int lIndex = 0;
        int sIndex = 0;
        for (int i = ca.length; i > 0; i--) {
            if (ca[i] == '0' && ca[i - 1] == '1') {
                sIndex = i;
            } else if (ca[i] == '1' && ca[i - 1] == '0') {
                lIndex = i;
            }
            i++;
        }
        //If no 0
        if (lIndex == 0) {

        }
        char[] largest = ca;
        char temp = largest[lIndex];
        largest[lIndex] = largest[lIndex - 1];
        largest[lIndex - 1] = temp;

        char[] smallest = ca;
        temp = smallest[lIndex];
        smallest[lIndex] = smallest[lIndex - 1];
        smallest[lIndex - 1] = temp;

        System.out.println("Largest=" + Integer.parseInt(largest.toString(), 2));
        System.out.println("Smallest=" + Integer.parseInt(smallest.toString(), 2));

    }
    public void main(String[] args) {
        numbersWithSameNumberOf1bits(11);
    }
}
