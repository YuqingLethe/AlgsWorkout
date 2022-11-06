package HashMap;

import java.util.HashSet;
import java.util.Set;

public class HT575DistributeCandies {
    class HashSet_Solution {
        public int distributeCandies(int[] candyType) {
            final int N = candyType.length;
            Set<Integer> candyTypes = new HashSet<>();
            for (Integer type : candyType) {
                if (!candyTypes.contains(type)) {
                    candyTypes.add(type);
                }
            }
            if (candyTypes.size() <= N/2) {
                return candyTypes.size();
            }
            return N/2;
        }
    }

}
