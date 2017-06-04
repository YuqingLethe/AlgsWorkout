package Codility;

import java.util.*;

/**
 * Created by Administrator on 2017/5/20.
 */
public class Perisex {


    public static int test (int A, int B) {
        String Astring = Integer.toString(A);
        String Bstring = Integer.toString(B);
        String ans = "";
        int Alen = Astring.length();
        int Blen = Bstring.length();
        if (Alen + Blen > 9) return -1;
        for (int i = 0; i < Alen; i++) {
            ans += Astring.charAt(i);
            if (i < Blen) {
                ans += Bstring.charAt(i);
            } else if (i == Alen  - 1){
                break;
            } else {
                ans += Astring.substring(i + 1, Alen);
                break;
            }
        }
        if (Alen < Blen) {
            ans += Bstring.substring(Alen, Blen);
        }
        int zip = Integer.parseInt(ans);
        return zip > 100000000 ? -1 : zip;

    }
    public static String digitalClock(int A, int B, int C, int D) {
        Set s = new HashSet<Integer>();
        s.add(A);
        s.add(B);
        s.add(C);
        s.add(D);
        int[] arr = {A,B,C,D};
        Arrays.sort(arr);


        String ans = "";
        int h1, h2, m1, m2;
        if (s.contains(2)) {
            h1 = 2;
            s.remove(2);
            if (s.contains(3)) {
                h2 = 3;
                s.remove(3);
            }
        }

        return ans;
    }
    private String whichGroup (int A, int B, int C, int D) {
        int[] arr = {A,B,C,D};
        Arrays.sort(arr);
        String ans = "";
        LinkedList<Integer> g1 = new LinkedList<>(); //0-3
        LinkedList<Integer> g2 = new LinkedList<>(); //4-5
        LinkedList<Integer> g3 = new LinkedList<>(); //6-9
        for (int i = 0; i < 4; i++) {
            int tmp = arr[i];
            if (tmp <= 3) {
                g1.add(tmp);
            } else if (tmp <= 5) {
                g2.add(tmp);
            } else {
                g3.add(tmp);
            }
        }
        //Hours
        if (g1.contains(2)) {
            ans += g1.remove(new Integer(2));
            if (g1.contains(3)) {
                ans += g1.remove(new Integer(3));
            } else if (!g1.isEmpty()){
                ans += g1.removeLast();
                g2 = copy(g1, g2);
            } else {
                return "NOT POSSIBLE";
            }
        } else if (g1.contains(3)){
            g2.addFirst(3);
            if (g1.isEmpty()) {

            } else {
                ans += g1.removeLast();
                g2 = copy(g1, g2);
                if (!g3.isEmpty()) {
                    ans += g3.removeLast();
                } else if (!g2.isEmpty()) {
                    ans += g2.removeLast();
                } else {
                    return "NOT POSSIBLE";
                }
            }
        }

        ans += ":";
        //Minutes
        if (!g2.isEmpty()) {
            ans += g2.removeLast();
            g3 = copy(g2, g3);
            if (!g3.isEmpty()) {
                ans += g3.removeLast();
            } else {
                return "NOT POSSIBLE";
            }
        } else {
            return "NOT POSSIBLE";
        }
        return ans;
    }
    //copy LinkedList a to LinkedList b to get a1, a2, b1, b2
    private LinkedList<Integer> copy (LinkedList<Integer> a, LinkedList<Integer> b) {
        while(!a.isEmpty()) {
            b.addFirst(a.removeLast());
        }
        return b;
    }

    public static int solution(int[] A) {
        // write your code in Java SE 8
        if (A.length >= 23)
            return 25;
        int cost = 0;
        for (int i = 0; i < A.length; i++) {
            int endOfWeek = A[i] + 6;
            if (endOfWeek > 30) endOfWeek = 30;
            for (int j = i + 1; j < A.length; j++) {
                if (A[i] >= endOfWeek) {
                    int diff = 0;
                    if (A[i] == endOfWeek) {
                        diff = j - i + 1;
                    } else if (A[i] > endOfWeek) {
                        diff = j - i;
                    }
                    if (diff > 3) {
                        cost += 7;
                    } else {
                        cost += 2 * diff;
                    }
                    System.out.println(i + "-" + j + "->" + diff);
                    i = j;
                    break;
                }

            }
        }
        return cost;
    }

    public static void main(String[] args) {
        int[] A = {1,2,4,5,7,29,30};
//        int A = 104, B = 1006;
//        System.out.println(test(A, B));
        System.out.println(solution(A));
    }
}
