package tree;

import org.junit.Before;
import org.junit.Test;
import tree.PreOrderTraversal;
import tree.TreeNode;

import static org.junit.Assert.assertArrayEquals;

public class PreOrderTraversalTest {
    private TreeNode root;
    private PreOrderTraversal preOrderTraversal;
    private Integer[] ans;

    @Before
    public void setUp() throws Exception {
        root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);

        preOrderTraversal = new PreOrderTraversal();

        ans = new Integer[]{1, 2, 3};
    }

    @Test
    public void testRecursive() {
        assertArrayEquals(ans, preOrderTraversal.recursive(root).toArray());
    }

    @Test
    public void testIterate() {
        assertArrayEquals(ans, preOrderTraversal.iterate(root).toArray());
    }

    @Test
    public void testMorris() {
        assertArrayEquals(ans, preOrderTraversal.morris(root).toArray());
    }
}
