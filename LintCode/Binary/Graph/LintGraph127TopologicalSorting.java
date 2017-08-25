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
     * 错误思路1: hashmap里面有的degree level是0 冗余   + 不能改变graph的size
     *
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
     * 8/23
     * 错误思路2: HashMap build的时候按照left degree的顺序决定next的degree,
     */
    public class Solution {
        public ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
            ArrayList<DirectedGraphNode> results = new ArrayList<>();
            if (graph == null || graph.size() == 0) {
                return results;
            }
            //build the degree hashmap
            HashMap<DirectedGraphNode, Integer> level = new HashMap<>();
            for (DirectedGraphNode left : graph) {
                int leftDeg = 0;
                if (level.containsKey(left)) {
                    leftDeg = level.get(left);
                }
                System.out.println("left.label=" + left.label + "  leftDeg=" + leftDeg);
                for (DirectedGraphNode right : left.neighbors) {
                    int rightDeg = 0;
                    if (level.containsKey(right)) {
                        rightDeg = level.get(right);
                    }
                    if (rightDeg < leftDeg + 1) {
                        rightDeg = leftDeg + 1;
                        level.put(right, rightDeg);
                    }
                    System.out.println("      right.label=" + right.label + " rightDeg=" + rightDeg);
                }
            }

            //find the 0 level
            Queue<DirectedGraphNode> q = new LinkedList<>();
            for (DirectedGraphNode node : graph) {
                if (!level.containsKey(node)) {
                    q.offer(node);
                    results.add(node);
                }
            }

            //find others by level
            //Bug: 答案是[0,2,1,4]   掠过了3
            //所以只需要让neighbor是以node为其实level + 1即可 需要死记硬背一下答案的hashmapbuild方法
            int degree = 1;
            while (!q.isEmpty())  {
                DirectedGraphNode node = q.poll();
                System.out.println(node.label);
                for (DirectedGraphNode next : node.neighbors) {

                    if (level.get(next) - degree == 0) {
                        results.add(next);
                        q.offer(next); //重大bug, 无论level degree到没到0, 都要offer
                    }
                }
                degree ++;
            }

            return results;
        }
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
