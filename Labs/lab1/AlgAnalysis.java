
// CSI2110 Fall 2015 Laboratory 1A: Algorithm Runtimes
// ==========================================================================
// (C)opyright:
//
//   Lachlan Plant
//   SITE, University of Ottawa
//   800 King Edward Ave.
//   Ottawa, ON, K1N 6N5
//   Canada. 
//   http://www.site.uottawa.ca
// 
// Creator: lplant (Lachlan Plant)
// Email:   lplan053@uottawa.ca
// ==========================================================================
// $Log: Lab00.java,v $
// Revision 1.0  2015/09/14 01:20:40  lplant
// Revision 1.1  2015/09/19 11:21:00  Lucia Moura
// ==========================================================================
import java.util.*;
public class AlgAnalysis {

	/**
	 * Tests runtimes of Arrays.sort()
	 * Runs experiments to find the average time taken to sort arrays of n elements
	 * prints results directly
	 *
	 * @param  maxSize size of largest array to be tested
	 * @param  count number of arrays tested.
	 */
	public static void arraySortRuntime(int count, int maxSize){
		// add your code here (part 1)
		{/*double step = ((double)maxSize/2)/(double)count;
		double size = maxSize;
		long worst = 0;
		int[] arr;
		long before, after;


		
		for (int i = 0; i < count; i++){//for each size n
			// System.out.println("Size (rounded): " + (int)size);
			worst = 0;
			// System.out.println("Size: " + size);

			for (int j = 0; j < 20; j++){
				// arr = genArray((int)size);
				arr = randomizeArray(genArray((int)size));
				// for (int l = 0; l < (int)size; l++){
				// 	System.out.print(arr[l] + " ");
				// }
				// System.out.println();
				

				//sort and record time
				before = System.nanoTime();
				Arrays.sort(arr);
				after = System.nanoTime();
				// for (int l = 0; l < (int)size; l++){
				// 	System.out.print(arr[l] + " ");
				// }
				// System.out.println();
				if (!(i == 0  && j == 0)) {
					if (worst < after - before)
					worst = after - before;
				}
				// System.out.println("Time: " +(after-before)+ "ns\n");
			}
			System.out.println("worst for size " + (int)size + ": " + worst);
			System.out.println("n: " + (int)size + ", T(n): " + worst + ", T(n)/n^2: "
				+ worst + " / " + Math.pow((int)size, 2) + " : " + (worst / Math.pow((int)size, 2) ) + ", T(n)/(n * log(n)): "+ worst + "/ " + (((int)size)* (Math.log((int)size)/Math.log(2))) + " : " + worst / (((int)size)* (Math.log((int)size)/Math.log(2))) );
			size -= step;
		}*/}
	int small = 4; 
	long worst = 0;
	long before, after;
    int[] sizes = new int[count];
    int[] arr;
    double step = (double)(maxSize - small) / (count - 1);
    for (int i = 0; i < count; i++) {	//make array of sizes
        sizes[i] = (int)Math.round(small + i * step);
    }


    for (int i = 0; i < count; i++){
    	// System.out.println(" size: "+sizes[i] + " ");
    	worst = 0;
    	for (int j = 0; j < 10; j ++){
    		// worst =0;
    		arr = genArray(sizes[i]);//make and randomize arrays
    		arr = randomizeArray(arr);

    		//begin timing the sort
    		before = System.nanoTime();
    		Arrays.sort(arr);
    		after = System.nanoTime();

    		//check if worst
    		if (!(i == 0 && j == 0)) {//had to ignore first measure (outlier)
    			if (after - before > worst){
    				worst = after - before;
    			}
    		}
    		// System.out.print("   time taken: " + (after - before) + " ns");
    		// System.out.println();
    	}
    	// System.out.println("worst for size " + sizes[i] + ": " + worst + " ns");

    	//printing stats
    	System.out.println("n: "+ sizes[i] + ", T(n): " +  worst + ", T(n)/(n^2): " + (double)worst/(sizes[i] * sizes[i]) + ", T(n)/(n*log(n)): " +  
    		 (worst)/(sizes[i] * (Math.log(sizes[i])/Math.log(2) ) ) );
    }


	}




	
	/**
	 * creates an array of size n, then tests the runtime of findDups1 using that array
	 *
	 * @param  n size of array
	 * @return time taken in nano seconds
	 */
	public static long unique1Runtime(int n){
		// add your code here (part 2)
		int[] arr = new int[n];
		long before, after = 0;
		boolean hasDup;
		for (int i = 0; i < n; i++){//generate array with elems 0 to n-1
			arr[i] = i;
		}

		//time the method
		before = System.nanoTime();
		hasDup = Unique1.unique1(arr);
		after = System.nanoTime();
		return (after - before);
	}
	
