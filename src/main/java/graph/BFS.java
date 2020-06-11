package graph;

import java.util.*;

/**
 * 广度优先搜索
 * 实现：使用队列保存未被检测的节点，节点按宽度优先的次序被访问和近乎队列
 * -- 类似树的按层次遍历
 */
class BFS {
    List<Integer> bfs(Node node) {
        Queue<Node> queue = new LinkedList<>();
        // 当前节点是否访问标记
        Set<Node> set = new HashSet<>();
        List<Integer> ans = new ArrayList<>();

        queue.add(node);
        set.add(node);
        while (!queue.isEmpty()) {
            // 删除并返回队列头
            Node cur = queue.poll();
            ans.add(cur.value);

            for (Node next : cur.nexts) {
                if (!set.contains(next)) {
                    set.add(next);
                    queue.add(next);
                }
            }
        }

        return ans;
    }
}
