#include <iostream>
#include <vector>
#include <queue>
#include <limits>

using namespace std;

// A structure to represent a weighted edge in the graph
struct Edge {
    int to;
    int weight;
};

// Dijkstra's Algorithm function
void dijkstra(int source, const vector<vector<Edge>>& graph, vector<int>& distances) {
    priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>> minHeap;
    distances[source] = 0;
    minHeap.push({0, source}); // {distance, vertex}

    while (!minHeap.empty()) {
        int currentDistance = minHeap.top().first;
        int currentVertex = minHeap.top().second;
        minHeap.pop();

        // If the distance is greater than the recorded distance, skip
        if (currentDistance > distances[currentVertex]) {
            continue;
        }

        // Explore neighbors
        for (const Edge& edge : graph[currentVertex]) {
            int neighbor = edge.to;
            int newDistance = currentDistance + edge.weight;

            // If a shorter path to the neighbor is found
            if (newDistance < distances[neighbor]) {
                distances[neighbor] = newDistance;
                minHeap.push({newDistance, neighbor});
            }
        }
    }
}

// Function to print the distances
void printDistances(const vector<int>& distances) {
    cout << "Vertex Distance from Source" << endl;
    for (size_t i = 0; i < distances.size(); i++) {
        cout << i << "\t\t" << distances[i] << endl;
    }
}

// Main function to test Dijkstra's Algorithm
int main() {
    // Create a graph represented as an adjacency list
    int vertices = 6; // Number of vertices
    vector<vector<Edge>> graph(vertices);

    // Add edges (example graph)
    graph[0].push_back({1, 4});
    graph[0].push_back({2, 1});
    graph[1].push_back({3, 1});
    graph[2].push_back({1, 2});
    graph[2].push_back({3, 5});
    graph[3].push_back({4, 3});
    graph[4].push_back({5, 2});
    graph[3].push_back({5, 1});

    // Initialize distances
    vector<int> distances(vertices, numeric_limits<int>::max());

    // Run Dijkstra's Algorithm from source vertex 0
    dijkstra(0, graph, distances);

    // Print the distances from the source
    printDistances(distances);

    return 0;
}
