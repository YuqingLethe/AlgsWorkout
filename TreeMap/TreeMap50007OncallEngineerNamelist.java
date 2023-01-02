package TreeMap;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class TreeMap50007OncallEngineerNamelist {
    static class Shift {
        int start;
        int end;
        String name;
        Shift(String name, int start, int end) {
            this.name = name;
            this.start = start;
            this.end = end;
        }
    }
    static class TreeMapNode {
        int start;
        int end;
        List<String> names;
        TreeMapNode(int start, int end) {
            this.start = start;
            this.end = end;
            names = new ArrayList<>();
        }
    }

    static class GetNameListDuringPeriod {
        private TreeMap<Integer, TreeMapNode> tm;
        public List<String> getNameList(List<Shift> list, int start, int end) {
            List<String> oncallNames = new ArrayList<>();
            tm = new TreeMap<>();
            for (Shift shift : list) {
                String name = shift.name;
                int startT = shift.start;
                int endT = shift.end;
                if (tm.size() == 0) {
                    TreeMapNode tmn = new TreeMapNode(startT, endT);
                    tmn.names.add(name);
                    tm.put(startT, tmn);
                }
            }
            return oncallNames;
        }
        private void putNodeInTreeMap(Shift shift) {
            int prevStartTime = 0;
            int currStart = shift.start;
            int currEnd = shift.end;
            String currName = shift.name;

            if (tm.containsKey(currStart)) {
                int shiftEnd = tm.get(currStart).end;
                List<String> shiftNames = tm.get(currStart).names;
                if (currEnd > shiftEnd) {
                    Shift newShift = new Shift(currName, shiftEnd, currEnd);
                    putNodeInTreeMap(newShift);
                }
                shiftNames.add(currName);
            }

            for (Integer s : tm.keySet()) {
                int shiftStart = s;
                int shiftEnd = tm.get(s).end;
                if (currStart == shiftStart && currEnd == shiftEnd) {
                    tm.get(s).names.add(currName);
                    return;
                }
                if (currStart < shiftStart && currEnd <= shiftEnd) {
                    addNewNodeToTreeMap(currStart, currEnd, shift.name);
                } else if (currStart < shiftStart && currEnd > shiftEnd) {
                    addNewNodeToTreeMap(currStart, shiftStart, shift.name);
                    Shift newShift = new Shift(shift.name, shiftStart, currEnd);
                    putNodeInTreeMap(newShift);
                }
            }
        }
        private void addNewNodeToTreeMap(int startT, int endT, String name) {
            TreeMapNode tmn = new TreeMapNode(startT, endT);
            tmn.names.add(name);
            tm.put(startT, tmn);
        }
    }


    public static void main(String[] args) {
        Shift s1 = new Shift("Alex", 1, 2);
        Shift s2 = new Shift("Ben", 2, 5);
        Shift s3 = new Shift("Jeff", 15, 17);

    }
}
