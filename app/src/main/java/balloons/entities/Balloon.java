package balloons.entities;

public class Balloon {
    
    private double xPosition; // rather than using a Point2D class, instead use fields for mutable operations!
    private double yPosition; // rather than using a Point2D class, instead use fields for mutable operations!
    private double speed; // speed of the balloon
    private int hitPoints; // amount of hitpoints the balloon has

    public Balloon(double speed, int hitPoints, double xPosition, double yPosition) {
        this.speed = speed;
        this.hitPoints = hitPoints;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }

    public Balloon(double speed, int hitPoints) {
        this(speed, hitPoints, 0, 0);
    }

    public String toString() {
        return "{\n\t"+this.getClass()+
                "\n\tspeed: "+speed+
                "\n\thitPoints: "+hitPoints+
                "\n\tposition: ("+xPosition+","+yPosition+")\n}";
    }
}
