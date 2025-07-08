public class TryStack2 {

    // Change this file as much or as little as you need to.


      protected Integer[] array;
      protected Stack<Integer> stack;

      /**
       * Default constructor.
       */
      public TryStack2() {
        setArray(50);
      }

      public TryStack2(int n) {
        setArray(n);
      }

      public TryStack2(int n, ArrayStack<Integer> stack) {
        setArray(n);
        this.stack = stack;
      }

      /**
       * Gets the array.
       * 
       * @return
       */
      public Integer[] getArray() {
        return this.array;
      }
    public void setArray(int n) {
    // creates an array with 50 indices
        this.array = new Integer[n];

    // populates array from 0 to n-1 with i*2
        for (int i = 0; i < n; i++) {
        getArray()[i] = i * 2;
        }
    }
    public Stack<Integer> getStack() {
        return this.stack;
    }

    public void setStack(int n) {
        stack = new LinkedStack();
    }

    protected void reverseArray() {

        for (int i = 0; i < getArray().length; i++)
          this.stack.push(getArray()[i]);
        System.out.println("\nInspecting stack (top..bottom): " + this.stack);// testing

        for (int i = 0; i < getArray().length; i++) {
          getArray()[i] = this.stack.pop();
        }
    }

    protected void printArray() {
        System.out.println();

        for (int elems : getArray()) {
          System.out.print(elems + "\t");
        }
        System.out.println();

    }

    protected void runSimulation() {
        printArray();
        reverseArray();
        printArray();
    }

    /**
     * Main driver method that will reverse an array.
     *
     * @param args
     */
    public static void main(String[] args) {

    int n = args.length > 0 ? Integer.valueOf(args[0]) : 50;

    TryStack1 tryStack = new TryStack1(n, new ArrayStack<>(n));
    tryStack.runSimulation();
    }
}
