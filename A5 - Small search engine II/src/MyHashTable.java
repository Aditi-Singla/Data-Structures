import java.io.*;
import java.util.*;
public class MyHashTable{
	private final static int TABLE_SIZE = 100;
	private PageIndex[] table;
	public MyHashTable(){
		table = new PageIndex[TABLE_SIZE];
		for (int i=0;i<TABLE_SIZE;i++){
			table[i] = null;
		}
	}
	private int getHashIndex(String str){
		int i = Math.abs(str.hashCode()%100);
		return (i);
	}	
	public void addPositionsForWord1(WordEntry w){
		String name = w.getString();
		int hash = getHashIndex(name);
		if (table[hash] == null)
			table[hash] = new PageIndex();
		table[hash].addPositionsForWord(w.getString(),w.getAllPositionsForThisWord());
	}
	public WordEntry getWord(String str){
		int hash = getHashIndex(str);
		if (table[hash] == null){
			return null;
		}
		else{
			PageIndex l = table[hash];
			if (l.isMember(str))
				return l.getWordEntry(str);
			else{
				return null;
			}
		}
	}
}