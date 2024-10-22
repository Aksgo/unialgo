import java.util.Scanner;

public class FCFS {
    
    // Method to calculate waiting time for all processes
    static void findWaitingTime(int processes[], int n, int bt[], int wt[]) {
        // Waiting time for the first process is 0
        wt[0] = 0;

        // Calculating waiting time for each subsequent process
        for (int i = 1; i < n; i++) {
            wt[i] = bt[i - 1] + wt[i - 1];
        }
    }

    // Method to calculate turn around time for all processes
    static void findTurnAroundTime(int processes[], int n, int bt[], int wt[], int tat[]) {
        // Turn around time = burst time + waiting time
        for (int i = 0; i < n; i++) {
            tat[i] = bt[i] + wt[i];
        }
    }

    // Method to calculate average waiting and turn around time
    static void findAverageTime(int processes[], int n, int bt[]) {
        int wt[] = new int[n]; // Array to store waiting times
        int tat[] = new int[n]; // Array to store turn around times
        int total_wt = 0, total_tat = 0;

        // Function to calculate waiting time of all processes
        findWaitingTime(processes, n, bt, wt);

        // Function to calculate turn around time for all processes
        findTurnAroundTime(processes, n, bt, wt, tat);

        // Display processes along with their burst time, waiting time, and turn around time
        System.out.println("Processes  Burst time  Waiting time  Turn around time");

        for (int i = 0; i < n; i++) {
            total_wt += wt[i];
            total_tat += tat[i];
            System.out.println("   " + (i + 1) + "\t\t" + bt[i] + "\t    " + wt[i] + "\t\t   " + tat[i]);
        }

        // Calculate and display the average waiting and turn around times
        System.out.println("\nAverage waiting time = " + (float) total_wt / n);
        System.out.println("Average turn around time = " + (float) total_tat / n);
    }

    public static void main(String[] args) {
        // Process IDs
        int processes[] = {1, 2, 3};
        int n = processes.length;

        // Burst times (in units)
        int burst_time[] = {10, 5, 8};

        // Calculate average times for the processes
        findAverageTime(processes, n, burst_time);
    }
}
