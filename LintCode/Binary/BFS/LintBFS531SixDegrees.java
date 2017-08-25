package LintCode.Binary.BFS;

import java.util.*;

/**
 * Created by Administrator on 2017/8/24.
 */
public class LintBFS531SixDegrees {
    /**
     * 8/24 One time right  Queue + Hash
     */
    public class Solution {

        public int sixDegrees(List<UndirectedGraphNode> graph,
                              UndirectedGraphNode s,
                              UndirectedGraphNode t) {
            // Write your code here
            if (s == t) {
                return 0;
            }
            if (graph == null || graph.size() == 0) {
                return -1;
            }
            int count = 0;
            HashSet<UndirectedGraphNode> hash = new HashSet<>();
            Queue<UndirectedGraphNode> q = new LinkedList<>();
            hash.add(s);
            q.offer(s);

            while (!q.isEmpty()) {
                int size = q.size();
                count++;
                for (int i = 0; i < size; i++) {
                    UndirectedGraphNode ugn = q.poll();
                    for (UndirectedGraphNode next : ugn.neighbors) {
                        if (hash.contains(next)) {
                            continue;
                        }
                        if (next == t) {
                            return count;
                        }
                        q.offer(next);
                        hash.add(next);
                    }
                }
            }
            return -1;
        }
    }

    class UndirectedGraphNode {
        int label;
        List<UndirectedGraphNode> neighbors;
        UndirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<UndirectedGraphNode>();
        }
    };
}
