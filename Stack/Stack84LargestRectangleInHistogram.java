package Stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created  5/25/22. Crib the answer
 */
public class Stack84LargestRectangleInHistogram {
    class BruteForce {
        public int largestRectangleArea(int[] heights) {
            int maxArea = 0;
            for (int i = 0; i < heights.length; i++) {
                int minHeight = Integer.MAX_VALUE;
                for (int j = i; j < heights.length; ++j) {
                    minHeight = Math.min(minHeight, heights[j]);
                    maxArea = Math.max(maxArea, minHeight * (j - i + 1));
                }
            }
            return maxArea;
        }
    }

    class DivideAndConquer {
        private int calculateArea(int[] heights, int start, int end) {
            if (start > end) {
                return 0;
            }
            int minIndex = start;
            for (int i = start; i <= end; ++i) {
                if (heights[minIndex] > heights[i]) {
                    minIndex = i;
                }
            }
            return Math.max(heights[minIndex] * (end - start + 1),
                    Math.max(calculateArea(heights, start, minIndex - 1), calculateArea(heights, minIndex + 1, end))
            );
        }
        public int largestRectangleArea(int[] heights) {
            return calculateArea(heights, 0, heights.length - 1);
        }
    }

    class Stack {
        public int largestRectangleArea(int[] heights) {
            Deque<Integer> stack = new ArrayDeque<>();
            stack.push(-1);
            int length = heights.length;
            int maxArea = 0;

            for (int i = 0; i < length; ++i) {
                while ((stack.peek() != -1) && (heights[stack.peek()] >= heights[i])) {
                    int currHeight = heights[stack.pop()];
                    int currWidth = i - stack.peek() - 1;
                    maxArea = Math.max(maxArea, currHeight * currWidth);
                }
                stack.push(i);
            }
            while (stack.peek() != -1) {
                int currHeight = heights[stack.pop()];
                int currWidth = length - stack.peek() - 1;
                maxArea = Math.max(maxArea, currHeight * currWidth);
            }
            return maxArea;
        }
    }
}
