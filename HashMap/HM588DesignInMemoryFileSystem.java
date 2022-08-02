package HashMap;

/**
 * Design a data structure that simulates an in-memory file system.

 Implement the FileSystem class:

 FileSystem() Initializes the object of the system.
 List<String> ls(String path)
 If path is a file path, returns a list that only contains this file's name.
 If path is a directory path, returns the list of file and directory names in this directory.
 The answer should in lexicographic order.
 void mkdir(String path) Makes a new directory according to the given path. The given directory path does not exist. If the middle directories in the path do not exist, you should create them as well.
 void addContentToFile(String filePath, String content)
 If filePath does not exist, creates that file containing given content.
 If filePath already exists, appends the given content to original content.
 String readContentFromFile(String filePath) Returns the content in the file at filePath.


 Example 1:


 Input
 ["FileSystem", "ls", "mkdir", "addContentToFile", "ls", "readContentFromFile"]
 [[], ["/"], ["/a/b/c"], ["/a/b/c/d", "hello"], ["/"], ["/a/b/c/d"]]
 Output
 [null, [], null, null, ["a"], "hello"]

 Explanation
 FileSystem fileSystem = new FileSystem();
 fileSystem.ls("/");                         // return []
 fileSystem.mkdir("/a/b/c");
 fileSystem.addContentToFile("/a/b/c/d", "hello");
 fileSystem.ls("/");                         // return ["a"]
 fileSystem.readContentFromFile("/a/b/c/d"); // return "hello"


 Constraints:

 1 <= path.length, filePath.length <= 100
 path and filePath are absolute paths which begin with '/' and do not end with '/' except that the path is just "/".
 You can assume that all directory names and file names only contain lowercase letters, and the same names will not exist in the same directory.
 You can assume that all operations will be passed valid parameters, and users will not attempt to retrieve file content or list a directory or file that does not exist.
 1 <= content.length <= 50
 At most 300 calls will be made to ls, mkdir, addContentToFile, and readContentFromFile.
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Your FileSystem object will be instantiated and called as such:
 * FileSystem obj = new FileSystem();
 * List<String> param_1 = obj.ls(path);
 * obj.mkdir(path);
 * obj.addContentToFile(filePath,content);
 * String param_4 = obj.readContentFromFile(filePath);
 */
public class HM588DesignInMemoryFileSystem {

    class FileSystem {
        class File {
            String name;
            String content = "";
            HashMap<String, File> subs = new HashMap<>();
            public File(String s) {
                this.name = s;
            }
        }

        private File root;
        public FileSystem() {
            this.root = new File("/");
        }

        public List<String> ls(String path) {
            String[] paths = path.split("/");
            File curr = returnLastFile(path);
            List<String> result = new ArrayList<>();
            if (!curr.content.equals("")) { //如果有content， 是個file path， 則return file name
                result.add(curr.name);
                return result;
            }
            if (!curr.subs.isEmpty()) {
                result.addAll(curr.subs.keySet());
            }
            Collections.sort(result); // 如果是個directory， 返回值要排序

            return  result;
        }

        public void mkdir(String path) {
            this.returnLastFile(path);
            return;
        }
        private File returnLastFile(String path) {
            String[] paths = path.split("/");
            File curr = root;
            for (int i = 1; i < paths.length; ++i) {
                HashMap<String, File> currSubs = curr.subs;
                String newName = paths[i];
                if (currSubs.containsKey(newName)) {
                    curr = currSubs.get(newName);
                } else {
                    File newFile = new File(newName);
                    curr.subs.put(newName, newFile);
                    // System.out.println("newName=" + newName);
                    // System.out.println("curr.subs.get(newName).name=" + curr.subs.get(newName).name);
                    curr = newFile;
                }
            }
            return curr;
        }

        public void addContentToFile(String filePath, String content) {
            File currFile = returnLastFile(filePath);
            currFile.content += content;
        }

        public String readContentFromFile(String filePath) {
            File currFile = returnLastFile(filePath);
            return currFile.content;
        }
    }



    public static void main(String[] args) {
        // Test Case 1 : ls result needs to be sorted.
        //["FileSystem","mkdir",     "ls",       "ls",        "mkdir","ls",          "ls",  "addContentToFile",    "readContentFromFile","ls",            "readContentFromFile"]
        //[[],         ["/zijzllb"], ["/"],      ["/zijzllb"],["/r"], ["/"],         ["/r"],["/zijzllb/hfktg","d"],["/zijzllb/hfktg"],   ["/"],           ["/zijzllb/hfktg"]]

        //[null,        null,        ["zijzllb"],[],           null,  ["zijzllb","r"],[],     null                  ,"d",                ["zijzllb","r"],  "d"]


        // Test Case 2:
        // ["FileSystem","mkdir",      "ls",        "ls",       "mkdir", "ls",           "ls",          "addContentToFile",       "ls",   "ls",         "ls"]
        // [[],          ["/goowmfn"],["/goowmfn"],["/"],       ["/z"],  ["/"],          ["/"],         ["/goowmfn/c","shetopcy"],["/z"],["/goowmfn/c"],["/goowmfn"]]
        // [null,         null,        [],          ["goowmfn"], null,   ["goowmfn","z"],["goowmfn","z"],null,                    [],    ["c"],           ["c"]]
    }
}
