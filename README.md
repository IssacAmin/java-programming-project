# java-programming-project
parsing mdl file to display some GUI components through JavaFX

Team Members:
1- Issac Amin Sabry Eskander - 2000251
2- Youssef Bekhiet narouz - 2101913
3- Rana Amr Abdel Naby - 2000256
4- Nourhan Ahmed Abd El Rahman - 2001453
5- Aley eldeen amr ali ali - 2000915

Project Description: 
Simulink Viewer 

This a college Project which aims to develop a software tool that can read Simulink MDL files and display their contents in a user-friendly way using a Java-based graphical user interface (GUI)

The program does few things:
1- Loading mdl file 
2 - Parsing the components needed 
3- Displaying the simulink model in a hierarchial structure 


Technology used : 
Javafx

we used Some Classes to describle the gui components:
Table of Classes : 

1- Project_test : Main class including the gui application with steps to draw components based on extracted objects from parsing class

2- ExtactorAndParser: uses the Sample to mdl to extract needed system tag, parse each string of block, line or branch into its respective objects through different static methods defined.

3- ClassOfLines : Containing necessary to draw a line correctly 

4- Block:   containing data field to describe its size and location and id

5- ClassOfLines: containing the source and destinations id, with array of branch objects.

6- Exception handling class(Empty file exception): class including user-defined exception to be executed if the file entered 
by the user is not found.
7- Branch : including the necessary data fields and methods to draw the branches ( if existing ) from lines.

8-sample.mdl : the MDL file which is going to be used to develop the gui application.

9- Output.png : the output gui from our project.
