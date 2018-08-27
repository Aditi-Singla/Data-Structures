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
		PageEntry pageNew = new PageEntry(str.toLowerCase());
		if (pageNew.getPageIndex()!=null){
			listOfPages.addElement(pageNew);
			ipi.addPage(pageNew);
		}	
 	}
 	
 	public void queryFindPagesWhichContainWord(String s){
 		try{
	 		Myset<PageEntry> lst = ipi.getPagesWhichContainWord(s.toLowerCase());
	 		int i;
	 		for (i=1;i<=lst.size();i++){
	 			System.out.print(lst.getithMember(i).getName()+" , ");
	 		}
	 		System.out.println("");
	 	}
	 	catch (IllegalArgumentException e){
			System.out.println("No webpage contains word '"+s+"'!");
			return;
		}	
 	}
 	public void queryFindPositionsOfWordInAPage(String x,String y){
 		try{
 			// for (int i=0;i<listOfPages.size();i++){
	 		// 	System.out.println(listOfPages.getithMember(i+1).getName()+" : "+listOfPages.getithMember(i+1).positionsOfWord("stack").size());
	 		// }
 			if (getPage(y.toLowerCase())==null)
 				System.out.println("No webpage by the name '"+y+"' exists!");
 			else{
 				MyLinkedList<Position> list = getPage(y.toLowerCase()).positionsOfWord(x.toLowerCase());
 				DNode<Position> current = list.getHead();
 				while (current.getNext()!= list.getTail()){
					current = current.getNext();
					System.out.print(current.getElement().getWordIndex()+" , ");
				}
				System.out.println("");
 			}
 		}
 		catch (IllegalArgumentException e){
 			System.out.println("Webpage '"+y+"' does not contain word '"+x+"'!");
 		}
 	}
	public void performAction(String actionMessage) {
		String[] s = actionMessage.trim().split("\\s+");
		System.out.println("");
		switch (s[0]){
			case "addPage": System.out.println(actionMessage+" ");
							addPage1(s[1]);
							break;
			case "queryFindPagesWhichContainWord":  System.out.print(actionMessage+" : ");
													queryFindPagesWhichContainWord(s[1]);
													break;
			case "queryFindPositionsOfWordInAPage": System.out.print(actionMessage+" : ");
													queryFindPositionsOfWordInAPage(s[1],s[2]);
													break;
			default : System.out.println("Action not defined!");
		}
	}
}
