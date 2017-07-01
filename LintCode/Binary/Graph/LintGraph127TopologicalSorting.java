package LintCode.Binary.Graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Administrator on 2017/6/30.
 */
public class LintGraph127TopologicalSorting {
    /**
     * 错误思路
     * @param graph: A list of Directed graph node
     * @return: Any topological order for the given graph.
     */
    public ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
        // write your code here

        ArrayList<DirectedGraphNode> results = new ArrayList<>();
        if (graph == null) {
            return results;
        }


        //Build a hashmap distance on DirectedGraphNode and the level number
        HashMap<DirectedGraphNode, Integer> distance = new HashMap<>();
        for (DirectedGraphNode dgn : graph) {
            int level;
            //不应该在两个地方添加node, 会乱. 答案用了一个trick, 就是不添加第一层节点到distance里面, 这样就不会乱
            if (distance.containsKey(dgn)) {
                level = distance.get(dgn);
            } else {
                distance.put(dgn, 0); //一旦是新的节点,
                level = 0;
            }

            for (DirectedGraphNode nb : dgn.neighbors) {
                if (distance.containsKey(nb)) {
                    if (distance.get(nb) < level) {//节点被重复加入怎么办?
                        distance.put(nb, level + 1);
                    }
                } else {
                    distance.put(nb, level + 1);
                }
            }
        }

        //Build the results by pop up level equals 0 nodes
        for (int i = 0; i < graph.size(); i++) {//不能依靠graph来找到最小层次, 因为graph不能改变size, 而一旦变为0又会和前面的level重复
            DirectedGraphNode dgn = graph.get(i);
            if (distance.get(dgn) == 0) {
                graph.remove(i);
                results.add(dgn);
            } else {
                distance.put(dgn, distance.get(dgn) - 1);
            }
        }
        return results;
    }

    /**
     *
     * @param graph: A list of Directed graph node
     * @return: Any topological order for the given graph.
     */
    public ArrayList<DirectedGraphNode> topSortSolution(ArrayList<DirectedGraphNode> graph) {
        // build the level hashmap
        ArrayList<DirectedGraphNode> results = new ArrayList<>();
        HashMap<DirectedGraphNode, Integer> level = new HashMap<>();
        for (DirectedGraphNode dgn : graph) {
            for (DirectedGraphNode ns : dgn.neighbors) {
                if (level.containsKey(ns)) {
                    level.put(ns, level.get(ns) + 1);
                } else {
                    level.put(ns, 1); //这样不添加第一层node, 就不会有新node被遍历到何时添加的冲突了
                }
            }
        }

        //Add first level nodes
        Queue<DirectedGraphNode> q = new LinkedList<>();
        for (DirectedGraphNode dgn : graph) {
            if (!level.containsKey(dgn)) {
                q.offer(dgn);
                results.add(dgn);
            }
        }

        //Remove smallest levels from hashmap
        while (!q.isEmpty()) {
            DirectedGraphNode dgn = q.poll();
            for (DirectedGraphNode n : dgn.neighbors) {
                level.put(n, level.get(n) - 1);
                if (level.get(n) == 0) {
                    q.offer(n);
                    results.add(n);
                }
            }
        }
        return results;
    }
}
