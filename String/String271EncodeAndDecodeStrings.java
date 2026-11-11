package String;

import java.util.ArrayList;
import java.util.List;

public class String271EncodeAndDecodeStrings {
    /**
     * 主要是分隔的处理，这个是Prefix方式<length><特殊分隔符><String_itself>的方式，即便有重复char也不怕因为长度是先读到的
     */
    public class Codec2026 {

        // Encodes a list of strings to a single string.
        public String encode(List<String> strs) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < strs.size(); i ++) {
                String curr = strs.get(i);
                int len = curr.length();
                sb.append(len).append('#').append(curr);
            }
            System.out.println(sb);
            return sb.toString();
        }

        // Decodes a single string to a list of strings.
        public List<String> decode(String s) {
            List<String> result = new ArrayList<>();
            int i = 0; // index of String s

            while (i < s.length()) { // index最好随着一起动不然后面会乱
                // Get the length of next string // 这里注意可能是两位数长度的string
                StringBuilder lenSb = new StringBuilder();
                for (int j = i; s.charAt(j) != '#'; j ++, i ++) {
                    lenSb.append(s.charAt(j));
                }
                int len = Integer.valueOf(lenSb.toString());

                // Check deliminator
                char deliminator = s.charAt(i ++);
                if (deliminator != '#') {
                    throw new IllegalArgumentException("Invalid input");
                }

                // Build the current String and push to the list
                StringBuilder currSb = new StringBuilder();
                for (int j = 0; j < len; j ++) {
                    currSb.append(s.charAt(i + j)); //注意这里i不能增加，否则就乱了
                }
                i += len;
                result.add(currSb.toString());

            }
            return result;
        }
    }

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.decode(codec.encode(strs));
}
