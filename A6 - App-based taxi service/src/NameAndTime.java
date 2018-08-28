public class NameAndTime{
	private int time;
	private Taxi name;
	public NameAndTime(int t,Taxi s){
		time = t;
		name = s;
	}
	public int getTiming(){
		return time;
	}
	public Taxi getName(){
		return name;
	}
}