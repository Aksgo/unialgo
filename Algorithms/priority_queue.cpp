#include <iostream>
#include <queue>
#include <vector>

using namespace std;

int main() {
    // Create a priority queue (max heap by default)
    priority_queue<int> pq;

    // Add elements to the priority queue
    pq.push(10);  // Adding 10
    pq.push(30);  // Adding 30
    pq.push(20);  // Adding 20
    pq.push(5);   // Adding 5

    // Display the top element (largest by default, because it's a max-heap)
    cout << "Top element (highest priority): " << pq.top() << endl;

    // Remove the top element
    pq.pop();

    // Display the top element after pop
    cout << "Top element after pop: " << pq.top() << endl;

    // Remove all elements and display them in order of priority
    cout << "Remaining elements in priority order: ";
    while (!pq.empty()) {
        cout << pq.top() << " ";  // Get the top (largest) element
        pq.pop();  // Remove the top element
    }
    cout << endl;

    return 0;
}
