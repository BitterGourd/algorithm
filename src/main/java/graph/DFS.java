package graph;

import java.util.*;

/**
 * 深度优先搜索
 * 实现：使用栈保存未被检测的节点，节点按深度优先的次序被访问并一次压入栈中，并以相反的次序出栈进行新的检测
 * -- 类似树的先序遍历
 */
class DFS {

    List<Integer> dfs(Node node) {
        Stack<Node> stack = new Stack<>();
        // 当前节点是否访问标记
        Set<Node> set = new HashSet<>();
        List<Integer> ans = new ArrayList<>();

        stack.push(node);
        set.add(node);
        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            ans.add(cur.value);

            ArrayList<Node> curNexts = cur.nexts;
            if (curNexts != null) {
                for (Node next : curNexts) {
                    if (!set.contains(next)) {
                        stack.push(next);
                        set.add(next);
                    }
                }
            }
        }

        return ans;
    }
}
