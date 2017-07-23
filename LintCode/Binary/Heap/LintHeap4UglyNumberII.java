package LintCode.Binary.Heap;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by Administrator on 2017/7/23.
 */
public class LintHeap4UglyNumberII {
    /**
     * 2017/7/23
     * 既要找到最小值, 也要看有没有存在,  因此是Heap+Hash的组合
     */
    public int nthUglyNumber(int n) {
        Queue<Long> q = new PriorityQueue<>();
        HashSet<Long> h = new HashSet<>();

        long[] primes = new long[3];
        primes[0] = 2;
        primes[1] = 3;
        primes[2] = 5;
        for (int i = 0; i < 3; i++) {
            q.offer(primes[i]);
            h.add(primes[i]);
        }
        long number = 1;
        //第一个数是1, 因此从2开始poll到第N个数一共poll了N次
        for (int i = 1; i < n; i++) {
            number = q.poll();
            for (int j = 0; j < 3; j++) {
                long newNumber = number * primes[j];
                if (!h.contains(newNumber)) {
                    q.offer(newNumber);
                    h.add(newNumber);
                }
            }
        }
        return (int) number;
    }
}
