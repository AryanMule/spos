import java.util.Scanner;
import java.util.Vector;

public class Deadlock {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of processes:");
        int n = sc.nextInt();
        System.out.println("Enter number of resource instances:");
        int k = sc.nextInt();
        
        int[][] maxMatrix = new int[n][k];
        int[][] allocation = new int[n][k];
        int[][] need = new int[n][k];
        int[] available = new int[k];
        boolean[] finish = new boolean[n];
        Vector<Integer> safeSequence = new Vector<>();

        // Initialize finish array to false
        for (int i = 0; i < n; i++) {
            finish[i] = false;
        }

        // Input maximum resources required by each process
        System.out.println("Enter max matrix:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < k; j++) {
                maxMatrix[i][j] = sc.nextInt();
            }
        }

        // Input currently allocated resources for each process
        System.out.println("Enter allocation matrix:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < k; j++) {
                allocation[i][j] = sc.nextInt();
            }
        }

        // Input available resources
        System.out.println("Enter available resources:");
        for (int j = 0; j < k; j++) {
            available[j] = sc.nextInt();
        }

        // Calculate the need matrix
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < k; j++) {
                need[i][j] = maxMatrix[i][j] - allocation[i][j];
            }
        }

        // Print matrices
        System.out.println("Max Matrix:");
        printMatrix(maxMatrix, n, k);
        System.out.println("Allocation Matrix:");
        printMatrix(allocation, n, k);
        System.out.println("Need Matrix:");
        printMatrix(need, n, k);

        // Banker's Algorithm for finding the safe sequence
        int total = 0;
        while (total < n) {
            boolean progress = false;
            for (int i = 0; i < n; i++) {
                if (!finish[i]) {
                    boolean canProceed = true;
                    for (int j = 0; j < k; j++) {
                        if (need[i][j] > available[j]) {
                            canProceed = false;
                            break;
                        }
                    }

                    if (canProceed) {
                        // Process can finish, release resources
                        for (int j = 0; j < k; j++) {
                            available[j] += allocation[i][j];
                        }
                        finish[i] = true;
                        total++;
                        safeSequence.add(i);
                        progress = true;
                    }
                }
            }
            if (!progress) {
                System.out.println("System is in an unsafe state, no safe sequence found.");
                return;
            }
        }

        // Print the safe sequence
        System.out.println("Safe sequence is:");
        for (Integer process : safeSequence) {
            System.out.print("P" + process + " ");
        }
    }

    // Method to print a matrix
    public static void printMatrix(int[][] matrix, int rows, int cols) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}
