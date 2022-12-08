package balloons.entities;

public class Round {
    
    public static final Balloon[] S1R1 = { 
        createRedBalloon(),
        createRedBalloon(),
        createRedBalloon(),
        createRedBalloon(),
        createRedBalloon(),
        createRedBalloon(),
        createRedBalloon(),
        createRedBalloon(),
        createRedBalloon(),
        createRedBalloon()
    };

    public static final Balloon[] S1R2 = {
        createRedBalloon(),
        createRedBalloon(),
        createRedBalloon(),
        createRedBalloon(),
        createRedBalloon(),
        createOrangeBalloon(),
        createOrangeBalloon(),
        createOrangeBalloon(),
        createOrangeBalloon(),
        createOrangeBalloon()
    };

    public static final Balloon[] S1R3 = {
        createOrangeBalloon(),
        createOrangeBalloon(),
        createOrangeBalloon(),
        createYellowBalloon(),
        createYellowBalloon(),
        createYellowBalloon(),
        createGreenBalloon(),
        createGreenBalloon(),
        createGreenBalloon()
    };

    public static final Balloon[] S1R4 = {
        createYellowBalloon(),
        createYellowBalloon(),
        createYellowBalloon(),
        createYellowBalloon(),
        createGreenBalloon(),
        createGreenBalloon(),
        createGreenBalloon(),
        createBlueBalloon(),
        createBlueBalloon()
    };

    public static final Balloon[] S1R5 = {
        createRedBalloon(),
        createRedBalloon(),
        createOrangeBalloon(),
        createOrangeBalloon(),
        createYellowBalloon(),
        createYellowBalloon(),
        createGreenBalloon(),
        createGreenBalloon(),
        createBlueBalloon(),
        createBlueBalloon(),
        createPurpleBalloon(),
        createPurpleBalloon()
    };

    public static Balloon createRedBalloon() { return new Balloon(0.5,1,0,0,1); }
    public static Balloon createOrangeBalloon() { return new Balloon(0.5,2,0,0,2); }
    public static Balloon createYellowBalloon() { return new Balloon(0.5,3,0,0,3); }
    public static Balloon createGreenBalloon() { return new Balloon(0.5,4,0,0,4); }
    public static Balloon createBlueBalloon() { return new Balloon(0.25,5,0,0,5); }
    public static Balloon createPurpleBalloon() { return new Balloon(1,6,0,0,6); }
}