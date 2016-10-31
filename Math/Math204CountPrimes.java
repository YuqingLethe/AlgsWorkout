package Math;

import java.util.LinkedList;

/**
 * Created by Administrator on 2016/10/31.
 */
public class Math204CountPrimes {
    /**
     * Runtime: Time limit exceed 49979     10/31/2016
     */
    public static int countPrimesByPrimeLL(int n) {
        if (n == 0 || n == 1 || n == 2) return 0;
        if (n == 3) return 1;

        LinkedList<Integer> prime = new LinkedList<>();
        prime.add(0, 2);
        prime.add(1, 3);
        for (int i = 4; i <= n; i++) {
            prime = isPrime(i, prime);
        }
        if (prime.peekLast() == n) return prime.size() - 1;
        else return prime.size();
    }
    public static LinkedList<Integer> isPrime(int n, LinkedList<Integer> prime) {
        if (n%2 == 0) return prime;
        if (n%3 == 0) return prime;
        int i = 1;
        for (; i < prime.size(); i++) {
            if (n%prime.get(i) == 0) return prime;
        }
        prime.add(i, n);
        return prime;
    }

    /**
     * Runtime:31ms   10/31/2016
     * https://discuss.leetcode.com/topic/25186/my-java-solution
     * In the boolean array m, m[n] means the number n. Thus for each time, if m[n] is a prime,
     * we need to delete all the multiple of m[n]. And finally, the remaining numbers are primes.
     */
    public static int countPrimesBySieveOfEratosthenes(int n) {
        boolean[] prime = new boolean[n];
        int count = 0; //The number of primes
        for (int i = 2; i < n; i++) {
            if (prime[i]) continue;
            count++;
            for (int j = 1; j*i < n; j++) {
                prime[j*i] = true; //not prime
            }
        }
        return count;
    }

    /**
     * Runtime: 18ms Use: 40min debug i*i < n   10/31/2016
     * Optimization of countPrimesBySieveOfEratosthenes()
     */
    public static int countPrimesBySieveOfEratosthenes2(int n) {
        if (n < 3) return 0;
        boolean[] prime = new boolean[n];
        int count = n/2;
        for (int i = 3; i*i < n; i += 2) { //skip these even numbers
            if(prime[i]) continue;
            for (int j = i*i; j < n; j += 2*i) {
                //j start from i*i because previous have all been deleted
                //both i and j are odd, then i + j is even which must not be a prime number.
                // Only j (odd) + 2 * i (even) can be an odd number.
                if (!prime[j]) {
                    --count;
                    prime[j] = true;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(countPrimesBySieveOfEratosthenes2(454));
    }
}
