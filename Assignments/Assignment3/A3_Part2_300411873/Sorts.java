import java.util.*;
public class Sorts{
	
	public static int[] mergeSort(int[] arr){
        if (arr.length <= 1) return arr;		//base cas

        int mid = arr.length / 2;			//find middle for halving

								        // Split array into halves
        int[] left = new int[mid];
        int[] right = new int[arr.length - mid];

        								//populate halves
        for (int i = 0; i < mid; i++)
            left[i] = arr[i];

        for (int i = mid; i < arr.length; i++)
            right[i - mid] = arr[i];

        							// Recursively sort + merge
        return merge(mergeSort(left), mergeSort(right));
	}

	private static int[] merge(int[] a1, int[] a2){
        int[] result = new int[a1.length + a2.length];//new merged array
        int i = 0, j = 0, k = 0;	//countrs

        while (i < a1.length && j < a2.length) {// loop till through both arrays
            if (a1[i] <= a2[j]) {			//decide which array to put in r[k]
                result[k++] = a1[i++];
            } else {
                result[k++] = a2[j++];
            }
        }

        while (i < a1.length)			//put the remaining things into result array
            result[k++] = a1[i++];

        while (j < a2.length)
            result[k++] = a2[j++];

        return result;
    }






	public static double[] mergeSort(double[] arr){
        if (arr.length <= 1) return arr;		//base cas

        int mid = arr.length / 2;			//find middle for halving

								        // Split array into halves
        double[] left = new double[mid];
        double[] right = new double[arr.length - mid];

        								//populate halves
        for (int i = 0; i < mid; i++)
            left[i] = arr[i];

        for (int i = mid; i < arr.length; i++)
            right[i - mid] = arr[i];

        							// Recursively sort + merge
        return merge(mergeSort(left), mergeSort(right));
	}

	private static double[] merge(double[] a1, double[] a2){
        double[] result = new double[a1.length + a2.length];//new merged array
        int i = 0, j = 0, k = 0;	//countrs

        while (i < a1.length && j < a2.length) {// loop till through both arrays
            if (a1[i] <= a2[j]) {			//decide which array to put in r[k]
                result[k++] = a1[i++];
            } else {
                result[k++] = a2[j++];
            }
        }

        while (i < a1.length)			//put the remaining things into result array
            result[k++] = a1[i++];

        while (j < a2.length)
            result[k++] = a2[j++];

        return result;
	}

    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            // Partition
            int pivotIndex = partition(arr, low, high);

