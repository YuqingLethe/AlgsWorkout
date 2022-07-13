package Queue;

import java.security.InvalidParameterException;
import java.util.InvalidPropertiesFormatException;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Given a sequence of timestamps & actions of a dasher's activity within a day, we would like to know the active time of the dasher. Idle time is defined as the dasher has NO delivery at hand. (That means all items have been dropped off at this moment and the dasher is just waiting for another pickup) Active time equals total time minus idle time. Below is an example. Dropoff can only happen after pickup. 12:00am means midnight and 12:00pm means noon. All the time is within a day.
 Timestamp(12h) | Action
 8:30am | pickup
 9:10am | dropoff
 10:20am | pickup
 12:15pm | pickup
 12:45pm | dropoff
 2:25pm | dropoff
 total time = 2:25pm-8:30am = 355 mins;	  idle time = 10:20am-9:10am = 70 mins;
 active time = total time-idle time = 355-70 = 285 mins;

 */
public class Dasher_ActiveTimeOfDasher {
    /**
     * 題目主要考設計，什麼時候轉換時間最方便， 怎樣處理各類狀態性價比最高。自己可以提高的點：
     * 1. 用count來代替queue的作用， 而不需要真正進行q的運算
     * 2. helper直接把時間戳換算成一個minutes的絕對值，最直接。 時間差可以在主函數裏用減號輕鬆得到.
     * 3. 何時call getMinutes（）？
     *
     * 編程易錯點：
     * 1. string用equals（）
     * 2. string邊int用parseInt
     */
    class firstTry {

    }

    final static String PICKUP = "pickup";
    final static String DROPOFF = "dropoff";

    private static int getActiveTimes(List<String> activities) {
        int workTimeInMinute = 0;
        String start = null;
        String end = null;
        int count = 0;
        for (String activity: activities) {
            String[] currActivity = activity.split("\\s+");
            String timeStamp = currActivity[0];
            String action = currActivity[2];
            System.out.println(timeStamp + " " + action);

            if (action.equals(DROPOFF)) {
                count --;
                if (count == 0) {
                    end = timeStamp;
                }
            } else if (action.equals(PICKUP)) {
                count ++;
                if (count == 1) {
                    start = timeStamp;
                }
            } else {
                throw new InvalidParameterException();
            }

            if (count == 0) {
                workTimeInMinute += getMinutes(end) - getMinutes(start);
            }
        }
        return workTimeInMinute;
    }

    private static int getMinutes(String timeStamp) {
        if (timeStamp == null) {
            throw new InvalidParameterException();
        }

        int result = 0;
        String[] times = timeStamp.split(":");
        int hours = Integer.parseInt(times[0]);
        hours = hours == 12 ? 0 : hours;
        int minutes = Integer.parseInt(times[1].substring(0, 2));
        if (times[1].substring(2, 4).equals("pm")) {
            result += 12 * 60;
        }
        result += hours * 60;
        result += minutes;
        return result;
    }

    public static void main(String[] args) {
        List<String> activity = new LinkedList<>();
        activity.add("8:30am | pickup");
        activity.add("9:10am | dropoff");
        activity.add("10:20am | pickup");
        activity.add("12:15pm | pickup");
        activity.add("12:45pm | dropoff");
        activity.add("2:25pm | dropoff");

        System.out.println(getActiveTimes(activity));

    }
}
