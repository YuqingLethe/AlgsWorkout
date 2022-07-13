package BinarySearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by yuqing on 6/20/22.
 */
public class Binary1268SearchSuggestionsSystem {
    /**
     * 自己想的重寫comparator直接增加了複雜度和可運行性。面試時候應該直接先跳過這些先寫邏輯。
     * 注意：如果第一個字母不匹配，則返回爲空這個邏輯， 不需要在BinarySearch裏面進行，
     *      在add word的時候加判斷比較簡單
     *
     *  June 2022 Same solution, crib coding
     */
    class Solution_BinarySearch {
        public List<List<String>> suggestedProducts(String[] products, String searchWord) {
            // Deep copy the list
            int n = products.length;
            String[] sortedProducts = new String[n];
            for (int i = 0; i < n; ++i) {
                sortedProducts[i] = products[i];
            }
            // Sort the copied products list in lexicographic order
            Arrays.sort(sortedProducts);

            // Iterate each character to get the list of recommended words
            List<List<String>> results = new ArrayList<>();
            String prefix = new String();
            int start = 0;
            for (char c : searchWord.toCharArray()) {
                prefix += c;
                start = findFirstMatchWordIndex(sortedProducts, start, prefix);
                List<String> tmpWordString = new ArrayList<>();
                for (int i = start; i < Math.min(start + 3, n); ++i) {
                    if (sortedProducts[i].length() < prefix.length()
                            || !sortedProducts[i].substring(0, prefix.length()).equals(prefix)) {
                        break;
                    }
                    tmpWordString.add(sortedProducts[i]);
                }
                results.add(tmpWordString);
                start = Math.abs(start);
            }
            return results;

        }

        private int findFirstMatchWordIndex (String[] products, int left, String target) {
            int right = products.length;
            while (left < right) {
                int mid = left + (right - left) / 2;
                if (products[mid].compareTo(target) >= 0) {
                    right = mid;
                } else if (products[mid].compareTo(target) < 0) {
                    left = mid + 1;
                }
            }
            return left;
        }
    }


    /**
     * 用
     */
    class SolutionTrie {
         class Trie {
            class Node {
                boolean isWord = false;
                List<Node> children = Arrays.asList(new Node[26]);
            };
            Node Root, curr;
            List<String> resultBuffer;
            void dfsWithPrefix(Node curr, String word) {
                if (resultBuffer.size() == 3) {
                    return;
                }
                if (curr.isWord) {
                    resultBuffer.add(word);
                }
                for (char c = 'a'; c <= 'z'; c++) {
                    if (curr.children.get(c - 'a') != null) {
                        dfsWithPrefix(curr.children.get(c - 'a'), word + c);

                    }
                }
            }

            Trie() {
                Root = new Node();
            }

            void insert(String s) {
                curr = Root;
                for (char c : s.toCharArray()) {
                    if (curr.children.get(c - 'a') == null) {
                        curr.children.set(c - 'a', new Node());
                    }
                    curr = curr.children.get(c - 'a');

                }
                curr.isWord = true;
            }
            List<String> getWordsStartingWith(String prefix) {
                curr = Root;
                resultBuffer = new ArrayList<String>();
                for (char c : prefix.toCharArray()) {
                    if (curr.children.get(c - 'a') == null) {
                        return resultBuffer;
                    }
                    curr = curr.children.get(c - 'a');
                }
                dfsWithPrefix(curr, prefix);
                return resultBuffer;
            }
        }
        public List<List<String>> suggestedProducts(String[] products, String searchWord) {
            Trie trie = new Trie();
            List<List<String>> result = new ArrayList<>();
            for (String w : products) {
                trie.insert(w);
            }
            String prefix = new String();
            for (char c : searchWord.toCharArray()) {
                prefix += c;
                result.add(trie.getWordsStartingWith(prefix));
            }
            return result;

        }
    }

}