	/**
	 * creates an array of size n, then tests the runtime of findDups2 using that array
	 *
	 * @param  n size of array
	 * @return time taken in nano seconds
	 */
	public static long unique2Runtime(int n){
		// add your code here (part 2)
		long before, after = 0;
		boolean hasDup;
		int[] arr = genArray(n);
		arr = randomizeArray(arr);

		//time method
		before = System.nanoTime();
		hasDup = Unique2.unique2(arr);
		after = System.nanoTime();
		

		return (after - before);
	}
	
	/**
	 * Generates an array of ins of size n
	 * Array contains values array[i]=i
	 *
	 * @param  n size of array
	 * @return ordered array
	 */
	private static int[] genArray(int n){
		int[] ret = new int[n];
		for(int i=0; i < ret.length; i++){
			ret[i] = i;
		}
		return ret;
	}
	
	/**
	 * Randomly shuffles an array
	 *
	 * @param  array array of ints to be shuffled
	 * @return      randomized array
	 */
	private static int[] randomizeArray(int[] array){
		Random rng = new Random();  		
 
		for (int i=array.length -1; i > 0; i--) {
			//rng.nextInt(N) returns random number between 0 and N-1 inclusive
			int randomPosition = rng.nextInt(i);
		    int temp = array[i];
		    array[i] = array[randomPosition];
		    array[randomPosition] = temp;
		}
 
		return array;
	}	
	
	/**
	 * Convert time in nanoseconds to seconds
	 *
	 * @param  time time in nanoseconds
	 * @return      time in seconds as a double
	 */
	private static double nanoToSeconds(long time){
		return (double)time / 1000000000.0;
	}

	/**
	 * Main method
	 * Provides prompts for all experiments
	 */
	public static void main(String[] args){
		Scanner scanner = new Scanner(System.in);
		while(true){
			System.out.println("Enter 0 to test Arrays.sort(), 1 for unique, any other number to exit");
			int opt1 = scanner.nextInt();
			if(opt1 == 0){
				System.out.println("Enter number of arrays to test");
				int count = scanner.nextInt();
				System.out.println("Enter largest array size");
				int n = scanner.nextInt();
				AlgAnalysis.arraySortRuntime(count, n);			
				System.out.println();
			}
			else if(opt1 == 1){
				while(true){
					System.out.println("Enter 1 to test unique1, 2 for unique2, any other number to exit");
					int opt2 = scanner.nextInt();
					if(opt2 == 1){
						System.out.println("Enter n value");
						int n = scanner.nextInt();
						System.out.println("Time Elapsed: "+AlgAnalysis.nanoToSeconds(AlgAnalysis.unique1Runtime(n))+" secs");
						System.out.println();
					}
					else if(opt2 == 2){
						System.out.println("Enter n value");
						int n = scanner.nextInt();
						System.out.println("Time Elapsed: "+AlgAnalysis.nanoToSeconds(AlgAnalysis.unique2Runtime(n))+" secs");
						System.out.println();
					}
					else{
						System.out.println("End of Program!");
						return;
					}
				}
			}
			else {
				System.out.println("End of Program!");
				return;
			}
			
		}
	}

}