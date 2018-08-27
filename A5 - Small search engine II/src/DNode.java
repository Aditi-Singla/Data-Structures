public class DNode<X>{
	private X element;
	private DNode<X> next,prev;
	public DNode(X e){
		element = e;
		next = null;
		prev = null;
	}
	public DNode(X e,DNode<X> p,DNode<X> n){
		element = e ;
		next = n ;
		prev = p ; 
	}
	public X getElement(){
		return element;
	}
	public DNode<X> getNext(){
		return next;
	}
	public DNode<X> getPrev(){
		return prev;
	}
	public void setElement(X newElem){
		element = newElem;
	}
	public void setNext(DNode<X> newNext){
		next = newNext;
	}
	public void setPrev(DNode<X> newPrev){
		prev = newPrev;
	}
}