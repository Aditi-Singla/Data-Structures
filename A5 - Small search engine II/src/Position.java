public class Position{
	private PageEntry p;
	private int wordIndex;
	public Position(PageEntry x,int y){
		p = x;
		wordIndex = y;
	}
	public PageEntry getPageEntry(){
		return p;
	}
	public int getWordIndex(){
		return wordIndex;
	}
	public Boolean equals(Position other){
		return (this.getPageEntry().equals(other.getPageEntry()) && this.getWordIndex()==other.getWordIndex());
	}
}