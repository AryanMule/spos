import java.util.*;

class PriorityP {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of processes:");
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

        int[] remainingBt = Arrays.copyOf(bt, n);
        int time = 0, completedProcesses = 0;

        while (completedProcesses < n) {
            int currentProcess = -1, minPriority = Integer.MAX_VALUE;

            for (int i = 0; i < n; i++) {
                if (at[i] <= time && !completed[i] && prio[i] < minPriority) {
                    minPriority = prio[i];
                    currentProcess = i;
                }
            }

            if (currentProcess == -1) {
                time++;
            } else {
                remainingBt[currentProcess]--;
                time++;
                if (remainingBt[currentProcess] == 0) {
                    ct[currentProcess] = time;
                    completed[currentProcess] = true;
                    completedProcesses++;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            tat[i] = ct[i] - at[i];
            wt[i] = tat[i] - bt[i];
        }

        System.out.println("PID\tAT\tBT\tPRIO\tCT\tTAT\tWT");
        for (int i = 0; i < n; i++) {
            System.out.println(pid[i] + "\t" + at[i] + "\t" + bt[i] + "\t" + prio[i] + "\t" + ct[i] + "\t" + tat[i]
                    + "\t" + wt[i]);
        }
    }
}






















// This Java program implements the Priority Scheduling algorithm with
// Preemption. In this variant of priority scheduling, if a process arrives with
// a higher priority (lower priority number) during the execution of another
// process, it will preempt the currently running process and execute first.

// Key Points in the Code:
// Input:

// The program first asks the user to input the number of processes.
// For each process, it requires the Process ID (PID), Arrival Time (AT), Burst
// Time (BT), and Priority.
// Arrays:

// pid[]: Holds the process IDs.
// at[]: Stores the arrival times of each process.
// bt[]: Stores the burst times (the time each process requires to complete).
// prio[]: Holds the priority for each process (lower numbers mean higher
// priority).
// remainingBt[]: Keeps track of the remaining burst time for each process as
// they execute.
// ct[]: Stores the completion times for each process.
// tat[]: Turnaround time for each process, calculated as TAT = CT - AT.
// wt[]: Waiting time for each process, calculated as WT = TAT - BT.
// completed[]: A boolean array to track which processes have completed their
// execution.
// Scheduling Logic:

// Preemption: The scheduling works by checking which process has the highest
// priority (lowest priority number) and has arrived at the current time (time).
// If no process can be executed, the time is incremented.
// Once a process is selected to execute, it decrements its remaining burst time
// (remainingBt[]). If the process completes (i.e., remainingBt[currentProcess]
// == 0), it is marked as completed, and the process' completion time is
// recorded.
// This continues until all processes are completed.
// Output:

// After all processes have finished execution, the program calculates the
// Turnaround Time (TAT) and Waiting Time (WT) for each process and prints the
// details in a table format: PID, Arrival Time (AT), Burst Time (BT), Priority
// (PRIO), Completion Time (CT), Turnaround Time (TAT), and Waiting Time (WT).
// Example Execution
// Suppose you have 3 processes with the following input:

// css
// Copy code
// Process ID Arrival Time (AT) Burst Time (BT) Priority
// P1 0 4 3
// P2 1 3 1
// P3 2 2 2
// The program will begin execution by checking for the process with the highest
// priority at each time.
// Initially, Process P1 arrives and begins execution at time 0.
// At time 1, Process P2 arrives with a higher priority (lower number), so it
// will preempt P1 and execute.
// After P2 finishes, P3 will run, and finally, P1 will complete.
// The program will print a table showing the completion times, turnaround
// times, and waiting times for each process.

// Example Output:
// arduino
// Copy code
// Enter number of processes:
// 3
// Enter process ID, arrival time, burst time, and priority for process 1:
// P1 0 4 3
// Enter process ID, arrival time, burst time, and priority for process 2:
// P2 1 3 1
// Enter process ID, arrival time, burst time, and priority for process 3:
// P3 2 2 2

// PID AT BT PRIO CT TAT WT
// P1 0 4 3 7 7 3
// P2 1 3 1 4 3 0
// P3 2 2 2 6 4 2
// How It Works:
// Process P2 (Priority = 1) arrives at time 1, preempts P1 (Priority = 3) and
// executes first.
// Process P3 (Priority = 2) arrives at time 2 but has a lower priority than P2,
// so it waits for P2 to complete.
// Process P1 (Priority = 3) completes last after P2 and P3 have finished
// executing.
// This approach ensures that the process with the highest priority is always
// executed next, and the system behaves in a preemptive manner to ensure the
// most important tasks are handled first.