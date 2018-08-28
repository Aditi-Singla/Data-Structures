import java.util.*; 
public class Dijkstra{
	private HashSet<Vertex> settled;
	private HashSet<Vertex> unsettled;
	private HashMap<Vertex,Integer> distance;		
	private HashMap<Vertex,Vector<Vertex>> route;
	public Dijkstra(){
		distance = new HashMap<Vertex,Integer>();
		route = new HashMap<Vertex,Vector<Vertex>>();
	}
	public HashMap<Vertex,Integer> getDistances(){
		return distance;
	}
	public HashMap<Vertex,Vector<Vertex>> getRoutes(){
		return route;
	}
	public void solve(Vertex source){
		settled = new HashSet<Vertex>();
		unsettled = new HashSet<Vertex>();
		route = new HashMap<Vertex,Vector<Vertex>>();
		distance = new HashMap<Vertex,Integer>();
		unsettled.add(source);
		distance.put(source,0);
		Vector<Vertex> hst = new Vector<Vertex>();
		route.put(source,hst);
		while (unsettled.size()!=0){
			Vertex evaluationNode = getNodeWithLowestDistance(unsettled);
			unsettled.remove(evaluationNode);
			settled.add(evaluationNode);
			Vector<Vertex> v = route.get(evaluationNode);
			v.add(evaluationNode);
			route.put(evaluationNode,v);
			findminDistancenRoute(evaluationNode);
		}
	}
	public Vertex getNodeWithLowestDistance(HashSet<Vertex> v){
		int minSoFar = Integer.MAX_VALUE ;
		Vertex ver = new Vertex("") ;
		Iterator<Vertex> it = v.iterator();
		while (it.hasNext()){
			Vertex ve = it.next();
			if (distance.get(ve)<minSoFar){
				minSoFar = distance.get(ve);
				ver = ve;
			}
		}
		return ver;
	}
	public void findminDistancenRoute(Vertex v){
		Iterator<Vertex> it = v.getNeighbours().keySet().iterator();
		while (it.hasNext()){
			Vertex node = it.next();
			if (!settled.contains(node)){
				int newDist = distance.get(v) + v.getNeighbours().get(node);
				if (route.get(node)==null){
					Vector<Vertex> v1 = (Vector<Vertex>)route.get(v).clone();
					route.put(node,v1);
					distance.put(node,newDist);
					unsettled.add(node);
				}
				else{
					if (distance.get(node)>newDist){
						distance.put(node,newDist);
						Vector<Vertex> v1 = (Vector<Vertex>)route.get(v).clone();
						route.put(node,v1);
					}
				}	
			}
		} 
	}
}