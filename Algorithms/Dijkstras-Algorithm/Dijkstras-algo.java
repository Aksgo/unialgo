import java.util.*;

class Graph {
    private int V;
    private LinkedList<Edge> adjList[];

    class Edge {
        int target;
        int weight;

        Edge(int target, int weight) {
            this.target = target;
            this.weight = weight;
        }
    }

    // Constructor to initialize graph with vertices
    Graph(int V) {
        this.V = V;
        adjList = new LinkedList[V];
        for (int i = 0; i < V; i++) {
            adjList[i] = new LinkedList<>();
        }
    }

    // Add edge to the graph
    void addEdge(int source, int target, int weight) {
        adjList[source].add(new Edge(target, weight));
        adjList[target].add(new Edge(source, weight));  // For undirected graph
    }

    // Dijkstra's algorithm to find the shortest path from source vertex
    void dijkstra(int start) {
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        // Priority queue to select the vertex with the smallest distance
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.weight));
        pq.add(new Edge(start, 0));

        while (!pq.isEmpty()) {
            Edge current = pq.poll();
            int u = current.target;

            // Traverse all adjacent vertices of u
            for (Edge neighbor : adjList[u]) {
                int v = neighbor.target;
                int weight = neighbor.weight;

                // Relaxation step
                if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v]) {
                    dist[v] = dist[u] + weight;
                    pq.add(new Edge(v, dist[v]));
                }
            }
        }

        // Print the shortest distances
        System.out.println("Vertex\t\tDistance from source (" + start + ")");
        for (int i = 0; i < V; i++) {
            System.out.println(i + "\t\t" + (dist[i] == Integer.MAX_VALUE ? "Infinity" : dist[i]));
        }
    }

    public static void main(String[] args) {
        Graph g = new Graph(9);

        // Adding edges (source, target, weight)
        g.addEdge(0, 1, 4);
        g.addEdge(0, 7, 8);
        g.addEdge(1, 2, 8);
        g.addEdge(1, 7, 11);
        g.addEdge(2, 3, 7);
        g.addEdge(2, 5, 4);
        g.addEdge(2, 8, 2);
        g.addEdge(3, 4, 9);
        g.addEdge(3, 5, 14);
        g.addEdge(4, 5, 10);
        g.addEdge(5, 6, 2);
        g.addEdge(6, 7, 1);
        g.addEdge(6, 8, 6);
        g.addEdge(7, 8, 7);

        // Find shortest path from source vertex 0
        g.dijkstra(0);
    }
}
/*
Vertex		Distance from source (0)
0		0
1		4
2		12
3		19
4		21
5		11
6		9
7		8
8		14
*/