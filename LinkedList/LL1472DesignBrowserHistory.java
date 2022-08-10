package LinkedList;

import java.util.ArrayList;
import java.util.List;

public class LL1472DesignBrowserHistory {
    /**
     * Aug 2022 self solve
     * 又在list.remove()栽坑了, 以后一旦list remove就要警钟敲响!
     */
    class BrowserHistory {
        // 注意这个题如果在history中间进行visit, 则后面的记录删除
        private List<String> list;
        private int retrievalIdx;
        private int endIdx;
        public BrowserHistory(String homepage) {
            list = new ArrayList<>();
            list.add(homepage);
            retrievalIdx = 0;
            endIdx = 0;
        }

        public void visit(String url) {
            while (retrievalIdx + 1 < list.size()) {
                list.remove(retrievalIdx + 1); // 注意这里remove list的话size一直在变化, idx不应该再增加.
            }
            list.add(url);
            endIdx = list.size() - 1;
            retrievalIdx = endIdx;
        }

        public String back(int steps) {
            if (steps > retrievalIdx) {
                retrievalIdx = 0; //这个别忘了
                return list.get(0);
            }
            retrievalIdx = retrievalIdx - steps;
            return list.get(retrievalIdx);
        }

        public String forward(int steps) {
            if (steps > endIdx - retrievalIdx) {
                retrievalIdx = endIdx; //这个别忘了
                return list.get(endIdx);
            }
            retrievalIdx = retrievalIdx + steps;
            return list.get(retrievalIdx);
        }
    }

    /**
     * Your BrowserHistory object will be instantiated and called as such:
     * BrowserHistory obj = new BrowserHistory(homepage);
     * obj.visit(url);
     * String param_2 = obj.back(steps);
     * String param_3 = obj.forward(steps);
     */
    public static void main(String[] args) {
        // Test 1
        // ["BrowserHistory","visit","forward","back","visit","visit","visit","visit","back","visit","back","forward","visit","visit","visit"]
   //[["hdqqhm.com"],["yltqtsj.com"],[1],[1],["cyv.com"],["vbpvni.com"],["opy.com"],["kbyzp.com"],[7],["fchhxaz.com"],[6],[9],["rg.com"],["oemqn.com"],["hyqsb.com"]]
        // Result:
        // [null,null,"yltqtsj.com","hdqqhm.com",null,null,null,null,"hdqqhm.com",null,"hdqqhm.com","fchhxaz.com",null,null,null]

        // Test 2
//        ["BrowserHistory","back","visit",      "back","visit",         "visit"   ,"back",
//        [["rlglu.com"],    [2],  ["zxowmd.com"],[4],  ["gbnssie.com"],["bqo.com"],[6],
//
//        "visit",   "visit",   "back","visit",    "visit",       "forward","visit","    forward","forward","visit",    "forward","forward"]
//        ["cq.com"],["pbh.com"],[5],  ["ijt.com"],["tnsiso.com"],[7],       ["xjxi.com"],[3],     [9],     ["zlnx.com"],[3],      [7]]


    }
}
