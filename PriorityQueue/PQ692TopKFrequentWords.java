package PriorityQueue;

import java.util.*;

public class PQ692TopKFrequentWords {
    /**
     * 2023 Feb
     */
    class PQ {
        class Word {
            String word;
            Integer freq;
            Word(String s, Integer n) {
                word = s;
                freq = n;
            }

        }
        public List<String> topKFrequent(String[] words, int k) {
            // Get frequency of words
            HashMap<String, Integer> hm = new HashMap<>();
            for (String word: words) {
                hm.put(word, hm.getOrDefault(word, 0) + 1);
            }

            // Put into a heap
            PriorityQueue<Word> pq = new PriorityQueue<>((a, b) -> {
                if (a.freq == b.freq) {
                    return a.word.compareTo(b.word); //可以用这个方法比较alphabetic orders
                } else {
                    return b.freq - a.freq;
                }
            });
            for (String word : hm.keySet()) {
                pq.add(new Word(word, hm.get(word)));
            }

            List<String> result = new ArrayList<>();
            for (int i = 0; i < k && !pq.isEmpty(); ++i) {
                result.add(pq.poll().word);
            }
            return result;

        }
    }

    /**
     * Standard Solution
     */
    class PQ_CustomizeClass {
        class Word implements Comparable<Word> {
            private String word;
            private int count;

            public Word(String word, int count) {
                this.word = word;
                this.count = count;
            }

            public int compareTo(Word other) { // 在word里面写比较
                if (this.count == other.count) {
                    return this.word.compareTo(other.word);
                }
                return other.count - this.count;
            }
        }

        public List<String> topKFrequent(String[] words, int k) {
            Map<String, Integer> cnt = new HashMap<>();
            for (String word : words) {
                cnt.put(word, cnt.getOrDefault(word, 0) + 1);
            }

            List<Word> candidates = new ArrayList<>();
            for (Map.Entry<String, Integer> entry : cnt.entrySet()) { // 注意如果entrySet返回的是Map.Entry
                candidates.add(new Word(entry.getKey(), entry.getValue()));
            }

            Queue<Word> h = new PriorityQueue<>(candidates);
            List<String> res = new ArrayList<>();
            for (int i = 0; i < k; i++) {
                res.add(h.poll().word);
            }
            return res;

        }
    }
}
