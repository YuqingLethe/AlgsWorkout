package LintCode.Binary.Graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by yuqing on 6/30/17.
 */
public class LintGraph615CourseSchedule {
    /**
     * 6、30、2017 不会做， 看的答案做的。 从入度的角度考虑， 比建立level map要简洁
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // Write your code here
        List[] edges = new ArrayList[numCourses];
        int[] degree = new int[numCourses];

        for (int i = 0; i < numCourses; i++) //不要忘了初始化！！！
            edges[i] = new ArrayList<Integer>();


        for (int i = 0; i < prerequisites.length; i++) {
            degree[prerequisites[i][0]]++;
            edges[prerequisites[i][1]].add(prerequisites[i][0]);
        }

        Queue<Integer> q = new LinkedList<Integer>();
        for (int i = 0; i < degree.length; i++) {
            if (degree[i] == 0) { //注意这里一定要用idx来遍历degree， 不要忘了idx的特殊性
                q.add(i);
            }
        }

        int count = 0;
        while (!q.isEmpty()) {
            int crt = (int) q.poll();
            count++;
            for (int i = 0; i < edges[crt].size(); i++) {
                int nextCourse = (int) edges[crt].get(i);
                degree[nextCourse]--;
                if (degree[nextCourse] == 0) {
                    q.add(nextCourse);
                }
            }
        }

        return count == numCourses;

    }


}
