import javax.sound.midi.Soundbank;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * Utilities for a graph.
 */
public class GraphUtil {

    private void dfsHelper(GraphNode graphNode, Set<GraphNode> visited) {
        System.out.print(graphNode + " ");
        visited.add(graphNode);
        Set<GraphNode> adjNodes = graphNode.getAdjNodes();
        for(GraphNode currentAdjNode : adjNodes) {
            if(!visited.contains(currentAdjNode)) {
                dfsHelper(currentAdjNode, visited);
            }
        }
    }

    public void dfs(Graph graph) {
        if(graph == null) {
            System.out.println("Empty graph");
            return;
        }
        Set<GraphNode> nodes = graph.getNodes();
        Set<GraphNode> visited = new HashSet<GraphNode>(nodes.size());
        for(GraphNode graphNode : nodes) {
            if(!visited.contains(graphNode)) {
                dfsHelper(graphNode, visited);
            }
        }
        System.out.println();
    }

    public void bfs(Graph graph, GraphNode root) {
        Queue<GraphNode> queue = new LinkedList<GraphNode>();
        Set<GraphNode> visited = new HashSet<GraphNode>(graph.getNodes().size());
        queue.offer(root);
        visited.add(root);
        while(!queue.isEmpty()) {
            GraphNode frontNode = queue.poll();
            System.out.print(frontNode + " ");
            Set<GraphNode> adjNodes = frontNode.getAdjNodes();
            for(GraphNode adjNode : adjNodes) {
                if(!visited.contains(adjNode)) {
                    visited.add(adjNode);
                    queue.add(adjNode);
                }
            }
        }
        System.out.println();
    }

    /*
     * Does path exist between two nodes in an undirected graph?
     * Finding out by bi-directional search.
     */
    public boolean doesPathExist(GraphNode first, GraphNode second) {
        if(first == second) {
            return true;
        }

        Set<GraphNode> firstVisited = new HashSet<GraphNode>(),
            secondVisited = new HashSet<GraphNode>();
        Queue<GraphNode> firstQueue = new LinkedList<GraphNode>(),
            secondQueue = new LinkedList<GraphNode>();
        firstQueue.offer(first);firstVisited.add(first);
        secondQueue.offer(second);secondVisited.add(second);
        while(!firstQueue.isEmpty() &&
            !secondQueue.isEmpty()) {
            GraphNode currentFirst = firstQueue.poll();
            Set<GraphNode> adjNodesFirst = currentFirst.getAdjNodes();
            for(GraphNode adjNode : adjNodesFirst) {
                if(!firstVisited.contains(adjNode)) {
                    if(secondVisited.contains(adjNode)) {
                        return true;
                    }
                    firstQueue.offer(adjNode);
                    firstVisited.add(adjNode);
                }
            }

            GraphNode currentSecond = secondQueue.poll();
            Set<GraphNode> adjNodesSecond = currentSecond.getAdjNodes();
            for(GraphNode adjNode : adjNodesSecond) {
                if(!secondVisited.contains(adjNode)) {
                    if(firstVisited.contains(adjNode)) {
                        return true;
                    }
                    secondQueue.offer(adjNode);
                    secondVisited.add(adjNode);
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {
        GraphNode n1 = new GraphNode(1), n2 = new GraphNode(2), n3 = new GraphNode(3), n4 = new GraphNode(4);

        Graph graph = new Graph();
        graph.addNode(n1);graph.addNode(n2);graph.addNode(n3);graph.addNode(n4);
        n1.addAdjNode(n2);
        n2.addAdjNode(n3);
        n3.addAdjNode(n4);
        n4.addAdjNode(n1);

        GraphUtil graphUtil = new GraphUtil();
        graphUtil.dfs(graph);
        graphUtil.bfs(graph, n1);
        GraphNode n5 = new GraphNode(5);
        for(GraphNode node : n5.getAdjNodes()) {
            System.out.println(node);
        }

        System.out.println(graphUtil.doesPathExist(n1, n3));
    }
}
