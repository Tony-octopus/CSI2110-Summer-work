import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class Tetranacci{
	public static int tetranacciMultiple(int n){
		if (n <=2){			//base cases
			return 0;
		}
		else if (n == 3){
			return 1;
		}
		return (tetranacciMultiple(n-1) + tetranacciMultiple(n-2) + tetranacciMultiple(n-3) + tetranacciMultiple(n-4));	//formula defined as T(n) = T(n-1) + T(n-2) + T(n-3) + T(n-4)
	}

	public static int tetranacciLinear(int n){
		return tetranacciLinearRecursive(n, 0,0,0,1);	//call to private recursive function with parameters of first 4 terms of tetrathingy sequence
	}

	private static int tetranacciLinearRecursive(int n, int a, int b, int c, int d){
		if (n <=2){		//base cases (for first 3 terms (which are 0))
			return 0;
		}
		else if (n == 3){	//other base case reached; return summation of arguments which have been incremented via recursion
			return (a + b + c + d);	
		}
		return tetranacciLinearRecursive(n-1, a+b+c+d, a, b, c);
	}

	public static void main(String[] args){	//main function
		int t1;
		int t2;
		long start = 0;
		long end = 0;
		long time1 = 0;
		long time2 = 0;

		try {		//try to make csv file
	      File f = new File("TetraOut.csv");	
	      if (f.createNewFile()) {
	        System.out.println("file created: " + f.getName());
	      } else {
	        System.out.println("file alr exists.");
	      }
	    } catch (IOException e) {
	      System.out.println("error ): ");
	      e.printStackTrace();
	    }

		try{	//try to write to csv file
			FileWriter w = new FileWriter("TetraOut.csv");
			w.write("n,tetranacciMultiple time,tetranacciLinear time\n");
			for (int n = 0; n <= 30; n+=5){//loop through different n values
				start = 0;
				end = 0;

				start = System.nanoTime();	//timing multiple
				t1 = tetranacciMultiple(n);
				end = System.nanoTime();
				time1 = end - start;

				start = 0;			//reinitialize
				end = 0;

				start = System.nanoTime();	//timing linear
				t2 = tetranacciLinear(n);
				end = System.nanoTime();
				time2 = end - start;
				
				//print outputs
				System.out.println("TetranacciMultiple(" + n + "): " + t1 + "time taken: " + time1 + " ns ------ " + "TetranacciLinear(" + n + "): " + t2 + "time taken: " + time2 + "ns");
				System.out.println("plot info: 			TM: ("+n + "," + time1 +" ns) -------  TL: (" + n + "," + time2 + " ns)");
				System.out.println();	

				//writing to csv file
				w.write(n + "," + time1 + "," + time2+'\n');
				if (t1 != t2){
					System.out.println("							SHIT NOT EQUAL WOMP WOMP");
				}
			}
			w.close();

		} catch (IOException e){
			System.out.println("error accessing th file");
			e.printStackTrace();
		}

	}



}