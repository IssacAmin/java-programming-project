/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Project_test;

import java.util.ArrayList;
import javafx.geometry.Point2D;

/**
 *
 * @author Issac
 */
public class Branch {
    private final ArrayList<Point2D> BranchPoints;
    private final int Destination;
    
    public Branch(ArrayList<Point2D> BranchPoints, int Destination)
    {
        this.BranchPoints = BranchPoints;
        this.Destination = Destination;
    }

    public ArrayList<Point2D> getBranchPoints() {
        return BranchPoints;
    }
    


    public int getDestination() {
        return Destination;
    }
    
}
