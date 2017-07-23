package LintCode.Binary.Hash;

/**
 * Created by Administrator on 2017/7/23.
 */
public class LintHash128HashFunction {
    /**
     * 细节背诵
     * 1 公式都背下来
     * 2 中间过程必须用long,否则没法存模之前的数
     * 2017/7/23
     */
    public int hashCode(char[] key,int HASH_SIZE) {
        // write your code here
        int len = key.length;
        long value = 0;
        for (int i = 0; i < len; i++) {
            value = (value * 33 + key[i]) % HASH_SIZE;
        }
        return (int) value;
    }
}
