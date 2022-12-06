package balloons.astar;

/**
 * Interface used for creating implementaitons for GraphNodes, essentially requires that a class has a qualified identifier other than the one java provides
 * @author Wyatt Rose
 */
public interface GraphNode {

    /**
     * Abstract method for returning IDs within a graph, it is up to the implementor to decide how to handle storing identifiers
     * @return the ID of this node
     */
    String getId();
}