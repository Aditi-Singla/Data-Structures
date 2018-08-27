import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
public class PageEntry{
	private String name;
	private PageIndex list;
	public PageEntry(String pageName){
		BufferedReader b = null;
		try {
			list = new PageIndex();
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
						pos = new Position(this,index+i);
						list.addPositionForWord(l[i],pos);
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
	public String getName(){
		return this.name;
	}
	public MyLinkedList<Position> positionsOfWord(String str) throws IllegalArgumentException{
		if (list.isMember(str)){
			return (list.getWordEntry(str).getAllPositionsForThisWord());
		}
		else{
			throw new IllegalArgumentException();
		}	 
	}		
}