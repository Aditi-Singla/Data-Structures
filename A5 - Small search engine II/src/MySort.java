import java.util.*;
import java.io.*;
public class MySort{
	public static ArrayList<SearchResult> sortThisList(Myset<SearchResult> listOfSortableEntries){
		ArrayList<SearchResult> al = new ArrayList<SearchResult>();
		for (int i=1;i<=listOfSortableEntries.size();i++){
			al.add(listOfSortableEntries.getithMember(i));
		}
		//Collections.sort(al);
		int n = al.size();
        int k;
        for (int m = n; m >= 0; m--) {
            for (int i = 0; i < n - 1; i++) {
                k = i + 1;
                if (al.get(i).compareTo(al.get(k))==-1) {
                	SearchResult temp;
			        temp = al.get(i);
			        al.set(i,al.get(k));
			        al.set(k,temp);
                }
            }
        }
		return al;
	}
}