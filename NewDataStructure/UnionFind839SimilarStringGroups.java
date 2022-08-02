package NewDataStructure;

/**
 * 839. Similar String Groups
 * Two strings X and Y are similar if we can swap two letters (in different positions) of X, so that it equals Y.
 *   Also two strings X and Y are similar if they are equal.
 *
 * For example, "tars" and "rats" are similar (swapping at positions 0 and 2), and "rats" and "arts" are similar,
 *   but "star" is not similar to "tars", "rats", or "arts".
 *
 * Together, these form two connected groups by similarity: {"tars", "rats", "arts"} and {"star"}.
 *   Notice that "tars" and "arts" are in the same group even though they are not similar.
 *   Formally, each group is such that a word is in the group if and only if it is similar to at least one other word in the group.
 *
 * We are given a list strs of strings where every string in strs is an anagram of every other string in strs.
 *   How many groups are there?
 *
 *
 *
 * Example 1:
 *
 * Input: strs = ["tars","rats","arts","star"]
 * Output: 2
 *
 * Example 2:
 *
 * Input: strs = ["omv","ovm"]
 * Output: 1
 *
 * Constraints:
 *
 * 1 <= strs.length <= 300
 * 1 <= strs[i].length <= 300
 * strs[i] consists of lowercase letters only.
 * All words in strs have the same length and are anagrams of each other.
 */
public class UnionFind839SimilarStringGroups {
    /**
     * Crib answer https://leetcode.com/problems/similar-string-groups/discuss/132374/Short-C%2B%2B-solution-at-220ms-using-disjoint-set
     */
    class DisjointSet {
        public int numSimilarGroups(String[] strs) {
            UnionFind uf = new UnionFind(strs.length);
            for (int i = 0; i < strs.length - 1; ++i) {
                for (int j = i + 1; j < strs.length; ++j) {
                    if (similar(strs[i], strs[j])) {
                        uf.union(i, j);
                    }
                }

            }
            return uf.count();
        }

        private boolean similar(String a, String b) {
            if (a.equals(b)) {
                return true;
            }
            if (a.length() != b.length()) {
                return false;
            }
            int diffCount = 0;
            for (int i = 0; i < a.length(); ++i) {
                if (a.charAt(i) != b.charAt(i)) {
                    diffCount ++;
                    if (diffCount > 2) {
                        return false;
                    }
                }
            }
            if (diffCount == 2) {
                return true;
            }
            return false;
        }

        class UnionFind {
            int[] parent, rank;
            int N;
            UnionFind (int num) {
                this.N = num;
                parent = new int[N];
                rank = new int[N];
                for (int i = 0; i < N; ++i) {
                    parent[i] = i;
                }
            }
            public int find(int idx) {
                if (parent[idx] != idx) {
                    return find(parent[idx]);
                }
                return parent[idx];
            }
            public void union (int a, int b) {
                int pA = find(a);
                int pB = find(b);
                if (pA == pB) {
                    return;
                }
                if (rank[pA] > rank[pB]) {
                    parent[pB] = pA;
                } else if (rank[pA] > rank[pB]) {
                    parent[pA] = pB;
                } else {
                    parent[pA] = pB;
                    rank[pB] ++;
                }
                --N;
            }
            public int count() {
                return N;
            }
        }
    }
}
