import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class ReaderWriter {
    static Semaphore mutex = new Semaphore(1); // Controls access to readCount
    static Semaphore wrt = new Semaphore(1); // Controls write access to the shared resource
    static int readCount = 0;
    static String message = "Hello";

    static class Reader implements Runnable {
        public void run() {
            try {
                mutex.acquire();
                readCount++;
                if (readCount == 1) {
                    // First reader acquires write lock to block writers
                    wrt.acquire();
                }
                mutex.release();

                // Simulating reading process
                System.out.println("Thread " + Thread.currentThread().getName() + " is reading: " + message);
                Thread.sleep(1500);
                System.out.println("Thread " + Thread.currentThread().getName() + " has finished reading");

                mutex.acquire();
                readCount--;
                if (readCount == 0) {
                    // Last reader releases the write lock to allow writers
                    wrt.release();
                }
                mutex.release();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    static class Writer implements Runnable {
        public void run() {
            try {
                wrt.acquire(); // Acquires write lock
                message = "Good Morning"; // Writing to the shared resource
                System.out.println("Thread " + Thread.currentThread().getName() + " is writing");
                Thread.sleep(1500);
                System.out.println("Thread " + Thread.currentThread().getName() + " has finished writing");
                wrt.release(); // Releases write lock
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Reader read = new Reader();
        Writer write = new Writer();

        // Creating reader and writer threads
        Thread r1 = new Thread(read);
        r1.setName("Reader1");
        Thread r2 = new Thread(read);
        r2.setName("Reader2");
        Thread r3 = new Thread(read);
        r3.setName("Reader3");
        Thread w1 = new Thread(write);
        w1.setName("Writer1");
        Thread w2 = new Thread(write);
        w2.setName("Writer2");
        Thread w3 = new Thread(write);
        w3.setName("Writer3");

        // Starting threads
        w1.start();
        r1.start();
        w2.start();
        r2.start();
        w3.start();
        r3.start();
    }
}
