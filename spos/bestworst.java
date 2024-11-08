import java.util.Scanner;

public class bestworst {
    // Function to implement Worst Fit Memory Allocation
    public static void worstFit(int[] memory, int[] processes) {
        System.out.println("Worst Fit Allocation:");
        for (int i = 0; i < processes.length; i++) {
            int worstIndex = -1;
            int maxDiff = -1;
            for (int j = 0; j < memory.length; j++) {
                if (memory[j] >= processes[i] && (memory[j] - processes[i] > maxDiff)) {
                    maxDiff = memory[j] - processes[i];
                    worstIndex = j;
                }
            }
            if (worstIndex != -1) {
                System.out.println("Process " + (i + 1) + " of size " + processes[i] + " allocated to memory block "
                        + (worstIndex + 1));
                memory[worstIndex] -= processes[i]; // reduce memory block size
            } else {
                System.out.println("Process " + (i + 1) + " could not be allocated.");
            }
        }
    }

    // Function to implement Best Fit Memory Allocation
    public static void bestFit(int[] memory, int[] processes) {
        System.out.println("Best Fit Allocation:");
        for (int i = 0; i < processes.length; i++) {
            int bestIndex = -1;
            int minDiff = Integer.MAX_VALUE;
            for (int j = 0; j < memory.length; j++) {
                if (memory[j] >= processes[i] && (memory[j] - processes[i] < minDiff)) {
                    minDiff = memory[j] - processes[i];
                    bestIndex = j;
                }
            }
            if (bestIndex != -1) {
                System.out.println("Process " + (i + 1) + " of size " + processes[i] + " allocated to memory block "
                        + (bestIndex + 1));
                memory[bestIndex] -= processes[i]; // reduce memory block size
            } else {
                System.out.println("Process " + (i + 1) + " could not be allocated.");
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input memory blocks
        System.out.print("Enter number of memory blocks: ");
        int n = sc.nextInt();
        int[] memory = new int[n];
        System.out.println("Enter memory block sizes: ");
        for (int i = 0; i < n; i++) {
            memory[i] = sc.nextInt();
        }

        // Input processes
        System.out.print("Enter number of processes: ");
        int m = sc.nextInt();
        int[] processes = new int[m];
        System.out.println("Enter process sizes: ");
        for (int i = 0; i < m; i++) {
            processes[i] = sc.nextInt();
        }

        // Call Worst Fit and Best Fit
        worstFit(memory.clone(), processes); // Clone memory to avoid modifying it for Best Fit
        bestFit(memory.clone(), processes);

        sc.close();
    }
}

// Worst Fit Allocation
// In Worst Fit, the algorithm assigns a process to the largest available memory
// block that can accommodate the process size. This strategy aims to leave
// smaller fragments of memory for future processes, hoping it will improve
// memory utilization.

// Here's how it works:

// Initialize Variables:
// worstIndex: Stores the index of the largest suitable memory block for the
// current process.
// maxDiff: Stores the difference between the memory block size and the process
// size (to find the largest block).
// Loop through Processes:
// For each process, check each memory block.
// If a block is large enough for the process and has a larger size difference
// than maxDiff, update worstIndex and maxDiff.
// Allocate Memory:
// If a suitable block (worstIndex) is found, allocate it by subtracting the
// process size from the block size.
// If no suitable block is found, display that the process could not be
// allocated.

// Best Fit Allocation
// In Best Fit, the algorithm assigns a process to the smallest memory block that can fit the process. This strategy minimizes wasted space by leaving the smallest unused portions in memory.

// Here's how it works:

// Initialize Variables:
// bestIndex: Stores the index of the smallest suitable memory block.
// minDiff: Stores the minimum difference between the block size and process size.
// Loop through Processes:
// For each process, check each memory block.
// If a block is large enough and has a smaller size difference than minDiff, update bestIndex and minDiff.
// Allocate Memory:
// If a suitable block is found (bestIndex), allocate it by subtracting the process size from the block size.
// If no suitable block is found, display that the process could not be allocated.