package tree;

import java.util.*;

/**
 * 后序遍历二叉树
 * 左子树 ---> 右子树 ---> 根结点
 * 第三次访问节点时放入结果集
 */
class PostOrderTraversal {
    /** 递归 */
    List< Integer > recursive(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root != null) {
            ans.addAll(recursive(root.left));
            ans.addAll(recursive(root.right));
            ans.add(root.val);
        }
        return ans;
    }

    /** 迭代 */
    List<Integer> iterate(TreeNode root) {
        LinkedList<Integer> ans = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();

        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                stack.push(cur);
                ans.addFirst(cur.val);   // Reverse the process of preorder
                cur = cur.right;         // Reverse the process of preorder
            } else {
                TreeNode node = stack.pop();
                cur = node.left;         // Reverse the process of preorder
            }
        }

        return ans;
    }

    /** Morris */
    List<Integer> morris(TreeNode root) {
        List<Integer> ans = new ArrayList<>();

        // 记录当前节点
        TreeNode cur = root;
        // 记录 cur 左子树最右的节点
        TreeNode mostRight;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                // 不断找当前节点左子树最右的节点 - mostRight
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }

                // 如果 mostRight 的 rightChild 指针指向空, 让其指向 cur, cur 向左移动
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {    // mostRight 的 rightChild 指针指向 cur, 让其指向空, cur 向右移动
                    mostRight.right = null;
                    printEdge(cur.left, ans);
                }
            }
            // 左孩子为空, 直接向右孩子移动
            cur = cur.right;
        }

        printEdge(root, ans);
        return ans;
    }

    private void printEdge(TreeNode head, List<Integer> ans) {
        TreeNode tail = reverseEdge(head);
        TreeNode cur = tail;
        while (cur != null) {
            ans.add(cur.val);
            cur = cur.right;
        }

        reverseEdge(tail);
    }

    private TreeNode reverseEdge(TreeNode from) {
        TreeNode pre = null;
        TreeNode next;
        while (from != null) {
            next = from.right;
            from.right = pre;
            pre = from;
            from = next;
        }

        return pre;
    }
}
