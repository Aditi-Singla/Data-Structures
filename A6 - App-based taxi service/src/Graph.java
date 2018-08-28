import java.util.*;
public class Graph{
	private HashSet<Vertex> adjacencyList;
	private HashMap<Vertex,Dijkstra> minDistanceMapping; 
	public Graph(){
		adjacencyList = new HashSet<Vertex>();
		minDistanceMapping = new HashMap<Vertex,Dijkstra>();
	}
	public HashSet<Vertex> getAdjacencyList(){
		return adjacencyList;
	}
	public HashMap<Vertex,Dijkstra> getMapping(){
		return minDistanceMapping;
	}
	public Vertex getVertex(String s){
		Iterator<Vertex> it = adjacencyList.iterator();
		while (it.hasNext()){
			Vertex v = it.next();
			if (v.getName().equals(s))
				return v;
		}
		return null;
	}
	public void  updateDijkstra(){
		Iterator<Vertex> it = adjacencyList.iterator();
		while (it.hasNext()){
			Vertex v = it.next();
			Dijkstra d = new Dijkstra();
			d.solve(v);
			minDistanceMapping.put(v,d);
		}
	}
	public void addEdge(String s1,String s2,int n){
		Vertex a1 = getVertex(s1);
		Vertex a2 = getVertex(s2);
		if (a1!=null){
			if (a2!=null){
				a1.addNeighbour(a2,n);
				a2.addNeighbour(a1,n);
			}
			else{
				a2 = new Vertex(s2);
				a1.addNeighbour(a2,n);
				a2.addNeighbour(a1,n);
				adjacencyList.add(a2);	
			}
		}
		else{
			if (a2!=null){
				a1 = new Vertex(s1);
				a1.addNeighbour(a2,n);
				a2.addNeighbour(a1,n);
				adjacencyList.add(a1);
			}
			else{
				a1 = new Vertex(s1);
				a2 = new Vertex(s2);
				a1.addNeighbour(a2,n);
				a2.addNeighbour(a1,n);
				adjacencyList.add(a1);
				adjacencyList.add(a2);	
			}
		}
		updateDijkstra();
	}
	public void printGraph(){
		System.out.print("Vertices: ");
		Iterator<Vertex> it = adjacencyList.iterator();
		while (it.hasNext()){
			Vertex v = it.next();
			System.out.print((v.getName())+" , "); 
		}
		System.out.println("");
		System.out.print("Edges: ");
		Iterator<Vertex> its = adjacencyList.iterator();
		while (its.hasNext()){
			Vertex v= its.next();
			Iterator<Vertex> itr = (v.getNeighbours().keySet()).iterator();
			while (itr.hasNext()){
				Vertex u = itr.next();
				System.out.print("("+v.getName()+","+u.getName()+") , ");
			}	 
		}
		System.out.println("");
	}
}