BFS/DFS 的迭代写法拥有共通之处，除保存结果的数据集数据结构不一样（BFS 使用 Queue，DFS 使用 Stack），其它可以完全一样

```java
Queue<Node> queue = new LinkedList<>();	// Stack<Node> stack = new Stack<>();
// 当前节点是否访问标记
Set<Node> set = new HashSet<>();
List<Integer> ans = new ArrayList<>();

queue.add(node);	// stack.push(node);
set.add(node);
while (!queue.isEmpty()) {	// while (!stack.isEmpty()) {
    // 删除并返回队列头
    Node cur = queue.poll();	// Node cur = stack.pop();
    ans.add(cur.value);

    for (Node next : cur.nexts) {
        if (!set.contains(next)) {
            set.add(next);
            queue.add(next);	// stack.push(next);
        }
    }
}

return ans;
```

