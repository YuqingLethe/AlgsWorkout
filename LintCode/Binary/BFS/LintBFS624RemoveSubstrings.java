package LintCode.Binary.BFS;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * Created by Administrator on 2017/8/4.
 */
public class LintBFS624RemoveSubstrings {
    /**
     * 8/4/2017
     * 不会做, 因为前面删掉哪个substring会直接影响后面结果. 看答案, 才晓得
     * 对于处理没有一定的排序方法的, 要Queue + hash把各个中间的string都经过一遍, 就晓得结果了
     *
     */
    public class Solution {
        public int minLength(String s, Set<String> dict) {
            // Write your code here
            Queue<String> q = new LinkedList<>();
            HashSet<String> hash = new HashSet<>();

            int len = s.length();
            int min = len;
            q.offer(s);
            hash.add(s);
            while (!q.isEmpty()) {
                String crt = q.poll();
                for (String rem : dict) {
                    int foundIdx = crt.indexOf(rem);
                    while (foundIdx != -1) { //注意这里用while 不止有一个substring
                        String new_s = crt.substring(0, foundIdx) + crt.substring(foundIdx + rem.length());
//                    System.out.println("rem=" + rem + "  new_s=" + new_s + "  foundIdx=" + foundIdx);
                        if (!hash.contains(new_s)) {
                            q.offer(new_s);
                            hash.add(new_s);
                            if (new_s.length() < min) {
                                min = new_s.length();
                            }
                        }
                        foundIdx = crt.indexOf(rem, foundIdx + 1); //写到这句才明白, new_s是每次只减去一个rem得到的
                    }
                }
            }
            return min;
        }
    }
}
