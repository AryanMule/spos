import java.util.Scanner;

public class LRU {
    
    private static int getLRUPageIndex(int[] buffer, int[] reference, int currentIndex) {
        int lruIndex = 0;
        int leastRecentlyUsed = currentIndex;

        // Find the least recently used page by scanning all buffer pages 
        for (int i = 0; i < buffer.length; i++) {
            boolean found = false;

            // Check all past references for the current page in the buffer
            for (int j = currentIndex - 1; j >= 0; j--) {
                if (buffer[i] == reference[j]) {
                    found = true;
                    if (j < leastRecentlyUsed) {
                        leastRecentlyUsed = j;
                        lruIndex = i;
                    }
                    break;
                }
            }

            // If a page is not found in the past references, it is the LRU and should be replaced
            if (!found) {
                return i;
            }
        }

        return lruIndex; 
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input frame and reference lengths
        System.out.print("Enter the number of frames: ");
        int frame_len = scanner.nextInt();

        System.out.print("Enter the number of references: ");
        int ref_len = scanner.nextInt();

        int[] reference = new int[ref_len];
        System.out.println("Enter the reference string:");
        for (int i = 0; i < ref_len; i++) {
            reference[i] = scanner.nextInt();
        }

        int fault = 0, hit = 0;

        // Initialize the buffer with -1 (empty slots)
        int[] buffer = new int[frame_len];
        for (int i = 0; i < frame_len; i++) {
            buffer[i] = -1;
        }

        // Track the state of the buffer after each reference
        int[][] matrix = new int[ref_len][frame_len];

        // Process the reference string
        for (int i = 0; i < ref_len; i++) {
            boolean found = false;

            // Check if the page is already in the buffer (hit)
            for (int j = 0; j < frame_len; j++) {
                if (buffer[j] == reference[i]) {
                    hit++;
                    found = true;
                    break;
                }
            }

            // If the page is not in the buffer (fault), replace the LRU page
            if (!found) {
                int replaceIndex = getLRUPageIndex(buffer, reference, i);
                buffer[replaceIndex] = reference[i];
                fault++;
            }

            // Save the current state of the buffer for display
            System.arraycopy(buffer, 0, matrix[i], 0, frame_len);
        }

        // Display the page frames after each reference
        System.out.println("Page frames after each reference:");
        for (int i = 0; i < ref_len; i++) {
            for (int j = 0; j < frame_len; j++) {
                System.out.print((matrix[i][j] == -1 ? "-" : matrix[i][j]) + "\t");
            }
            System.out.println();
        }

        // Display the total page faults and hits
        System.out.println("Page faults: " + fault + "\tPage hits: " + hit);

        scanner.close();
    }
}















// Here's an explanation of the Least Recently Used (LRU) page replacement algorithm in Java, which simulates how an operating system manages page frames in memory by using the LRU strategy. This algorithm replaces the page that has not been used for the longest time.

// Code Explanation
// Getting the LRU Page Index

// The getLRUPageIndex function determines which page in the buffer is the least recently used.
// It scans each page in the buffer and, for each page, searches backward through the reference string from the current index to find the last access time.
// If a page in the buffer is not found in any past references (meaning it was never used), it is automatically considered the least recently used.
// The index of the page to be replaced is returned.
// Input Frame and Reference Lengths

// The program prompts the user for:
// frame_len: The number of frames available in memory.
// ref_len: The total number of page references in the reference string.
// The reference string itself is then taken as input, representing the sequence of page accesses.
// Initialize Buffer and Track Hits and Faults

// An array buffer is initialized with -1, indicating empty frame slots.
// Variables fault and hit are used to keep track of the number of page faults and hits, respectively.
// A matrix array is used to store the state of the buffer after each reference for display purposes.
// Process Each Page in the Reference String

// For each page in the reference string:
// Check if the Page is Already in Buffer (Hit):
// If the page is already in the buffer, it is a "hit," and no replacement is needed. The hit counter is incremented.
// Page Replacement (LRU):
// If the page is not found in the buffer (fault), the getLRUPageIndex function determines which page to replace.
// The selected page in the buffer is replaced with the current page, and the fault counter is incremented.
// The buffer's state is saved in matrix after processing each page for later display.
// Display Buffer State

// After processing all references, the program displays the state of the page frames after each page reference in a tabular format.
// Display Total Page Faults and Hits

// Finally, the total number of page faults and hits is printed.