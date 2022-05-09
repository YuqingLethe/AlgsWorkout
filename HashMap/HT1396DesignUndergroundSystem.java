package HashMap;

import javafx.util.Pair;

import java.util.HashMap;

/**
 * An underground railway system is keeping track of customer travel times between different stations.
 *   They are using this data to calculate the average time it takes to travel from one station to another.
 *
 * Implement the UndergroundSystem class:
 *
 * void checkIn(int id, string stationName, int t)
 * A customer with a card ID equal to id, checks in at the station stationName at time t.
 * A customer can only be checked into one place at a time.
 * void checkOut(int id, string stationName, int t)
 * A customer with a card ID equal to id, checks out from the station stationName at time t.
 * double getAverageTime(string startStation, string endStation)
 * Returns the average time it takes to travel from startStation to endStation.
 * The average time is computed from all the previous traveling times from startStation to endStation that happened directly, meaning a check in at startStation followed by a check out from endStation.
 * The time it takes to travel from startStation to endStation may be different from the time it takes to travel from endStation to startStation.
 * There will be at least one customer that has traveled from startStation to endStation before getAverageTime is called.
 * You may assume all calls to the checkIn and checkOut methods are consistent. If a customer checks in at time t1 then checks out at time t2, then t1 < t2. All events happen in chronological order.
 *
 *
 *
 * Example 1:
 *
 * Input
 * ["UndergroundSystem","checkIn","checkIn","checkIn","checkOut","checkOut","checkOut","getAverageTime","getAverageTime","checkIn","getAverageTime","checkOut","getAverageTime"]
 * [[],[45,"Leyton",3],[32,"Paradise",8],[27,"Leyton",10],[45,"Waterloo",15],[27,"Waterloo",20],[32,"Cambridge",22],["Paradise","Cambridge"],["Leyton","Waterloo"],[10,"Leyton",24],["Leyton","Waterloo"],[10,"Waterloo",38],["Leyton","Waterloo"]]
 *
 * Output
 * [null,null,null,null,null,null,null,14.00000,11.00000,null,11.00000,null,12.00000]
 *
 * Explanation
 * UndergroundSystem undergroundSystem = new UndergroundSystem();
 * undergroundSystem.checkIn(45, "Leyton", 3);
 * undergroundSystem.checkIn(32, "Paradise", 8);
 * undergroundSystem.checkIn(27, "Leyton", 10);
 * undergroundSystem.checkOut(45, "Waterloo", 15);  // Customer 45 "Leyton" -> "Waterloo" in 15-3 = 12
 * undergroundSystem.checkOut(27, "Waterloo", 20);  // Customer 27 "Leyton" -> "Waterloo" in 20-10 = 10
 * undergroundSystem.checkOut(32, "Cambridge", 22); // Customer 32 "Paradise" -> "Cambridge" in 22-8 = 14
 * undergroundSystem.getAverageTime("Paradise", "Cambridge"); // return 14.00000. One trip "Paradise" -> "Cambridge", (14) / 1 = 14
 * undergroundSystem.getAverageTime("Leyton", "Waterloo");    // return 11.00000. Two trips "Leyton" -> "Waterloo", (10 + 12) / 2 = 11
 * undergroundSystem.checkIn(10, "Leyton", 24);
 * undergroundSystem.getAverageTime("Leyton", "Waterloo");    // return 11.00000
 * undergroundSystem.checkOut(10, "Waterloo", 38);  // Customer 10 "Leyton" -> "Waterloo" in 38-24 = 14
 * undergroundSystem.getAverageTime("Leyton", "Waterloo");    // return 12.00000. Three trips "Leyton" -> "Waterloo", (10 + 12 + 14) / 3 = 12
 *
 *
 * Example 2:
 *
 * Input
 * ["UndergroundSystem","checkIn","checkOut","getAverageTime","checkIn","checkOut","getAverageTime","checkIn","checkOut","getAverageTime"]
 * [[],[10,"Leyton",3],[10,"Paradise",8],["Leyton","Paradise"],[5,"Leyton",10],[5,"Paradise",16],["Leyton","Paradise"],[2,"Leyton",21],[2,"Paradise",30],["Leyton","Paradise"]]
 *
 * Output
 * [null,null,null,5.00000,null,null,5.50000,null,null,6.66667]
 *
 * Explanation
 * UndergroundSystem undergroundSystem = new UndergroundSystem();
 * undergroundSystem.checkIn(10, "Leyton", 3);
 * undergroundSystem.checkOut(10, "Paradise", 8); // Customer 10 "Leyton" -> "Paradise" in 8-3 = 5
 * undergroundSystem.getAverageTime("Leyton", "Paradise"); // return 5.00000, (5) / 1 = 5
 * undergroundSystem.checkIn(5, "Leyton", 10);
 * undergroundSystem.checkOut(5, "Paradise", 16); // Customer 5 "Leyton" -> "Paradise" in 16-10 = 6
 * undergroundSystem.getAverageTime("Leyton", "Paradise"); // return 5.50000, (5 + 6) / 2 = 5.5
 * undergroundSystem.checkIn(2, "Leyton", 21);
 * undergroundSystem.checkOut(2, "Paradise", 30); // Customer 2 "Leyton" -> "Paradise" in 30-21 = 9
 * undergroundSystem.getAverageTime("Leyton", "Paradise"); // return 6.66667, (5 + 6 + 9) / 3 = 6.66667
 *
 *
 * Constraints:
 *
 * 1 <= id, t <= 106
 * 1 <= stationName.length, startStation.length, endStation.length <= 10
 * All strings consist of uppercase and lowercase English letters and digits.
 * There will be at most 2 * 104 calls in total to checkIn, checkOut, and getAverageTime.
 * Answers within 10-5 of the actual value will be accepted.
 *
 * Complexity Analysis
 * Time complexity : O(1)O(1) for all.
 * checkIn(...): Inserting a key/value pair into a HashMap is an O(1)O(1) operation.
 * checkOut(...): Additionally, getting the corresponding value for a key from a HashMap is also an O(1)O(1) operation.
 * getAverageTime(...): Dividing two numbers is also an O(1)O(1) operation.
 *
 * Space complexity : O(P + S^2), where S is the number of stations on the network, and P is the number of passengers making a journey concurrently during peak time.
 * The program uses two HashMaps. We need to determine the maximum sizes these could become.
 * Firstly, we'll consider checkInData. This HashMap holds one entry for each passenger who has checkIn(...)ed, but not checkOut(...)ed.
 *     Therefore, the maximum size this HashMap could be is the maximum possible number of passengers making a journey at the same time,
 *     which we defined to be P. Therefore, the size of this HashMap is O(P).
 * Secondly, we need to consider journeyData. This HashMap has one entry for each pair of stations that has had at least one passenger start and end a journey at those stations.
 *     Over time, we could reasonably expect every possible pair of the S stations on the network to have an entry in this HashMap, which would be O(S^2).
 * Seeing as we don't know whether S^2 or P is larger, we need to add these together, giving a total space complexity of O(P + S^2).
 */
