import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * Graph and its utilities.
 */
public class Graph {
    private Set<GraphNode> nodes;

    public static class GraphNode {
        int label;
        public Set<GraphNode> adjList;
        public GraphNode(int label) {
            this.label = label;
            adjList = new HashSet<GraphNode>();
        }
        public void addEdge(GraphNode other) {
            adjList.add(other);
        }

        @Override
        public boolean equals(Object obj) {
            if(obj == null || obj.getClass() != getClass()) {
                return false;
            }
            return label == ((GraphNode)obj).label;
        }

        @Override
        public int hashCode() {
            return label;
        }

        @Override
        public String toString() {
            StringBuilder output = new StringBuilder(" Data : " + label + " Edges : ");
            for(GraphNode graphNode : adjList) {
                output.append(graphNode.label + " ");
            }
            return output.toString();
        }
    }

    public void addNode(GraphNode graphNode) {
        nodes.add(graphNode);
    }

    public GraphNode dfsCloneHelper(Map<GraphNode, GraphNode> cloneMap, GraphNode current) {
        GraphNode currentClone = new GraphNode(current.label + 100);
        cloneMap.put(current, currentClone);
        for(GraphNode edge : current.adjList) {
            if(!cloneMap.containsKey(edge)) {
                dfsCloneHelper(cloneMap, edge);
            }
            currentClone.addEdge(cloneMap.get(edge));
        }
        return currentClone;
    }

    public Graph clone() {
        Map<GraphNode, GraphNode> cloneMap = new HashMap<GraphNode, GraphNode>();
        Graph cloneGraph = new Graph();
        for(GraphNode node : nodes) {
            if(!cloneMap.containsKey(node)) {
                dfsCloneHelper(cloneMap, node);
            }
            cloneGraph.addNode(cloneMap.get(node));
        }
        return cloneGraph;
    }

    private void dfsHelper(GraphNode graphNode, Set<GraphNode> visited) {
        System.out.println(graphNode);
        visited.add(graphNode);

        for(GraphNode adjNode : graphNode.adjList) {
            if(!visited.contains(adjNode)) {
                dfsHelper(adjNode, visited);
            }
        }
    }

    public void dfs() {
        System.out.println("DFS:");
        Set<GraphNode> visited = new HashSet<GraphNode>(nodes.size());
        for(GraphNode graphNode : nodes) {
            if(!visited.contains(graphNode)) {
                dfsHelper(graphNode, visited);
            }
        }
    }
    /*
     * Classic BFS
     */
    public void bfs(GraphNode start) {
        Queue<GraphNode> bfsQueue = new ArrayDeque<GraphNode>(nodes.size());
        Set<GraphNode> visitedNodes = new HashSet<GraphNode>(nodes.size());
        System.out.println("BFS:");
        bfsQueue.offer(start);
        visitedNodes.add(start);
        while(!bfsQueue.isEmpty()) {
            GraphNode nextNode = bfsQueue.poll();
            System.out.println(nextNode.toString());
            Iterator<GraphNode> adjListIterator = nextNode.adjList.iterator();
            while(adjListIterator.hasNext()) {
                GraphNode nextAdjNode = adjListIterator.next();
                if(!visitedNodes.contains(nextAdjNode)) {
                    bfsQueue.offer(nextAdjNode);
                    visitedNodes.add(nextAdjNode);
                }
            }
        }
    }

    public Graph() {
        nodes = new HashSet<GraphNode>();
    }

    @Override
    public String toString() {
        return nodes.toString();
    }

    public static void main(String[] args) {
        GraphNode n1 = new GraphNode(1); GraphNode n2 = new GraphNode(2);
        GraphNode n3 = new GraphNode(3); GraphNode n4 = new GraphNode(4);
        n1.addEdge(n3);n1.addEdge(n4);
        n2.addEdge(n3);n2.addEdge(n1);
        n4.addEdge(n2);

        Graph graph = new Graph();
        graph.addNode(n1);graph.addNode(n2);
        graph.addNode(n3);graph.addNode(n4);

        GraphNode n5 = new GraphNode(5);
        graph.addNode(n5);
        n1.addEdge(n5);
        System.out.println(graph);
        System.out.println(graph.clone());
        graph.bfs(n1);
        graph.dfs();
    }
}
