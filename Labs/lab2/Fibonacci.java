public class Fibonacci {

    /**
     * Computes the nth Fibonacci number using recursion.
     *
     * @param n the index of the Fibonacci number
     * @return the nth Fibonacci number
     */
    public static long recursiveFib(int n) {
        // TODO: Implement recursive Fibonacci
        //base cases
        if (n == 0)
            return 0;
        if (n == 1)
            return 1;

        else{
            return (recursiveFib(n-1) + recursiveFib(n-2)  );
        }

        // return 0; // placeholder
    }

    /**
     * Computes the nth Fibonacci number using iteration.
     *
     * @param n the index of the Fibonacci number
     * @return the nth Fibonacci number
     */
    public static long iterativeFib(int n) {
        // TODO: Implement iterative Fibonacci
        long n1 = 0;
        long n2 = 1;
        long sum = 0; 
        if (n == 0){return 0;}
        if (n == 1){return 1;}
        for (int i = 1; i < n; i++){
            sum = n1 + n2;
            n1 = n2;
            n2 = sum;
        }
        return sum; // placeholder
    }

    /*public static void main(String[] args){
        System.out.println("iterativeFib(0) : " + iterativeFib(0));
        System.out.println("iterativeFib(1) : " + iterativeFib(1));
        System.out.println("iterativeFib(2) : " + iterativeFib(2));
        System.out.println("iterativeFib(3) : " + iterativeFib(3));
        System.out.println("iterativeFib(4) : " + iterativeFib(4));
        System.out.println("iterativeFib(5) : " + iterativeFib(5));
        System.out.println("iterativeFib(6) : " + iterativeFib(6));
        System.out.println("iterativeFib(7) : " + iterativeFib(7));
        System.out.println("iterativeFib(8) : " + iterativeFib(8));
        System.out.println("iterativeFib(9) : " + iterativeFib(9));

        System.out.println();
        System.out.println("resursiveFib(0) : " + recursiveFib(0));
        System.out.println("resursiveFib(1) : " + recursiveFib(1));
        System.out.println("resursiveFib(2) : " + recursiveFib(2));
        System.out.println("resursiveFib(3) : " + recursiveFib(3));
        System.out.println("resursiveFib(4) : " + recursiveFib(4));
        System.out.println("resursiveFib(5) : " + recursiveFib(5));
        System.out.println("resursiveFib(6) : " + recursiveFib(6));
        System.out.println("resursiveFib(7) : " + recursiveFib(7));
        System.out.println("resursiveFib(8) : " + recursiveFib(8));
        System.out.println("resursiveFib(9) : " + recursiveFib(9));
    }
    */
}
