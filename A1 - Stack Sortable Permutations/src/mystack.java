import java.util.*;
public class mystack {
    // implement the stack using an array
	private int t;
	int[] myarray;
	public mystack(){
		t= -1;
		myarray=new int[100];
	}
	// declare additional variables here
	public int size(){
		return t+1;
	}
	public void push(int element) throws IllegalStateException{
		if (size()==100)
			throw new IllegalStateException("Stack if full");
		myarray[++t]=element;
	}
	public int pop() throws NoSuchElementException{
		int elem;
		if (size()==0)
			throw new NoSuchElementException("Stack is empty");
		elem = myarray[t];
		myarray[t--]=0;
		return elem;	
	}
	public boolean isEmpty() {
		return (t<0);
	}
	public int getElementAtTopOfStack() throws NoSuchElementException{
		if (isEmpty())
			throw  new NoSuchElementException("Stack is empty");
		return myarray[t];
	}
}