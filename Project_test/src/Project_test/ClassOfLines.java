package Project_test;


import java.util.ArrayList;
import javafx.geometry.Point2D;

public class ClassOfLines {
    private final int source;
    private final int destination;
    private final ArrayList<Point2D> LinePoints;
    private final ArrayList<Branch> branches;
    
    

    public ClassOfLines(int source, int destination, ArrayList<Point2D> LinePoints, ArrayList<Branch> branches ) {
        this.destination = destination;
        this.source = source;
        this.LinePoints = LinePoints;
        this.branches = branches;
    }



    public int getDestination() {
        return destination;
    }

    public ArrayList<Point2D> getLinePoints() {
        return LinePoints;
    }

    public ArrayList<Branch> getBranches() {
        return branches;
    }
    

    public int getSource() {
        return source;
    }
    
}