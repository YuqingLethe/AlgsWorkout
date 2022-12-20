package PriorityQueue;

import java.util.*;

/**
 * https://www.1point3acres.com/bbs/thread-943252-1-1.html VO 1-1
 * 第一題：給一堆request logs（array of string），log包含start time，duration以及其他用不到的訊息，要計算每個request開始的時間點上，有多少request正在執行
     Clarification：
     1. 只需處理log中的timestamp嗎？yes
     2. 會不會有同時開始的request？no, assume each start time is unique
     忘記問的：log有沒有排序（後面面試官提示）
     一開始想說用TreeMap存開始與結束時間，面試官提示後想到可以直接用PriorityQueue存結束時間
     每次拿到log的結束時間放入queue中，然後移除比當前start time小的值，保存當下queue的size
 */
public class PQ50006HowManyRequestRunning {
    /**
     * Nov 2022 算是自己想的吧 40min
     */
    static class PQ{
        public List<Integer> getRunningRequestNum (List<int[]> requests) {
            List<Integer> result = new ArrayList<>();
            if (requests == null || requests.size() == 0) {
                return result;
            }

            PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> (a[0] + a[1] - b[0] - b[1])); // minHeap by end time

            //默認輸入是inorder就免去這兩步
            // 1. 如果輸入不是sorted則可能需要另建立class
            // 2. Collections.sort(requests, (a, b) -> (a[0] - b[0]));

            for (int i = 0; i < requests.size(); ++i) {
                int[] startRequest = requests.get(i);
                int runningRequest = 1;
                while (!pq.isEmpty()) {
                    int[] topRequest = pq.peek();
                    if (topRequest[0] + topRequest[1] - 1 < startRequest[0]) {
                        pq.poll();
                    } else {
                        break;
                    }
                }
                runningRequest += pq.size();
                result.add(runningRequest);
                pq.add(startRequest);
            }
            return result;
        }

        public static void main(String[] args) {
            PQ solution = new PQ();
            List<int[]> requests = new ArrayList<>();
            requests.add(new int[]{1, 100});
            requests.add(new int[]{2, 4});

            requests.add(new int[]{3, 9});
            requests.add(new int[]{5, 10});
            requests.add(new int[]{6, 2});
            requests.add(new int[]{13, 100});

            List<Integer> answer = solution.getRunningRequestNum(requests);
            System.out.println(answer.toString());
        }

    }
}
