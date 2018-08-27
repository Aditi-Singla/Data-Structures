import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
public class PageEntry{
	private String name;
	private PageIndex list;
	private AVLTree tree;
	public PageEntry(String pageName){
		BufferedReader b = null;
		try {
			list = new PageIndex();
			tree = new AVLTree();
			String textString;
			String[] array = {"a","an","the","they","these","for","is","are","of","or","and","does","will","whose","this","was"};
			Vector<String> restricted = new Vector<String>(Arrays.asList(array));
			b = new BufferedReader(new FileReader("webpages/"+pageName));
			int index = 1;
			Position pos;
			name = pageName;
			while ((textString = b.readLine()) != null) {
				if (textString.equals(""))
					continue;
				String[] l = textString.trim().replaceAll("[^a-zA-Z0-9+ ]"," ").toLowerCase().split("\\s+");
				for (int i=0;i<l.length;i++){
					if (!restricted.contains(l[i])){
						if (l[i].equals("stacks"))
							l[i] = "stack";
						if (l[i].equals("structures"))
							l[i] = "structure";
						if (l[i].equals("applications"))
							l[i] = "application";
						pos = new Position(this,index+i);
						list.addPositionForWord(l[i],pos);
						tree.insert(index+i,l[i]);
						//System.out.println(l[i]);
					}	
				}
				index = index + l.length;
			}
		}
		catch (IOException e) {
			list = null;
			System.out.println("		Sorry, the file '"+pageName+"' not found!");
		}	
	}	
	public PageIndex getPageIndex(){
		return this.list;
	}
	public AVLTree getTree(){
		return this.tree;
	}
	public String getName(){
		return this.name;
	}
	public MyLinkedList<Position> positionsOfWord(String str){
		if (list.isMember(str)){
			return (list.getWordEntry(str).getAllPositionsForThisWord());
		}
		else{
			return (new MyLinkedList<Position>());
		}	 
	}
	public double score(String str,MyLinkedList<Position> l){
		double soFar = 0.0;
		DNode<Position> current = l.getHead();
		while (current.getNext()!= l.getTail()){
			Position x = current.getNext().getElement();
			double a = (1.0/x.getWordIndex());
			soFar += (a*a);
			current = current.getNext();
		}
		return soFar;
	}
	public double getRelevanceOfPage(String str[], boolean doTheseWordsRepresentAPhrase){
		double sofar = 0.0;
		if (doTheseWordsRepresentAPhrase){
			MyLinkedList<Position> l = new MyLinkedList<Position>();
			if (str[0].equals("stacks"))
				str[0] = "stack";
			if (str[0].equals("structures"))
				str[0] = "structure";
			if (str[0].equals("applications"))
				str[0] = "application";
			MyLinkedList<Position> lp = positionsOfWord(str[0]);
			DNode<Position> node = lp.getHead();
			while (node.getNext()!= lp.getTail()){
				int p = node.getNext().getElement().getWordIndex();
				DNode<Position> q = node.getNext();
				int s;
				for (s=1;s<str.length;s++){
					if (str[s].equals("stacks"))
						str[s] = "stack";
					if (str[s].equals("structures"))
						str[s] = "structure";
					if (str[s].equals("applications"))
						str[s] = "application";
					if (str[s].equals(tree.searchNext(p).getName()))
						p = tree.searchNext(p).getData();
					else 
						break;
				}
				if (s==str.length){
					DNode<Position> r = new DNode<Position>(q.getElement());
					l.addFirst(r);
				}	
				node = node.getNext();
			}  
			sofar = score(str[0],l);
		}
		else{
			for (int i=0;i<str.length;i++){
				sofar += score(str[i],positionsOfWord(str[i]));
			}
		}
		return sofar;
	}		
}