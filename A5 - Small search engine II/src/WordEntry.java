import java.util.*;
public class WordEntry{
	private String word;
	private MyLinkedList<Position> positions;
	public WordEntry(String x){
		word = x;
		positions = new MyLinkedList<Position>();
	}
	public String getString(){
		return this.word;
	}
	public void addPosition(Position position){
		DNode<Position> n = new DNode<Position>(position);
		this.positions.addFirst(n);
	}
	public void addPositions(MyLinkedList<Position> p){
		DNode<Position> s = p.getHead();
		while (s.getNext() != p.getTail()){
			s = s.getNext();
			DNode<Position> temp = new DNode<Position>(s.getElement());
			this.positions.addFirst(temp);			
		}
	}
	public MyLinkedList<Position> getAllPositionsForThisWord(){
		return this.positions;
	}
	public Boolean equals(String s){
		return (word.equals(s));// || (word.equals("stacks") && s.equals("stack")) || (word.equals("structures") && s.equals("structure")) || (word.equals("applications") && s.equals("application")) || (s.equals("stacks") && word.equals("stack")) || (s.equals("structures")&& word.equals("structure")) || (s.equals("applications") && word.equals("application")));
	} 
}