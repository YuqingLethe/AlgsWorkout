package Design;

import java.util.HashSet;
import java.util.Set;

/**
 * https://www.1point3acres.com/bbs/thread-942679-1-1.html
 *
 * 1. 当你上传一个文件到cloud drive，client端会把这个文件分成一个一个chunk，chunk_id是连续的。作为server端你要实现以下三个func
 * 1）init(id) ->id是chunk的第一个id，或者说是初始id，可能不是1，可能是10,20.。。
 * 2) handle(id) ->handle 这个id
 * 3) get_lowest_missing_chunk_id() ->在已经收到的chunk id中寻找最小的，没有被handle的id
 * for example:
 * init(10)
 * get_lowest_missing_chunk_id() -> 10
 * handle(10)
 * handle(11)
 * handle(20)
 * get_lowest_missing_chunk_id()->12
 * get_lowest_missing_chunk_id()->12
 * handle(12)
 * get_lowest_missing_chunk_id->13
 * follow up：
 * 如果你的存储序列太大怎么办？
 * 会不会影响上传速度？ 影响了怎么解决？
 * 我的答案应该不是最好的，我觉得可以用segment tree但是这个代码我不是很熟悉就没用
 */
public class Design50002CouldFileServerMakeChunk {
    static class HandleChuck {
        private int init;
        private int prevLowestMissing;
        private Set<Integer> serverFile;
        public void init(int id) {
            this.init = id;
            this.serverFile = new HashSet<>();
            this.prevLowestMissing = init;
        }
        public boolean handle(int id) {
            if (id < init || serverFile.contains(id)) {
                return false;
            }
            serverFile.add(id);
            return true;
        }
        public int get_lowest_missing_chunk_id() {
            while (serverFile.contains(prevLowestMissing)) {
                prevLowestMissing ++;
            }
            return prevLowestMissing;
        }
    }
    public static void main(String[] args) {
        HandleChuck solution = new HandleChuck();

        solution.init(10);
        System.out.println(solution.get_lowest_missing_chunk_id());
        solution.handle(10);
        solution.handle(11);
        solution.handle(20);
        System.out.println(solution.get_lowest_missing_chunk_id());
        System.out.println(solution.get_lowest_missing_chunk_id());
        solution.handle(12);
        System.out.println(solution.get_lowest_missing_chunk_id());
    }
    private void printResult() {

    }
}
