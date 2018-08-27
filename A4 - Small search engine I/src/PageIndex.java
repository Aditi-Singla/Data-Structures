public class PageIndex{
	private MyLinkedList<WordEntry> wordList;
	public PageIndex(){
		wordList = new MyLinkedList<WordEntry>();
	}
	public Boolean isMember(String x){
		DNode<WordEntry> current = wordList.getHead();
		for (int i=0;i<wordList.size();i++){
			current = current.getNext();
			if (current.getElement().equals(x))
				return true;
		}
		return false;

	}
	public WordEntry getWordEntry(String str) throws IllegalArgumentException{
		if (isMember(str)){
			DNode<WordEntry> s = wordList.getHead();
			while (s.getNext()!= wordList.getTail()){
				s = s.getNext();
				if (s.getElement().equals(str))
					return s.getElement();
			}
			return null;
		}
		else
			throw new IllegalArgumentException("This word is not in the list!");
	}
	public void addPositionForWord(String str,Position p){
		if  (isMember(str)){
			getWordEntry(str).addPosition(p);
		}
		else{
			WordEntry w = new WordEntry(str);
			w.addPosition(p);
			DNode<WordEntry> n = new DNode<WordEntry>(w);
			this.wordList.addFirst(n);
		} 
	}
	public void addPositionsForWord(String str,MyLinkedList<Position> p){
		if  (isMember(str)){
			getWordEntry(str).addPositions(p);
		}
		else{
			WordEntry w = new WordEntry(str);
			w.addPositions(p);
			DNode<WordEntry> n = new DNode<WordEntry>(w);
			this.wordList.addFirst(n);
		} 
	}
	public MyLinkedList<WordEntry> getWordEntries(){
		return this.wordList;
	}
}	