public class HT1396DesignUndergroundSystem {
    /**
     * April 2022 错误思路: 因为新建object没办法用hashmap搜索相同, 此题不能通过单独的route class来完成.
     * 如果用route, 必须配合一个RouteArray表格来枚举所有路线才行, 这样每个Route保证唯一(指针)
     */
    private static class Route {
        String start;
        String end;
        Route(String a, String b) {
            this.start = a;
            this.end = b;
        }
    }

    class UndergroundSystem {
        HashMap<String, Pair<Double, Double>> journeyData = new HashMap<>(); // StartStation->EndStation, Pair<Total time, total trips>
        HashMap<Integer, Pair<String, Integer>> checkInData = new HashMap<>(); // cardId, Pair<StartStation, checkinTime>

        public UndergroundSystem() {
        }

        public void checkIn(int id, String stationName, int t) {
            Pair<String, Integer> checkIn = new Pair<>(stationName, t); // https://www.geeksforgeeks.org/pair-class-in-java/
            checkInData.put(id, checkIn);
        }

        public void checkOut(int id, String stationName, int t) {
            Pair<String, Integer> checkInInfo = checkInData.get(id);
            String currRoute = formatRoute(checkInInfo.getKey(), stationName);
            double currTime = t - checkInInfo.getValue();

            Pair<Double, Double> existingTimes = journeyData.get(currRoute);
            if (existingTimes == null) {
                journeyData.put(currRoute, new Pair(currTime, 1.0));
                // 可以用这个替换 Pair<Double, Double> routeStats  = journeyData.getOrDefault(routeKey, new Pair<>(0.0, 0.0));
            } else {
                Double totalTime = existingTimes.getKey() + currTime;
                Double totalTrip = existingTimes.getValue() + 1;
                journeyData.put(currRoute, new Pair<>(totalTime, totalTrip));
            }
            // 最后还可以 Remove check in data for this id. 拿时间换空间
            // Note that this is optional, we'll talk about it in the space complexity analysis.
            checkInData.remove(id);
        }

        public double getAverageTime(String startStation, String endStation) {
            String currRoute = formatRoute(startStation, endStation);
            Pair<Double, Double> currData = journeyData.get(currRoute);
            return currData.getKey() / currData.getValue();
        }
        private String formatRoute(String start, String end) {
            return start + "->" + end;
        }
    }
    public static void main(String[] args) {
        Route a = new Route("A", "B");
        Route b = new Route("A", "B");
        HashMap<Route, Integer> hm = new HashMap<>();
        hm.put(a, 3);
        System.out.println(hm.get(b));
    }
}
