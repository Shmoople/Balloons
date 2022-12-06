package balloons.playingfield;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import balloons.astar.*;

public class Board {

    private Graph<GraphPoint> balloonGraph;

    public Board() {
        this.balloonGraph = Board.createGrid(5,5,10,5); // temp values
    }

    public Board(Graph<GraphPoint> balloonGraph) {
        this.balloonGraph = balloonGraph;
    }

    public Graph<GraphPoint> getBalloonGraph() { return this.balloonGraph; }

    public static Graph<GraphPoint> createGrid(int width, int height, int spacing, int offset) {
        
        // poor implementation... I'll make a better one later

        GraphPoint[][] nodes = new GraphPoint[width][height];

        for(int i = 0; i < width; i++) {
            for(int j = 0; j < height; j++) {
                nodes[i][j] = new GraphPoint(String.format("%d-%d",j,i),j*spacing+offset,i*spacing+offset);
            }
        }

        HashSet<GraphPoint> nodeSet = new HashSet<GraphPoint>();

        for(GraphPoint[] i : nodes) {
            for(GraphPoint g : i) {
                nodeSet.add(g);
            }
        }

        HashMap<String, Set<String>> nodeMap = new HashMap<String, Set<String>>();

        for(int i = 0; i < nodes.length; i++) {
            for(int j = 0; j < nodes[i].length; j++) {
                Set<String> adjacentNodeIDs = new HashSet<String>();

                // probably could make a better implementation
                if(j > 0) adjacentNodeIDs.add(nodes[i][j-1].getId());
                if(i > 0) adjacentNodeIDs.add(nodes[i-1][j].getId());
                if(i < nodes.length-1) adjacentNodeIDs.add(nodes[i+1][j].getId());
                if(j < nodes[i].length-1) adjacentNodeIDs.add(nodes[i][j+1].getId());

                nodeMap.put(nodes[i][j].getId(), adjacentNodeIDs);
            }
        }

        return new Graph<GraphPoint>(nodeSet, nodeMap);
    }
}
