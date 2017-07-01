package LintCode.Binary.Graph;

import java.util.*;

public class LIntGraph605SequenceReconstruction {
    /**
     * 2017/7/1.
     * 读题目: 注意这个seqs可以是任意n*m 而非n*2
     * 读题目: 每一个值不可为0, 可以为n
     * @param org a permutation of the integers from 1 to n
     * @param seqs a list of sequences
     * @return true if it can be reconstructed only one or false
     */
    public boolean sequenceReconstruction(int[] org, int[][] seqs) {
        // Write your code here
        Map<Integer, Set<Integer>> map = new HashMap<Integer, Set<Integer>>();
        Map<Integer, Integer> indegree = new HashMap<Integer, Integer>();

        for (int num : org) {
            map.put(num, new HashSet<Integer>());
            indegree.put(num, 0);
        }

        //Compare count and org lengths, and build map and indegree
        //set里面add()return boolean

        //第一种:
        int n = org.length;
        int count = 0;
        for (int[] seq : seqs) {
            count += seq.length;
            for (int i = 0; i < seq.length; i++) {
                if (seq[i] <= 0 || seq[i] > n) {
                    return false;
                }
                if (i != 0 && map.get(seq[i - 1]).add(seq[i])) {
                    indegree.put(seq[i], indegree.get(seq[i]) + 1); //这里的确求的是入度, 不是总入度
                }
            }
        }

        if (count < n) {
            return false;
        }

        Queue<Integer> q = new LinkedList<>();
        for (int i : indegree.keySet()) { //keySet()
            if (indegree.get(i) == 0) {
                q.offer(i);
            }
        }

        int cnt = 0; //Count all existing numbers in the seqs
        while (q.size() == 1) {
            for (int next : map.get(q.poll())) {
                indegree.put(next, indegree.get(next) - 1);
                if (indegree.get(next) == 0) {
                    q.offer(next);

                }
            }
            cnt++;
            System.out.println();
        }

        return cnt == n;

    }
}
