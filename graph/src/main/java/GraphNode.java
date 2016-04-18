import java.util.HashSet;
import java.util.Set;

/**
 * Representation of a graph node.
 */
public class GraphNode {
    private int data;
    private Set<GraphNode> adjNodes;

    public GraphNode(int data) {
        this.data = data;
        adjNodes = new HashSet<GraphNode>();
    }

    public void addAdjNode(GraphNode graphNode) {
        adjNodes.add(graphNode);
    }

    public int getData() {
        return data;
    }

    public Set<GraphNode> getAdjNodes() {
        return adjNodes;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null || obj.getClass() != getClass()) {
            return false;
        }
        GraphNode other = (GraphNode)obj;
        return other.getData() == getData() &&
            adjNodes.equals(other.adjNodes);
    }

    @Override
    public int hashCode() {
        return 31 * data;
    }

    @Override
    public String toString() {
        return Integer.toString(data);
    }
}
