package BinarySearch;

import java.security.InvalidParameterException;

/**
 * https://www.1point3acres.com/bbs/thread-942679-1-1.html
 * TODO: check 答案讨论
 *
 * 给两个string: target, source. target和source几乎是一样的，除了source里面会插入一个非空的substring，要你找出这个substring
 * ex: target: abcdefgh source: abcdeXYZfgh return:XYZ
 */
public class Binary50003SearchOfInsertedString {
    static class BinarySearch {
        public String findInsertString(String target, String source) {
            if (target.length() == source.length()) {
                return "";
            }
            if (target.length() > source.length()) {
                throw new InvalidParameterException("Source string should be longer to target string");
            }

            // 不可能找substring开始的地方, 没有判断依据. target[mid] == source[mid]有可能在substring里面.
            int left = 0;
            int right = source.length() - 1;
            boolean found = false;
            while (!found) {

            }
            return "";
        }

        public static void main(String[] args) {
            String target;
            String source;

            target = "abcdefgh";
            source = "abcdeXYZfgh";

            target = "aaaabbbb";
            source = "aaaaFaabbFbbbb";

            target = "aaaaaaaa";
            source = "aaaaFaaaaFaaaa";
        }
    }
}
