import java.util.*;

class Job {
    int id;
    int deadline;
    int profit;

    // Constructor to initialize a job
    public Job(int id, int deadline, int profit) {
        this.id = id;
        this.deadline = deadline;
        this.profit = profit;
    }
}

public class JobScheduling {

    // Comparator to sort jobs by profit in descending order
    public static class JobComparator implements Comparator<Job> {
        @Override
        public int compare(Job a, Job b) {
            return b.profit - a.profit;
        }
    }

    // Function to perform job scheduling
    public static int jobScheduling(Job[] jobs, int n) {
        // Step 1: Sort jobs based on profit in descending order
        Arrays.sort(jobs, new JobComparator());

        // Step 2: Find the maximum deadline to know the size of the slot array
        int maxDeadline = 0;
        for (int i = 0; i < n; i++) {
            if (jobs[i].deadline > maxDeadline) {
                maxDeadline = jobs[i].deadline;
            }
        }

        // Step 3: Create a boolean array to track free time slots
        boolean[] slot = new boolean[maxDeadline];
        Arrays.fill(slot, false); // Initialize all slots to false (unoccupied)
        int totalProfit = 0;

        // Step 4: Schedule jobs one by one
        for (int i = 0; i < n; i++) {
            // Find the latest available slot before the job's deadline
            for (int j = jobs[i].deadline - 1; j >= 0; j--) {
                if (!slot[j]) {
                    slot[j] = true;  // Mark the slot as filled
                    totalProfit += jobs[i].profit; // Add the job's profit
                    break;
                }
            }
        }

        return totalProfit; // Return the total profit
    }

    public static void main(String[] args) {
        Job[] jobs = {
            new Job(1, 4, 20),
            new Job(2, 1, 10),
            new Job(3, 1, 40),
            new Job(4, 1, 30)
        };

        int n = jobs.length;
        int maxProfit = jobScheduling(jobs, n);
        System.out.println("Maximum Profit: " + maxProfit);
    }
}
