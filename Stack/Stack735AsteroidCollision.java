package Stack;

import java.util.Stack;

/**
 * Created by yuqing on 7/31/22.
 */
public class Stack735AsteroidCollision {
    /**
     * July 2022
     * 題目沒寫清楚的是，一開始向左後來向右的行星不會發生碰撞。
     */
    class Solution {
        public int[] asteroidCollision(int[] asteroids) {
            Stack<Integer> stack = new Stack<>();
            for (int ast : asteroids) {
                collide : {
                    while (!stack.isEmpty() && ast < 0 && stack.peek() > 0) {
                        if (stack.peek() < -ast) {
                            stack.pop();
                            continue;
                        } else if (stack.peek() == -ast) {
                            stack.pop();
                        }
                        break collide;
                    }
                    stack.push(ast);
                }

            }
            int[] result = new int[stack.size()];
            for (int i = result.length - 1; i >= 0; --i) {
                result[i] = stack.pop();
            }
            return result;
        }
    }
}
