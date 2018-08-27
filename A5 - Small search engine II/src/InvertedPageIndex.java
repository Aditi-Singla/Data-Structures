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
	public Myset<PageEntry> getPagesWhichContainWord(String str){
		str = str.toLowerCase();
		if (str.equals("stacks"))
			str = "stack";
		if (str.equals("structures"))
			str = "structure";
		if (str.equals("applications"))
			str = "application";
		if (hTable.getWord(str)==null)
			return (new Myset<PageEntry>());
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
	public Myset<PageEntry> getPagesWhichContainWords(String[] str){ //str must not be empty
		Myset<PageEntry> l = getPagesWhichContainWord(str[0]);
		for (int i=1 ; i<str.length ; i++){
			//str[i] = str[i].toLowerCase();
			l = l.intersection(getPagesWhichContainWord(str[i]));
		}
		return l;		
	}
	public Myset<PageEntry> getPagesWhichContainPhrase(String[] str){  //str must be non empty
		Myset<PageEntry> l = getPagesWhichContainWords(str);
		Myset<PageEntry> ln = new Myset<PageEntry>();
		for (int i=0 ; i<l.size() ; i++){
			PageEntry page = l.getithMember(i+1);
			AVLTree tree = page.getTree();
			MyLinkedList<Position> lp = page.positionsOfWord(str[0]);
			DNode<Position> node = lp.getHead();
			while (node.getNext()!= lp.getTail()){
				int p = node.getNext().getElement().getWordIndex();
				int s;
				for (s=1;s<str.length;s++){
					if (str[s].equals(tree.searchNext(p).getName()))
						p = tree.searchNext(p).getData();
					else 
						break;
				}
				if (s==str.length){
					ln.addElement(page);
					break;
				}	
				node = node.getNext();	
			}  
		}
		return ln;
	}
}