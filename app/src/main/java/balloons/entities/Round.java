package balloons.entities;

public class Round {
    
    public static Balloon[][] rounds = { 
        {
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
        },
    
        {
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
        },

        {
        createOrangeBalloon(),
        createOrangeBalloon(),
        createOrangeBalloon(),
        createYellowBalloon(),
        createYellowBalloon(),
        createYellowBalloon(),
        createGreenBalloon(),
        createGreenBalloon(),
        createGreenBalloon()
        },

        {
        createYellowBalloon(),
        createYellowBalloon(),
        createYellowBalloon(),
        createYellowBalloon(),
        createGreenBalloon(),
        createGreenBalloon(),
        createGreenBalloon(),
        createBlueBalloon(),
        createBlueBalloon()
        },

        {
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
        },

        {
        createBlueBalloon(),
        createBlueBalloon(),
        createBlueBalloon(),
        createBlueBalloon(),
        createRedBalloon(),
        createRedBalloon(),
        createRedBalloon(),
        createRedBalloon()
        },

        {
        createYellowBalloon(),
        createYellowBalloon(),
        createGreenBalloon(),
        createGreenBalloon(),
        createPurpleBalloon(),
        createPurpleBalloon(),
        createBlueBalloon(),
        createBlueBalloon()
        },

        {
        createPurpleBalloon(),
        createPurpleBalloon(),
        createPurpleBalloon(),
        createPurpleBalloon(),
        createPurpleBalloon()
        },

        {
        createPurpleBalloon(),
        createBlueBalloon(),
        createGreenBalloon(),
        createYellowBalloon(),
        createOrangeBalloon(),
        createRedBalloon(),
        createBlueBalloon(),
        createBlueBalloon(),
        createBlueBalloon(),
        createPurpleBalloon()
        },

        {
        createBlueBalloon(),
        createBlueBalloon(),
        createBlueBalloon(),
        createBlueBalloon(),
        createBlueBalloon(),
        createPurpleBalloon(),
        createPurpleBalloon(),
        createPurpleBalloon(),
        createPurpleBalloon(),
        createPurpleBalloon()
        },

        {
        createOrangeBalloon(),
        createOrangeBalloon(),
        createOrangeBalloon(),
        createOrangeBalloon(),
        createOrangeBalloon(),
        createOrangeBalloon(),
        createOrangeBalloon(),
        createOrangeBalloon(),
        createOrangeBalloon(),
        createOrangeBalloon(),
        },

        {
        createOrangeBalloon(),
        createOrangeBalloon(),
        createOrangeBalloon(),
        createOrangeBalloon(),
        createOrangeBalloon(),
        createYellowBalloon(),
        createYellowBalloon(),
        createYellowBalloon(),
        createYellowBalloon(),
        createYellowBalloon(),
        },

        {
        createYellowBalloon(),
        createYellowBalloon(),
        createYellowBalloon(),
        createGreenBalloon(),
        createGreenBalloon(),
        createGreenBalloon(),
        createBlueBalloon(),
        createBlueBalloon(),
        createBlueBalloon(),
        },

        {
        createPurpleBalloon(),
        createPurpleBalloon(),
        createPurpleBalloon(),
        createBlueBalloon(),
        createBlueBalloon(),
        createBlueBalloon(),
        createGreenBalloon(),
        createGreenBalloon(),
        createGreenBalloon(),
        },

        {
        createPurpleBalloon(),
        createPurpleBalloon(),
        createPurpleBalloon(),
        createZepBalloon(),
        createBlueBalloon(),
        createBlueBalloon(),
        createBlueBalloon(),
        }
    };

    public static Balloon createRedBalloon() { return new Balloon(0.5,1,0,0,1); }
    public static Balloon createOrangeBalloon() { return new Balloon(0.5,2,0,0,2); }
    public static Balloon createYellowBalloon() { return new Balloon(0.5,3,0,0,3); }
    public static Balloon createGreenBalloon() { return new Balloon(0.5,4,0,0,4); }
    public static Balloon createBlueBalloon() { return new Balloon(0.4,5,0,0,5); }
    public static Balloon createPurpleBalloon() { return new Balloon(.75,6,0,0,6); }
    public static Balloon createZepBalloon() { return new Balloon(1,20,0,0,10); }
}