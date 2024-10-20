#include <iostream>
using namespace std;

// Function to calculate waiting time for each process
void findWaitingTime(int processes[], int n, int bt[], int wt[]) {
    // The waiting time for the first process is 0, as it arrives first
    wt[0] = 0;

    // Calculating waiting time for each process
    for (int i = 1; i < n; i++) {
        // Waiting time for current process = burst time of previous process + waiting time of previous process
        wt[i] = bt[i - 1] + wt[i - 1];
    }
}

// Function to calculate turn around time
void findTurnAroundTime(int processes[], int n, int bt[], int wt[], int tat[]) {
    // Turn around time = burst time + waiting time for each process
    for (int i = 0; i < n; i++) {
        tat[i] = bt[i] + wt[i];
    }
}

// Function to calculate average waiting and turn around time
void findAverageTime(int processes[], int n, int bt[]) {
    int wt[n], tat[n], total_wt = 0, total_tat = 0;

    // Calculating waiting time for all processes
    findWaitingTime(processes, n, bt, wt);

    // Calculating turn around time for all processes
    findTurnAroundTime(processes, n, bt, wt, tat);

    // Display processes along with burst time, waiting time and turn around time
    cout << "Processes  Burst time  Waiting time  Turn around time\n";
    
    for (int i = 0; i < n; i++) {
        total_wt = total_wt + wt[i];
        total_tat = total_tat + tat[i];
        cout << "   " << i + 1 << "\t\t" << bt[i] << "\t    " << wt[i] << "\t\t   " << tat[i] << endl;
    }

    // Display average waiting time and turn around time
    cout << "\nAverage waiting time = " << (float)total_wt / (float)n;
    cout << "\nAverage turn around time = " << (float)total_tat / (float)n << endl;
}

// Main function to run the program
int main() {
    // Process IDs
    int processes[] = {1, 2, 3};
    int n = sizeof(processes) / sizeof(processes[0]);

    // Burst times (the time each process requires)
    int burst_time[] = {10, 5, 8};

    // Calling function to calculate average times
    findAverageTime(processes, n, burst_time);

    return 0;
}
