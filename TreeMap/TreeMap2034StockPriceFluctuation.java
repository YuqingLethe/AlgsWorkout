package TreeMap;

import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class TreeMap2034StockPriceFluctuation {
    /**
     * Nov 2022 Crib Answer
     */
    class TreeMap_HashMap {
        private HashMap<Integer, Integer> prices;
        private TreeMap<Integer, Integer> priceFrequency;
        private int latestTime;

        public TreeMap_HashMap() {
            latestTime = 0;
            prices = new HashMap<>();
            priceFrequency = new TreeMap<>();
        }

        public void update(int timestamp, int price) {
            latestTime = Math.max(latestTime, timestamp);
            if (prices.containsKey(timestamp)) {
                int oldPrice = prices.get(timestamp);
                priceFrequency.put(oldPrice, priceFrequency.get(oldPrice) - 1);
                if (priceFrequency.get(oldPrice) == 0) {
                    priceFrequency.remove(oldPrice);
                }

            }
            prices.put(timestamp, price);
            priceFrequency.put(price, priceFrequency.getOrDefault(price, 0) + 1);
        }

        public int current() {
            return prices.get(latestTime);
        }

        public int maximum() {
            return priceFrequency.lastKey();
        }

        public int minimum() {
            return priceFrequency.firstKey();
        }
    }

    class HashMap_Heaps {
        private HashMap<Integer, Integer> prices;
        private PriorityQueue<int[]> minHeap, maxHeap;

        private int latestTime;

        public HashMap_Heaps() {
            latestTime = 0;
            prices = new HashMap<>();
            minHeap = new PriorityQueue<>((a, b) -> (a[0] - b[0]));
            maxHeap = new PriorityQueue<>((a, b) -> (b[0] - a[0]));
        }

        public void update(int timestamp, int price) {
            latestTime = Math.max(latestTime, timestamp);
            prices.put(timestamp, price);
            minHeap.add(new int[]{price, timestamp});
            maxHeap.add(new int[]{price, timestamp});
        }

        public int current() {
            return prices.get(latestTime);
        }

        public int maximum() {
            int[] top = maxHeap.peek();
            while (prices.get(top[1]) != top[0]) {
                maxHeap.poll();
                top = maxHeap.peek();
            }
            return top[0];
        }

        public int minimum() {
            int[] top = minHeap.peek();
            while (prices.get(top[1]) != top[0]) { // invalid entry
                minHeap.poll();
                top = minHeap.peek();
            }
            return top[0];
        }
    }

}
