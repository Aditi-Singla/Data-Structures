import java.util.*;
public class SearchResult implements Comparable{
	PageEntry page;
	double relevance;
	public SearchResult(PageEntry p,double r){
		this.page = p;
		this.relevance = r;
	}
	public PageEntry getPageEntry(){
		return page;
	}
	public double getRelevance(){
		return relevance;
	}
	public int compareTo(Object obj){
		SearchResult sr = (SearchResult)obj;
		if (relevance==sr.relevance)
			return 0;
		else if (relevance>sr.relevance)
			return 1;
		else
			return -1;
	}
} 