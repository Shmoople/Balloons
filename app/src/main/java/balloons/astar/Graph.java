package balloons.astar;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * The Graph class represnts a graph as a collection of nodes and a mapping of edges to node IDs
 * @author Wyatt Rose
 */
public class Graph<T extends GraphNode> {
    private final Set<T> nodes;

    private final Map<String, Set<String>> connections;

    /**
     * Initializes a new Graph instance given a set of nodes and an adjacency list (as a mapping of ids to set of ids)
     * @param nodes the nodes that are apart of the graph
     * @param connections the adjacency list for the graph
     */
    public Graph(Set<T> nodes, Map<String, Set<String>> connections) {
        this.nodes = nodes;
        this.connections = connections;
    }

    /**
     * Returns a node within the graph given an ID (as a string)
     * @param id the requested node's ID
     * @return the first node instance with that ID (with a poor implementaiton, there could be more than one node with the same identifier)
     */
    public T getNode(String id) {

        // first time working with streams and its making my balls bleed
        return nodes.stream()
        .filter(node -> node.getId().equals(id)) // so many fucking lambda expressions
        .findFirst()
        .orElseThrow(() -> new IllegalArgumentException("No node found with that ID!")); // this could be very problematic but なんでもいい
    }

    /**
     * Returns a set containing all the node instances apart of this graph
     * @return a set containing all node instances
     */
    public Set<T> getNodes() {
        return nodes.stream().collect(Collectors.toSet()); // dude why did I do that just return the fucking set im so drunk i need sleep i think i have a cold
    }

    /**
     * Returns all the nodes adjacent to the specified node
     * @param node node within this graph
     * @return set contianing all nodes adjacent to the specified node
     */
    public Set<T> getConnections(T node) {
        return connections.get(node.getId()).stream()
        .map(this::getNode)
        .collect(Collectors.toSet());
    }
}