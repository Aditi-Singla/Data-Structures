import java.util.*;
public class myqueue {
    // implement the stack using an array
	private int f,r;
	int[] myarray;
	public myqueue(){
		f = 0;
		r = 0 ;
		myarray = new int[100];
	}
	// declare additional variables here
	public int size() {
		return (100-f+r) % 100;
	}
	public void enqueue(int element) {
		if (size()==100)
			throw new IllegalStateException("Stack if full");
		myarray[r]=element;
		r=(r+1)%100;
	}
	public int dequeue() {
		int elem;
		if (size()==0)
			throw new NoSuchElementException("Stack is empty");
		elem = myarray[f];
		myarray[f] = 0;
		f = (f+1)%100;
		return elem;
	}
	public boolean isEmpty() {
		return (size()==0);
	}
	public boolean isSorted() {
		for (int i=0;i<size()-1;i++){
			if (myarray[i] > myarray[i+1])
				return false;
		}
		return true;
	}
}
