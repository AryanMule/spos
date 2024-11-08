import java.util.*;

class PriorityNp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the number of processes:");
        int n = sc.nextInt();
        int[] pid = new int[n], at = new int[n], bt = new int[n], ct = new int[n], tat = new int[n], wt = new int[n],
                prio = new int[n];
        boolean[] completed = new boolean[n];

        for (int i = 0; i < n; i++) {
            System.out.println("Enter process ID, arrival time, burst time, and priority for process " + (i + 1) + ":");
            pid[i] = sc.nextInt();
            at[i] = sc.nextInt();
            bt[i] = sc.nextInt();
            prio[i] = sc.nextInt();
        }
        sc.close();

        int st = 0, total = 0;
        while (total < n) {
            int currentProcess = -1, minPriority = Integer.MAX_VALUE;
            for (int i = 0; i < n; i++) {
                if (!completed[i] && at[i] <= st && prio[i] < minPriority) {
                    minPriority = prio[i];
                    currentProcess = i;
                }
            }

            if (currentProcess == -1) {
                st++;
            } else {
                ct[currentProcess] = st + bt[currentProcess];
                tat[currentProcess] = ct[currentProcess] - at[currentProcess];
                wt[currentProcess] = tat[currentProcess] - bt[currentProcess];
                completed[currentProcess] = true;
                st = ct[currentProcess];
                total++;
            }
        }

        System.out.println("PID\tAT\tBT\tPRIO\tCT\tTAT\tWT");
        for (int i = 0; i < n; i++) {
            System.out.println(pid[i] + "\t" + at[i] + "\t" + bt[i] + "\t" + prio[i] + "\t" + ct[i] + "\t" + tat[i]
                    + "\t" + wt[i]);
        }
    }
}




























// This Java program demonstrates Non-Preemptive Priority Scheduling for
// processes in an operating system. In this scheduling algorithm, each process
// is assigned a priority, and the process with the highest priority (lowest
// priority number) is executed first. The algorithm is non-preemptive, meaning
// once a process starts executing, it runs to completion before the next
// process is considered, regardless of other processes arriving or having
// higher priorities.

// Key Components of the Code
// Input:

// The user is prompted to enter the number of processes.
// For each process, the user inputs the process ID, arrival time (AT), burst
// time (BT), and priority.
// Variables and Arrays:

// pid: Stores process IDs.
// at: Stores arrival times.
// bt: Stores burst times.
// prio: Stores priority values (lower numbers indicate higher priority).
// ct: Completion time for each process.
// tat: Turnaround time, calculated as TAT = CT - AT.
// wt: Waiting time, calculated as WT = TAT - BT.
// completed: A boolean array to keep track of completed processes.
// Scheduling Logic:

// The program tracks the current time (time) and iterates until all processes
// are completed.
// In each iteration, it searches for the process with the highest priority
// (smallest priority number) that has arrived and is not yet completed.
// If no process is ready, the time is incremented.
// If a process is found, it is executed until completion. The completion time
// (CT), turnaround time (TAT), and waiting time (WT) for that process are
// calculated and stored.
// The completed flag is set to true for the current process, and
// completedProcesses is incremented to track the number of processes that have
// finished.
// Output:

// After scheduling, the program prints a table with the following details for
// each process: PID, Arrival Time (AT), Burst Time (BT), Priority (PRIO),
// Completion Time (CT), Turnaround Time (TAT), and Waiting Time (WT).
// Example Execution
// Suppose you have 3 processes with the following input:

// Process 1: AT = 0, BT = 5, Priority = 2
// Process 2: AT = 2, BT = 3, Priority = 1
// Process 3: AT = 1, BT = 4, Priority = 3
// The program will:

// Start with Process 1 as it has the highest priority among those that have
// arrived at time = 0.
// Execute Process 2 next since it has the next highest priority among processes
// that have arrived by its completion time.
// Finally, execute Process 3.
// The table output will show Completion Time (CT), Turnaround Time (TAT), and
// Waiting Time (WT) for each process.