import java.util.*;

class Process {
    int PID, AT, BT, CT, TAT, WT;

    // Constructor to initialize process details
    public Process(int PID, int AT, int BT) {
        this.PID = PID;
        this.AT = AT;
        this.BT = BT;
    }
}

public class fcfs {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of processes: ");
        int n = sc.nextInt();

        Process[] processes = new Process[n];

        // Input process details
        for (int i = 0; i < n; i++) {
            System.out.print("Enter Process ID: ");
            int PID = sc.nextInt();
            System.out.print("Enter Arrival Time: ");
            int AT = sc.nextInt();
            System.out.print("Enter Burst Time: ");
            int BT = sc.nextInt();
            processes[i] = new Process(PID, AT, BT);
        }

        // Sort processes by Arrival Time
        Arrays.sort(processes, Comparator.comparingInt(p -> p.AT));

        // Calculate Completion Time (CT), Turnaround Time (TAT), and Waiting Time (WT)
        int currentTime = 0;
        for (Process p : processes) {
            // Set Completion Time based on Arrival Time and current time
            if (p.AT > currentTime) {
                currentTime = p.AT;
            }
            p.CT = currentTime + p.BT;
            p.TAT = p.CT - p.AT; // Turnaround Time
            p.WT = p.TAT - p.BT; // Waiting Time
            currentTime = p.CT;
        }

        // Output process details
        System.out.println("\nPID\tAT\tBT\tCT\tTAT\tWT");
        for (Process p : processes) {
            System.out.println(p.PID + "\t" + p.AT + "\t" + p.BT + "\t" + p.CT + "\t" + p.TAT + "\t" + p.WT);
        }
        // Calculate and display average Turnaround Time and Waiting Time
        int totalTAT = 0, totalWT = 0;
        for (Process p : processes) {
            totalTAT += p.TAT;
            totalWT += p.WT;
        }
        System.out.printf("\nAverage Turnaround Time: %.2f\n", (float) totalTAT / n);
        System.out.printf("Average Waiting Time: %.2f\n", (float) totalWT / n);
        sc.close();
    }
}



















// This code demonstrates the First-Come, First-Served (FCFS) scheduling
// algorithm, a basic CPU scheduling technique that allocates CPU resources to
// processes based on their arrival times. Here’s a detailed explanation with
// concepts and a step-by-step breakdown of the code, including an example for
// better understanding.

// Concepts and Key Terms
// Arrival Time (AT): The time at which a process enters the system.
// Burst Time (BT): The time required by a process for execution on the CPU.
// Completion Time (CT): The time at which a process finishes execution.
// Turnaround Time (TAT): The total time taken by a process from arrival to
// completion. It’s calculated as:
// TAT
// =
// CT
// −
// AT
// TAT=CT−AT
// Waiting Time (WT): The time a process spends waiting in the queue for its
// turn. It’s calculated as:
// WT
// =
// TAT
// −
// BT
// WT=TAT−BT
// FCFS Algorithm Explanation
// In FCFS, processes are executed in the order of their Arrival Time. Once a
// process starts executing, it continues until completion. No process can
// interrupt another in FCFS, making it a non-preemptive scheduling algorithm.

// Step-by-Step Explanation of the Code
// Define Process Class:

// The Process class holds information about each process, including its Process
// ID (PID), Arrival Time (AT), Burst Time (BT), Completion Time (CT),
// Turnaround Time (TAT), and Waiting Time (WT).
// Input Process Details:

// The user is prompted to enter the number of processes.
// For each process, the user enters the PID, AT, and BT, which are stored in an
// array of Process objects.
// Sort Processes by Arrival Time:

// The processes are sorted based on AT to ensure they are processed in the
// order they arrive.
// Calculate CT, TAT, and WT:

// The program iterates through each process to compute CT, TAT, and WT.
// CT is calculated by adding the BT of the process to currentTime. If the AT of
// a process is greater than currentTime, currentTime is updated to the
// process’s AT.
// TAT is calculated as CT - AT.
// WT is calculated as TAT - BT.
// Display Results:

// The program prints out PID, AT, BT, CT, TAT, and WT for each process.
// It also calculates the average TAT and WT across all processes.
// Code Walkthrough with Example
// Consider an example with three processes:

// Process 1: PID=1, AT=0, BT=5
// Process 2: PID=2, AT=1, BT=3
// Process 3: PID=3, AT=2, BT=8
// Execution Steps
// Input:

// yaml
// Copy code
// Number of processes: 3
// Process 1: AT=0, BT=5
// Process 2: AT=1, BT=3
// Process 3: AT=2, BT=8
// Sorting by Arrival Time:

// The processes are already sorted as per the given arrival times (0, 1, and
// 2).
// Calculating Times for Each Process:

// Process 1:
// Starts at AT=0, so currentTime=0.
// CT = currentTime + BT = 0 + 5 = 5.
// TAT = CT - AT = 5 - 0 = 5.
// WT = TAT - BT = 5 - 5 = 0.
// Update currentTime to 5.
// Process 2:
// currentTime=5, and AT=1 (already passed).
// CT = currentTime + BT = 5 + 3 = 8.
// TAT = CT - AT = 8 - 1 = 7.
// WT = TAT - BT = 7 - 3 = 4.
// Update currentTime to 8.
// Process 3:
// currentTime=8, and AT=2 (already passed).
// CT = currentTime + BT = 8 + 8 = 16.
// TAT = CT - AT = 16 - 2 = 14.
// WT = TAT - BT = 14 - 8 = 6.
// Update currentTime to 16.
// Output:

// Copy code
// PID AT BT CT TAT WT
// 1 0 5 5 5 0
// 2 1 3 8 7 4
// 3 2 8 16 14 6
// Average TAT and WT:

// Total TAT = 5 + 7 + 14 = 26
// Total WT = 0 + 4 + 6 = 10
// Average TAT = 26 / 3 ≈ 8.67
// Average WT = 10 / 3 ≈ 3.33