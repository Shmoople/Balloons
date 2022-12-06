package balloons.playingfield;
import balloons.astar.*;

public class EuclideanGraphPointScorer implements Scorer<GraphPoint> {
    
    @Override
    public double computeCost(GraphPoint from, GraphPoint to) {
        return Math.sqrt(Math.pow(from.getxPosition()-to.getxPosition(),2) +
        Math.pow(from.getyPosition()-to.getyPosition(),2));
    }
}