package Graph;

import javafx.util.Pair;

import java.util.*;

/**
 有一个地方审题没主义, 那就是有n个节点, 给的k在n里面. 因此可以活用array来找寻每个节点的相关信息

 主要就三步
 1. 建立adj这个HashMap, value注意是list, 是和node相邻的所有节点和权重
 2. 建立一个signalReceiveAt[] 对应关系, 节点存放从k到那个节点的最短路径的权重和
 3. 遍历signalReceiveAt[]来找出距离k权重和最大值(最远, 最delay的节点到k的距离)

 重点就在signalReceiveAt怎样获取
 */
public class Graph743NetworkDelayTime {
    /**
     * May 2022
     */
    class DFS {
        Map<Integer, List<Pair<Integer, Integer>>> adj = new HashMap<>();

        public int networkDelayTime(int[][] times, int n, int k) {
            // 存储每个node相邻节点到adj
            for(int[] edge: times) {
                int source = edge[0];
                int dest = edge[1];
                int time = edge[2];
                adj.putIfAbsent(source, new ArrayList<>());
                adj.get(source).add(new Pair(time, dest));
            }

            for (int node: adj.keySet()) {
                Collections.sort(adj.get(node), (a, b) -> a.getKey() - b.getKey());
            }
            // 主要是这个signalReceiveAt[] array, 存储每个节点signal time最小值
            // 找出到每个节点的路径
            int[] signalReceiveAt = new int[n + 1];
            Arrays.fill(signalReceiveAt, Integer.MAX_VALUE);
            DFS(signalReceiveAt, k, 0);
            // 找出最远的节点
            int answer = Integer.MIN_VALUE;
            for (int node = 1; node <= n; node ++) {
                answer = Math.max(answer, signalReceiveAt[node]);
            }
            return answer == Integer.MAX_VALUE ? -1 : answer;
        }

        private void DFS(int[] signalReceiveAt, int currNode, int currTime) {
            if (currTime >= signalReceiveAt[currNode]) {
                return;
            }
            signalReceiveAt[currNode] = currTime;
            if (!adj.containsKey(currNode)) {
                return;
            }
            for (Pair<Integer, Integer> edge: adj.get(currNode)) {
                int travelTime = edge.getKey();
                int neighborNode = edge.getValue();

                DFS(signalReceiveAt, neighborNode, currTime + travelTime);
            }
        }
    }

    /**
     * 这个BFS用Queue来维持k节点到其他节点的最短路径
     * May2022 Crib the answer
     */
    class BFS {
        Map<Integer, List<Pair<Integer, Integer>>> adj = new HashMap<>();
        public int networkDelayTime(int[][] times, int n, int k) {
            // 存储每个node相邻节点到adj
            for(int[] edge: times) {
                int source = edge[0];
                int dest = edge[1];
                int time = edge[2];
                adj.putIfAbsent(source, new ArrayList<>());
                adj.get(source).add(new Pair(time, dest));
            }
            //注意这里不需要排序了
            int[] signalReceiveAt = new int[n + 1];
            Arrays.fill(signalReceiveAt, Integer.MAX_VALUE);
            BFS(signalReceiveAt, k);
            // 找出最远的节点
            int answer = Integer.MIN_VALUE;
            for (int node = 1; node <= n; node ++) {
                answer = Math.max(answer, signalReceiveAt[node]);
            }
            return answer == Integer.MAX_VALUE ? -1 : answer;
        }
        private void BFS(int[] signalReceiveAt, int sourceNode) {
            Queue<Integer> q = new LinkedList<>();
            q.add(sourceNode);

            signalReceiveAt[sourceNode] = 0;  //Feb2023 注意这个别忘了
            while (!q.isEmpty()) {
                int currNode = q.remove();
                if (!adj.containsKey(currNode)) {
                    continue;
                }
                for (Pair<Integer, Integer> edge : adj.get(currNode)) {
                    int time = edge.getKey();
                    int neighborNode = edge.getValue();
                    int arrivalTime = signalReceiveAt[currNode] + time; //Feb2023 注意这个别忘了加已经有的时间
                    // networkAvailAt[to] = Math.min(networkAvailAt[to], reachTime); //错误写法
                    // 这里有一个重要思想, 就是一旦现有路径比后面的小, 那么就不要再enqueue后面的路径了, 这样就避免了循环timeout!
                    if (signalReceiveAt[neighborNode] > arrivalTime) {
                        signalReceiveAt[neighborNode] = arrivalTime;
                        q.add(neighborNode);
                    }
                }
            }
        }
    }

    /**
     May 2022 Crib answer

     */
    class Dijkstra {
        Map<Integer, List<Pair<Integer, Integer>>> adj = new HashMap<>();

        public int networkDelayTime(int[][] times, int n, int k) {
            // 存储每个node相邻节点到adj
            for(int[] edge: times) {
                int source = edge[0];
                int dest = edge[1];
                int time = edge[2];
                adj.putIfAbsent(source, new ArrayList<>());
                adj.get(source).add(new Pair(time, dest));
            }
            // 找出到每个节点的路径
            int[] signalReceiveAt = new int[n + 1];
            Arrays.fill(signalReceiveAt, Integer.MAX_VALUE);
            dijkstra(signalReceiveAt, k);
            // 找出最远的节点
            int answer = Integer.MIN_VALUE;
            for (int node = 1; node <= n; node ++) {
                answer = Math.max(answer, signalReceiveAt[node]);
            }

            return answer == Integer.MAX_VALUE ? -1 : answer;
        }

        private void dijkstra(int[] signalReceiveAt, int sourceNode) {
            Queue<Pair<Integer, Integer>> pq = new PriorityQueue<Pair<Integer, Integer>>(Comparator.comparing(Pair::getKey));
            pq.add(new Pair(0, sourceNode));

            signalReceiveAt[sourceNode] = 0;
            while (!pq.isEmpty()) {
                Pair<Integer, Integer> topPair = pq.remove();
                int currTime = topPair.getKey();
                int currNode = topPair.getValue();
                if (currTime > signalReceiveAt[currNode]) {
                    continue;
                }
                if (!adj.containsKey(currNode)) {
                    continue;
                }
                for (Pair<Integer, Integer> edge : adj.get(currNode)) {
                    int time = edge.getKey();
                    int neighborNode = edge.getValue();
                    if (signalReceiveAt[neighborNode] > currTime + time) {
                        signalReceiveAt[neighborNode] = currTime + time;
                        pq.add(new Pair(signalReceiveAt[neighborNode], neighborNode));
                    }
                }

            }
        }
    }

    public static void main(String[] args) {
//        int[][] times = [[4,2,76],[1,3,79],[3,1,81],[4,3,30],[2,1,47],
//        [1,5,61],[1,4,99],[3,4,68],[3,5,46],[4,1,6],[5,4,7],[5,3,44],
//        [4,5,19],[2,3,13],[3,2,18],[1,2,0],[5,1,25],[2,5,58],[2,4,77],[5,2,74]];

    }
}
