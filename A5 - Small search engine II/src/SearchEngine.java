import java.util.*;
public class SearchEngine{
	InvertedPageIndex ipi;
	Myset<PageEntry> listOfPages;
	public SearchEngine() {
		ipi = new InvertedPageIndex();
		listOfPages = new Myset<PageEntry>();
	}
	public PageEntry getPage(String str){
 		for (int i=0;i<listOfPages.size();i++){
 			if (listOfPages.getithMember(i+1).getName().equals(str.toLowerCase()))
 				return listOfPages.getithMember(i+1);
 		}
 		return null;
 	}
	public void addPage1(String str){
		//System.out.println(str);
		PageEntry pageNew = new PageEntry(str.toLowerCase());
		if (pageNew.getPageIndex()!=null){
			listOfPages.addElement(pageNew);
			ipi.addPage(pageNew);
		}	
 	}
 	
 	public void queryFindPagesWhichContainWord(String s){
 		if (s.equals("stacks"))
			s = "stack";
		if (s.equals("structures"))
			s = "structure";
		if (s.equals("applications"))
			s = "application";
 		Myset<PageEntry> l = ipi.getPagesWhichContainWord(s.toLowerCase());
 		if (l.size()==0){
 			System.out.println("No webpage contains word '"+s+"'!");
			return;
 		}
 		String[] str = new String[]{s.toLowerCase()}; 
 		Myset<SearchResult> m = new Myset<SearchResult>();
 		for (int i=0;i<l.size();i++){
 			double r = l.getithMember(i+1).getRelevanceOfPage(str,false);
 			SearchResult sr = new SearchResult(l.getithMember(i+1),r);
 			m.addElement(sr);
 		}
 		ArrayList<SearchResult> sorted = MySort.sortThisList(m);
 		for (int i=0;i<sorted.size();i++){
 			System.out.print(sorted.get(i).getPageEntry().getName()+" , ");
 		}
 		System.out.println("");
 	}
	public void queryFindPositionsOfWordInAPage(String x,String y){
		// for (int i=0;i<listOfPages.size();i++){
 		// 	System.out.println(listOfPages.getithMember(i+1).getName()+" : "+listOfPages.getithMember(i+1).positionsOfWord("stack").size());
 		// }
 		if (x.equals("stacks"))
			x = "stack";
		if (x.equals("structures"))
			x = "structure";
		if (x.equals("applications"))
			x = "application";
			if (getPage(y.toLowerCase())==null)
				System.out.println("No webpage by the name '"+y+"' exists!");
			else{
				MyLinkedList<Position> list = getPage(y.toLowerCase()).positionsOfWord(x.toLowerCase());
				if (list.size()==0){
					System.out.println("Webpage '"+y+"' does not contain word '"+x+"'!");
					return;
				}
				DNode<Position> current = list.getTail();
				while (current.getPrev()!= list.getHead()){
				current = current.getPrev();
				System.out.print(current.getElement().getWordIndex()+" , ");
			}
			System.out.println("");
			}
 	}
 	public void queryFindPagesWhichContainAllWords(String[] s){
 		for (int st=0;st<s.length;st++){
 			s[st] = s[st].toLowerCase();
 		}
 		Myset<PageEntry> l = ipi.getPagesWhichContainWords(s);
 		if (l.size()==0){
 			System.out.println("No webpage contains all of these words!");
 			return;
 		}
 		Myset<SearchResult> m = new Myset<SearchResult>();
 		for (int i=0;i<l.size();i++){
 			double r = l.getithMember(i+1).getRelevanceOfPage(s,false);
 			SearchResult sr = new SearchResult(l.getithMember(i+1),r);
 			m.addElement(sr);
 		}
 		ArrayList<SearchResult> sorted = MySort.sortThisList(m);
 		for (int i=0;i<sorted.size();i++){
 			System.out.print(sorted.get(i).getPageEntry().getName()+" , ");
 		}
 		System.out.println("");
 	}
 	public void queryFindPagesWhichContainAnyOfTheseWords(String[] s){
 		for (int st=0;st<s.length;st++){
 			s[st] = s[st].toLowerCase();
 		}
 		Myset<PageEntry> l = listOfPages;
 		Myset<SearchResult> m = new Myset<SearchResult>();
 		for (int i=0;i<l.size();i++){
 			double r = l.getithMember(i+1).getRelevanceOfPage(s,false);
 			if (r!=0.0){
	 			SearchResult sr = new SearchResult(l.getithMember(i+1),r);
	 			m.addElement(sr);
 			}
 		}
 		ArrayList<SearchResult> sorted = MySort.sortThisList(m);
 		if (sorted.size()==0){
 			System.out.println("No webpage contans any ofthese words!");
 			return;
 		}
 		for (int i=0;i<sorted.size();i++){
 			System.out.print(sorted.get(i).getPageEntry().getName()+" , ");
 		}
 		System.out.println("");
 	}
 	public void queryFindPagesWhichContainPhrase(String[] s){
 		for (int st=0;st<s.length;st++){
 			s[st] = s[st].toLowerCase();
 		}
 		Myset<PageEntry> l = ipi.getPagesWhichContainPhrase(s);
 		if (l.size()==0){
 			System.out.println("No webpage contains this phrase!");
 			return;
 		}
 		Myset<SearchResult> m = new Myset<SearchResult>();
 		for (int i=0;i<l.size();i++){
 			double r = l.getithMember(i+1).getRelevanceOfPage(s,true);
 			SearchResult sr = new SearchResult(l.getithMember(i+1),r);
 			m.addElement(sr);
 		}
 		ArrayList<SearchResult> sorted = MySort.sortThisList(m);
 		for (int i=0;i<sorted.size();i++){
 			System.out.print(sorted.get(i).getPageEntry().getName()+" , ");
 		}
 		System.out.println("");
 	} 
 	public void performAction(String actionMessage) {
		String[] s = actionMessage.trim().split("\\s+");
		System.out.println("");
		switch (s[0]){
			case "addPage": System.out.println(actionMessage+" ");
							if (s.length == 2)
								addPage1(s[1]);
							else
								System.out.println("Arguments improper!");	
							break;
			case "queryFindPagesWhichContainWord":  System.out.print(actionMessage+" : ");
													if (s.length == 2)
														queryFindPagesWhichContainWord(s[1]);
													else
														System.out.println("Arguments improper!");	
													break;
			case "queryFindPositionsOfWordInAPage": System.out.print(actionMessage+" : ");
													if (s.length == 3)														
														queryFindPositionsOfWordInAPage(s[1],s[2]);
													else
														System.out.println("Arguments improper!");
													break;
			case "queryFindPagesWhichContainAllWords":  System.out.print(actionMessage+" : "); 
														if (s.length > 1)
															queryFindPagesWhichContainAllWords(Arrays.copyOfRange(s,1,s.length));
														else
															System.out.println("Arguments improper!");
														break;
			case "queryFindPagesWhichContainAnyOfTheseWords": System.out.print(actionMessage+" : "); 
															  if (s.length > 1) 
																queryFindPagesWhichContainAnyOfTheseWords(Arrays.copyOfRange(s,1,s.length));
															  else
															  	System.out.println("Arguments improper!");
															  break;
			case "queryFindPagesWhichContainPhrase": System.out.print(actionMessage+" : ");
													 if (s.length > 1)
													 	queryFindPagesWhichContainPhrase(Arrays.copyOfRange(s,1,s.length));
													 else
													 	System.out.println("Arguments improper!");
													 break; 											  
			default : System.out.println("Action not defined!");
		}
	}
}
