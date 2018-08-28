import java.util.*;
public class Vertex{
	private String name;
	private HashMap<Vertex,Integer> neighbours;
	public Vertex(String a){
		name = a;
		neighbours = new HashMap<Vertex,Integer>();
	}
	public String getName(){
		return name;
	}
	public HashMap<Vertex,Integer> getNeighbours(){
		return neighbours;
	}
	public void addNeighbour(Vertex a,int n){
		neighbours.put(a,n);
	}
}