import java.util.*;
public class MyLinkedList<X>{
	private int size;
	private DNode<X> head,tail;
	public MyLinkedList(){
		size=0;
		head = new DNode<X>(null, null, null);
		tail = new DNode<X>(null, head, null);
		head.setNext(tail);
	}
	public int size(){
		return size;
	}
	public Boolean isEmpty(){
		return (this.size()==0);
	}
	public DNode<X> getHead(){
		return this.head;
	}
	public DNode<X> getTail(){
		return this.tail;
	}
	public DNode<X> getFirst() throws IllegalStateException{
		if (isEmpty())
			throw new IllegalStateException("List is Empty");
		return getHead().getNext();
	}
	public DNode<X> getLast() throws IllegalStateException{
		if (isEmpty())
			throw new IllegalStateException("List is Empty");
		return getTail().getPrev();
	}
	public DNode<X> getPrev(DNode<X> v) throws IllegalArgumentException{
		if (v==getHead())
			throw new IllegalArgumentException("Cannot move back past the head of the list");
		return v.getPrev();
	}
	public DNode<X> getNext(DNode<X> v) throws IllegalArgumentException{
		if (v==getTail())
			throw new IllegalArgumentException("Cannot move forward past the tail of the list");
		return v.getNext();
	}
	//Add DNode<X> z after DNode<X> v
	public void addAfter (DNode<X> v, DNode<X> z) throws IllegalArgumentException{
		DNode<X> u = getNext(v);
		z.setNext(u);
		z.setPrev(v);
		v.setNext(z);
		u.setPrev(z);
		this.size++;
	}
	public void addFirst(DNode<X> v){
		addAfter(getHead(),v);
	}
	public void remove(DNode<X> v){
		DNode<X> u = getPrev(v);
		DNode<X> w = getNext(v);
		w.setPrev(u);
		u.setNext(w);
		v.setPrev(null);
		v.setNext(null);
		this.size --;
	}
	public Boolean hasPrev(DNode<X> v){
		return (v!=getHead());
	}
	public Boolean hasNext(DNode<X> v){
		return (v!=getTail());
	}
}