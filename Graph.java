
//import static Application.read;
import java.io.File;
import java.util.*;

/**
 *
 * @author manar
 */
public class Graph {

    int verticesNo;
    int edgeNo;
    boolean isDigraph;
    Edge[][] matrix;
    ArrayList<Vertex> vertices;

    static Scanner read;

    ArrayList<Character> labels;//to store labels of the graph if characters
    ArrayList<Integer> numLabels;//to store labels of the graph if numbers
    //we used the above two arrays to store the labels of the vertices to making searching
    //for vertices quicker. since we needed to check with every read vertex if it already exist
    //also to set its position 

    Graph(int verticesNo, int edgeNo, boolean isDigraph) {
        this.verticesNo = verticesNo;
        this.edgeNo = edgeNo;
        this.isDigraph = isDigraph;
        matrix = new Edge[verticesNo][verticesNo];
        //initialize all elements of the adjMatrix with null.
        for (int i = 0; i < matrix.length; ++i) {
            for (int j = 0; j < matrix[i].length; ++j) {
                matrix[i][j] = null;
            }
        }

        //initialize the arrays we will need in making graphs
        vertices = new ArrayList<Vertex>();
        labels = new ArrayList<Character>();
        numLabels = new ArrayList<Integer>();

    }

    public void makeGraph() {

        //making distance from an edge to itself = zero 
        for (int i = 0; i < this.verticesNo; i++) {
            addEdge(i, i, 0);
            numLabels.add(i);
        }

// object of Random class
        Random randm = new Random();
        // ensure that all verts are connected
        for (int i = 0; i < this.verticesNo - 1; i++) {

            int weight = randm.nextInt(30) + 1;//generate random edge weights between 0-30
            addEdge(i, i + 1, weight); //connect verts

            //if directed graph add the edge 
            if (!isDigraph) {
                addEdge(i + 1, i, weight);
            }
        }

        // generate edges bewteen verts with the remaining edges
        int remEdges = this.edgeNo - (2 * this.verticesNo - 1);

        for (int i = 0; i < remEdges; i++) {
            //choose a source vertix randomly
            int srcVert = randm.nextInt(this.verticesNo);

            if (numLabels.contains(srcVert)) {
            } //if source is a new vertix
            else {
                //add it to labels
                numLabels.add(srcVert);
            }
            //choose a destination vertix randomly
            int destVert = randm.nextInt(this.verticesNo);

            if (numLabels.contains(destVert)) {
            } //if destination is a new vertix
            else {

                numLabels.add(destVert);
            }
            //isConnected(srcVert, destVert, matrix)
            if (destVert == srcVert || !(matrix[srcVert][destVert] == null)) { // to avoid self loops and duplicate edges
                i--;
                continue;
            }
            // generate random weights in range 0 to 30
            int weight = randm.nextInt(30) + 1;
            // add edge to the graph
            addEdge(srcVert, destVert, weight);

        }

    }

    public void printGraph() {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                System.out.print(matrix[i][j] + "  ");
            }
            System.out.println();
        }

    }

    public void readGraphFromFile() throws Exception {
        //check if file exist
        File input = new File("diagraph.txt");
        if (!input.exists()) {
            System.out.println("File does not exist");
            System.exit(0);
        }
        //distance from vertix to itself is set to zero  
        for (int i = 0; i < this.verticesNo; i++) {
            addEdge(i, i, 0);
        }
        //creat a scanner and skip the first two lines
        read = new Scanner(input);
        read.nextLine();
        read.nextInt();
        read.nextInt();

        char source;
        char destination;

        int weight;
        int sPosition;
        int dPosition;

        //continuously read edges 
        while (read.hasNextLine()) {
            //read source vertix
            source = read.next().charAt(0);
            //read destination vertix 
            destination = read.next().charAt(0);
            //read weight
            weight = read.nextInt();
            //if source exist then return the vertix object
            if (labels.contains(source)) {
                sPosition = labels.indexOf(source);
            } //if source is a new vertix
            else {
                //set position and add it as its label
                sPosition = labels.size();
                labels.add(source);
            }
            //if destination vertix exist return it
            if (labels.contains(destination)) {
                dPosition = labels.indexOf(destination);
            } //if destination is a new vertix
            else {
                dPosition = labels.size();
                labels.add(destination);
            }

            addEdge(sPosition, dPosition, weight);
        }
    }

    public Edge addEdge(int source, int destination, int weight) {//source: v, destination(target): u, vertex weight: w
        Vertex d;
        Vertex s;
        boolean found = false;

        //with its position check if vertix exist return it . and if not create a new vertex
        s = new Vertex(source);

        for (int i = 0; i < vertices.size(); i++) {

            if (vertices.get(i).equals(s)) {
                s = vertices.get(i);
                found = true;
                break;
            }
        }
        if (!found) {
            s = new Vertex(source);
            vertices.add(s);
        }

        found = false;

        //if destination vertex exist
        d = new Vertex(destination);
        for (int i = 0; i < vertices.size(); i++) {

            if (vertices.get(i).equals(d)) {
                d = vertices.get(i);
                found = true;
                break;
            }
        }
        if (!found) {
            d = new Vertex(destination);
            vertices.add(d);
        }
        found = false;

        //creating the edge between the two vertices. if the graph is undirected add edge it the opposite side
        Edge edge = new Edge(s, d, weight);
        if (isIsDigraph()) {
            matrix[s.getPosition()][d.getPosition()] = edge;
        }
        if (isIsDigraph() == false) {
            matrix[s.getPosition()][d.getPosition()] = edge;
            matrix[d.getPosition()][s.getPosition()] = edge;
        }
        return edge;

    }

    public boolean addVertLabel(/*vLabel*/) {
        return false;
    }

    public void setIsDigraph(boolean isDigraph) {
        this.isDigraph = isDigraph;
    }

    public void setVerticesNo(int verticesNo) {
        this.verticesNo = verticesNo;
    }

    public void setEdgeNo(int edgeNo) {
        this.edgeNo = edgeNo;
    }

    public int getVerticesNo() {
        return verticesNo;
    }

    public int getEdgeNo() {
        return edgeNo;
    }

    public boolean isIsDigraph() {
        return isDigraph;
    }

}
