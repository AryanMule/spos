import java.util.*;

class SJFNonPreemptive {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();

        int[] pid = new int[n], at = new int[n], bt = new int[n], ct = new int[n], tat = new int[n], wt = new int[n];
        boolean[] finished = new boolean[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Enter Process ID, Arrival Time, and Burst Time: ");
            pid[i] = sc.nextInt();
            at[i] = sc.nextInt();
            bt[i] = sc.nextInt();
            finished[i] = false;
        }
        sc.close();

        int st = 0, total = 0;
        while (total < n) {
            int min = Integer.MAX_VALUE, index = -1;
            for (int i = 0; i < n; i++) {
                if (at[i] <= st && !finished[i] && bt[i] < min) {
                    min = bt[i];
                    index = i;
                }
            }

            if (index == -1) {
                st++; // Increment time if no process is ready
            } else {
                ct[index] = st + bt[index];
                finished[index] = true;
                st = ct[index];
                total++;
            }
        }

        // Calculate Turnaround Time and Waiting Time
        for (int i = 0; i < n; i++) {
            tat[i] = ct[i] - at[i];
            wt[i] = tat[i] - bt[i];
        }

        // Display results
        System.out.println("\nPID\tArrival Time\tBurst Time\tCompletion Time\tTurnaround Time\tWaiting Time");
        for (int i = 0; i < n; i++) {
            System.out.printf("%d\t%d\t\t%d\t\t%d\t\t%d\t\t%d\n", pid[i], at[i], bt[i], ct[i], tat[i], wt[i]);
        }
    }
}








// SJF Non-Preemptive Scheduling Logic:

// The algorithm iterates until all processes are completed (total keeps track
// of completed processes).
// For each time unit (st, the system time):
// It checks each process to find the one with the shortest burst time (bt[i] <
// min) that has arrived (at[i] <= st) and is not finished.
// If a suitable process is found, it executes the process:
// Completion Time (ct): Calculated as the current system time plus the burst
// time of the selected process (ct[index] = st + bt[index]).
// The system time is updated to the completion time of the current process.
// The selected process is marked as finished and total is incremented.
// If no process is ready, it increments the system time by 1.























 
// The Java code implements the Shortest Job First (SJF) scheduling algorithm in
// a non-preemptive manner. Here's a breakdown of how it works:

// Key Points:
// Input:

// The program prompts for the number of processes and for each process's
// Process ID, Arrival Time, and Burst Time.
// Execution:

// The algorithm simulates the scheduling of processes based on their burst
// times, selecting the shortest burst time process that has arrived.
// The finished[] array ensures that each process is only executed once.
// Time Simulation:

// The st variable represents the system time.
// The program looks for the process with the shortest burst time that is ready
// to be executed (i.e., its arrival time is less than or equal to the current
// time).
// If no process is ready, the system time st is incremented by 1 until a
// process becomes ready.
// Completion and Result Calculation:

// The Completion Time (ct[]) is calculated when the process finishes execution.
// Turnaround Time (tat[]) is computed as the difference between Completion Time
// and Arrival Time.
// Waiting Time (wt[]) is calculated as the difference between Turnaround Time
// and Burst Time.
// Output:

// The program outputs the Process ID, Arrival Time, Burst Time, Completion
// Time, Turnaround Time, and Waiting Time for each process.
// Example:
// For example, if we have the following input:

// less
// Copy code
// Enter number of processes: 3
// Enter Process ID, Arrival Time, and Burst Time: 1 0 6
// Enter Process ID, Arrival Time, and Burst Time: 2 1 8
// Enter Process ID, Arrival Time, and Burst Time: 3 2 7
// The program will output something like:

// css
// Copy code
// PID Arrival Time Burst Time Completion Time Turnaround Time Waiting Time
// 1 0 6 6 6 0
// 3 2 7 13 11 4
// 2 1 8 21 20 12
// Considerations:
// The program assumes that all processes will eventually be ready to execute,
// and the time is incremented until there is a ready process.
// The input assumes valid integers are provided for Process IDs, Arrival Times,
// and Burst Times.
// The finished[] array prevents re-execution of a process after it has
// completed.