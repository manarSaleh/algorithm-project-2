
/**
 *
 * @author manar
 */
// Floyd Warshall Algorithm in Java
public class AllSourceSPAlg extends ShortestPathAlgorithm {

    final static int INFINITE = 9999;

    // Implementing floyd warshall algorithm
    void computeFloyedWarshalAlg(Graph graph, boolean choice) {
        //Create the distance matrix 
        int DMatrix[][] = new int[graph.verticesNo][graph.verticesNo];

        int i, j, k;

        for (i = 0; i < graph.verticesNo; i++) {
            for (j = 0; j < graph.verticesNo; j++) {
                if (graph.matrix[i][j] == null) {
                    DMatrix[i][j] = INFINITE;

                } else {
                    DMatrix[i][j] = graph.matrix[i][j].getWeight();
                }
            }
        }

        // Adding vertices individually
        for (k = 0; k < graph.verticesNo; k++) {
            for (i = 0; i < graph.verticesNo; i++) {
                for (j = 0; j < graph.verticesNo; j++) {
                    if (DMatrix[i][k] + DMatrix[k][j] < DMatrix[i][j]) {
                        DMatrix[i][j] = DMatrix[i][k] + DMatrix[k][j];
                    }
                }
            }
            //if the user chose reading from file we want to print the labels
            if (choice) {
                System.out.print("    ");
                for (i = 0; i < graph.verticesNo; i++) {
                    System.out.printf("%-4s", graph.labels.get(i));
                }
                //printing the matrix
                System.out.println();
                printMatrix(DMatrix, graph);
                System.out.println("\n\nـــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــ\n\n");

            }

            
        }

    }

    
    //printing the matrix 
    //used when read from file is chosen
    void printMatrix(int matrix[][], Graph graph) {

        for (int i = 0; i < matrix.length; ++i) {
            //printing labels at the begining of every iteration
            System.out.printf("%-4s", graph.labels.get(i));

            //printing each iteration matrix
            for (int j = 0; j < matrix.length; ++j) {

                if (matrix[i][j] == INFINITE) {
                    System.out.printf("%-4s", "INF");
                } else {
                    System.out.printf("%-4d", matrix[i][j]);
                }
            }
            System.out.println();
        }
    }

}
