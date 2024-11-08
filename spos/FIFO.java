import java.util.*;

public class FIFO {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input the reference string and frame size
        System.out.print("Enter reference string size: ");
        int refLength = sc.nextInt();
        System.out.print("Enter frame size: ");
        int frameSize = sc.nextInt();

        // Arrays for buffer and reference string
        int[] buffer = new int[frameSize];
        int[] reference = new int[refLength];
        Arrays.fill(buffer, -1); // Initialize buffer with -1 (empty frames)

        // Input the reference string
        System.out.print("Enter reference string: ");
        for (int i = 0; i < refLength; i++) {
            reference[i] = sc.nextInt();
        }

        int hit = 0, fault = 0, pointer = 0;

        // Process each page reference
        for (int i = 0; i < refLength; i++) {
            boolean pageFound = false;

            // Check if the current page is already in buffer (hit)
            for (int j = 0; j < frameSize; j++) {
                if (buffer[j] == reference[i]) {
                    hit++;
                    pageFound = true;
                    break;
                }
            }

            // If page is not found, replace the oldest page (FIFO)
            if (!pageFound) {
                buffer[pointer] = reference[i];
                pointer = (pointer + 1) % frameSize; // Circular pointer for FIFO
                fault++;
            }

            // Display current buffer state (memory layout)
            System.out.print("Frame: ");
            for (int j = 0; j < frameSize; j++) {
                System.out.print((buffer[j] == -1 ? "-1" : buffer[j]) + " ");
            }
            System.out.println();
        }

        // Output results
        System.out.println("\nTotal Hits: " + hit);
        System.out.println("Total Faults: " + fault);

        sc.close();
    }
}







// This Java program simulates the First-In-First-Out (FIFO) page replacement
// algorithm, which is used in operating systems to manage the page frame
// buffer. FIFO replaces the oldest page when a new page needs to be loaded into
// a full frame.

// Step-by-Step Walkthrough
// Input Reference String and Frame Size

// The program starts by asking the user for the size of the reference string
// (total pages to be accessed) and the frame size (number of frames available
// in memory).
// A reference string is a sequence of page numbers that represent memory pages
// accessed in order.
// Initialize Buffers and Arrays

// An array buffer of size frameSize is initialized with -1, which indicates
// empty frames.
// An array reference of size refLength is used to store the reference string
// inputted by the user.
// Input the Reference String

// The program takes user input to fill the reference array with page numbers
// (pages to be accessed).
// Track Hits and Faults

// hit keeps track of how many times a page is found in the buffer (a page hit).
// fault counts the number of page faults, which occur when a page needs to be
// loaded into the buffer because it's not already present.
// pointer is used to point to the next frame to replace, implementing FIFO's
// "replace oldest page first" behavior.
// Process Each Page in the Reference String

// For each page in the reference string:
// Check if Page is Already in Buffer:
// The program iterates through the buffer to check if the current page is
// already loaded (page hit). If it is, hit is incremented, and the program
// moves to the next page.
// Page Replacement (FIFO):
// If the page is not found in the buffer (page fault), the oldest page is
// replaced with the new page:
// The pointer variable points to the position in the buffer to replace (FIFO
// replacement).
// The pointer moves circularly using (pointer + 1) % frameSize, so it resets to
// 0 when reaching the end of the buffer.
// Increment Fault Counter: When a page fault occurs, fault is incremented.
// Display Buffer State after Each Page Access

// After each page access, the current state of the buffer is printed, showing
// the pages currently loaded in memory.
// Final Results: Total Hits and Faults

// After all pages in the reference string are processed, the total number of
// hits and faults are displayed.