package LintCode.Binary.Recursive;

import java.util.*;

/**
 * Created by Administrator on 2017/6/28.
 */
public class LintRecursive120WordLadder {
    /**
     * 并不会自己写. 6/28/2017
     */
    public int ladderLength(String start, String end, Set<String> dict) {
        if (dict == null) {
            return 0;
        }
        if (start.equals(end)) { ////这个不能少, 不然加进去程序先找的是neighbors,就不知道几个了...
            return 1;
        }

        dict.add(start); //这个加入是很必要的, 题意并没有说一定包含start end
        dict.add(end);

        ArrayList<String> paths = new ArrayList<>();
        paths.add(start);
        Queue<String> q = new LinkedList<>();
        HashSet<String> hash = new HashSet<>();
        q.offer(start);
        hash.add(start);
        int length = 1;

        while (!paths.isEmpty()) {
            length++;
            int size = q.size();
            for (int i = 0; i < size; i++) {
                String s = q.poll();
                for (String w : neighbors(dict, s)) {
                    if (hash.contains(w)) {
                        continue;
                    }
                    if (w.equals(end)) {
                        return length;
                    }
                    q.offer(w);
                    hash.add(w);
                }
            }
        }
        return 0; //不是return null, 要动脑子啊

    }

    private ArrayList<String> neighbors(Set<String> dict, String word) {
        ArrayList<String> results = new ArrayList<>();
        for (char c = 'a'; c <= 'z'; c++) {
            for (int i = 0; i < word.length(); i++) {
                if (c == word.charAt(i)) {
                    continue;
                }
                String s = replace(word, i, c);
                if (dict.contains(s)) {
                    results.add(s);
                }
            }
        }
        return results;
    }
    private String replace(String word, int idx, char c) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < word.length(); i++) {
            if (i != idx) {
                sb.append(word.charAt(i));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * 用char array来重建string 7/23/2017
     */
    private ArrayList<String> getNext(String word, Set<String> dict) {
        ArrayList<String> ans = new ArrayList<>();
        for (int i = 0; i < word.length(); i++) {
            for (char c = 'a'; c <= 'z'; c++) {
                String newS = replaceLetter(word, i, c); //原word也被加到了neighbor里, 但是被主程序过滤了, 因此无事
                if (dict.contains(newS)) {
                    ans.add(newS);
                }
            }
        }
        return ans;
    }


    private String replaceLetter(String word, int idx, char c) {
        char[] charArray = word.toCharArray();
        charArray[idx] = c;
        return new String(charArray);
    }
}
