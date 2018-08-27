public class InvertedPageIndex{
	private MyHashTable hTable;
	public InvertedPageIndex(){
		hTable = new MyHashTable();
	}
	public void addPage(PageEntry p){
		MyLinkedList<WordEntry> words = p.getPageIndex().getWordEntries();
		DNode<WordEntry> current = words.getHead();
		int counter = 0;
		while (current.getNext()!= words.getTail()){
			current = current.getNext();
			DNode<WordEntry> temp = new DNode<WordEntry>(current.getElement()); 
			hTable.addPositionsForWord1(temp.getElement());
			counter++;
		}
	}
	public Myset<PageEntry> getPagesWhichContainWord(String str) throws IllegalArgumentException{
		if (hTable.getWord(str)==null)
			throw new IllegalArgumentException();
		else{
			Myset<PageEntry> l = new Myset<PageEntry>();
			WordEntry w = hTable.getWord(str);
			MyLinkedList<Position> listOfPos = w.getAllPositionsForThisWord();
			DNode<Position> current = listOfPos.getHead();
			while (current.getNext()!= listOfPos.getTail()){
				current = current.getNext();
				if (!l.isMember(current.getElement().getPageEntry()))
					l.addElement(current.getElement().getPageEntry());
			}
			return l;
		}	
	}
}