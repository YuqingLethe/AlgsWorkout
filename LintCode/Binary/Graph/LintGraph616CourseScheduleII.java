package LintCode.Binary.Graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by Administrator on 2017/6/30.
 */
public class LintGraph616CourseScheduleII {
    /**
     * 6/30/2017 算是自己写的把.....
     */
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // Write your code here
        List[] edges = new ArrayList[numCourses];
        int[] degree = new int[numCourses];

        for (int i = 0; i < numCourses; i++) {
            edges[i] = new ArrayList<Integer>();
        }

        for (int i = 0; i < prerequisites.length; i++) {
            degree[prerequisites[i][0]]++;
            edges[prerequisites[i][1]].add(prerequisites[i][0]);
        }

        ArrayList<Integer> ans = new ArrayList<>();
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (degree[i] == 0) {
                q.offer(i);
                ans.add(i);
            }
        }


        int count = 0;
        while(!q.isEmpty()) { //这个条件就包含了, 入度为0的节点不存在, 有环的情况
            int crtCourse = q.poll();
            count++;
            int size = edges[crtCourse].size();
            for (int i = 0; i < size; i++) {
                int nextCourse = (int) edges[crtCourse].get(i);
                degree[nextCourse]--;
                if (degree[nextCourse] == 0) {
                    q.offer(nextCourse);
                    ans.add(nextCourse);
                }
            }
        }
        if (count != numCourses) {
            return new int[0];
        }
        int[] result = new int[ans.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = ans.get(i);
        }
        return result;
    }
}
