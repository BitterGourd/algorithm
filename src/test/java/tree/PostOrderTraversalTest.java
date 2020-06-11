package tree;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class PostOrderTraversalTest {
    private TreeNode root;
    private PostOrderTraversal postOrderTraversal;
    private Integer[] ans;

    @Before
    public void setUp() throws Exception {
        root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);

        postOrderTraversal = new PostOrderTraversal();

        ans = new Integer[]{3, 2, 1};
    }

    @Test
    public void testRecursive() {
        assertArrayEquals(ans, postOrderTraversal.recursive(root).toArray());
    }

    @Test
    public void testIterate() {
        assertArrayEquals(ans, postOrderTraversal.iterate(root).toArray());
    }

    @Test
    public void testMorris() {
        assertArrayEquals(ans, postOrderTraversal.morris(root).toArray());
    }
}