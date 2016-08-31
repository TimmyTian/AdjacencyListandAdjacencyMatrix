import java.io.*;
import java.util.*;
import java.util.Map.Entry;


/**
 * Adjacency list implementation for the FriendshipGraph interface.
 * 
 * Your task is to complete the implementation of this class.  You may add methods, but ensure your modified class compiles and runs.
 *
 * @author Jeffrey Chan, 2016.
 * @author Anto Dominic - S3553172
 * @author Rashiv Romio Bhusal - S3511441
 * 
 */
public class AdjMatrix <T extends Object> implements FriendshipGraph<T>
{
	private Map<T, Integer> nodeMap;
	private Map<Integer, T> nodeMapReverse;
	private static int GRAPH_SIZE = 8;// 4096;
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

	/*
     * adding Vertex to Adjacency Matrix
     * @see FriendshipGraph#addVertex(java.lang.Object)
     */
	
	public void addVertex(T vertLabel) {
		// for increasing the graph size if graph size is less than counter
		if(counter >= GRAPH_SIZE){
			int[][] tempmatrix = new int[GRAPH_SIZE*GRAPH_SIZE][GRAPH_SIZE*GRAPH_SIZE];
			
			for (int i = 0; i < tempmatrix.length; i++) {
				for (int j = 0; j < tempmatrix[i].length; j++) {
					if(i< GRAPH_SIZE && j < GRAPH_SIZE){
						tempmatrix[i][j] = adjmatrix[i][j];
					}else
						tempmatrix[i][j] = 0;
				}
			}
			
			adjmatrix = tempmatrix;
			GRAPH_SIZE = GRAPH_SIZE*GRAPH_SIZE;
			
		}
		
		if(!nodeMap.containsKey(vertLabel)){
			nodeMap.put(vertLabel,counter);
			nodeMapReverse.put(counter,vertLabel);
			counter++;
		}
		if(nodeMap.containsKey(vertLabel))
    		return;
	} // end of addVertex()

    /*
     * add Edges to the Adjacency Matrix
     * @see FriendshipGraph#addEdge(java.lang.Object, java.lang.Object)
     */
	public void addEdge(T srcLabel, T tarLabel) throws IllegalArgumentException {
		if(!nodeMap.containsKey(srcLabel) || !nodeMap.containsKey(tarLabel))
    		throw new IllegalArgumentException("The Vertex doesn't exist");
		
		int srcInt = nodeMap.get(srcLabel);
		int tarInt = nodeMap.get(tarLabel);
		adjmatrix[srcInt][tarInt] = 1;
		adjmatrix[tarInt][srcInt] = 1;
		
	} // end of addEdge()


    /*
     * Return the Neighbours of the given Vertex
     * @see FriendshipGraph#neighbours(java.lang.Object)
     */
	public ArrayList<T> neighbours(T vertLabel) throws IllegalArgumentException  {
		if(!nodeMap.containsKey(vertLabel))
    		throw new IllegalArgumentException("The Vertex doesn't exist");
		
		ArrayList<T> neighbours = new ArrayList<T>();
		int vertex = nodeMap.get(vertLabel);
		for(int i=0;i<counter;i++){
			if(adjmatrix[vertex][i]==1){
				neighbours.add(nodeMapReverse.get(i));
			}
		}
		return neighbours;
	} // end of neighbours()

    
	/*
     * remove the vertex from the Adjacency Matrix
     * @see FriendshipGraph#removeVertex(java.lang.Object)
     */
	public void removeVertex(T vertLabel) throws IllegalArgumentException  {
		if(!nodeMap.containsKey(vertLabel))
    		throw new IllegalArgumentException("The Vertex doesn't exist");
		
		int vertex = nodeMap.get(vertLabel);
		for(int i=0;i<counter;i++){
		adjmatrix[vertex][i] = 0;
		adjmatrix[i][vertex] = 0;
		}
		nodeMap.remove(vertLabel);
		nodeMapReverse.remove(vertex);
	} // end of removeVertex()

	
    /*
     * Remove the Edge from the Adjacency Matrix
     * @see FriendshipGraph#removeEdge(java.lang.Object, java.lang.Object)
     */
	public void removeEdge(T srcLabel, T tarLabel) throws IllegalArgumentException  {
		if(!nodeMap.containsKey(srcLabel) || !nodeMap.containsKey(tarLabel))
    		throw new IllegalArgumentException("The Vertex doesn't exist");
		
		int srcInt = nodeMap.get(srcLabel);
		int tarInt = nodeMap.get(tarLabel);
		adjmatrix[srcInt][tarInt] = 0;
		adjmatrix[tarInt][srcInt] = 0;
	} // end of removeEdges()

	
    /*
     * Print all the Vertices 
     * @see FriendshipGraph#printVertices(java.io.PrintWriter)
     */
	public void printVertices(PrintWriter os) {
		for(Entry<T, Integer> e: nodeMap.entrySet()) {
			os.print(e.getKey()+" ");
		}
		os.println("");
	} // end of printVertices()

	
    /*
     * Print all the Edges 
     * @see FriendshipGraph#printVertices(java.io.PrintWriter)
     */
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

    /*
     * Return the shortest path between two vertices
     * @see FriendshipGraph#shortestPathDistance(java.lang.Object, java.lang.Object)
     */
	public int shortestPathDistance(T vertLabel1, T vertLabel2) throws IllegalArgumentException {
		// Implementation of Dijkstra Algorithm for Shortest Path Calculation
		
		// Algo:
		// 1. Initialise a distance matrix of size counter as infinite and visited_array of size counter as false
		// 2. Visit all its neighbours.
		// 3. Update the distances for all the neighbours (In the Distance Array).
		// Repeat the process till all the connected nodes are visited
		
		if(!nodeMap.containsKey(vertLabel1) || !nodeMap.containsKey(vertLabel2))
    		throw new IllegalArgumentException("The Vertex doesn't exist");
		
		int src = nodeMap.get(vertLabel1);
		int dest = nodeMap.get(vertLabel2);
		
		int distance[] = new int[counter]; 
		
		Boolean visited_array[] = new Boolean[counter];
		
		// Initialize all distances as INFINITE and stpSet[] as false
		for (int i = 0; i < counter; i++){
		distance[i] = Integer.MAX_VALUE;
		visited_array[i] = false;
		}
		
		// Distance of source vertex from itself is always 0
		distance[src] = 0;
		
		// Find shortest path for all vertices
		for (int count = 0; count < counter-1; count++)
		{
			// Pick the minimum distance vertex from the set of vertices
			// not yet processed. u is always equal to src in first
			// iteration.
				
			int min = Integer.MAX_VALUE, min_index=-1;
				 
		    for (int v = 0; v < counter; v++)
		       if (visited_array[v] == false && distance[v] <= min){
		         min = distance[v];
		         min_index = v;
		       }
		        
			int u = min_index;
			
			// Mark the picked vertex as processed
			visited_array[u] = true;
			
			// Update dist value of the adjacent vertices of the
			// picked vertex.
			for (int v = 0; v < counter; v++)
				if (!visited_array[v] && adjmatrix[u][v]!=0 && distance[u] != Integer.MAX_VALUE && distance[u]+adjmatrix[u][v] < distance[v])
					distance[v] = distance[u] + adjmatrix[u][v];
			}
			
			// print the constructed distance array
			if(distance[dest]>0 && distance[dest]!=Integer.MAX_VALUE)
		    	return distance[dest];
		    else
		    	return disconnectedDist;   	
	} // end of shortestPathDistance()

} // end of class AdjMatrix