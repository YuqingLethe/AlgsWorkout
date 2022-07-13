package HashMap;

import java.util.HashMap;
import java.util.Map;

/**
 * You are asked to design a file system that allows you to create new paths and associate them with different values.

 The format of a path is one or more concatenated strings of the form: / followed by one or more lowercase English letters.
      For example, "/leetcode" and "/leetcode/problems" are valid paths while an empty string "" and "/" are not.

 Implement the FileSystem class:

 bool createPath(string path, int value) Creates a new path and associates a value to it if possible and returns true.
     Returns false if the path already exists or its parent path doesn't exist.
 注意審題：
 1. 一個路徑只能有一個文件。 重複createPath， 哪怕value一致， 也返回false

 int get(string path) Returns the value associated with path or returns -1 if the path doesn't exist.


 Example 1:

 Input:
 ["FileSystem","createPath","get"]
 [[],["/a",1],["/a"]]
 Output:
 [null,true,1]
 Explanation:
 FileSystem fileSystem = new FileSystem();

 fileSystem.createPath("/a", 1); // return true
 fileSystem.get("/a"); // return 1

 Example 2:

 Input:
 ["FileSystem","createPath","createPath","get","createPath","get"]
 [[],["/leet",1],["/leet/code",2],["/leet/code"],["/c/d",1],["/c"]]
 Output:
 [null,true,true,2,false,-1]
 Explanation:
 FileSystem fileSystem = new FileSystem();

 fileSystem.createPath("/leet", 1); // return true
 fileSystem.createPath("/leet/code", 2); // return true
 fileSystem.get("/leet/code"); // return 2
 fileSystem.createPath("/c/d", 1); // return false because the parent path "/c" doesn't exist.
 fileSystem.get("/c"); // return -1 because this path doesn't exist.



 Constraints:

 The number of calls to the two functions is less than or equal to 104 in total.
 2 <= path.length <= 100
 1 <= value <= 109
 */
public class HM1166DesignFileSystem {
    static class FileSystem {
        class TrieNode {
            String name;
            int val = -1;
            Map<String, TrieNode> map = new HashMap<>();
            TrieNode(String name) {
                this.name = name;
            }
        }
        TrieNode root;

        public FileSystem() {
            this.root = new TrieNode("/");
        }

        public boolean createPath(String path, int value) {
            String[] parts = path.split("/");
            TrieNode parent = root;

            for (int i = 1; i < parts.length; ++i) {

                TrieNode next = parent.map.get(parts[i]);
                if (i < parts.length - 1 && next == null ) {
                    return false;
                }
                if (i == parts.length - 1 && next != null) {
                    return false;
                }
                if (next == null && i == parts.length - 1) {
                    next = new TrieNode(parts[i]);
                    next.val = value;
                    parent.map.put(parts[i], next);
                }
                parent = next;
            }
            return true;
        }

        public int get(String path) {
            String[] parts = path.split("/");
            TrieNode parent = root;
            for (int i = 1; i < parts.length; ++i) {
                TrieNode curr = parent.map.get(parts[i]);
                if (curr == null) {
                    return -1;
                }
                if (i == parts.length - 1) {
                    return curr.val;
                }
                parent = curr;
            }
            return -1;
        }
    }

    /**
     * July 2022 Crib answer totally.
     */
    class HashMapSolution {
        HashMap<String, Integer> paths;
        public HashMapSolution() {
            this.paths = new HashMap<>();
        }

        public boolean createPath(String path, int value) {
            //  注意這裏的corner case都可以寫。雖然test case並沒cover到。
            if (path.isEmpty() || (path.length() == 1 && path.equals("/")) || this.paths.containsKey(path)) {
                return false;
            }
            int delimIndex = path.lastIndexOf("/");
            String parent = path.substring(0, delimIndex);

            if (parent.length() > 1 && !paths.containsKey(parent)) {
                return false;
            }
            this.paths.put(path, value);
            return true;

        }

        public int get(String path) {
            return this.paths.getOrDefault(path, -1);
        }
    }


    /**
     * Your FileSystem object will be instantiated and called as such:
     * FileSystem obj = new FileSystem();
     * boolean param_1 = obj.createPath(path,value);
     * int param_2 = obj.get(path);
     */
    public static void main (String[] args) {
        String init = "FileSystem";
        String create = "createPath";
        String get = "get";

        FileSystem test = new FileSystem();
        test.createPath("/leet",1);
        test.createPath("/leet/code",2);
        test.get("/leet/code");
        test.createPath("/leet/code",3);
        test.get("/leet/code");





        // Command ["FileSystem","createPath","createPath","createPath","get","createPath","get"]
        // Parameters [[],["/leet",1],["/leet/code",2],["/leet/code",2],["/leet/code"],["/c/d",1],["/c"]]
        // expectedResult = {null,true,true,false,2,false,-1};

    }
}
