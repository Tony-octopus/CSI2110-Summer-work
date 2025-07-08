import java.util.Scanner;

public class RecursionTester {

    /**
     * Runs experiments comparing recursive and iterative Fibonacci runtimes.
     */
    public static void runFibonacciExperiment(int maxN) {
        System.out.printf("%-4s %-20s %-20s %-18s %-18s%n",
            "n", "RecursiveTime(ns)", "IterativeTime(ns)", "RecursiveFib(n)", "IterativeFib(n)");

        for (int n = 1; n <= maxN; n++) {
            long start, end;

            start = System.nanoTime();
            long fibRec = Fibonacci.recursiveFib(n);
            end = System.nanoTime();
            long recursiveTime = end - start;

            start = System.nanoTime();
            long fibIter = Fibonacci.iterativeFib(n);
            end = System.nanoTime();
            long iterativeTime = end - start;

            System.out.printf("%-4d %-20d %-20d %-18d %-18d%n",
                n, recursiveTime, iterativeTime, fibRec, fibIter);
        }
    }

    /**
     * Runs Tower of Hanoi for user-specified disk count.
     */
    public static void runHanoiExperiment(Scanner scanner) {
        System.out.println("Enter the number of disks (n):");
        int n = scanner.nextInt();

        boolean verbose = (n <= 10);
        TowerOfHanoi.moveCounter = 0;

        System.out.println("Solving Tower of Hanoi with " + n + " disks...");
        long start = System.nanoTime();
        TowerOfHanoi.solveHanoi(n, 'A', 'C', 'B', verbose);
        long end = System.nanoTime();

        long expectedMoves = (1L << n) - 1;
        long actualMoves = TowerOfHanoi.moveCounter;
        long elapsedTime = end - start;

        System.out.println("Expected Moves: " + expectedMoves);
        System.out.println("Actual Moves:   " + actualMoves);
        System.out.println("Time Taken:     " + elapsedTime + " ns");
        System.out.println("----------------------------------------");
    }

    /**
     * Main menu for selecting experiments.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Choose an experiment to run:");
            System.out.println("0: Test Fibonacci runtimes");
            System.out.println("1: Test Tower of Hanoi");
            System.out.println("Any other number to exit.");

            int option = scanner.nextInt();

            if (option == 0) {
                System.out.println("Enter the maximum value of n for Fibonacci:");
                int maxN = scanner.nextInt();
                runFibonacciExperiment(maxN);
                System.out.println();

            } else if (option == 1) {
                runHanoiExperiment(scanner);
                System.out.println();

            } else {
                System.out.println("End of Program!");
                break;
            }
        }
    }
}
