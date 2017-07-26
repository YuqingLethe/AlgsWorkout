package Math;

/**
 * Created by Administrator on 2017/7/25.
 */
public class Lint254DropEggs {
    /**
     * 需要准确理解minimize worst case这句话
     * https://www.jiuzhang.com/qa/2711/
     * 2017/7/25
     */
    public int dropEggs(int n) {
        long times = 0; // 注意n是int, 和要long型!!!
        for (int i = 1; ; i++) {
            times += i;
            if (times >= n) {
                return i;
            }
        }
    }
}
