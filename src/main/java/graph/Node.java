package graph;

import java.util.ArrayList;

class Node {
    int value;
    ArrayList<Node> nexts;

    Node(int value) {
        this.value = value;
        nexts = new ArrayList<>();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Node) {
            return ((Node) obj).value == value;
        } else {
            return false;
        }
    }
}
