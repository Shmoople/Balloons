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

    public void setxPosition(double xPosition) { this.xPosition = xPosition; }

    public void setyPosition(double yPosition) { this.yPosition = yPosition; }

    public double getxPosition() { return xPosition; }

    public double getyPosition() { return yPosition; }

    public double getSpeed() { return speed; }

    public void setSpeed(double speed) { this.speed = speed; }

    public int getHitPoints() { return hitPoints; }

    public void setHitPoints(int hitPoints) { this.hitPoints = hitPoints; }

    public String toString() {
        return "{\n\t"+this.getClass()+
                "\n\tspeed: "+speed+
                "\n\thitPoints: "+hitPoints+
                "\n\tposition: ("+xPosition+","+yPosition+")\n}";
    }
}
