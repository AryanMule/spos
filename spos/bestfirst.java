import java.util.Scanner;

public class bestfirst {
    // Function to implement First Fit Memory Allocation
    public static void firstFit(int[] memory, int[] processes) {
        System.out.println("First Fit Allocation:");
        for (int i = 0; i < processes.length; i++) {
            boolean allocated = false;
            
            for (int j = 0; j < memory.length; j++) {
                if (memory[j] >= processes[i]) {
                    System.out.println("Process " + (i + 1) + " of size " + processes[i] + " allocated to memory block "
                            + (j + 1));
                    memory[j] -= processes[i]; // reduce memory block size
                    allocated = true;
                    break;
                }
            }
            if (!allocated) {
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

        // Call First Fit and Best Fit
        firstFit(memory.clone(), processes); // Clone memory to avoid modifying it for Best Fit
        bestFit(memory.clone(), processes);

        sc.close();
    }
}

// This Java code demonstrates two memory allocation strategies: First Fit and
// Best Fit. It allocates memory blocks to processes based on these strategies,
// helping understand different approaches to memory management in operating
// systems.

// firstFit
// Purpose: This function allocates memory to processes using the First Fit
// method, which allocates each process to the first memory block large enough
// to hold it.
// Process:
// For each process, it looks for the first memory block that can accommodate
// the process size.
// Once it finds a suitable block, it allocates that block to the process,
// reduces the block size by the process size, and stops searching further for
// that process.
// If no suitable block is found, it outputs that the process could not be
// allocated.

// bestFit
// Purpose: This function allocates memory using the Best Fit method, which
// allocates each process to the smallest memory block that can accommodate it.
// Process:
// For each process, it searches for the memory block that leaves the smallest
// amount of unused space after allocation.
// If it finds a suitable block, it allocates the block to the process and
// reduces the block size accordingly.
// If no block can fit the process, it outputs that the process could not be
// allocated.