package Tree;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * At DoorDash, menus are updated daily even hourly to keep them up-to-date. Each menu can be regarded as a tree. When the merchant sends us the latest menu, can we calculate
 how many nodes have changed/added/deleted?

 Assume each Node structure is as below:

 class Node {
 String key;
 int value;
 List children;
 }

 Assume there are no duplicate nodes with the same key.

 Output: Return the number of changed nodes in the tree.

 Existing tree
 a(1)
    /
   b(2)      c(3)
 /     \
 d(4)    e(5)      f(6)

 New tree
 a(1)

 c(3)

 f(66)

 a(1) a is the key, 1 is the value
 For example, there are a total of 4 changed nodes. Node b, Node d, Node e are automatically set to inactive. The value of Node f changed as well.

 Existing tree
     a(1)
  /         \
 b(2‍‍‌‌‌‍‍‌‌‍‍‍‌‍‍‌‌‍)      c(3)
 /   \           \
 d(4) e(5)      g(7)

 New tree
           a(1)
     /             \
     b(2)         h(8)
 /    |   \           \
 e(5) d(4) f(6)       g(7)

 There are a total of 5 changed nodes. Node f is a newly-added node. c(3) and old g(7) are deactivated and h(8) and g(7) are newly added nodes

 Old tree:
                   a(1)
                  /      \
               b(2‍‍‌‌‌‍‍‌‌‍‍‍‌‍‍‌‌‍)       c(3)
              /     \        \
          d(4)      e(5)     f(6)

New tree:
                   a(1)
                  /      \
               b(2‍‍‌‌‌‍‍‌‌‍‍‍‌‍‍‌‌‍)      c(2)
                        /     \
                       g(5)    f(66)
                              /     \
                             k(2)   l(7)

Output:
Value changed for node: C
Extra nodes in the new tree: G
Value changed for node: F
Extra nodes in the new tree: K
Extra nodes in the new tree: L
Missing nodes from old tree: D
Missing nodes from old tree: E

 */
public class Dasher_NumberOfChangedNode {
    static class MenuNode {
        String key;
        int value;
        List<MenuNode> children;
        MenuNode(String k, int v) {
            this.key = k;
            this.value = v;
        }
    }

    static class HashMap_Recursive {
        int count;
        HashMap<String, MenuNode> hm;
        StringBuilder updates;
        static final String missingNode = "Missing nodes from old tree: ";
        static final String valueChanged = "Value changed for node: ";
        static final String newNode = "Extra nodes in the new tree: ";

        public int changedNodes(MenuNode root1, MenuNode root2) {
            hm = new HashMap<>();
            updates = new StringBuilder();

            pushAllNodesToHashMap(root1);
            compareTrees(root2);
            if (!hm.isEmpty()) {
                count += hm.size();
                updates.append("\n");
                updates.append(missingNode).append(hm.keySet().toString());
            }
            System.out.println(updates.toString());
            return count;

        }
        private void compareTrees(MenuNode root) {
            String currKey = root.key;
            if (hm.containsKey(currKey)) {
                MenuNode original = hm.get(currKey);
                if (original.value != root.value) {
                    updates.append("\n").append(valueChanged).append(currKey);
                    count ++;
                }
                hm.remove(currKey);
            } else {
                updates.append("\n");
                updates.append(newNode);
                recursivelyGetAllNodes(root);
            }
            if (root.children == null) {
                return;
            }
            for (MenuNode mn : root.children) {
                compareTrees(mn);
            }

        }

        private void recursivelyGetAllNodes(MenuNode root) {
            updates.append(root.key);
            if (root != null) {
                count ++;
            }
            if (root.children == null) {
                return;
            }
            for (MenuNode mn : root.children) {
                recursivelyGetAllNodes(mn);
            }
        }


        private void pushAllNodesToHashMap(MenuNode root) {
            if (root == null) {
                return;
            }
            hm.put(root.key, root);
            if (root.children == null) {
                return;
            }
            for (MenuNode mn : root.children) {
                pushAllNodesToHashMap(mn);
            }
        }
    }

    public static void main(String[] args) {
        MenuNode a = new MenuNode("a", 1);
        MenuNode b = new MenuNode("b", 2);
        MenuNode c = new MenuNode("c", 3);
        MenuNode d = new MenuNode("d", 4);
        MenuNode e = new MenuNode("e", 5);
        MenuNode f = new MenuNode("f", 6);
        a.children = new LinkedList<>();
        a.children.add(b);
        a.children.add(c);
        b.children = new LinkedList<>();
        b.children.add(d);
        b.children.add(e);
        c.children = new LinkedList<>();
        c.children.add(f);


        MenuNode a2 = new MenuNode("a", 1);
        MenuNode b2 = new MenuNode("b", 2);
        MenuNode c2 = new MenuNode("c", 2);
        MenuNode f2 = new MenuNode("f", 66);
        MenuNode g2 = new MenuNode("g", 5);
        MenuNode k2 = new MenuNode("k", 2);
        MenuNode l2 = new MenuNode("l", 7);
        a2.children = new LinkedList<>();
        a2.children.add(b2);
        a2.children.add(c2);
        b2.children = new LinkedList<>();
        b2.children.add(g2);
        b2.children.add(f2);
        f2.children = new LinkedList<>();
        f2.children.add(k2);
        f2.children.add(l2);

        MenuNode a3 = new MenuNode("a", 1);
        MenuNode b3 = new MenuNode("b", 2);
        MenuNode c3 = new MenuNode("c", 3);
        MenuNode d3 = new MenuNode("d", 4);
        MenuNode e3 = new MenuNode("e", 5);
        MenuNode f3 = new MenuNode("f", 6);
        a3.children = new LinkedList<>();
        a3.children.add(b3);
        a3.children.add(c3);
        b3.children = new LinkedList<>();
        b3.children.add(d3);
        b3.children.add(e3);
        c3.children = new LinkedList<>();
        c3.children.add(f3);

        MenuNode a4 = new MenuNode("a", 1);
        MenuNode c4 = new MenuNode("c", 3);
        MenuNode f4 = new MenuNode("f", 66);
        a4.children = new LinkedList<>();
        a4.children.add(c4);
        c4.children = new LinkedList<>();
        c4.children.add(f4);



        HashMap_Recursive solution = new HashMap_Recursive();
//        int res = solution.changedNodes(a, a2);
        int res = solution.changedNodes(a3, a4);
        System.out.println(res);

    }
}
