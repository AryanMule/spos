import java.util.*;

public class RoundRobin {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();

        System.out.print("Enter quantum time: ");
        int q = sc.nextInt();

        int[] pid = new int[n], at = new int[n], bt = new int[n], ct = new int[n], tat = new int[n], wt = new int[n],
                rbt = new int[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Enter process ID for process " + (i + 1) + ": ");
            pid[i] = sc.nextInt();
            System.out.print("Enter Arrival Time for process " + (i + 1) + ": ");
            at[i] = sc.nextInt();
            System.out.print("Enter Burst Time for process " + (i + 1) + ": ");
            bt[i] = sc.nextInt();
            rbt[i] = bt[i];
        }

        // Sort processes by arrival time
        Integer[] indexes = new Integer[n];
        for (int i = 0; i < n; i++)
            indexes[i] = i;
        Arrays.sort(indexes, Comparator.comparingInt(i -> at[i]));

        // Round Robin Scheduling
        int st = 0, total = 0;
        Queue<Integer> queue = new LinkedList<>();
        while (total < n) {
            for (int i = 0; i < n && at[indexes[i]] <= st; i++)
                queue.add(indexes[i]);

            if (queue.isEmpty()) {
                st++;
                continue;
            }

            int idx = queue.poll();
            if (rbt[idx] > q) {
                st += q;
                rbt[idx] -= q;
            } else {
                st += rbt[idx];
                ct[idx] = st;
                rbt[idx] = 0;
                total++;
            }

            for (int i = 0; i < n && at[indexes[i]] <= st; i++)
                queue.add(indexes[i]);

            if (rbt[idx] > 0)
                queue.add(idx);
        }

        // Calculate Turnaround Time and Waiting Time
        float avgwt = 0, avgta = 0;
        for (int i = 0; i < n; i++) {
            tat[i] = ct[i] - at[i];
            wt[i] = tat[i] - bt[i];
            avgta += tat[i];
            avgwt += wt[i];
        }

        // Output Results
        System.out.println("\nProcess ID\tArrival Time\tBurst Time\tCompletion Time\tTurnaround Time\tWaiting Time");
        for (int i = 0; i < n; i++) {
            System.out.println(
                    pid[i] + "\t\t" + at[i] + "\t\t" + bt[i] + "\t\t" + ct[i] + "\t\t\t" + tat[i] + "\t\t\t" + wt[i]);
        }

        System.out.println("\nAverage Turnaround Time: " + (avgta / n));
        System.out.println("Average Waiting Time: " + (avgwt / n));

        sc.close();
    }
}














// Key Sections of the Code:
// Input Section:

// The program starts by asking the user for the number of processes (n) and the
// quantum time (q).
// It then asks for the process ID, arrival time, and burst time for each
// process.
// Sorting Processes by Arrival Time:

// The program creates an array indexes to store the indices of processes. It
// then sorts the processes by their arrival time (at[]), which ensures that the
// process with the earliest arrival time is handled first.
// Round Robin Scheduling:

// The scheduling is done in a loop where the processes are executed in a
// circular manner.
// A queue (queue) is used to manage the processes that are ready to be executed
// at the current time (st).
// If a process has remaining burst time greater than the quantum time (q), the
// quantum time is deducted, and the process is put back into the queue with the
// remaining burst time.
// If a process completes (i.e., its remaining burst time becomes zero), the
// process's completion time (ct[]) is set, and it is no longer added to the
// queue.
// The scheduling continues until all processes are completed.
// Turnaround Time (TAT) and Waiting Time (WT):

// Turnaround Time (TAT) is calculated as:
// css
// Copy code
// TAT[i] = Completion Time[i] - Arrival Time[i]
// Waiting Time (WT) is calculated as:
// css
// Copy code
// WT[i] = Turnaround Time[i] - Burst Time[i]
// The averages of TAT and WT are calculated to give the overall performance of
// the scheduling algorithm.
// Output:

// The program prints a table with the following columns:
// Process ID
// Arrival Time
// Burst Time
// Completion Time
// Turnaround Time
// Waiting Time
// It then prints the average Turnaround Time and Waiting Time for all
// processes.
// Explanation of the Code:
// Input and Process Initialization: The input section collects the necessary
// data for each process (process ID, arrival time, burst time) and initializes
// arrays (pid[], at[], bt[], ct[], etc.).

// Sorting the Processes by Arrival Time: The processes are sorted by arrival
// time (at[]) using Arrays.sort() in conjunction with a custom comparator that
// sorts based on the at[] array.

// Round Robin Scheduling Logic: A queue is used to store processes that are
// ready to execute (i.e., their arrival time has passed). The processes are
// handled in a round-robin manner where each process is executed for a time
// slice of q. If a process doesn't finish in its time slice, it is placed back
// in the queue with its remaining burst time.

// Completion Time and Turnaround Calculation: Once a process completes (its
// remaining burst time becomes zero), the program calculates its completion
// time and stores it. The Turnaround Time is calculated as the difference
// between the completion time and arrival time, and the Waiting Time is derived
// from Turnaround Time minus Burst Time.

// Result Display: After all processes are completed, the program prints the
// results in a table format and calculates the average Turnaround and Waiting
// times.

// Example Walkthrough:
// Let's consider an example where:

// n = 3 (3 processes)
// q = 4 (quantum time)
// Processes (Arrival Time, Burst Time):

// Process 1: Arrival Time = 0, Burst Time = 5
// Process 2: Arrival Time = 2, Burst Time = 3
// Process 3: Arrival Time = 4, Burst Time = 2
// Execution:
// Input:

// Processes 1, 2, and 3 are entered.
// Arrival Time and Burst Time are stored in arrays.
// Sorting by Arrival Time: The processes are sorted by arrival time. The sorted
// processes are:

// Process 1: Arrival = 0, Burst = 5
// Process 2: Arrival = 2, Burst = 3
// Process 3: Arrival = 4, Burst = 2
// Round Robin Scheduling:

// At time st = 0, Process 1 starts (Burst Time = 5).
// Process 1 runs for 4 units (q = 4), remaining burst time = 1.
// At st = 4, Process 1 is added back to the queue with a remaining burst time
// of 1.
// At st = 4, Process 2 starts (Burst Time = 3).
// Process 2 runs for 3 units, completing at st = 7.
// At st = 7, Process 1 runs again for its remaining 1 unit, completing at st =
// 8.
// Process 3 starts at st = 8 and runs for 2 units, completing at st = 10.
// Final Results: After all processes are completed, the program computes and
// prints the Turnaround and Waiting Times for each process.

// Sample Output:
// less
// Copy code
// Enter number of processes: 3
// Enter quantum time: 4
// Enter process ID for process 1: 1
// Enter Arrival Time for process 1: 0
// Enter Burst Time for process 1: 5
// Enter process ID for process 2: 2
// Enter Arrival Time for process 2: 2
// Enter Burst Time for process 2: 3
// Enter process ID for process 3: 3
// Enter Arrival Time for process 3: 4
// Enter Burst Time for process 3: 2

// Process ID Arrival Time Burst Time Completion Time Turnaround Time Waiting
// Time
// 1 0 5 8 8 3
// 2 2 3 7 5 2
// 3 4 2 10 6 4

// Average Turnaround Time: 6.33
// Average Waiting Time: 3.0