package balloons.astar;

/**
 * RouteNode represents a node within a route returned by the RouteFinder's "findRoute(GraphNode from, GraphNode to)" method
 * @author Wyatt Rose
 */
public class RouteNode<T extends GraphNode> implements Comparable<RouteNode<T>> {

    /* Fields */
    private final T current;
    private T previous;
    private double routeScore;
    private double estimatedScore;

    /**
     * Constructs a RouteNode when there isn't a previous node 
     * @param current the "current" node being examined
     */
    public RouteNode(T current) {
        /* if there isn't any previous node, just pass a perfect score */
        this(current, null, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
    }

    /**
     * Constructs a RouteNode given a previous node and current node
     * @param current the current node
     * @param previous the previous node
     * @param routeScore the score for this edge node pair passed via findRoute()
     * @param estimatedScore the estimated score passed via findRoute()
     */
    RouteNode(T current, T previous, double routeScore, double estimatedScore) {
        this.current = current;
        this.previous = previous;
        this. routeScore = routeScore;
        this.estimatedScore = estimatedScore;
    }

    /**
     * Returns the "current" node
     * @return the current node within this node pair
     */
    public T getCurrent() { return current; }

    /**
     * Returns the "previous" node
     * @return the previous node within this node pair
     */
    public T getPrevious() { return previous; }

    /**
     * Returns the score for this node pair
     * @return routeScore field
     */
    public double getRouteScore() { return routeScore; }

    /**
     * Returns the estimated score for this node pair
     * @return estimatedScore field
     */
    public double getEstimatedScore() { return estimatedScore; }

    /**
     * Sets the previous node for this pairing
     * @param previous new value for previous field
     */
    public void setPrevious(T previous) { this.previous = previous; }

    /**
     * Sets the routeScore for this pairing
     * @param routeScore new value for routeScore field
     */
    public void setRouteScore(double routeScore) { this.routeScore = routeScore; }

    /**
     * Sets the estimatedScore for this field
     * @param estimatedScore new value for estimatedScore field
     */
    public void setEstimatedScore(double estimatedScore) { this.estimatedScore = estimatedScore; }

    /**
     * Compares this part of the path to another option by comparing estimatedScores
     * @param other another RouteNode instance (another choice the algorithm could take)
     * @return value to determine if this path is better or worse than another path (1 better) (0 equal) (-1 worse)
     */
    @Override // オーバーライド
    public int compareTo(RouteNode<T> other) {
        if(this.estimatedScore > other.estimatedScore) {
            return 1;
        } else if(this.estimatedScore < other.estimatedScore) {
            return -1;
        } else {
            return 0; // I hate this structure なんでもいい
        }
    }

    /**
     * @return String represenation of this RouteNode instance
     */
    @Override
    public String toString() {
        // I'll define this later
        return "無い!"; // I still haven't defined this 12/6/22
    }
}