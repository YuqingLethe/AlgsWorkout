package LintCode.Binary.Heap;

import java.util.*;

class Record {
    public int id, score;
    public Record(int id, int score){
        this.id = id;
        this.score = score;
    }
}
public class LintHeap613HighFive {

    public class SolutionLTE {
        /**
         * 这是我的直白的初始思路, 有的TestCase毫不留情的LTE了. 我想问一下正式面试时候我这种Object[] 转来转去是不是不太好啊?
         * 先占坑, 我马上学一下怎么写PrioritiyQueue.... ,
         *
         * @param results a list of <student_id, score>
         * @return find the average of 5 highest scores for each person
         * Map<Integer, Double> (student_id, average_score)
         */
        public Map<Integer, Double> highFive(Record[] results) {
            // Build the mapping between student and all his scores
            Map<Integer, ArrayList<Integer>> totalScores = new HashMap<>();
            ArrayList<Integer> ids = new ArrayList<>();
            for (Record r : results) {
                if (!totalScores.containsKey(r.id)) {
                    totalScores.put(r.id, new ArrayList<Integer>());
                    ids.add(r.id);
                }
                totalScores.get(r.id).add(r.score);
            }

            //Sort the scores and use the top five ones to get the avg.
            Map<Integer, Double> ans = new HashMap<>();
            for (Integer stu : ids) {
                Object[] scores = totalScores.get(stu).toArray();
                Arrays.sort(scores);
                int len = scores.length;
                if (len < 5) {
                    return null;
                }
                int topFive = (int) scores[len - 1] + (int) scores[len - 2] + (int) scores[len - 3] + (int) scores[len- 4] + (int) scores[len- 5];
                ans.put(stu, (double) topFive / 5);
            }
            return ans;
        }
    }

    public class SolutionSelectSort {
        /**
         * 写了一个select sort的排序, 65%的时候依旧LTE了
         */
        public Map<Integer, Double> highFive(Record[] results) {
            // Build the mapping between student and all his scores
            Map<Integer, ArrayList<Integer>> totalScores = new HashMap<>();
            ArrayList<Integer> ids = new ArrayList<>();
            for (Record r : results) {
                if (!totalScores.containsKey(r.id)) {
                    totalScores.put(r.id, new ArrayList<Integer>());
                    ids.add(r.id);
                }
                totalScores.get(r.id).add(r.score);
            }

            //Sort the scores and use the top five ones to get the avg.
            Map<Integer, Double> ans = new HashMap<>();
            for (Integer stu : ids) {
                ArrayList<Integer> scores = totalScores.get(stu);
                selectSort(scores);
                if (scores.size() < 5) {
                    return null;
                }
                int topFive = scores.get(0) + scores.get(1) + scores.get(2) + scores.get(3) + scores.get(4);
                ans.put(stu, (double) topFive / 5);
            }
            return ans;
        }

        private void selectSort(ArrayList<Integer> nums) {
            for (int i = 0; i < 5; i++) {
                for (int j = i + 1; j < nums.size(); j++) {
                    if (nums.get(i) < nums.get(j)) {
                        Integer tmp = nums.get(i);
                        nums.set(i, nums.get(j));
                        nums.set(j, tmp);
                    }
                }
            }
        }
    }

    /**
     * Priority Queue
     * TimeComplexity O(Nlog5) N is the number of students
     */
    public class Solution {
        int topN = 5;

        public Map<Integer, Double> highFiveHeap(Record[] results) {
            // Write your code here
            Map<Integer, Double> ans = new HashMap<>();
            if (results == null) {
                return ans;
            }

            //Build Priority Queue which stores top 5 scores
            Comparator<Integer> comparator = new DescendingComparator();

            Map<Integer, PriorityQueue<Integer>> allscores = new HashMap<>();
            for (Record r : results) {
                if (!allscores.containsKey(r.id)) {
                    allscores.put(r.id, new PriorityQueue<>(topN, comparator));
                }
                allscores.get(r.id).add(r.score);
            }
            //Get the avg scores
            for (Integer stu : allscores.keySet()) {  //也可以用entrySet() 然后通过stu的getValue()和getKey()
                double sum = 0.0;
                int tops = topN;
                while(tops-- > 0) {
                    sum += allscores.get(stu).poll();
                }
                ans.put(stu, sum/topN);
            }
            return ans;
        }
    }

    class DescendingComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer x, Integer y) {
            if (x < y) {
                return 1;
            }
            if (x > y) {
                return -1;
            }
            return 0;
        }
    }

}
