import java.util.*;
public class TaxiService{
	Graph map;
	HashSet<Taxi> taxiList;
	public TaxiService() {
		map = new Graph();
		taxiList = new HashSet<Taxi>();
	}
	public void addEdgeToGraph(String s1,String s2,int n){
		map.addEdge(s1,s2,n);
		//map.printGraph();
	}
	public void addTaxi(String name,String currentP){
		Vertex v = map.getVertex(currentP);
		if (v!=null){
			Taxi t = new Taxi(name,v);
			taxiList.add(t);
		}
		else
			System.out.println("No location by the name of '"+currentP+"' exists!");	
	}
	public Taxi getNearestTaxi(String node,int time){
		Vertex vt = map.getVertex(node); 
		int minSoFar = Integer.MAX_VALUE;
		Taxi v = null;
		Iterator<Taxi> it = taxiList.iterator();
		while (it.hasNext()){
			Taxi t = it.next();
			if (time>=t.getNextTimeWhenFree()){
				int j = map.getMapping().get(t.getLocation()).getDistances().get(vt);
				if (minSoFar>j){
					minSoFar = j;
					v = t;
				}
			}
		}
		return v;
	}
	public void attendCustomer(String source,String destination,int time){
		// Iterator<Vertex> test = map.getAdjacencyList().iterator();
		// while (test.hasNext()){
		// 	Vertex  v =test.next();
		// 	System.out.println("For "+v.getName()+" : ");
		// 	Iterator<Vertex> test1 = map.getMapping().get(v).getRoutes().keySet().iterator();
		// 	while (test1.hasNext()){
		// 		Vertex v2 = test1.next();
		// 		System.out.print(v2.getName()+" : ");
		// 		Vector<Vertex> v1 =  map.getMapping().get(v).getRoutes().get(v2);
		// 		Iterator<Vertex> test2 = v1.iterator();
		// 		while (test2.hasNext()){
		// 			System.out.print(test2.next().getName()+" , ");
		// 		}
		// 		System.out.println("");
		// 	}
		// }
		Vertex src = map.getVertex(source);
		Vertex dst = map.getVertex(destination);
		if (src!=null){
			if (dst!=null){
				Taxi taxi = getNearestTaxi(source,time);
				if (taxi!=null){
					System.out.println("Available taxis:");
					Iterator<Taxi> it = taxiList.iterator();
					while (it.hasNext()){
						Taxi t = it.next();
						if (time>=t.getNextTimeWhenFree()){
							System.out.print("Path of taxi '"+t.getName()+"' : ");
							Iterator<Vertex> ist = map.getMapping().get(t.getLocation()).getRoutes().get(src).iterator();
							while (ist.hasNext()){
								System.out.print(ist.next().getName()+" , ");
							}
							System.out.println("Time taken is "+map.getMapping().get(t.getLocation()).getDistances().get(src)+" units.");
						}
					}
					System.out.println("** Chose '"+taxi.getName()+"' to service the customer request ***");
					taxi.setFreeAfter(time+map.getMapping().get(src).getDistances().get(dst)+map.getMapping().get(taxi.getLocation()).getDistances().get(src));
					System.out.print("Path of Customer : ");
					Iterator<Vertex> ist = map.getMapping().get(src).getRoutes().get(dst).iterator();
					while (ist.hasNext()){
						System.out.print(ist.next().getName()+" , ");
					}
					System.out.println("Time taken is "+map.getMapping().get(src).getDistances().get(dst)+" units.");
					taxi.changeLocation(dst);
				} 
				else{
					System.out.println("No taxi available");
				}
			}	
			else
				System.out.println("No location by the name of '"+destination+"' exists!");
		}
		else
			System.out.println("No location by the name of '"+source+"' exists!");	

	}
	public void printTaxiPosition(int time){
		Iterator<Taxi> it = taxiList.iterator();
		int i = 0;
		while (it.hasNext()){
			Taxi t = it.next();
			if (time>=t.getNextTimeWhenFree()){
				i++;
				System.out.println(t.getName()+" : "+t.getLocation().getName());
			}
		}
		if (i == 0)
			System.out.println("No taxi is available!");
	}
	int time = 0;
	public void performAction(String actionMessage) {
		String[] s = actionMessage.trim().split("\\s+");
		System.out.println("");
		switch (s[0]){
			case "edge": System.out.print(actionMessage+" ");
						 addEdgeToGraph(s[1],s[2],Integer.parseInt(s[3]));
						 break;
			case "taxi": System.out.print(actionMessage+" ");
						 addTaxi(s[1],s[2]);
						 break;
			case "customer" : System.out.println(actionMessage+":");
							  if (Integer.parseInt(s[3])>=time){	
								  attendCustomer(s[1],s[2],Integer.parseInt(s[3]));
							  		time = Integer.parseInt(s[3]);
							  		break;
							  }
							  else{
							  	System.out.println("Cannot go back in time!");
							  	break;
							  }

			case "printTaxiPosition" : System.out.println(actionMessage+":");
									  if (Integer.parseInt(s[1])>=time){	
										  printTaxiPosition(Integer.parseInt(s[1]));
									  		time = Integer.parseInt(s[1]);
									  		break;
									  }
									  else{
									  	System.out.println("Cannot go back in time!");
									  	break;
									  }
			default : System.out.println("Action not defined!");
		}
	}
}
