import java.io.*;
import java.util.*;
import java.util.Map.Entry;


/**
 * Adjacency matrix implementation for the FriendshipGraph interface.
 * 
 * Your task is to complete the implementation of this class.  You may add methods, but ensure your modified class compiles and runs.
 *
 * @author Jeffrey Chan, 2016.
 */
public class AdjMatrix <T extends Object> implements FriendshipGraph<T>
{
	private Map<T, Integer> nodeMap;
	private Map<Integer, T> nodeMapReverse;
	private static final int GRAPH_SIZE = 10;
	private static int counter = 0;
	int[][] adjmatrix;
	/**
	 * Contructs empty graph.
	 */
	public AdjMatrix() {
		nodeMap = new HashMap<>();
		nodeMapReverse = new HashMap<>();
		adjmatrix = new int[GRAPH_SIZE][GRAPH_SIZE];  //TO DO check in lab for size of an array
		for (int i = 0; i < adjmatrix.length; i++) {
			for (int j = 0; j < adjmatrix[i].length; j++) {
				adjmatrix[i][j] = 0;
			}
		}
	} // end of AdjMatrix()


	public void addVertex(T vertLabel) {
		if(!nodeMap.containsKey(vertLabel)){
			nodeMap.put(vertLabel,counter);
			nodeMapReverse.put(counter,vertLabel);
			counter++;
		}

	} // end of addVertex()


	public void addEdge(T srcLabel, T tarLabel) {
		int srcInt = nodeMap.get(srcLabel);
		int tarInt = nodeMap.get(tarLabel);
		adjmatrix[srcInt][tarInt] = 1;
		adjmatrix[tarInt][srcInt] = 1;
		
//		Node distance;
//		if(srcLabel != tarLabel){
//			if(nodeMap.containsKey(srcLabel) && !nodeMap.get(srcLabel).search(tarLabel)){
//				nodeMap.get(srcLabel).add(tarLabel);
//			}
//			if(nodeMap.containsKey(tarLabel) && !nodeMap.get(tarLabel).search(srcLabel)){
//				nodeMap.get(tarLabel).add(srcLabel);
//			}
			// Node[srcLabel][tarLabel]=distance;
			// Node[tarLabel][srcLabel]=distance;
		//}
		// Implement me!
	} // end of addEdge()


//	public String toString() {
//		int numberOfVertices = adjmatrix.length;
//		String result = "";
//
//		// For Adjacency Matrix
//		for (int j = 0, k = 0; j < numberOfVertices; j++) {
//			result += j + ":";
//			for (k = j; k < numberOfVertices; k++) {
//				if (adjmatrix[j][k] != null) {
//					result += " (" + k + "," + adjmatrix[j][k] + ")";
//				}
//			}
//		}
//		return toString();
//	}

	public ArrayList<T> neighbours(T vertLabel) {
		ArrayList<T> neighbours = new ArrayList<T>();
		// Implement me!
		int vertex = nodeMap.get(vertLabel);
		for(int i=0;i<counter;i++){
			if(adjmatrix[vertex][i]==1){
				neighbours.add(nodeMapReverse.get(i));
			}
		}
		
		
		return neighbours;
	} // end of neighbours()


	public void removeVertex(T vertLabel) {
		// Implement me!
		int vertex = nodeMap.get(vertLabel);
		for(int i=0;i<counter;i++){
		adjmatrix[vertex][i] = 0;
		adjmatrix[i][vertex] = 0;
		}
		nodeMap.remove(vertLabel);
		nodeMapReverse.remove(vertex);
	} // end of removeVertex()


	public void removeEdge(T srcLabel, T tarLabel) {
		// Implement me!
		int srcInt = nodeMap.get(srcLabel);
		int tarInt = nodeMap.get(tarLabel);
		adjmatrix[srcInt][tarInt] = 0;
		adjmatrix[tarInt][srcInt] = 0;
	} // end of removeEdges()


	public void printVertices(PrintWriter os) {
		for(Entry<T, Integer> e: nodeMap.entrySet()) {
			os.print(e.getKey()+" ");
		}
		os.println("");
	} // end of printVertices()


	public void printEdges(PrintWriter os) {
		// Implement me!
		for(Entry<T, Integer> e: nodeMap.entrySet()) {
			int srcvertex = e.getValue();
			
			for(Entry<T, Integer> f: nodeMap.entrySet()) {
				int tarvertex = f.getValue();
				
				if(srcvertex != tarvertex){
					if(adjmatrix[srcvertex][tarvertex]==1)
						os.println(e.getKey() +" "+ f.getKey());
				}
	    	}
    	}
	} // end of printEdges()


	public int shortestPathDistance(T vertLabel1, T vertLabel2) {
		// Implement me!

		// if we reach this point, source and target are disconnected
		return disconnectedDist;    	
	} // end of shortestPathDistance()

} // end of class AdjMatrix