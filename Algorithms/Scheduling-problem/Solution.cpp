#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

// Define a structure to represent a job
struct Job {
    int id;         // Job ID
    int deadline;   // Job deadline
    int profit;     // Job profit
};

// Function to compare jobs based on their profit
bool compare(Job a, Job b) {
    return a.profit > b.profit;
}

// Function to perform job scheduling to maximize profit
int jobScheduling(Job arr[], int n) {
    // Step 1: Sort jobs in decreasing order of profit
    sort(arr, arr + n, compare);

    // Step 2: Find the maximum deadline to determine the size of the slot array
    int maxDeadline = 0;
    for (int i = 0; i < n; i++) {
        if (arr[i].deadline > maxDeadline)
            maxDeadline = arr[i].deadline;
    }

    // Create an array to store the slot availability
    vector<bool> slot(maxDeadline, false);
    int totalProfit = 0;

    // Step 3: Schedule jobs one by one
    for (int i = 0; i < n; i++) {
        // Find a slot for the current job
        for (int j = arr[i].deadline - 1; j >= 0; j--) {
            // If slot is available, schedule the job
            if (!slot[j]) {
                slot[j] = true;
                totalProfit += arr[i].profit;
                break;
            }
        }
    }

    return totalProfit; // Return the total profit
}

int main() {
    Job arr[] = {{1, 4, 20}, {2, 1, 10}, {3, 1, 40}, {4, 1, 30}};
    int n = sizeof(arr) / sizeof(arr[0]);

    int maxProfit = jobScheduling(arr, n);
    cout << "Maximum Profit: " << maxProfit << endl;
    return 0;
}
