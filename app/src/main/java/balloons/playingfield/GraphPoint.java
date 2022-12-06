package balloons.playingfield;
import balloons.astar.*;

public class GraphPoint implements GraphNode {

    private String id;
    private double xPosition;
    private double yPosition;

    public GraphPoint(String id, double xPosition, double yPosition) {
        this.id = id;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }

    public GraphPoint(String id) { this(id, 0, 0); }

    public double getxPosition() { return this.xPosition; }

    public double getyPosition() { return yPosition; }

    public void setxPosition(double xPosition) { this.xPosition = xPosition; }

    public void setyPosition(double yPosition) { this.yPosition = yPosition; }

    public void setPosition(double xPosition, double yPosition) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }

    @Override
    public String getId() {
        return id;
    }
    
}