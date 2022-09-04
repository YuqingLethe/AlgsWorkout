package NewDataStructure;

import java.util.*;

public class Iterator_Fundamentals {
    public int[] setWithPrimitiveArray (int[] arr) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < arr.length; ++i) {
            set.add(arr[i]);
        }

        List<Integer> result = new ArrayList<>();
        Iterator<Integer> iterator = set.iterator(); //不能多次拿iterator
        while (iterator.hasNext()) {
            result.add(iterator.next());
        }
        Collections.sort(result);
        return result.stream().mapToInt(Integer :: intValue).toArray();
    }

    public void main(String[] args) {

    }
}
