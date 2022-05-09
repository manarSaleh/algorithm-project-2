
import java.io.File;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author manar
 */
import java.util.*;

public class Application {

    static Scanner in = new Scanner(System.in);

    public static void main(String[] args) throws Exception {

        //printing header
        System.out.println("----------------------------------------------");
        System.out.println("       --- CPCS324 PROJECT PART II ---");
        System.out.println("       --- Floyd-Warshall Algorithm ---");
        System.out.println("----------------------------------------------");
        System.out.println("Choose from the menu:");
        System.out.println("> Enter 1 to answer task #1, Read from file");
        System.out.println("> Enter 2 to answer task #2, make random graph");
        System.out.println("> Enter 0 to Quit");
        System.out.println("----------------------------------------------");
        //reading user choice
        System.out.print("Enter Your Choice: ");
        int choice = in.nextInt();

        //read from file
        if (choice == 1) {

            //check if file exist
            File input = new File("diagraph.txt");
            if (!input.exists()) {
                System.out.println("File does not exist");
                System.exit(0);
            }
            //reading from file header information to create graph object 
            Scanner read = new Scanner(input);
            boolean isDigraph;
            String line = read.nextLine();
            if (line.equals("digraph0")) {
                isDigraph = false;
            } else {
                isDigraph = true;
            }

            int verticesNo = read.nextInt();

            int edgeNo = read.nextInt();

            //make graph object
            Graph graph = new Graph(verticesNo, edgeNo, isDigraph);
            //call read from file to read the edges 
            graph.readGraphFromFile();
            System.out.print("    ");
            System.out.println();

            //make new floyd warshal algorithm
            AllSourceSPAlg a = new AllSourceSPAlg();
            //execute the algorithm
            a.computeFloyedWarshalAlg(graph, true);

        } else if (choice == 2) {

            //printing options
            System.out.println("Test  cases : (where n is the number of vertices "
                    + "and m is the number of edges: ");
            System.out.println(" 1:  n=3,000 ,  m=15,000");
            System.out.println(" 2:  n=6,000 ,  m=30,000");
            System.out.println(" 3:  n=9,000 ,  m=45,000");
            System.out.println(" 4:  n=12,000 ,  m=60,000");
            System.out.println(" 5:  n=15,000 ,  m=75,000");
            System.out.print("Enter your choice: ");

            //read choice
            int choice2 = in.nextInt();

            //reading if the graph is directed or not 
            boolean isDigraph;
            while (true) {
                System.out.print("is the graph directed? (y/n)");
                char answer = in.next().charAt(0);
                if (answer == 'y') {
                    isDigraph = true;
                    break;
                } else if (answer == 'n') {
                    isDigraph = false;
                    break;
                } else {
                    System.out.println("Sorry not a choice");
                }
            }

            Graph graph = null;
            switch (choice2) {
                case 1:
                    //creating graph object and calling makeGraph according to choices 
                    graph = new Graph(3000, 15000, isDigraph);
                    graph.makeGraph();
                    break;
                case 2:
                    graph = new Graph(6000, 30000, isDigraph);
                    graph.makeGraph();
                    break;
                case 3:
                    graph = new Graph(9000, 45000, isDigraph);
                    graph.makeGraph();
                    break;
                case 4:
                    graph = new Graph(12000, 60000, isDigraph);
                    graph.makeGraph();
                    break;
                case 5:
                    graph = new Graph(15000, 75000, isDigraph);
                    graph.makeGraph();
                    break;
                default:
                    System.out.println("Invalid input");
                    System.exit(0);
            }

            System.out.println();
            //setting starting time for the execution of the algorithm
            double startTime = System.currentTimeMillis();
            //calling the algorithm
            AllSourceSPAlg a = new AllSourceSPAlg();
            a.computeFloyedWarshalAlg(graph, false);
            //setting finish time 
            double ftime = System.currentTimeMillis();
            //print the total time consumed by the algorithm
            System.out.println("Total runtime of FloyedWarshal's Algorithm: " + (ftime - startTime) + " ms.");

        }

        System.out.println("\n----------------------------------------------");
        System.out.println("             -- Thank You ---");
        System.out.println("----------------------------------------------");

    }

}
