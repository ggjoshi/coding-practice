import java.util.HashSet;
import java.util.Set;

/**
 * Class to represent a graph.
 */
public class Graph {
    Set<GraphNode> nodes;
    public Graph() {
        nodes = new HashSet<GraphNode>();
    }

    void addNode(GraphNode graphNode) {
        nodes.add(graphNode);
    }

    public Set<GraphNode> getNodes() {
        return nodes;
    }
}
