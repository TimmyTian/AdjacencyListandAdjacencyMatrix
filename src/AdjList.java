import java.io.*;
import java.util.*;
import java.util.Map.Entry;

import javax.management.RuntimeErrorException;

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
public class AdjList <T extends Object> implements FriendshipGraph<T>
{   
		
	private Map<T, MyLinkedList<T>> vertlabels;
   
	/**
	 * Contructs empty graph.
	 */
    public AdjList() {
    	vertlabels = new HashMap<T, MyLinkedList<T>>();
    	
    } // end of AdjList()
    
    /*
     * adding Vertex to Adjacency List
     * @see FriendshipGraph#addVertex(java.lang.Object)
     */
    public void addVertex(T vertLabel) {
    	
    	if(!vertlabels.containsKey(vertLabel))
    		vertlabels.put(vertLabel, new MyLinkedList<T>());
    	if(vertlabels.containsKey(vertLabel))
    		return;
    	
    	// Implement me!
    } // end of addVertex()
	
    /*
     * add Edges to the Adjacency List
     * @see FriendshipGraph#addEdge(java.lang.Object, java.lang.Object)
     */
    public void addEdge(T srcLabel, T tarLabel) throws IllegalArgumentException {

	        if(!vertlabels.containsKey(srcLabel) || !vertlabels.containsKey(tarLabel))
	        	throw new IllegalArgumentException("The vertex does not exist");
	        
	        if(vertlabels.containsKey(srcLabel) && !vertlabels.get(srcLabel).search(tarLabel)){
	        	vertlabels.get(srcLabel).add(tarLabel);
	        }
	        if(vertlabels.containsKey(tarLabel) && !vertlabels.get(tarLabel).search(srcLabel)){
	        	vertlabels.get(tarLabel).add(srcLabel);
	        }
	        

        //if srclabel and tarlabel exist then throw exception
       // otherwise add edge between them
        	
    } // end of addEdge()
	
    /*
     * Return the Neighbours of the given Vertex
     * @see FriendshipGraph#neighbours(java.lang.Object)
     */
    public ArrayList<T> neighbours(T vertLabel) throws IllegalArgumentException {
    	if(!vertlabels.containsKey(vertLabel))
    		throw new IllegalArgumentException("The Vertex doesn't exist");    	
    	
    	return vertlabels.get(vertLabel).neighbours();
        
    } // end of neighbours()
    
    
    /*
     * remove the vertex from the Adjacency List
     * @see FriendshipGraph#removeVertex(java.lang.Object)
     */
    public void removeVertex(T vertLabel) throws IllegalArgumentException {
    	if(!vertlabels.containsKey(vertLabel))
    		throw new IllegalArgumentException("The Vertex doesn't exist");
    
    	if(vertlabels.containsKey(vertLabel))
    	{
    	  for(T neighbours:vertlabels.get(vertLabel).neighbours()){
    		  if(vertlabels.containsKey(neighbours))
    			  vertlabels.get(neighbours).remove(vertLabel);
    	  }
    	   vertlabels.remove(vertLabel);
    	}
    	
    } // end of removeVertex()
	
    /*
     * Remove the Edge from the Adjacency List
     * @see FriendshipGraph#removeEdge(java.lang.Object, java.lang.Object)
     */
    public void removeEdge(T srcLabel, T tarLabel) throws IllegalArgumentException {
    	if(!vertlabels.containsKey(srcLabel) || !vertlabels.containsKey(tarLabel))
    		throw new IllegalArgumentException("The Vertex doesn't exist");
    	
    	if(vertlabels.containsKey(srcLabel))
    		vertlabels.get(srcLabel).remove(tarLabel);
    	if(vertlabels.containsKey(tarLabel))
    		vertlabels.get(tarLabel).remove(srcLabel);
    	
    } // end of removeEdges()
	
    
    /*
     * Print all the Vertices 
     * @see FriendshipGraph#printVertices(java.io.PrintWriter)
     */
    public void printVertices(PrintWriter os) {
    	for(Entry<T, MyLinkedList<T>> e: vertlabels.entrySet()) {
    			os.print(e.getKey()+" ");
    	}
    		os.println("");
    } // end of printVertices()
	
    
    /*
     * Print all the Edges 
     * @see FriendshipGraph#printVertices(java.io.PrintWriter)
     */
    public void printEdges(PrintWriter os) {
    	for(Entry<T, MyLinkedList<T>> e: vertlabels.entrySet()) {
    		for(T neighbour : e.getValue().neighbours()){
    			os.println(e.getKey() +" "+ neighbour);
    		}
    	}
    	
        // Implement me!
    } // end of printEdges()
    
    
    /*
     * Return the shortest path between two vertices
     * @see FriendshipGraph#shortestPathDistance(java.lang.Object, java.lang.Object)
     */
	public int shortestPathDistance(T vertLabel1, T vertLabel2) throws IllegalArgumentException {
		// Implementation of Dijkstra Algorithm for Shortest Path Calculation
		
		// Algo:
		// 1. Take the unvisited node with minimum weight.
		// 2. Visit all its neighbours.
		// 3. Update the distances for all the neighbours (In the Queue).
		// Repeat the process till all the connected nodes are visited.
		
		if(!vertlabels.containsKey(vertLabel1) || !vertlabels.containsKey(vertLabel2))
    		throw new IllegalArgumentException("The Vertex doesn't exist");
		
		for(Entry<T, MyLinkedList<T>> e: vertlabels.entrySet()) {
    		e.getValue().minDistance = Integer.MAX_VALUE;
    		e.getValue().path = new ArrayList<>();
    	}
		vertlabels.get(vertLabel1).minDistance =0; 
		
		Queue<T> queue = new LinkedList<T>();
		queue.add(vertLabel1);
		

		while(!queue.isEmpty()){

			T u = queue.poll();
			for(T neighbour: neighbours(u)){
				int newDist = vertlabels.get(u).minDistance+1;
				
				if(vertlabels.get(neighbour).minDistance>newDist){
					vertlabels.get(neighbour).minDistance = newDist;
					//update with the new shorter distance from the src vertex
					vertlabels.get(neighbour).path = vertlabels.get(u).path;
					//update with the new shorter path from the src vertex
					vertlabels.get(neighbour).path.add(u);
					
					queue.add(neighbour);
				}
			}
		}
		if(vertlabels.get(vertLabel2).path.size() == 0 || vertlabels.get(vertLabel2).minDistance ==0)
			return disconnectedDist;
		else
			return vertlabels.get(vertLabel2).minDistance;
		
	}
	// end of shortestPathDistance()
    

	 
} // end of class AdjList