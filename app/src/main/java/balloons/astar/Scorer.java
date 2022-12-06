package balloons.astar;

/**
 * Interface used for creating Scorer implementations
 * @author Wyatt Rose
 */
public interface Scorer<T extends GraphNode> {
    
    /**
     * Computes the cost of traveling from one node to another
     * @param from The starting node
     * @param to The ending node
     * @return the "cost" of traveling from node 'from' to node 'to'
     */
    double computeCost(T from, T to); // since this interface only declares one abstract method, it is considered a functional interface
}