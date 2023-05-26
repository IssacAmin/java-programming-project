package Project_test;

import javafx.geometry.Point2D;

public class Block{
    private String name;
    private int ID; 
    private Point2D p1,p2;
    private double sideleng;
    
   
    public Block(String name, int id,Point2D p1,Point2D p2){
        this.ID=id;
        this.name=name;
        this.p1=p1;
        this.p2=p2;
        this.sideleng= p2.getY()- p1.getY();
   
}


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public int getID() {
        return ID;
    }


    public void setID(int iD) {
        ID = iD;
    }


    public Point2D getP1() {
        return p1;
    }


    public void setP1(Point2D p1) {
        this.p1 = p1;
    }


    public Point2D getP2() {
        return p2;
    }


    public void setP2(Point2D p2) {
        this.p2 = p2;
    }


    public double getSideleng() {
        return sideleng;
    }


    public void setSideleng(double sideleng) {
        this.sideleng = sideleng;
    }


}
  