            // Recursively sort before/ after partition
            quickSort(arr, low, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, high);
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];  // Choose last element as pivot
        int i = low - 1;        // Index of smaller 

        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // Swap pivot to correct place
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1; // pivot index
    }

	public static void quickSort(double[] arr, int low, int high){
        if (low < high) {
            // Partition
            int pivotIndex = partition(arr, low, high);

            // Recursively sort before/ after partitionin g
            quickSort(arr, low, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, high);
        }
	}
    private static int partition(double[] arr, int low, int high) {
        double pivot = arr[high];  // Choose last element as pivot
        int i = low - 1;        // Index of smaller 

        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                double temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // Swap pivot to correct place
        double temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1; // pivot index
    }

	public static double[] bucketSort(double[] arr) {
	    if (arr == null || arr.length == 0) return arr;	//no sorting needed

	    int n = arr.length;
	    int bucketCount = (int)Math.sqrt(n);		//number of buckets
	    double[][] buckets = new double[bucketCount][n];  //buckets each can hold n
	    int[] sizes = new int[bucketCount];		//track size of buckets

	    // Distribute values into buckets
	    for (double num : arr) {	
	        int index = (int)(num * bucketCount);
	        if (index == bucketCount) index--;  // if val is "outside interval" (at 1)
	        buckets[index][sizes[index]++] = num;	//put in sizes[index] in index bucket  
	    }

	    // Sort buckets with insertion
	    double[] sorted = new double[n];
	    int guh = 0;
	    for (int i = 0; i < bucketCount; i++) {
	        bucketInsertionSort(buckets[i], sizes[i]);  // sort up to sizes[i] elements
	        for (int j = 0; j < sizes[i]; j++) {
	            sorted[guh++] = buckets[i][j];
	        }
	    }

	    return sorted;
	}


	private static void bucketInsertionSort(double[] arr, int length) {
    for (int i = 1; i < length; i++) {
        double key = arr[i];
        int j = i - 1;

        // Shift elements >  key to the right
        while (j >= 0 && arr[j] > key) {
            arr[j + 1] = arr[j];
            j--;
        }
        arr[j + 1] = key;
    }
}



    public static void radixSort(int[] arr) {
        if (arr == null || arr.length == 0) return;	//no sort needed

        int max = getMax(arr);	//find biggest num digits (num iterations to do)

        for (int exp = 1; max / exp > 0; exp *= 10) {	//do counting sort digit by digit till biggest digit
            countingSortByDigit(arr, exp);
        }
    }

    //find biggest
    private static int getMax(int[] arr) {
        int max = arr[0];
        for (int num : arr) {
            if (num > max)
                max = num;
        }
        return max;
    }

    // sort by digit at exponen place
    private static void countingSortByDigit(int[] arr, int exp) {
        int n = arr.length;
        int[] output = new int[n];
        int[] count = new int[10]; // digits 0 - 9

        // Count freq of digits for current exponnnent
        for (int i = 0; i < n; i++) {
            int digit = (arr[i] / exp) % 10;
            count[digit]++;	
        }

        //make count arr into arr with cumaltive value so can find end postiion of each digit
        //(count[i] contains actual position of digit in output)
        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }
        //count[i] speaks of how many elem <= count[i]

        // make output array right to left so stable
        for (int i = n - 1; i >= 0; i--) {
            int digit = (arr[i] / exp) % 10;
            output[count[digit] - 1] = arr[i];
            count[digit]--;
        }

        // Copy output into arr 
        for (int i = 0; i < n; i++) {
            arr[i] = output[i];
        }
    }

    public static void main(String[] args){

    	/*
    	System.out.println("Sorting with ");
    	start = System.nanoTime();
    	sort();
    	end = System.nanoTime();
    	System.out.println(" sort took " +(end - start) + " ns");
    	*/

    	long start = 0;long end = 0;
    																							//ds1
    	System.out.println("DS1: Random floating-point numbers in the range [0, 1)");
    	double[] ds1 = makeDS1();

    	//sort ds1 with all except radix
    	System.out.println("Sorting with merge");
    	start = System.nanoTime();
    	ds1 = mergeSort(ds1);
    	end = System.nanoTime();
    	System.out.println("Merge sort took " +(end - start) + " ns");
    	start = 0; end = 0;


    	ds1 = makeDS1();
    	System.out.println("Sorting with quick");
    	start = System.nanoTime();
    	quickSort(ds1, 0, ds1.length - 1);
    	end = System.nanoTime();
    	System.out.println("quick sort took " +(end - start) + " ns");
    	start = 0; end = 0;

    	ds1 = makeDS1();
    	System.out.println("Sorting with buckets");
    	start = System.nanoTime();
    	ds1 = bucketSort(ds1);
    	end = System.nanoTime();
    	System.out.println("bucket sort took " +(end - start) + " ns");
    	start = 0; end = 0;

    	System.out.println();System.out.println();System.out.println();System.out.println();


    																							//ds2
    	System.out.println("DS2: Random non-negative integers");
    	int[] ds2 = makeDS2();
    	//all sorts except bucket

    	//sort ds1 with all except radix
    	System.out.println("Sorting with merge");
    	start = System.nanoTime();
    	ds2 = mergeSort(ds2);
    	end = System.nanoTime();
    	System.out.println("Merge sort took " +(end - start) + " ns");
    	start = 0; end = 0;


    	ds2 = makeDS2();
    	System.out.println("Sorting with quick");
    	start = System.nanoTime();
    	quickSort(ds2, 0, ds2.length - 1);
    	end = System.nanoTime();
    	System.out.println("quick sort took " +(end - start) + " ns");
    	start = 0; end = 0;

    	ds2 = makeDS2();
    	System.out.println("Sorting with radix");
    	start = System.nanoTime();
    	radixSort(ds2);
    	end = System.nanoTime();
    	System.out.println("radix sort took " +(end - start) + " ns");
    	start = 0; end = 0;

    	System.out.println();System.out.println();System.out.println();System.out.println();

    																							//ds3
    	System.out.println("DS3: A nearly sorted list (e.g., 90% sorted + 10% random noise)");
    	int[] ds3 = makeDS3();

    	System.out.println("Sorting with merge");
    	start = System.nanoTime();
    	ds3 = mergeSort(ds3);
    	end = System.nanoTime();
    	System.out.println("Merge sort took " +(end - start) + " ns");
    	start = 0; end = 0;

    	ds3 = makeDS3();
    	System.out.println("Sorting with quick");
    	start = System.nanoTime();
    	quickSort(ds3, 0, ds3.length - 1);
    	end = System.nanoTime();
    	System.out.println("quick sort took " +(end - start) + " ns");
    	start = 0; end = 0;

    	ds3 = makeDS3();
    	System.out.println("Sorting with radix");
    	start = System.nanoTime();
    	radixSort(ds3);
    	end = System.nanoTime();
    	System.out.println("radix sort took " +(end - start) + " ns");
    	start = 0; end = 0;

    	System.out.println();System.out.println();System.out.println();System.out.println();


    																							//ds4
    	System.out.println("DS4: A list with many duplicate values");
    	int[] ds4 = makeDS4();
    	//all except for bucket
    	System.out.println("Sorting with merge");
    	start = System.nanoTime();
    	ds4 = mergeSort(ds4);
    	end = System.nanoTime();
    	System.out.println("Merge sort took " +(end - start) + " ns");
    	start = 0; end = 0;

    	ds4 = makeDS4();
    	System.out.println("Sorting with quick");
    	start = System.nanoTime();
    	quickSort(ds4, 0, ds4.length - 1);
    	end = System.nanoTime();
    	System.out.println("quick sort took " +(end - start) + " ns");
    	start = 0; end = 0;

    	ds4 = makeDS4();
    	System.out.println("Sorting with radix");
    	start = System.nanoTime();
    	radixSort(ds4);
    	end = System.nanoTime();
    	System.out.println("radix sort took " +(end - start) + " ns");
    	start = 0; end = 0;


    }

    public static double[] makeDS1(){
    	// System.out.println("DS1: Random floating-point numbers in the range [0, 1)");

    	double[] ds1 = new double[10000];
    	Random r = new Random();
    	double rand;

    	for (int i = 0; i < 9999; i++){
    		ds1[i] = 0 + r.nextDouble() * (0.9999999 - 0);
    	}

    	// System.out.println("ds1 before sorts");
    	// for (int i = 0; i < 9999; i++){
    	// 	System.out.print(ds1[i]);
    	// }
    	//ds1 for all except radix sort
    	return (ds1);
    }

    public static int[] makeDS2(){
    	// System.out.println("DS2: Random non-negative integers");
    	Random r = new Random();
    	int[] ds2 = new int[10000];
	   	for (int i = 0; i < 9999; i ++){
	   		ds2[i] = r.nextInt(20000);
	   	}
    	// System.out.println("ds2 before sorts");
    	// for (int i = 0; i < 9999; i++){
    	// 	System.out.print(ds2[i]);
    	// }System.out.println();
    	return (ds2);
    }

    public static int[] makeDS3(){
    	Random r = new Random();
    	int[] ds3 = new int[10000];
	   	for (int i = 0; i < 9999; i ++){
	   		ds3[i] = r.nextInt(100000);
	   	}

	   	Arrays.sort(ds3, 0, (int)(0.9 * ds3.length));

        for (int i = 0; i < 10; i++) {		//move stuff around so not all noise at end
            int ind1 = r.nextInt((int)(0.9 * ds3.length));
            int ind2 = r.nextInt((int)(0.9 * ds3.length));
            int temp = ds3[ind1];
            ds3[ind1] = ds3[ind2]; ds3[ind2] = temp;
        }
        return (ds3);
    }

    public static int[] makeDS4(){
    	// System.out.println("DS4: A list with many duplicate values");
    	Random r = new Random();

    	//generate array and sort to certain percentage
    	int[] ds4 = new int[10000];
	   	// int randInt = 0;
	   	for (int i = 0; i < 9999; i ++){
	   		ds4[i] = r.nextInt(2000);
	   	}
	   	return (ds4);
    }

}