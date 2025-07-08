import java.util.*;

public class APQ <K extends Comparable<K>, V>{
	//write entry class
	private static class Thing<K, V>{
		private K key;
		private V value;

		public Thing(K key, V value){
			this.key = key;
			this.value =value;
		}

		public K getKey(){
			return this.key;
		}

		public V getValue(){
			return this.value;
		}

		public void setKey(K key){
			this.key = key;
		}

		public void setValue(V val){
			this.value = val;
		}

        @Override
        public String toString() {	
            return "(" + key + ", " + value + ")";
        }

	}

	//instance vars
	private Thing<K, V>[] heap;
	private int size;
	private boolean min;
	private static int initialCap = 16;//power of 22

	//constructor
	public APQ(){
		this.heap =(Thing<K,V>  []) new Thing[initialCap];
		this.size = 0;
		this.min = true;	//initally min heap
	}

	public Thing<K,V> insert(K key, V value){
		//check if need resize
		//else, make obj, put at end and upheap then increment siz3e
		if (size == heap.length){resize();}

		Thing<K,V> add = new Thing<>(key, value);
		heap[size] = add;
		upheap(size); size++;
		return add;
	}

	public boolean isEmpty(){
		return (size == 0);
	}

	public int size(){
		return this.size;
	}

	public String state(){
		if (min)
			return "Min";
		else 
			return "Max";
	}

	public Thing<K, V> top(){
		if (this.isEmpty()){return null;}
		return (heap[0]);
	}

	public Thing<K,V> removeTop(){
		if (this.isEmpty()){return null;}
		Thing<K,V> temp = heap[0];
		heap[0] = heap[this.size - 1];//put last elem into root
		heap[size - 1] = null; size --;//decrease size
		downheap(0);//reorder
		return temp;		
	}

	public Thing<K, V> remove(Thing<K,V> target){
		int index = -1;//initial val
		for (int i = 0; i < this.size; i++){
			if (heap[i] == target){index = i;}
		}

		if (index == -1){//if didn't find index
			throw new NoSuchElementException("not in APQ");
		}
		Thing<K,V> removed = heap[index];
		heap[index] = heap[this.size-1];
		heap[this.size - 1] = null; size --;
		if (index < this.size){//if we didn't just remove last thing
			upheap(index);
			downheap(index);
		}
		return removed;
	}

	public V replaceValue(Thing<K,V> target, V value){
		int index = -1; 
		for (int i = 0; i < this.size; i ++){		//find index
			if (heap[i] == target){index = i;}
		}
		if (index == -1){throw new NoSuchElementException("not found");}
		V old = target.getValue();
		target.setValue(value);			//set
		return (old);
	}

	public K replaceKey(Thing<K,V> target, K key){
		int index = -1; 
		for (int i = 0; i < this.size; i ++){		//find index
			if (heap[i] == target){index = i;}
		}
		if (index == -1){throw new NoSuchElementException("not found");}
		K old = target.getKey();		//set
		target.setKey(key);
		upheap(index); downheap(index);
		return (old);
	}

	public void merge(APQ<K,V> other){
		for (int i = 0; i < other.size; i++){	//loop and in each it, add to calling apq which reorders as well
			Thing<K,V> temp = other.heap[i];
			this.insert(temp.getKey(), temp.getValue());
		}
	}


	public void toggle(){
		min = !min;
        for (int i = parent(size - 1); i >= 0; i--) {//go up and reheap the stuff until top
            downheap(i);
        }
	}

	public Thing<K, V> peekAt(int n) {
    if (n < 1 || n > size) {throw new IndexOutOfBoundsException("Index out of bounds");}

    // Copy
    Thing<K, V>[] copy = Arrays.copyOf(heap, size);

    // sort
    for (int i = 0; i < size - 1; i++) {
        int selected = i;
        for (int j = i + 1; j < size; j++) {
            if (compare(copy[j], copy[selected]) < 0) {
                selected = j;
            }
        }
        Thing<K, V> temp = copy[i];
        copy[i] = copy[selected];
        copy[selected] = temp;
    }

    return copy[n - 1];		//look at n-1 index of sorted
}


