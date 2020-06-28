package graph;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertArrayEquals;

public class DFSTest {
    private Integer[] ans;
    private DFS dfs;
    private Node node;

    @Before
    public void setUp() throws Exception {
        dfs = new DFS();

        initGraph();

        ans = new Integer[]{10, 40, 70, 60, 50, 30, 25, 15, 20};
    }

    @Test
    public void testDfs() {
        System.out.println(dfs.dfs(node));
        assertArrayEquals(ans, dfs.dfs(node).toArray());
    }

    private void initGraph() {
        node = new Node(10);
        ArrayList<Node> nexts = new ArrayList<>();
        nexts.add(new Node(20));

        Node node1 = new Node(30);
        ArrayList<Node> nexts1 = new ArrayList<>();
        nexts1.add(new Node(15));
        nexts1.add(new Node(25));
        node1.nexts = nexts1;

        nexts.add(node1);

        Node node2 = new Node(40);
        ArrayList<Node> nexts2 = new ArrayList<>();
        nexts2.add(new Node(50));
        nexts2.add(new Node(60));
        nexts2.add(new Node(70));
        node2.nexts = nexts2;

        nexts.add(node2);

        node.nexts = nexts;
    }
}