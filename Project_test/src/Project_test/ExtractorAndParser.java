package Project_test;


import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javafx.geometry.Point2D;
import java.lang.System;

public class ExtractorAndParser {
    
    /**
     * @param file
     * @return
     * @throws FileNotFoundException
     */
    public static String SystemExtractor(File file) throws FileNotFoundException
    {
        String tmp;
        String SystemTag = "";
        boolean containBlock = false;
        boolean foundStart = false;
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                tmp = scanner.nextLine();
                if(tmp.equals("<System>"))
                {
                    SystemTag += tmp + "\n";
                    foundStart = true;
                }
                else if(foundStart)
                {
                    SystemTag += tmp + "\n";
                    if(tmp.contains("Block"))
                    {
                        containBlock = true;
                    }
                } 
                if(tmp.equals("</System>") && containBlock)
                    return SystemTag;
            }
        }
        catch(FileNotFoundException ex)
        {
            System.out.println("File not Found");
        }
        return "Tag Not Found...";
    } 
    
    public static  ArrayList<String> getBlocks(String system) {
        ArrayList<String> blocks = new ArrayList<>();
        int startIndex = 0;
        int endIndex = 0;
        
        while (true) {
            startIndex = system.indexOf("<Block", endIndex);
            endIndex = system.indexOf("</Block>", startIndex);
            
            if (startIndex == -1 || endIndex == -1)
                break;
            
            String block = system.substring(startIndex, endIndex + "</Block>".length());
            blocks.add(block);
        }
        
        return blocks;
    }
    
    public static ArrayList<String> getLines(String system) {
        ArrayList<String> lines = new ArrayList<>();
        int startIndex = 0;
        int endIndex = 0;
        
        while (true) {
            startIndex = system.indexOf("<Line>", endIndex);
            endIndex = system.indexOf("</Line>", startIndex);
            
            if (startIndex == -1 || endIndex == -1)
                break;
            
            String line = system.substring(startIndex, endIndex + "</Line>".length());
            lines.add(line);
        }
        
        return lines;
    }
    
    public static ArrayList<String> getBranches(String system) {
        ArrayList<String> branches = new ArrayList<>();
        int startIndex = 0;
        int endIndex = 0;

        while (true) {
            startIndex = system.indexOf("<Branch>", endIndex);
            endIndex = system.indexOf("</Branch>", startIndex);

            if (startIndex == -1 || endIndex == -1)
                break;

            String branch = system.substring(startIndex + "<branch>".length(), endIndex);
            branches.add(branch);
        }

        return branches;
    }
    
    public static Block BlockParser(String blockString) {
        String name = getAttribute(blockString, "Name=\"", "\"");
        char[] position = getAttribute(blockString, "<P Name=\"Position\">", "</P>").toCharArray();
        int id = Integer.parseInt(getAttribute(blockString, "\" SID=\"", "\""));
        int x1 = 0, y1=0, x2=0, y2=0 , counter = 0, assignor = 0;
            String num = "";
            boolean start = false;
            for (char c : position) {
                if(Character.isDigit(c) && start == false)
                      start = true;
                
                if(c == ',' || c == ']')
                {
                    start = false;
                    switch(assignor)
                    {
                        case 0:
                            x1 =Integer.parseInt(num);
                            assignor++;
                            num = "";
                            break;
                        case 1:
                        y1 =Integer.parseInt(num);
                        assignor++;
                        num = "";
                            break;
                        case 2:
                        x2 =Integer.parseInt(num);
                        assignor++;
                        num = "";
                            break;
                        case 3:
                        y2 =Integer.parseInt(num);
                        assignor++;
                        num = "";
                            break;
                        default:
                            break;
                    }
                }
                if(start == true)
                   num += Character.toString(c); 
                counter++;
            }
        
        Point2D point1 = new Point2D(x1,y1);
        Point2D point2 = new Point2D(x2,y2);
        Block block = new Block(name, id, point1, point2);
        return block;
    }
    
     public static ClassOfLines LineParser(String LineString)
    {
        int source = Integer.parseInt(getAttribute(LineString, "<P Name=\"Src\">", "#"));
        int destination = 0;
        ArrayList<Point2D> LinePoints = new ArrayList<>();
        ArrayList<Point2D> BranchPoints = new ArrayList<>();
        ArrayList<Branch> branches = new ArrayList<>();
        int branchIndex = 0;
        
         if(LineString.contains("Branch"))
         {
                String BranchStr = String.valueOf(LineString);
                destination = 0;
                while(true)
                {
                branchIndex = BranchStr.indexOf("<Branch>");
                 if(branchIndex == -1)
                     break;
                 BranchStr = BranchStr.substring(branchIndex);
                 System.out.println(BranchStr);
                 int BranchDestination = Integer.parseInt(getAttribute(BranchStr, "<P Name=\"Dst\">", "#"));
        	 char[] BranchPointsStr = getAttribute(BranchStr, "<P Name=\"Points\">", "</P>").toCharArray();
                 
                         int x = 0, y=0, counter = 0, assignor = 0;
                            String num = "";
                boolean start = false;
                for (char c : BranchPointsStr) {
                    if((Character.isDigit(c) || c == '-' )&& start == false)
                          start = true;

                    if(c == ','||c == ';' || c == ']')
                    {
                        start = false;
                        switch(assignor)
                        {
                            case 0:
                                x =Integer.parseInt(num);
                                assignor++;
                                num = "";
                                break;
                            case 1:
                            y =Integer.parseInt(num);
                            assignor++;
                            num = "";
                                break;    
                            default:
                                break;
                        }
                    if(c == ';' || c == ']')
                    {
                        start = false;
                        assignor = 0;
                        num = "";
                        BranchPoints.add(new Point2D(x,y));
                    }
                    }

                    if(start == true)
                       num += Character.toString(c); 
                    counter++;
       }
                BranchStr = BranchStr.substring(BranchStr.indexOf("</Branch>"));
                branches.add(new Branch(BranchPoints, BranchDestination));
                BranchPoints = new ArrayList<>();
         }
         }
         else 
            destination = Integer.parseInt(getAttribute(LineString, "<P Name=\"Dst\">", "#in"));
         
            if(getAttribute(LineString, "<P Name=\"Points\">", "</P>") != "")
            {
                char[] LinePointsStr = getAttribute(LineString, "<P Name=\"Points\">", "</P>").toCharArray();
                int x = 0, y=0, counter = 0, assignor = 0;
                String num = "";
                boolean start = false;
                for (char c : LinePointsStr) {
                    if((Character.isDigit(c) || c == '-' )&& start == false)
                          start = true;

                    if(c == ','||c == ';' || c == ']')
                    {
                        start = false;
                        switch(assignor)
                        {
                            case 0:
                                x =Integer.parseInt(num);
                                assignor++;
                                num = "";
                                break;
                            case 1:
                            y =Integer.parseInt(num);
                            assignor++;
                            num = "";
                                break;    
                            default:
                                break;
                        }
                    if(c == ';' || c == ']')
                    {
                        start = false;
                        assignor = 0;
                        num = "";
                        LinePoints.add(new Point2D(x,y));
                    }
                    }

                    if(start == true)
                       num += Character.toString(c); 
                    counter++;
       }
            }
        System.out.println(source);
        System.out.println(destination);
        for(Point2D point: LinePoints)
            System.out.println(point + "sdff");
        for(Branch branch: branches)
        {
            for(Point2D point:branch.getBranchPoints())
                System.out.println(point+ "asd");
            System.out.println(branch.getDestination());
        }
        System.out.println("////////////////////");
         return new ClassOfLines(source, destination, LinePoints, branches);
    }
     
     
    public static String getStringBetweenTwoCharacters(String input, String to, String from)
    {
        return input.substring(input.indexOf(to)+1, input.lastIndexOf(from));
    }
    
public static String getAttribute(String input, String to, String from)
    {
        	int start =(input.indexOf(to) + to.length());
        	int end = input.indexOf(from,input.indexOf(to) + to.length());
        	if (start == -1 || end == -1)
        		return "";
        	else
                    return input.substring(start, end);
    }
}
