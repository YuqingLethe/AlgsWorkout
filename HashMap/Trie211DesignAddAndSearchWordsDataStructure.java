package HashMap;

import java.util.HashMap;
import java.util.Map;

public interface Trie211DesignAddAndSearchWordsDataStructure {
    /**
     * Oct2022基本的test case都过了, 就是timeout了. Search用的是recursive.
     * 答案recursive只在.匹配的时候用. 40min
     */
    class TimeOut_WordDictionary {
        class WordNode {
            Character val;
            Map<Character, WordNode> children;
            boolean isWord;
            WordNode(Character c) {
                this.val = c;
                this.children = new HashMap<>();
                this.isWord = false;
            }
        }

        private WordNode root;

        public TimeOut_WordDictionary() {
            root = new WordNode('.');
        }

        public void addWord(String word) {
            WordNode prev = root;
            for (int i = 0; i < word.length(); ++i) {
                Character c = word.charAt(i);
                if (prev.children.containsKey(c)) {
                    prev = prev.children.get(c);
                } else {
                    WordNode newNode = new WordNode(c);
                    prev.children.put(c, newNode);
                    prev = newNode;
                }
            }
            prev.isWord = true;
        }

        public boolean search(String word) {
            WordNode prev = root;
            return searchByRange(word, 0, root);
        }

        private boolean searchByRange(String word, int idx, WordNode prev) {
            if (idx == word.length() && prev.isWord) {
                return true;
            } else if (idx == word.length()) {
                return false;
            }
            Character curr = word.charAt(idx);
            if (curr == '.') {
                for (Character nextChar : prev.children.keySet()) {
                    WordNode nextNode = prev.children.get(nextChar);
                    if (searchByRange(word, idx + 1, nextNode)) {
                        return true;
                    }
                }
                return false;
            } else if (prev.children.containsKey(curr)) {
                WordNode next = prev.children.get(curr);
                return searchByRange(word, idx + 1, next);
            } else {
                return false;
            }
        }
    }

    /**
     * Solution: https://leetcode.com/problems/design-add-and-search-words-data-structure/solutions/722388/add-and-search-word/
     * 自己也debug了很久. 这个答案很巧妙 60min
     */
    class LeetcodeSolution_WordDictionary {
        class WordNode {
            Character val;
            Map<Character, WordNode> children;
            boolean isWord;
            WordNode(Character c) {
                this.val = c;
                this.children = new HashMap<>();
                this.isWord = false;
            }
        }

        private WordNode root;

        public LeetcodeSolution_WordDictionary() {
            root = new WordNode('.');
        }

        public void addWord(String word) {
            WordNode prev = root;
            for (int i = 0; i < word.length(); ++i) {
                Character c = word.charAt(i);
                if (prev.children.containsKey(c)) {
                    prev = prev.children.get(c);
                } else {
                    WordNode newNode = new WordNode(c);
                    prev.children.put(c, newNode);
                    prev = newNode;
                }
            }
            prev.isWord = true;
        }

        public boolean search(String word) {
            WordNode prev = root;
            return searchByRange(word, 0, root);
        }

        private boolean searchByRange(String word, int idx, WordNode prev) {
            // 下面这个判断直接被return的那种情况包含了!
//            if (idx == word.length() && prev.isWord) {
//                return true;
//            } else if (idx == word.length()) {
//                return false;
//            }


            for (int i = idx; i < word.length(); ++i) {
                Character curr = word.charAt(i);
                if (curr == '.') {
                    for (Character nextChar : prev.children.keySet()) {
                        WordNode nextNode = prev.children.get(nextChar);
                        if (searchByRange(word, i + 1, nextNode)) {
                            return true;
                        }
                    }
                    return false;
                } else if (prev.children.containsKey(curr)){
                    prev = prev.children.get(curr);
                } else {
                    return false;
                }
            }
            return prev.isWord; //这里很巧妙 即便正好结束了也不行
        }
    }
}
