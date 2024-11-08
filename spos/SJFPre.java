import java.util.Scanner;

public class SJFPre {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input number of processes
        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();

        // Declare arrays for process details
        int[] pid = new int[n], at = new int[n], bt = new int[n], ct = new int[n], tat = new int[n], wt = new int[n];
        int[] remainingBt = new int[n]; // Remaining burst times
        float atat = 0, awt = 0;

        // Input process details
        for (int i = 0; i < n; i++) {
            System.out.print("Enter process id, arrival time, and burst time: ");
            pid[i] = sc.nextInt();
            at[i] = sc.nextInt();
            bt[i] = sc.nextInt();
            remainingBt[i] = bt[i];
        }

        // Variables to track time and process completion
        int st = 0, total = 0;
        boolean[] finished = new boolean[n];

        // Preemptive SJF Scheduling
        while (total < n) {
            int min = Integer.MAX_VALUE, idx = -1;

            for (int i = 0; i < n; i++) {
                if (at[i] <= st && !finished[i] && remainingBt[i] < min) {
                    min = remainingBt[i];
                    idx = i;
                }
            }

            if (idx == -1) {
                st++; // No process is ready, increment time
            } else {
                remainingBt[idx]--;
                st++;

                if (remainingBt[idx] == 0) {
                    ct[idx] = st;
                    finished[idx] = true;
                    total++;
                }
            }
        }

        // Calculate Turnaround Time and Waiting Time
        for (int i = 0; i < n; i++) {
            tat[i] = ct[i] - at[i];
            wt[i] = tat[i] - bt[i];
            atat += tat[i];
            awt += wt[i];
        }

        // Display results
        System.out.println("PID\tAT\tBT\tCT\tTAT\tWT");
        for (int i = 0; i < n; i++) {
            System.out.printf("%d\t%d\t%d\t%d\t%d\t%d\n", pid[i], at[i], bt[i], ct[i], tat[i], wt[i]);
        }

        System.out.printf("Average TAT = %.2f, Average WT = %.2f\n", atat / n, awt / n);
        sc.close();
    }
}

















// The code implements the Preemptive Shortest Job First (SJF) scheduling
// algorithm, which is also known as SJF Preemptive or Shortest Job Next (SJN).
// It prioritizes processes based on the remaining burst time, executing the
// process with the shortest remaining burst time first.

// Here’s a breakdown of how the program works:

// Key Features:
// Input:

// The number of processes (n), process IDs, arrival times (at[]), and burst
// times (bt[]) are taken from the user.
// A remainingBt[] array is used to track the remaining burst time of each
// process as the execution progresses.
// Preemptive Scheduling:

// At every unit of time (st), the algorithm selects the process with the
// shortest remaining burst time that is ready to execute (i.e., its arrival
// time is less than or equal to st).
// If no process is ready (i.e., no process's arrival time is less than or equal
// to st), the system time (st) is incremented.
// If a process is selected for execution, its remaining burst time is
// decremented. Once its remaining burst time reaches zero, the process
// completes, and its completion time (ct[]) is recorded.
// Termination Condition:

// The scheduling continues until all processes have been completed (total < n),
// i.e., all processes have their remainingBt[i] == 0 and are marked as
// finished.
// Calculating Turnaround Time (TAT) and Waiting Time (WT):

// Turnaround Time (TAT) is calculated as TAT[i] = CT[i] - AT[i] where CT[i] is
// the Completion Time and AT[i] is the Arrival Time.
// Waiting Time (WT) is calculated as WT[i] = TAT[i] - BT[i] where BT[i] is the
// Burst Time.
// The averages for both Turnaround Time and Waiting Time are also calculated.
// Output:

// The program outputs the Process ID (PID), Arrival Time (AT), Burst Time (BT),
// Completion Time (CT), Turnaround Time (TAT), and Waiting Time (WT) for each
// process.
// It also calculates and displays the average Turnaround Time and Waiting Time.
// Example Walkthrough:
// If the input is:

// less
// Copy code
// Enter number of processes: 3
// Enter process id, arrival time, and burst time: 1 0 6
// Enter process id, arrival time, and burst time: 2 1 8
// Enter process id, arrival time, and burst time: 3 2 7
// The output will be:

// java
// Copy code
// PID AT BT CT TAT WT
// 1 0 6 6 6 0
// 3 2 7 13 11 4
// 2 1 8 21 20 12
// Average TAT = 12.67, Average WT = 5.33
// Key Variables:
// st: System time, which increments as processes are executed.
// remainingBt[]: Tracks the remaining burst time of each process during
// execution.
// ct[]: Completion time for each process.
// tat[]: Turnaround time for each process.
// wt[]: Waiting time for each process.
// finished[]: Flags to indicate whether a process is finished.
// total: Counter to track the number of completed processes.
// Important Considerations:
// Preemption:
// If a new process arrives with a shorter burst time than the remaining time of
// the currently running process, the current process is preempted, and the new
// process starts.
// No Process Ready:
// If no process is ready to execute at a certain time (st), the time st is
// incremented until a process arrives.
// Averages:
// The program calculates and displays the average Turnaround Time and Waiting
// Time across all processes.
// Conclusion:
// This implementation ensures that processes are scheduled in the order of
// their remaining burst time, providing a preemptive shortest job first
// scheduling mechanism. The program is efficient for systems where shorter
// tasks should be prioritized, reducing the overall waiting time for smaller
// tasks.