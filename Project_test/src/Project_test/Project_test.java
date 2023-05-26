package Project_test;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.stage.Stage;
/**
 *
 * @author Issac
 */
public class Project_test extends Application{

            static ArrayList<Block> blockObjects = new ArrayList<>();
            static ArrayList<ClassOfLines> LineObjects = new ArrayList<>();
     @Override
    public void start(Stage stage) throws IOException {



        Group pane = new Group();
        
        for (int i = 0; i < blockObjects.size(); i++) {
             double x1 = blockObjects.get(i).getP1().getX() ;
             double y1= blockObjects.get(i).getP1().getY() ;

            Rectangle background = new Rectangle( blockObjects.get(i).getSideleng() + 6,blockObjects.get(i).getSideleng() + 6) ;
            Rectangle rect = new Rectangle( blockObjects.get(i).getSideleng(),blockObjects.get(i).getSideleng()) ;

            rect.setStyle("-fx-fill: white; -fx-stroke: black ; -fx-stroke-width: 2;");
            background.setStyle("-fx-fill: white; -fx-stroke: cyan ; -fx-stroke-width: 3;");
            Label label1 = new Label(blockObjects.get(i).getName());
            VBox box1 = new VBox(rect, label1);
            box1.setLayoutX(x1-300);
            box1.setLayoutY(y1-100);
            background.setLayoutX(x1-302);
            background.setLayoutY(y1-102);
            pane.getChildren().addAll(background);
            pane.getChildren().addAll(box1);
        }
        //draw lines
        for(int i = 0; i < LineObjects.size();i++)
        {
            double x1 = 0, y1 = 0, x2 = 0, y2 = 0;
                //get src point
                for(int k = 0; k < blockObjects.size(); k++)
                {
                    if(blockObjects.get(k).getID() == LineObjects.get(i).getSource())
                    {
                        if(blockObjects.get(k).getID() == 4)
                        {
                            x1 = blockObjects.get(k).getP1().getX();
                            y1 = blockObjects.get(k).getP1().getY() + (blockObjects.get(k).getSideleng()/2) ;
                            break;
                        }
                        else
                        {
                            x1 = blockObjects.get(k).getP1().getX() + blockObjects.get(k).getSideleng();
                            y1 = blockObjects.get(k).getP1().getY() + (blockObjects.get(k).getSideleng()/2) ;
                            break;
                        }
                    }
                }
                for(int j =0; j < LineObjects.get(i).getLinePoints().size(); j ++)
                {
                    Line line = new Line();
                    line.setStyle("-fx-fill: black; -fx-stroke: black ; -fx-stroke-width: 1;");
                    line.setStrokeType(StrokeType.OUTSIDE);
                    
                    x2 = x1 + LineObjects.get(i).getLinePoints().get(j).getX();
                    y2 = y1 + LineObjects.get(i).getLinePoints().get(j).getY();
                    
                    line.setStartX(x1 - 300);
                    line.setStartY(y1 - 100);
                    line.setEndX(x2 - 300);
                    line.setEndY(y2- 100);
                    pane.getChildren().addAll(line);
                    if(LineObjects.get(i).getDestination() == 0)
                    {
                    Circle circle = new Circle(x2 -300, y2-100, 4);
                    pane.getChildren().addAll(circle);
                    }
                    x1 = x2;
                    y1 = y2;
                }
            if(LineObjects.get(i).getDestination() != 0) // No branches
            {

                // draw to destination
                Line line = new Line();
                line.setStyle("-fx-fill: black; -fx-stroke: black ; -fx-stroke-width: 1;");
                line.setStrokeType(StrokeType.OUTSIDE);
                for(int k = 0; k < blockObjects.size(); k++)
                {
                    if(blockObjects.get(k).getID() == LineObjects.get(i).getDestination())
                    {
                           x2 = (blockObjects.get(k).getID() == 4) ?  blockObjects.get(k).getP1().getX() 
                                   + blockObjects.get(k).getSideleng(): blockObjects.get(k).getP1().getX();
                            y2 = (blockObjects.get(k).getID() == 3)? y2:
                                     blockObjects.get(k).getP1().getY() + (blockObjects.get(k).getSideleng()/2);
                            line.setStartX(x1 - 300);
                            line.setStartY(y1 - 100);
                            line.setEndX(x2 - 300);
                            line.setEndY(y2- 100);
                            pane.getChildren().addAll(line);
                            Polygon triangle = new Polygon();
                            triangle.getPoints().setAll(x2- 300, y2 -100, x2 -308, y2 -105, x2- 308 , y2 - 95);
                            pane.getChildren().addAll(triangle);
                            break;
                    }                   
                }
                
            }
            else
            {

                for(int j = 0; j < LineObjects.get(i).getBranches().size(); j++)
                {
                    double startingPointx = x1;
                    double startingPointy = y1;
                    boolean ifbranchpoints = false;
                    for(int l = 0 ; l < LineObjects.get(i).getBranches().get(j).getBranchPoints().size(); l++)
                    {
                        ifbranchpoints = true;
                        Line line = new Line();
                       line.setStyle("-fx-fill: black; -fx-stroke: black ; -fx-stroke-width: 1;");
                       line.setStrokeType(StrokeType.OUTSIDE);

                       x2 = startingPointx +LineObjects.get(i).getBranches().get(j).getBranchPoints().get(l).getX();
                       y2 = startingPointy + LineObjects.get(i).getBranches().get(j).getBranchPoints().get(l).getY();

                       line.setStartX(x1 - 300);
                       line.setStartY(y1 - 100);
                       line.setEndX(x2 - 300);
                       line.setEndY(y2- 100);
                       pane.getChildren().addAll(line);
                       startingPointx = x2;
                       startingPointy = y2;
                       

                for(int k = 0; k < blockObjects.size() ;k++)
                {
                Line destline = new Line();
                destline.setStyle("-fx-fill: black; -fx-stroke: black ; -fx-stroke-width: 1;");
                destline.setStrokeType(StrokeType.OUTSIDE);
                    if(blockObjects.get(k).getID() == LineObjects.get(i).getBranches().get(j).getDestination())
                    {
                           x2 = (blockObjects.get(k).getID() == 4) ?  blockObjects.get(k).getP1().getX() 
                                   + blockObjects.get(k).getSideleng(): blockObjects.get(k).getP1().getX();
                            y2 = (blockObjects.get(k).getID() == 3)? y2:
                                     blockObjects.get(k).getP1().getY() + (blockObjects.get(k).getSideleng()/2);
                            destline.setStartX(startingPointx - 300);
                            destline.setStartY(startingPointy - 100);
                            destline.setEndX(x2 - 300);
                            destline.setEndY(y2- 100);
                            pane.getChildren().addAll(destline);
                            if((blockObjects.get(k).getID() == 4))
                            {
                            Polygon triangle = new Polygon();
                            triangle.getPoints().setAll(x2- 300, y2 -100, x2 -292, y2 -105, x2- 292 , y2 - 95);
                            pane.getChildren().addAll(triangle);
                            }
                            else{
                            Polygon triangle = new Polygon();
                            triangle.getPoints().setAll(x2- 300, y2 -100, x2 -308, y2 -105, x2- 308 , y2 - 95);
                            pane.getChildren().addAll(triangle);
                            }
                    }
                }
                
                    }
                    if(!ifbranchpoints)
                    {
                        for(int k = 0; k < blockObjects.size() ;k++)
                        {
                        Line destline = new Line();
                        destline.setStyle("-fx-fill: black; -fx-stroke: black ; -fx-stroke-width: 1;");
                        destline.setStrokeType(StrokeType.OUTSIDE);
                            if(blockObjects.get(k).getID() == LineObjects.get(i).getBranches().get(j).getDestination())
                            {
                                   x2 = (blockObjects.get(k).getID() == 4) ?  blockObjects.get(k).getP1().getX() 
                                           + blockObjects.get(k).getSideleng(): blockObjects.get(k).getP1().getX();
                                    y2 = (blockObjects.get(k).getID() == 3)? y1:
                                             blockObjects.get(k).getP1().getY() + (blockObjects.get(k).getSideleng()/2);
                                    destline.setStartX(x1 - 300);
                                    destline.setStartY(y1 - 100);
                                    destline.setEndX(x2 - 300);
                                    destline.setEndY(y2- 100);
                                    pane.getChildren().addAll(destline);
                                    Polygon triangle = new Polygon();
                                    triangle.getPoints().setAll(x2- 300, y2 -100, x2 -308, y2 -105, x2- 308 , y2 - 95);
                                    pane.getChildren().addAll(triangle);
                            }
                        }
                    }
                    

                    
                }
                
            }
        }
        
        
        Scene scene = new Scene(pane, 1500, 800);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    
    
    
    
    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        try{  
            File file = new File("Sample.mdl");
            Scanner scanner = new Scanner(file);
            if(file.length() == 0)
            {
                scanner.close();
                throw new EmptyFileException("Empty file ");
            }
            //Main Code Here...
            String string = ExtractorAndParser.SystemExtractor(file);
            ArrayList<String> blocks = new ArrayList<>();

            blocks = ExtractorAndParser.getBlocks(string);

            for (String block : blocks)
                blockObjects.add(ExtractorAndParser.BlockParser(block));
            
            ArrayList<String> lines = new ArrayList<>();
            lines = ExtractorAndParser.getLines(string);
            for (String line : lines)
                LineObjects.add(ExtractorAndParser.LineParser(line));
            
            scanner.close();
        }
        catch(EmptyFileException | FileNotFoundException e)
        {
            System.out.println(e);
        }
        launch();
    }
    
    }
   
