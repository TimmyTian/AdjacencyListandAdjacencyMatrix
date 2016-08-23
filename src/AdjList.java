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
 */
public class AdjList <T extends Object> implements FriendshipGraph<T>
{   
		
	private Map<T, MyLinkedList<T>> vertlabels;
//       MyLinkedList[] linkedlist = new MyLinkedList[];
    /**
	 * Contructs empty graph.
	 */
    public AdjList() {
    	vertlabels = new HashMap<T, MyLinkedList<T>>();
    	
    } // end of AdjList()
    
    
    public void addVertex(T vertLabel) {
    	
    	if(!vertlabels.containsKey(vertLabel))
    		vertlabels.put(vertLabel, new MyLinkedList<T>());
    	
    	// Implement me!
    } // end of addVertex()
	
    
    public void addEdge(T srcLabel, T tarLabel) {
	        if(!vertlabels.containsKey(srcLabel)){
	        	vertlabels.put(srcLabel, new MyLinkedList<T>());
	        }
	        if(!vertlabels.containsKey(tarLabel)){
	        	vertlabels.put(tarLabel, new MyLinkedList<T>());
	        }
	        
	        if(vertlabels.containsKey(srcLabel) && !vertlabels.get(srcLabel).search(tarLabel)){
	        	vertlabels.get(srcLabel).add(tarLabel);
	        }
	        if(vertlabels.containsKey(tarLabel) && !vertlabels.get(tarLabel).search(srcLabel)){
	        	vertlabels.get(tarLabel).add(srcLabel);
	        }

        //if srclabel and tarlabel exist then throw exception
       // otherwise add edge between them
        	
        	//vertlabels.put(tarLabel, new MyLinkedList<T>(tarLabel));
    	// Implement me!
    } // end of addEdge()
	

    public ArrayList<T> neighbours(T vertLabel) {
    	return vertlabels.get(vertLabel).neighbours();
        // Implement me!
    } // end of neighbours()
    
    
    public void removeVertex(T vertLabel) {
       if(vertlabels.containsKey(vertLabel))
       {
    	  for(T neighbours:vertlabels.get(vertLabel).neighbours()){
    		  if(vertlabels.containsKey(neighbours))
    			  vertlabels.get(neighbours).remove(vertLabel);
    	  }
    	   vertlabels.remove(vertLabel);
       }
    	// Implement me!
    } // end of removeVertex()
	
    
    public void removeEdge(T srcLabel, T tarLabel) {    	
    	if(vertlabels.containsKey(srcLabel))
    		vertlabels.get(srcLabel).remove(tarLabel);
    	if(vertlabels.containsKey(tarLabel))
    		vertlabels.get(tarLabel).remove(srcLabel);
    	
    	// Implement me!
    } // end of removeEdges()
	
    
    public void printVertices(PrintWriter os) {
    	for(Entry<T, MyLinkedList<T>> e: vertlabels.entrySet()) {
    			os.print(e.getKey()+" ");
    	}
    		os.println("");
    	    
    	
    	//os.println(abc);
    	// Implement me!
    } // end of printVertices()
	
    
    public void printEdges(PrintWriter os) {
    	for(Entry<T, MyLinkedList<T>> e: vertlabels.entrySet()) {
    		for(T neighbour : e.getValue().neighbours()){
    			os.println(e.getKey() +" "+ neighbour);
    		}
    	}
    	
        // Implement me!
    } // end of printEdges()
    
    
    
    
	public int shortestPathDistance(T vertLabel1, T vertLabel2){
		// Algo:
		// 1. Take the unvisited node with minimum weight.
		// 2. Visit all its neighbours.
		// 3. Update the distances for all the neighbours (In the Priority Queue).
		// Repeat the process till all the connected nodes are visited.
		
		for(Entry<T, MyLinkedList<T>> e: vertlabels.entrySet()) {
    		e.getValue().minDistance = Integer.MAX_VALUE;
    		e.getValue().path = new ArrayList<>();
    	}
		vertlabels.get(vertLabel1).minDistance =0; 
		
		//PriorityQueue<T> queue = new PriorityQueue<T>();
		Queue<T> queue = new LinkedList<T>();
		queue.add(vertLabel1);
		


		while(!queue.isEmpty()){

			T u = queue.poll();
			for(T neighbour: neighbours(u)){
				int newDist = vertlabels.get(u).minDistance+1;
				
				if(vertlabels.get(neighbour).minDistance>newDist){
					// Remove the node from the queue to update the distance value.
					vertlabels.get(neighbour).minDistance = newDist;
					// Take the path visited till now and add the new node.s
					
					
					vertlabels.get(neighbour).path = vertlabels.get(u).path;
					vertlabels.get(neighbour).path.add(u);
					//Reenter the node with new distance.
					queue.add(neighbour);
				}
			}
		}
		if(vertlabels.get(vertLabel2).path.size() == 0 || vertlabels.get(vertLabel2).minDistance ==0)
			return disconnectedDist;
		else
			return vertlabels.get(vertLabel2).minDistance;
		
	}
    
	 public int shortestPathDistance3(T vertLabel1, T vertLabel2) {	
	
		ArrayList<T> shortestPathList = new ArrayList<T>();
		HashMap<T, Boolean> visited = new HashMap<T, Boolean>();

		if (vertLabel1 == vertLabel2)
			return disconnectedDist;
		Queue<T> queue = new LinkedList<T>();
		Stack<T> pathStack = new Stack<T>();

		queue.add(vertLabel1);
		pathStack.add(vertLabel1);
		visited.put(vertLabel1, true);
		
		while(!queue.isEmpty())
		{
			T u = queue.poll();
			ArrayList<T> adjList = neighbours(u);
			for(T v : adjList)
			{	
				if(!visited.containsKey(v))
				{
					queue.add(v);
					visited.put(v, true);
					pathStack.add(v);
					if(u == vertLabel2)
						break;
				}
			}
		}
		
		
		//To find the path
		T node, currentSrc=vertLabel2;
		shortestPathList.add(vertLabel2);
		while(!pathStack.isEmpty())
		{
			node = pathStack.pop();
			if(vertlabels.get(currentSrc).search(node))
			{
				shortestPathList.add(node);
				currentSrc = node;
				if(node == vertLabel1)
					break;
			}
		}
    	
		if(shortestPathList.size()>1){
			return shortestPathList.size()-1;
		}else{       
    	// Implement me!
    	
        // if we reach this point, source and target are disconnected
        return disconnectedDist;
		}
		
    } // end of shortestPathDistance()
    
    
	 
} // end of class AdjList