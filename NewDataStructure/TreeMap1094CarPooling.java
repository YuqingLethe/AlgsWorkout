package NewDataStructure;
import java.util.*;

/**
 *
 * There is a car with capacity empty seats. The vehicle only drives east (i.e., it cannot turn around and drive west).
 *
 * You are given the integer capacity and an array trips where trips[i] = [numPassengersi, fromi, toi] indicates that the ith trip has numPassengersi passengers and the locations to pick them up and drop them off are fromi and toi respectively. The locations are given as the number of kilometers due east from the car's initial location.
 *
 * Return true if it is possible to pick up and drop off all passengers for all the given trips, or false otherwise.
 *
 *
 *
 * Example 1:
 *
 * Input: trips = [[2,1,5],[3,3,7]], capacity = 4
 * Output: false
 * Example 2:
 *
 * Input: trips = [[2,1,5],[3,3,7]], capacity = 5
 * Output: true
 *
 *
 * Constraints:
 *
 * 1 <= trips.length <= 1000
 * trips[i].length == 3
 * 1 <= numPassengersi <= 100
 * 0 <= fromi < toi <= 1000
 * 1 <= capacity <= 105
 *
 * 自己的思路：每个time slot遍历一次， 更接近bucket sort那个思路， 没人家巧妙
 *  *
 */
public class TreeMap1094CarPooling {

    /**
     * Crib answer July 2022
     *
     * 答案思想： 只寻求变化， 记录change的timestamp， 以一开始capacity为0作为基准
     */
    class TimeStamp {
        public boolean carPooling(int[][] trips, int capacity) {
//            HashMap<Integer, Integer> timestamp = new HashMap<>(); //不能用hashmap， hashmap没有排序 treemap的values（）是有排序的
             Map<Integer, Integer> timestamp = new TreeMap<>();
            for (int[] trip : trips) {
                int numP = trip[0];
                int from = trip[1];
                int to = trip[2];
                int startPassenger = timestamp.getOrDefault(from, 0) + numP;
                timestamp.put(from, startPassenger);
                int endPassenger = timestamp.getOrDefault(to, 0) - numP;
                timestamp.put(to, endPassenger);

            }

            int usedCapacity = 0;
            for (int passengerChange : timestamp.values()) {
                usedCapacity += passengerChange;
                if (usedCapacity > capacity) {
                    return false;
                }
            }
            return true;
        }
    }

    class BucketSort {
        public boolean carPooling(int[][] trips, int capacity) {
            int[] timestamp = new int[1001];
            // Map<Integer, Integer> timestamp = new TreeMap<>();
            for (int[] trip : trips) {
                timestamp[trip[1]] += trip[0];
                timestamp[trip[2]] -= trip[0];
            }

            int usedCapacity = 0;
            for (int number : timestamp) {
                usedCapacity += number;
                if (usedCapacity > capacity) {
                    return false;
                }
            }
            return true;
        }
    }

    public static void main(String[] args) {
        //Test case 1
        // [[12,93,896],[77,291,904],[78,424,659],[41,668,962],[79,2,493],[7,369,840],[60,229,928],[41,7,163],[46,621,736],[97,958,984],[53,832,980],[15,218,815],[24,74,428],[12,415,959],[20,81,85],[45,567,601],[17,266,535],[65,828,943],[30,416,432],[27,48,142],[52,413,756],[21,79,274],[4,260,387],[49,180,314],[51,628,880],[94,271,462],[41,163,457],[30,187,925],[39,349,999],[5,289,809],[9,214,374],[10,302,534],[59,412,778],[77,306,497],[17,594,839],[53,404,892],[5,525,844],[89,275,619],[2,27,310],[79,473,755],[10,812,853],[76,55,549],[100,643,770],[36,701,997],[59,354,475],[70,586,924],[60,146,972],[32,121,305],[27,75,132],[17,32,758],[24,389,465],[81,55,258],[70,74,728],[36,184,703],[66,603,853],[63,319,964],[15,355,676],[69,312,521],[83,344,995],[41,73,439],[28,384,758],[90,341,365],[11,473,980],[49,631,737],[6,116,531],[99,334,460],[78,358,508],[26,426,823],[10,312,677],[48,532,711],[64,433,635],[62,591,765],[100,150,837],[24,60,945],[6,72,237],[21,602,838],[75,255,629],[54,824,935],[53,169,26...
        //2637
    }
}
