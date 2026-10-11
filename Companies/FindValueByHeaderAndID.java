package Companies;

import java.util.*;
import java.util.stream.Collectors;
/**
 * 有一个文件，第一行是headers，其他的事真正的数据。其中ID都是unqiue的，header也都是unqiue的，数据也都是nonnull的。
 * 完成get（id，header）返回相应的纸。比如我如果给11号，header为age，应该返回41.
 * 要求时间复杂度是O（1）
 *
 * 我一开始的想法是header用set确认存不存在，然后其他的根据ID做HashMap，key=ID，value=所在行的index。
 * 但后来寻找header所在的position的时候时间复杂度增加了，因此考官问我怎么优化
 *
 * 其实Header也用HashMap直接保存position为value就行了。于是又重新改的builder
 *
 * -------------------------------=====================================
 * 原题目
 *
 * Given a CSV file with N rows and M columns following these rules:
 *
 *
 *
 *
 * First row contains headers
 * First column is named “ID” and the values are unique
 * All rows contain values for all columns
 * All values are strings, even if they appear as numbers
 * Create a data structure allowing querying the file by ID and header name. Load the data to the data structure and create a function get(String id, String header) which should operate with O(1) complexity. Data loading should be generic and compatible with any number of columns.
 *
 * Assume that the CSV is provided in a form of Array[]
 *
 * CSV file example 1:
 *
 * ID	Name	Age
 * 11	Ran	41
 * 2	Noy	25
 * 13	Lilach	38
 * 45	Amit	6
 * array form: ["ID,Name,Age","11,Ran,41","2,Noy,25","13,Lilach,38","45,Amit,6"]
 *
 * CSV file example 2:
 *
 * ID	Brand	Model	Year
 * A	Toyota	Corolla	2022
 * B	Toyota	Yaris	2000
 * C	Mazda	3	2013
 * D	Mazda	6	2017
 * array form: ["ID,Brand,Model,Year", "A,Toyota,Corolla,2022", "B,Toyota,Yaris,2000" ,"C,Mazda,3,2013", "D,Mazda,6,2017"]
 *
 */
public class FindValueByHeaderAndID {
    public static void main(String[] args) {
        List<String> csvData = Arrays.asList(
                "ID,Name,Age",
                "11,Ran,41",
                "2,Noy,25",
                "13,Lilach,38",
                "45,Amit,6"
        );

        Dataset ds = new Dataset(csvData);

        System.out.println(ds.get("11", "Name"));
        System.out.println(ds.get("11", "Age"));
        // System.out.println(ds.get("sdd", "Name"));
        System.out.println(ds.get("11", ""));
    }

    static class Dataset {
        HashMap<String, Integer> headers; //应该叫headersAndPosition
        HashMap<String, Integer> hashData;
        ArrayList<List<String>> wholeData;

        public Dataset(List<String> dataFile) {
            this.headers = new HashMap<>();
            this.wholeData = new ArrayList<>();
            this.hashData = new HashMap<>();

            // Build header and index
            String[] headerArray = dataFile.get(0).split(",");
            for (int i = 1; i < headerArray.length; i++) {
                headers.put(headerArray[i], i); // header name and index
            }

            // Build data set content
            for (int i = 0; i < dataFile.size(); i ++) {
                wholeData.add(Arrays.stream(dataFile.get(i).split("\\s*,\\s*"))
                        .collect(Collectors.toList()));
            }

            // Build the HashMap for search
            for (int i = 1; i < dataFile.size(); i ++) {
                String[] rowData = dataFile.get(i).split(",");
                hashData.put(rowData[0], i); // ID , index of the dataFile array
                // TODO: handle duplicte
            }
        }
        public String get(String id, String header) {
            if(!headers.containsKey(header)) {
                throw new IllegalArgumentException("Invalid header");
            }
            if (!hashData.containsKey(id)) {
                throw new IllegalArgumentException("Invalid id");
            }

            int index = hashData.get(id); //应该叫IdIdx比较好听
            return wholeData.get(index).get(headers.get(header));
        }

    }

    class DatasetAIAnswer {
        // 封装为 private final，不使用 static
        private final Map<String, Integer> headerIndexMap;
        private final Map<String, Integer> idRowIndexMap;
        private final List<String[]> dataRows;

        public DatasetAIAnswer(List<String> dataFile) {
            if (dataFile == null || dataFile.isEmpty()) {
                throw new IllegalArgumentException("Data file cannot be null or empty");
            }

            this.headerIndexMap = new HashMap<>();
            this.idRowIndexMap = new HashMap<>();
            this.dataRows = new ArrayList<>(dataFile.size() - 1);

            // 1. 解析表头 (包含 ID 列，避免遗漏)
            String[] headers = dataFile.get(0).split("\\s*,\\s*");
            for (int i = 0; i < headers.length; i++) {
                headerIndexMap.put(headers[i], i);
            }

            // 2. 单次遍历解析真实数据并建立 ID 索引
            for (int rowIdx = 1; rowIdx < dataFile.size(); rowIdx++) {
                String[] rowData = dataFile.get(rowIdx).split("\\s*,\\s*");

                String id = rowData[0];
                if (idRowIndexMap.containsKey(id)) {
                    throw new IllegalArgumentException("Duplicate ID found: " + id);
                }

                int currentStoredIndex = dataRows.size();
                dataRows.add(rowData);
                idRowIndexMap.put(id, currentStoredIndex);
            }
        }

        public String get(String id, String header) {
            Integer colIdx = headerIndexMap.get(header);
            if (colIdx == null) {
                throw new IllegalArgumentException("Invalid header: " + header);
            }

            Integer rowIdx = idRowIndexMap.get(id);
            if (rowIdx == null) {
                throw new IllegalArgumentException("Invalid id: " + id);
            }

            String[] row = dataRows.get(rowIdx);
            if (colIdx >= row.length) {
                return ""; // 或根据需求抛出异常，防止某行缺少列导致数组越界
            }

            return row[colIdx];
        }
    }

}
