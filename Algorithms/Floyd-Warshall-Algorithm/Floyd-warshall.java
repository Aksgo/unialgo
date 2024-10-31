class FloydWarshall {
  final static int INF = 9999, nV = 4;

  // Implementing Floyd-Warshall algorithm
  void floydWarshall(int graph[][]) {
      int matrix[][] = new int[nV][nV]; // Create a matrix to store the shortest paths
      int i, j, k;

      // Copying the input graph to the matrix
      for (i = 0; i < nV; i++)
          for (j = 0; j < nV; j++)
              matrix[i][j] = graph[i][j];

      // Adding vertices individually
      for (k = 0; k < nV; k++) { // Iterate through each intermediate vertex
          for (i = 0; i < nV; i++) { // Iterate through each source vertex
              for (j = 0; j < nV; j++) { // Iterate through each destination vertex
                  // Update the shortest path if a shorter path is found
                  if (matrix[i][k] + matrix[k][j] < matrix[i][j])
                      matrix[i][j] = matrix[i][k] + matrix[k][j];
              }
          }
      }
      printMatrix(matrix); // Print the resulting shortest path matrix
  }

  // Function to print the matrix
  void printMatrix(int matrix[][]) {
      for (int i = 0; i < nV; ++i) { // Iterate through each row of the matrix
          for (int j = 0; j < nV; ++j) { // Iterate through each column of the matrix
              // Print 'INF' for infinite distances
              if (matrix[i][j] == INF)
                  System.out.print("INF ");
              else
                  System.out.print(matrix[i][j] + "  "); // Print the distance
          }
          System.out.println(); // Move to the next line after printing a row
      }
  }

  public static void main(String[] args) {
      // Defining the graph as an adjacency matrix
      int graph[][] = { { 0, 3, INF, 5 }, 
                        { 2, 0, INF, 4 }, 
                        { INF, 1, 0, INF }, 
                        { INF, INF, 2, 0 } };
      FloydWarshall a = new FloydWarshall(); // Create an instance of the FloydWarshall class
      a.floydWarshall(graph); // Call the Floyd-Warshall function with the graph
  }
}
