public class TowerOfHanoi {

    public static long moveCounter = 0;

    /**
     * Recursive Tower of Hanoi solver.
     * @param n number of disks
     * @param source starting rod
     * @param target destination rod
     * @param auxiliary temporary rod
     * @param verbose if true, prints each move
     *
     * TODO:
     * - Implement the recursive algorithm to move 'n' disks from 'source' to 'target'
     *   using 'auxiliary' as a temporary holder.
     * - You must follow the recursive structure:
     *     1. Move n - 1 disks from source to auxiliary.
     *     2. Move the largest disk from source to target.
     *     3. Move n - 1 disks from auxiliary to target.
     * - If 'verbose' is true, print each move in the format:
     *     Move disk X from A to C
     * - Increment moveCounter after every disk move.
     */
    public static void solveHanoi(int n, char source, char target, char auxiliary, boolean verbose) {
        // TODO: Your recursive implementation goes here.
        // if (moveCounter == 0){  //will fail on base case when n within funciton where n becomes 1
        //     if (n >= 10)
        //         verbose = false;
        //     else
        //         verbose = true;
        // }
        
        //base case: only 1 disk on rod
        if ((n >= 10 && moveCounter == 0)) {
           verbose = false;
        }

        if (n == 1){    
            //move to target???
            if (verbose)        //n + moveCounter ???
                System.out.println("Move disk " + (n) + " from " + source + " to " + target);
            //System.out.println("Move disk " + (n) + " from " + source + " to " + auxiliary);
            moveCounter ++;
            return;
        }
        solveHanoi(n-1, source, auxiliary, target, verbose);
        if (verbose)
            System.out.println("Move disk " + (n) + " from " + source + " to " + target);
        //System.out.println("Move disk " + (n) + " from " + source + " to " + auxiliary);
        moveCounter++;
        solveHanoi(n-1, auxiliary, target, source, verbose);


    }
}
