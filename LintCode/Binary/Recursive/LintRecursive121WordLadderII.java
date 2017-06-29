package LintCode.Binary.Recursive;

import java.util.*;

/**
 * Created by Administrator on 2017/6/29.
 */
public class LintRecursive121WordLadderII {
    /**
     * 6/29/2017 并不会做, 对着答案做的
     */
    public List<List<String>> findLadders(String start, String end, Set<String> dict) {
        // write your code here
        List<List<String>> ladders = new ArrayList<List<String>>();
        Map<String, List<String>> map = new HashMap<String, List<String>>(); //反向的neighbors map
        Map<String, Integer> distance = new HashMap<String, Integer>();

        dict.add(start);
        dict.add(end);

        bfs(map, distance, start, end, dict);

        List<String> path = new ArrayList<String>();

        dfs(ladders, path, end, start, distance, map);

        return ladders;

    }

    private void dfs(List<List<String>> ladders,
                     List<String> path,
                     String crt,
                     String start,
                     Map<String, Integer> distance,
                     Map<String, List<String>> map) {
        path.add(crt); //这个可以放最前面的, 两个条件都需要

        if (crt.equals(start)) {
            Collections.reverse(path);
            ladders.add(new ArrayList<String>(path));
            Collections.reverse(path);
            path.remove(path.size()  - 1); //这里别忘了减去最后一个, 因为也加了...
            return;
        }

        for (String next : map.get(crt)) {
            if (distance.containsKey(next) //这个很需要哦, 不是所有节点都有distance!!
                    && distance.get(crt) > distance.get(next)) {
//                && distance.get(crt) == distance.get(next) + 1) {
                dfs(ladders, path, next, start, distance, map);
            }
        }
        path.remove(path.size() - 1);

    }

    private void bfs(Map<String, List<String>> map,
                     Map<String, Integer> distance,
                     String start,
                     String end,
                     Set<String> dict) {
        Queue<String> q = new LinkedList<>();
        q.offer(start);
        distance.put(start, 0);
        for (String s : dict) {//初始化不要忘了.....
            map.put(s, new ArrayList<String>());
        }

        while (!q.isEmpty()) {
            String crt = q.poll();
            List<String> neighbors = expand(crt, dict);

            for (String n : neighbors) {
                map.get(n).add(crt); //这句话, 是在建立反向的neighbors
                if (!distance.containsKey(n)) {
                    distance.put(n, distance.get(crt) + 1);
                    q.offer(n);
                }
            }
        }

    }


    private List<String> expand(String crt, Set<String> dict) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < crt.length(); i++) {
            for (char c = 'a'; c < 'z'; c++) {
                if (c == crt.charAt(i)) {
                    continue;
                }
                String s = crt.substring(0, i) + c + crt.substring(i + 1);
                if (dict.contains(s)) {
                    list.add(s);
                }
            }
        }
        return list;
    }
}
