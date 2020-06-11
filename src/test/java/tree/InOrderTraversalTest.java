package tree;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class InOrderTraversalTest {
    private TreeNode root;
    private InOrderTraversal inOrderTraversal;
    private Integer[] ans;

    @Before
    public void setUp() throws Exception {
        root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);

        inOrderTraversal = new InOrderTraversal();

        ans = new Integer[]{1, 3, 2};
    }

    @Test
    public void testRecursive() {
        assertArrayEquals(ans, inOrderTraversal.recursive(root).toArray());
    }

    @Test
    public void testIterate() {
        assertArrayEquals(ans, inOrderTraversal.iterate(root).toArray());
    }

    @Test
    public void testMorris() {
        assertArrayEquals(ans, inOrderTraversal.morris(root).toArray());
    }
}