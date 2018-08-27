import java.util.*;
public class Myset<X>{
	private MyLinkedList<X> l;
	private DNode<X> current;
	public Myset(){
		l = new MyLinkedList<X>();
	} 
	public int size(){
		return l.size();
	}
	public Boolean isEmpty(){
		return (l.isEmpty());
	}
	public Boolean isMember (X o){
		current = l.getHead();
		while (current.getNext() != l.getTail()){
			if (current.getNext().getElement().equals(o))
				return true;
			current = current.getNext();
		}
		return false; 
	}
	public void addElement(X o){
		DNode<X> n = new DNode<X>(o);
		l.addFirst(n); 
	}
	public void delete(X o){
		try{
			current = l.getHead();
			if (!isMember(o))
				throw new Exception(); 
			while (current.getNext()!= l.getTail()){
				if (current.getNext().getElement() == o){
					current = current.getNext();
					l.remove(current);
					break;
				}
				current = current.getNext();	
			}
		}
		catch (Exception e){
			System.out.println("No such X exists!");
			return;
		}	
	}
	public Myset<X> union(Myset<X> a){
		Myset<X> y = a;
		current = l.getHead();
		while (current.getNext()!= l.getTail()){
			X x = current.getNext().getElement();
			if (!a.isMember(x))
				y.addElement(x);
			current = current.getNext();
		}
		return y; 
	}
	public Myset<X> intersection(Myset<X> a){
		Myset<X> y= new Myset<X>();
		current = l.getHead();
		while (current.getNext()!= l.getTail()){
			X x = current.getNext().getElement();
			if (a.isMember(x))
				y.addElement(x);
			current = current.getNext();
		}
		return y; 
	}
	public void update(Myset<X> a){
		current = l.getHead();
		while (current.getNext()!= l.getTail()){
			X x = current.getNext().getElement();
			if (!a.isMember(x))
				addElement(x);
			current = current.getNext();
		}
	}
	public X getithMember(int i){   //i:1-size
		try{
			if (size()<i)
				throw new Exception();
			else{
				DNode<X> counter = l.getHead();
				while (i!=0){
					i--;
					counter=counter.getNext();
				}
				return counter.getElement();
			}
		}
		catch (Exception e){
			System.out.println("It doesnot have this many members!");
			return null;
		}	
	}
}