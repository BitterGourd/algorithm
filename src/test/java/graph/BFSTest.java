package graph;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertArrayEquals;

public class BFSTest {
    private Integer[] ans;
    private BFS bfs;
    private Node node;


    @Before
    public void setUp() throws Exception {
        bfs = new BFS();

        initGraph();

        ans = new Integer[]{10, 20, 30, 40, 15, 25, 50, 60, 70};
    }

    @Test
    public void testBfs() {
        // System.out.println(bfs.bfs(node));
        assertArrayEquals(ans, bfs.bfs(node).toArray());
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