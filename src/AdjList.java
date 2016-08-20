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
    	
    	if(!vertlabels.containsKey(vertLabel)){
    	vertlabels.put(vertLabel, new MyLinkedList<T>());
    	}
    	// Implement me!
    } // end of addVertex()
	
    
    public void addEdge(T srcLabel, T tarLabel) {
    	if(srcLabel != tarLabel){
	        if(vertlabels.containsKey(srcLabel) && !vertlabels.get(srcLabel).search(tarLabel)){
	        	vertlabels.get(srcLabel).add(tarLabel);
	        }
	        if(vertlabels.containsKey(tarLabel) && !vertlabels.get(tarLabel).search(srcLabel)){
	        	vertlabels.get(tarLabel).add(srcLabel);
	        }
    	}
       

        //if srclabel and tarlabel exist then throw exception
       // otherwise add edge between them
        	
        	//vertlabels.put(tarLabel, new MyLinkedList<T>(tarLabel));
    	// Implement me!
    } // end of addEdge()
	

    public ArrayList<T> neighbours(T vertLabel) {
        ArrayList<T> neighbours = new ArrayList<T>();
        if(vertlabels.containsKey(vertLabel)){
        	
        	for(int i=0;i<vertlabels.get(vertLabel).mLength;i++){
        		neighbours.add(vertlabels.get(vertLabel).get(i));
        	}
        	
        }
        // Implement me!
        
        return neighbours;
    } // end of neighbours()
    
    
    public void removeVertex(T vertLabel) {
       if(vertlabels.containsKey(vertLabel))
       {
    	   for(int i=0;i< vertlabels.get(vertLabel).mLength;i++){    		   
    		   if(vertlabels.containsKey(vertlabels.get(vertLabel).get(i))){
    			   if(vertlabels.get(vertlabels.get(vertLabel).get(i)).search(vertLabel)){
    				   vertlabels.get(vertlabels.get(vertLabel).get(i)).remove(vertLabel);
    			   }
    		   }
    	   }
    	   vertlabels.remove(vertLabel);
       }
    	// Implement me!
    } // end of removeVertex()
	
    
    public void removeEdge(T srcLabel, T tarLabel) {
    	if(srcLabel != tarLabel){
    		if(vertlabels.containsKey(srcLabel) && vertlabels.get(srcLabel).search(tarLabel))
    			vertlabels.get(srcLabel).remove(tarLabel);
    		if(vertlabels.containsKey(tarLabel) && vertlabels.get(tarLabel).search(srcLabel))
	            vertlabels.get(tarLabel).remove(srcLabel); 
    	}
        
    	// Implement me!
    } // end of removeEdges()
	
    
    public void printVertices(PrintWriter os) {
    	String abc = null;
    	for(Entry<T, MyLinkedList<T>> e: vertlabels.entrySet()) {
    		if(abc==null){
    			abc = ""+e.getKey();
    		}else{
    			abc = abc + " "+ e.getKey();
    		}
    		
    	    
    	}
    	os.println(abc);
    	// Implement me!
    } // end of printVertices()
	
    
    public void printEdges(PrintWriter os) {
    	for(Entry<T, MyLinkedList<T>> e: vertlabels.entrySet()) {
    		for(int i=0; i< e.getValue().mLength;i++){
    			os.println(e.getKey() +" "+ e.getValue().get(i));
    		}
    	    
    	}
    	
        // Implement me!
    } // end of printEdges()
    
    
    public int shortestPathDistance(T vertLabel1, T vertLabel2) {	

    			
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
    	        
		if(shortestPathList.size()>0){
			return shortestPathList.size();
		}else{       
    	// Implement me!
    	
        // if we reach this point, source and target are disconnected
        return disconnectedDist;
		}
		
    } // end of shortestPathDistance()
    
} // end of class AdjList