	//helpers
		//resize
	@SuppressWarnings("unchecked")
	private void resize(){
		Thing<K, V>[] heapBigger = (Thing<K,V> []) new Thing[this.heap.length * 2];
		System.arraycopy(this.heap, 0, heapBigger, 0, this.size);
		this.heap = heapBigger;
	}
		//upheap
	private void upheap(int index){//NEED TO BE ABLE TO COMPARE THE V vals
		while (index > 0){		//breaks if the thing becomes root
			//check if the parent is greater
			int par = parent(index);
			if (compare(heap[index], heap[par] ) < 0){

				//yes, swap them
				Thing<K, V> temp = heap[index];
				heap[index] = heap[par];
				heap[par] = temp;
				index = par;
			}else{
				break;
			}
			//otherwise, break from this loop
		}
	}
		//downheap
	private void downheap(int index) {
	    while (true) {
	        int left = leftChild(index);
	        int right = rightChild(index);
	        int smallest = index;

	        // For min-heap: compare and select the smallest
	        // For max-heap: compare and select the largest
	        if (left < size && compare(heap[left], heap[smallest]) < 0) {
	            smallest = left;
	        }
	        if (right < size && compare(heap[right], heap[smallest]) < 0) {
	            smallest = right;
	        }

	        if (smallest == index) {
	            break; // heap satisfied
	        }

	        Thing<K, V> temp = heap[index];		//swap and move along
	        heap[index] = heap[smallest];
	        heap[smallest] = temp;

	        index = smallest;
	    }
	}


		//compare
	private int compare(Thing<K, V> a, Thing<K,V> b){			//change order of comparison depending on mode of heap
	    if (min) {
	        return a.getKey().compareTo(b.getKey());
	    } else {
	        return b.getKey().compareTo(a.getKey());
	    }
	}

	private int parent(int index){			//takes index, returns index of parent
		return (index -1)/2;
	}
	private int leftChild(int index){
		return (2*index + 1);
	}
	private int rightChild(int index){
		return (2*(index + 1));
	}

public static void main(String[] args) {
    APQ<Integer, String> apq = new APQ<>();

    //Insertions + upheap
    apq.insert(10, "ten");
    apq.insert(5, "five");
    apq.insert(15, "fifteen");
    apq.insert(1, "one");
    apq.insert(20, "twenty");
    System.out.println("After 5 inserts (min heap): " + Arrays.toString(Arrays.copyOf(apq.heap, apq.size)));

    //resize
    for (int i = 0; i < 20; i++) {
        apq.insert(i + 100, "num" + (i + 100));
    }
    System.out.println("After inserting 20 more to trigger resize: size=" + apq.size());

    //PeekAt for min heap
    System.out.println("peekAt(1) (min): " + apq.peekAt(1));
    System.out.println("peekAt(10) (min): " + apq.peekAt(10));
    //RemoveTop + check new top
    System.out.println("Removed top: " + apq.removeTop());
    System.out.println("New top after removeTop: " + apq.top());
    //Removefrom middle
    APQ.Thing<Integer, String> toRemove = apq.heap[5];
    System.out.println("Removing element from middle: " + toRemove);
    apq.remove(toRemove);
    System.out.println("Heap after removal: " + Arrays.toString(Arrays.copyOf(apq.heap, apq.size)));
    //Change key/val(cause reheap)
    APQ.Thing<Integer, String> toChange = apq.heap[3];
    System.out.println("Changing key/value of element: " + toChange);
    apq.replaceKey(toChange, 0);   // smaller key, should upheap
    apq.replaceValue(toChange, "zero");
    System.out.println("Heap after key/value change: " + Arrays.toString(Arrays.copyOf(apq.heap, apq.size)));

    //Toggle heap to max
    System.out.println("Heap mode before toggle: " + apq.state());
    apq.toggle();
    System.out.println("Heap mode after toggle (max heap): " + apq.state());
    System.out.println("Heap after toggle: " + Arrays.toString(Arrays.copyOf(apq.heap, apq.size)));

    //peekAt for max
    System.out.println("peekAt(1) (max): " + apq.peekAt(1));
    System.out.println("peekAt(5) (max): " + apq.peekAt(5));
    //Merge
    APQ<Integer, String> other = new APQ<>();
    other.insert(7, "seven");
    other.insert(3, "three");
    System.out.println("Other APQ before merge: " + Arrays.toString(Arrays.copyOf(other.heap, other.size())));
    apq.merge(other);
    System.out.println("Heap after merge: " + Arrays.toString(Arrays.copyOf(apq.heap, apq.size)));
    //Remove top till empty
    System.out.println("Removing all elements:");
    while (!apq.isEmpty()) {
        System.out.println("Removed: " + apq.removeTop());
    }
    System.out.println("Heap empty? " + apq.isEmpty());

    //peekAt on empty heap
    try {
        apq.peekAt(1);
    } catch (IndexOutOfBoundsException e) {
        System.out.println("Exception on peekAt(1) empty heap: " + e.getMessage());
    }
    //Insert on empty and check
    apq.insert(50, "fifty");
    apq.insert(40, "forty");
    apq.insert(60, "sixty");
    System.out.println("After inserting 3 elements post-empty: " + Arrays.toString(Arrays.copyOf(apq.heap, apq.size)));
    //Toggle to min
    apq.toggle();
    System.out.println("Heap mode after toggle back to min: " + apq.state());
    System.out.println("Heap after toggle back: " + Arrays.toString(Arrays.copyOf(apq.heap, apq.size)));
}



}