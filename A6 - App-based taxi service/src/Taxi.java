public class Taxi{
	private String name;
	private Vertex currentPosition;
	//private int availability;
	private int nextTimeWhenFree;
	public Taxi(String s,Vertex cp){
		name = s;
		currentPosition = cp;
		//availability = 1;
		nextTimeWhenFree = 0;   // 1 : Available ; 0 : Busy
		
	}
	public String getName(){
		return name;
	}
	public Vertex getLocation(){
		return currentPosition;
	}
	// public int isAvailable(){
	// 	return availability;
	// }
	public int getNextTimeWhenFree(){
		return nextTimeWhenFree;
	}
	public void changeLocation(Vertex v){
		currentPosition = v;
	}
	public void setFreeAfter(int x){
		nextTimeWhenFree = x;
	}

}