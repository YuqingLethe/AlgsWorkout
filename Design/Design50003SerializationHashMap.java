package Design;

import java.util.HashMap;

/**
 * https://www.1point3acres.com/bbs/thread-942679-1-1.html
 *
 * 第一个func是：serialization python dict/hashmap to string
 * 第二个是根据第一个r‍‍‍‍‌‌‌‌‍‌‌‌‍‌‍‍eturn的string，把他deserialization。这里map/dict的key是int value是str
 * 我的做法是 “1LEN34LENXXXXXX" 1是key 34是value的长度 然后XXX是value本身
 */
public class Design50003SerializationHashMap {

    static class CribAnswer {
        private final int maxChunkLen = 34;
        private static int N;
        public String serialization(HashMap<Integer, String> map) {
            this.N = map.size();  // TODO: 这里应该是找出最大key所占的长度, 而不是根据map size定长度!
            StringBuilder sb = new StringBuilder();
            for (Integer key : map.keySet()) {
                String keyString = convertIntegerToDefinedLengthString(key, N);
                System.out.println(key + " " + keyString);
                sb.append(keyString);

                String value = map.get(key);
                if (value.length() <= maxChunkLen) {
                    value = makeStringToDefinedLengthString(value, maxChunkLen);
                    sb.append(value);
                } else { // If longer than maxChunkLen, divide them and insert with same KeyString until finished
                    String remaining = value;
                    while (remaining.length() > maxChunkLen) {
                        sb.append(remaining.substring(0, maxChunkLen));
                        sb.append(keyString);
                        remaining = remaining.substring(maxChunkLen);
                    }
                    value = makeStringToDefinedLengthString(remaining, N);
                    sb.append(value);
                }
            }
            return sb.toString();
        }

        public HashMap<Integer, String> deserialization(String s) {
            int unitLen = N + maxChunkLen;
            int fromIdx = 0;
            HashMap<Integer, String> result = new HashMap<>();
            Integer prevKey = null;
            String prevValue = "";
            do {
                String keyString = s.substring(fromIdx, fromIdx + N);
                String valueString = s.substring(fromIdx + N, fromIdx + unitLen);
                fromIdx += unitLen;

                Integer key = getMapKeyFromString(keyString);
                String value = getMapValueFromString(valueString);
                if (prevKey != null && prevKey != key) {
                    result.put(prevKey, prevValue);
                    prevValue = "";
                }
                prevKey = key;
                prevValue += value;
            } while (fromIdx < s.length());
            result.put(prevKey, prevValue); //Handle之前的value就要记得while 跳出以后的处理
            return result;
        }

        private String convertIntegerToDefinedLengthString(Integer num, int N) {
            int[] nums = new int[N];
            int tmp = num;
            int idx = 0;
            while (tmp%10 != 0 || tmp/10 != 0) { // num = 115
                nums[idx] = tmp%10;
                tmp = tmp/10;
                idx ++;
            }

            StringBuilder sb = new StringBuilder();
            for (int i = N - 1; i >= 0; --i) {
                sb.append(nums[i]);
            }
            return sb.toString();
        }
        private String makeStringToDefinedLengthString(String s, int N) {
            int diff = N - s.length();
            StringBuilder sb = new StringBuilder();
            sb.append(s);
            for (int i = 0; i < diff; ++i) {
                sb.append(".");
            }
            return sb.toString();
        }
        private Integer getMapKeyFromString(String s) {
            int startIdx = 0;
            for (int i = 0; i < s.length(); ++i) {
                char c = s.charAt(i);
                if (c != '0') {
                    startIdx = i;
                }
            }
            Integer result = 0;
            for (; startIdx < s.length(); ++startIdx) {
                Integer c = s.charAt(startIdx) - '0';
                result = result * 10 + c;
            }
            return result;
        }
        private String getMapValueFromString(String s) {
            for (int i = s.length() - 1; i >= 0; --i) {
                char c = s.charAt(i);
                if (c != '.') {
                    return s.substring(0, i);
                }
            }
            return "";
        }

    }
    public static void main(String[] args) {
        HashMap<Integer, String> map = new HashMap<>();
        map.put(1, "aaaaa");
        map.put(2, "bbbbbbbbaaaaaaaaaaaaaakjklkjlkj");
        map.put(3, "");
        map.put(101, "check101");
        map.put(105, "check105");
        map.put(1100, "jlkjcheck1100");

        CribAnswer solution = new CribAnswer();
        String serializedString = solution.serialization(map);
        System.out.println(serializedString);

        HashMap<Integer, String> answer = solution.deserialization(serializedString);
        System.out.println(answer.toString());
    }

}
