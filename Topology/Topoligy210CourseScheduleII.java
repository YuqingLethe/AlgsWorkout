package Topology;

import java.util.*;

public class Topoligy210CourseScheduleII {
    class Solution {
        public int[] findOrder(int numCourses, int[][] prerequisites) {
            int[] result = new int[numCourses];
            int resultIdx = 0;
            if (numCourses == 0) {
                return result;
            }

            // Build the graph and indegree
            int[] indegree = new int[numCourses];
            HashMap<Integer, List<Integer>> graph = new HashMap<>();
            for (int[] coursePrer : prerequisites) {
                int course = coursePrer[0];
                int prerequisite = coursePrer[1];
                indegree[course] ++;
                if (!graph.containsKey(prerequisite)) {
                    graph.put(prerequisite, new ArrayList<>());
                }
                graph.get(prerequisite).add(course);
            }

            // Find scheduling order by using indegreeIsZero queue
            Queue<Integer> indegreeIsZero = new LinkedList<>();
            for (int i = 0; i < numCourses; ++i) {
                if (indegree[i] == 0) {
                    indegreeIsZero.add(i);
                }
            }

            while (!indegreeIsZero.isEmpty()) {
                int prerequisite = indegreeIsZero.poll();
                result[resultIdx] = prerequisite;
                resultIdx ++;
                List<Integer> toTake = graph.get(prerequisite);
                for (int i = 0; toTake != null && i < toTake.size(); ++i) { //注意这里是有可能为null的!
                    Integer currCourse = toTake.get(i);
                    indegree[currCourse] --;
                    if (indegree[currCourse] == 0) {
                        indegreeIsZero.add(currCourse);
                    }
                }
            }

            if (resultIdx < numCourses) {
                return new int[0];
            }
            return result;

        }
    }
}
