package tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 前序遍历二叉树
 * 顺序：根结点 ---> 左子树 ---> 右子树
 */
class PreOrderTraversal {

    /** 递归 */
    List<Integer> recursive(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root != null) {
            ans.add(root.val);
            ans.addAll(recursive(root.left));
            ans.addAll(recursive(root.right));
        }

        return ans;
    }

    /** 迭代 */
    List<Integer> iterate(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();

        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                // 1.遍历根节点
                ans.add(cur.val);   // Add before going to children
                // 3.遍历右子树
                // 先把当前根节点右子树入栈，等当前根节点左子树遍历完毕再遍历右子树
                stack.push(cur.right);
                // 2.遍历左子树，下一轮循环就遍历左子树
                cur = cur.left;
            } else {
                // 取出右子树
                cur = stack.pop();
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
                    ans.add(cur.val);
                    cur = cur.left;
                    continue;
                } else {    // mostRight 的 rightChild 指针指向 cur, 让其指向空, cur 向右移动
                    mostRight.right = null;
                }

            } else {    // 当前节点没有左孩子, 第1次和第2次访问当前节点重合
                ans.add(cur.val);
            }

            // 左孩子为空, 直接向右孩子移动
            cur = cur.right;
        }

        return ans;
    }
}
