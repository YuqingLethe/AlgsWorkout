package HashMap;

import java.util.HashMap;
import java.util.Map;

public class HM369LoggerRateLimiter {
    class Logger {
        private Map<String, Integer> lastPrintTime;
        public Logger() {
            lastPrintTime = new HashMap<>();
        }

        public boolean shouldPrintMessage(int timestamp, String message) {
            if (lastPrintTime.containsKey(message)) {
                int lastPrintTimeStamp = lastPrintTime.get(message);
                if (lastPrintTimeStamp + 10 <= timestamp) {
                    lastPrintTime.put(message, timestamp);
                    return true;
                } else {
                    return false;
                }
            }
            lastPrintTime.put(message, timestamp);
            return true;
        }
    }
}
