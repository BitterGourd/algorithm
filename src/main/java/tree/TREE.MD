二叉树的递归写法只要理解了遍历顺序能很快给出，迭代写法初时却有点费脑，但也不是无规律可循。下面给出了前、中、后序遍历迭代模板，框架大体一致，不同在于添加到结果集的时机（后序遍历有点特殊）

#### 前中序模板

```java
List<Integer> iterate(TreeNode root) {
    List<Integer> ans = new ArrayList<>();
    Stack<TreeNode> stack = new Stack<>();

    TreeNode cur = root;
    while (cur != null || !stack.isEmpty()) {
        if (cur != null) {
            stack.push(cur);
            // ans.add(cur.val); => 前序
            cur = cur.left;
        } else {
            TreeNode node = stack.pop();
            // ans.add(node.val); => 中序
            cur = node.right;
        }
    }

    return ans;
}
```

#### 后序遍历与前序相反

```java
List<Integer> iterate(TreeNode root) {
    LinkedList<Integer> ans = new LinkedList<>();
    Stack<TreeNode> stack = new Stack<>();

    TreeNode cur = root;
    while (cur != null || !stack.isEmpty()) {
        if (cur != null) {
            stack.push(cur);
            ans.addFirst(cur.val);  // 添加到结果集头部
            cur = cur.right;        // 与前序(cur = cur.left)相反
        } else {
            TreeNode node = stack.pop();
            cur = node.left;        // 与前序(cur = node.right)相反
        }
    }

    return ans;
}
```

