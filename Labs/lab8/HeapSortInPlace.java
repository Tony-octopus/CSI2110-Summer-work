// ==========================================================================
// $Id: HeapSortInPlace.java,v 1.1 2006/11/05 03:27:51 jlang Exp $
// CSI2110 Lab code; Heap sort in place
// ==========================================================================
// (C)opyright:
//
//   SITE, University of Ottawa
//   800 King Edward Ave.
//   Ottawa, On., K1N 6N5
//   Canada. 
//   http://www.site.uottawa.ca
// 
// Creator: unknown (Lab source without reference), adapted by J.Lang
// Email:   jlang@site.uottawa.ca
// ==========================================================================
// $Log: HeapSortInPlace.java,v $
// Revision 1.1  2006/11/05 03:27:51  jlang
// Added lab8 on sorting.
//
// Revision 2.1 2022/11/13 lmoura
// Changed the implementation of heapsort to rely on full downheap operations,
// matching the concept of downheap seen in class
//
// ==========================================================================
/**
 * Implements an in-place array-based heap sort.
 */
public class HeapSortInPlace<T extends Comparable<T>> {
  protected T[] d_seq;

  /**
   * Construct a HeapSort function object
   * _seq will be sorted on construction
   */
  public HeapSortInPlace( T[] _seq ) {
	  d_seq = _seq;
    int size = d_seq.length;
    /**/
    System.out.println("Heapsort has dummy body.\n"
    		+ "Please implement:\n"+"1) heapify (bottom up max-heap construction);\n"
    		+ "2) Repeat several delete max, placing the largest in the sorted part\n"
    		+ "Both parts must call downheap.");
    /**/
    // **** Heapify to build a maxheap: bottom-up heap construction ******
    // Include next a sequence of downheap to trasnform array in heap
    // Remove the next line, it is just an example,
    // since Heapify will have a loop with several calls to downheap
    /**/
    // downheap((size-2)/2, size-1); // first downheap needed to heapify (remove this line)
    /**/
    for (int i = (size - 2 )/2; i>= 0; i-- ){
        downheap(i, size - 1);
    }

    for (int i = size - 1; i > 0; i--) {
        T temp = d_seq[0];
        d_seq[0] = d_seq[i];
        d_seq[i] = temp;

        downheap(0, i - 1);
    }
    /**/
    // iteratively loop, delete max and fill in large elements from
    // the end
    /**/



  }

  /**
   * downheap(start, end) fixes the maxheap property for subtree rooted at start
   * Preconditions: 
   * d_seq[0..end] represents a complete binary tree
   * subtree rooted at position start may violate maxheap property
   * subtrees rooted at the children of start do not violate the maxheap property
   * Postcondition:
   * subtree rooted at start does no violate maxheap property
   **/
  protected void downheap(int start, int end) {
	  /**/
	  System.out.println("Downheap not implemented:\n"
	  
	  		+ "This method must do sucessive swaps between node and largest of two kids "
	  		+ "until no swap is needed or the node has no kids");
	  /**/
	  int curr = start;
    T temp;
    while ( (left(curr) <= end)){
        System.out.println("uuuggghhhh");

        //assume left is the largest
        //(ensure right is in range (exists)) check against right child to see which should swap current with
          //if right, swap current w right
          //else if left, swap current w left
        //if current node greater than both children, break

        int left = left(curr);
        int right = right(curr);
        int largest = left;

        if (right <= end && d_seq[largest].compareTo( d_seq[right]) < 0 ){
            largest = right;
        }

        if (d_seq[curr].compareTo( d_seq[largest]) > 0){break;}

        temp = d_seq[curr];
        d_seq[curr] = d_seq[largest];
        d_seq[largest] = temp;

        curr = largest;

    }
	  
  }

  private int left(int index){
      return (2*index + 1);
  }

  private int parent(int index){
      return (int)Math.floor(((index-1)/2));
  }

  private int right (int index){
      return (2*index + 2);
  }
}
