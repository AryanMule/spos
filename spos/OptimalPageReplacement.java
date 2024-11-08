import java.util.Scanner;

public class OptimalPageReplacement {

    private static int getOptimalPageIndex(int[] buffer, int[] reference, int currentIndex) {
        int farthest = currentIndex;
        int replaceIndex = 0;

        for (int i = 0; i < buffer.length; i++) {
            int j;
            for (j = currentIndex + 1; j < reference.length; j++) {
                if (buffer[i] == reference[j]) {
                    if (j > farthest) {
                        farthest = j;
                        replaceIndex = i;
                    }
                    break;
                }
            }
            if (j == reference.length) {
                return i; // If the page is never used again, choose this index.
            }
        }
        return replaceIndex;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter reference string length:");
        int refLen = sc.nextInt();
        int[] reference = new int[refLen];
        System.out.println("Enter reference string:");
        for (int i = 0; i < refLen; i++) {
            reference[i] = sc.nextInt();
        }

        System.out.println("Enter frame size:");
        int frameLen = sc.nextInt();
        sc.close();

        int[] buffer = new int[frameLen];
        int fault = 0, hit = 0;

        for (int i = 0; i < frameLen; i++) {
            buffer[i] = -1; // Initialize buffer.
        }

        for (int i = 0; i < refLen; i++) {
            boolean isHit = false;

            // Check if the current page is in the buffer.
            for (int j = 0; j < frameLen; j++) {
                if (buffer[j] == reference[i]) {
                    hit++;
                    isHit = true;
                    break;
                }
            }

            if (!isHit) {
                int replaceIndex = getOptimalPageIndex(buffer, reference, i);
                buffer[replaceIndex] = reference[i];
                fault++;
            }

            // Print the current state of the buffer.
            for (int j = 0; j < frameLen; j++) {
                System.out.print((buffer[j] == -1 ? "-" : buffer[j]) + "\t");
            }
            System.out.println();
        }

        System.out.println("Total Page Faults: " + fault);
        System.out.println("Total Page Hits: " + hit);
    }
}


































// This Java program simulates the Optimal Page Replacement algorithm, which is
// used in operating systems to manage memory by minimizing page faults. In the
// Optimal algorithm, when a page fault occurs and a page needs to be replaced,
// it selects the page that won’t be used for the longest time in the future.

// Code Walkthrough
// 1. getOptimalPageIndex Method
// This method calculates the optimal page replacement index. Here's how it
// works:

// It iterates through each page in the buffer.
// For each page, it looks ahead in the reference string to see when it will be
// used next.
// If a page is not used again, it is selected for replacement.
// If all pages are used again in the future, the page that is used last
// (farthest in time) is selected for replacement.
// 2. Main Logic
// Input: The reference string (the sequence of page accesses) and the frame
// size (the number of pages that can be held in memory at once).
// The buffer (array representing memory) is initialized with -1 indicating that
// no page is currently in memory.
// Page hits and page faults are tracked, and after each reference, the buffer
// state is displayed.
// Code Explanation
// Input:
// Reference string: A sequence of page accesses that the program simulates.
// Frame size: The number of pages that can be stored in memory at any given
// time (i.e., the buffer size).
// Output:
// The program outputs the state of the memory buffer after processing each page
// in the reference string.
// The total number of page faults and page hits are printed at the end.
// Example Walkthrough
// Let’s take an example to walk through the program:

// Frame size: 3
// Reference string: [7, 0, 1, 2, 0, 3, 0, 4]

// Here’s a step-by-step breakdown of what happens:

// Initial Buffer: [-1, -1, -1] (All frames are empty)

// Reference 7:

// The page 7 is not in the buffer. This results in a page fault.
// The buffer becomes: [7, -1, -1]
// Page Faults: 1
// Page Hits: 0
// Reference 0:

// The page 0 is not in the buffer. This results in a page fault.
// The buffer becomes: [7, 0, -1]
// Page Faults: 2
// Page Hits: 0
// Reference 1:

// The page 1 is not in the buffer. This results in a page fault.
// The buffer becomes: [7, 0, 1]
// Page Faults: 3
// Page Hits: 0
// Reference 2:

// The page 2 is not in the buffer.
// We need to replace one of the pages in memory. According to the optimal page
// replacement algorithm, we select the page that is used farthest in the
// future.
// We look ahead in the reference string:
// Page 7 will be used next at index 4.
// Page 0 will be used next at index 5.
// Page 1 will not be used again.
// Since page 1 is not used again, we replace it with page 2.
// The buffer becomes: [7, 0, 2]
// Page Faults: 4
// Page Hits: 0
// Reference 0:

// The page 0 is already in the buffer. This is a page hit.
// The buffer remains: [7, 0, 2]
// Page Faults: 4
// Page Hits: 1
// Reference 3:

// The page 3 is not in the buffer.
// We need to replace one of the pages. Looking ahead:
// Page 7 will be used next at index 4.
// Page 0 will be used next at index 5.
// Page 2 will be used next at index 7.
// Since page 7 is used farthest in the future, it gets replaced with page 3.
// The buffer becomes: [3, 0, 2]
// Page Faults: 5
// Page Hits: 1
// Reference 0:

// The page 0 is already in the buffer. This is a page hit.
// The buffer remains: [3, 0, 2]
// Page Faults: 5
// Page Hits: 2
// Reference 4:

// The page 4 is not in the buffer.
// We need to replace one of the pages. Looking ahead:
// Page 3 will not be used again.
// Page 0 will be used next at index 5.
// Page 2 will be used next at index 7.
// Since page 3 is not used again, it gets replaced with page 4.
// The buffer becomes: [4, 0, 2]
// Page Faults: 6
// Page Hits: 2
// Final Result:
// Total Page Faults: 6
// Total Page Hits: 2
// Summary:
// Optimal Page Replacement minimizes page faults by replacing the page that
// will not be used for the longest time in the future.
// In our example, the buffer size was 3, and there were 6 page faults and 2
// page hits, demonstrating how the optimal algorithm works by predicting future
// page usage.