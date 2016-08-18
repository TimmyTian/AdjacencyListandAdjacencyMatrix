import java.io.*;
import java.util.*;

/**
 * Adjacency list implementation for the FriendshipGraph interface.
 * 
 * Your task is to complete the implementation of this class.  You may add methods, but ensure your modified class compiles and runs.
 *
 * @author Jeffrey Chan, 2016.
 */
public class AdjList <T extends Object> implements FriendshipGraph<T>
{   
		
	Map<T, MyLinkedList<T>> vertlabels = new HashMap<T, MyLinkedList<T>>();

//       MyLinkedList[] linkedlist = new MyLinkedList[];
    /**
	 * Contructs empty graph.
	 */
    public AdjList() {
    	
    	
    } // end of AdjList()
    
    
    public void addVertex(T vertLabel) {
    	
    	if(vertLabel==null) 
    	{
    		return;
    	}
    	if(vertlabels.containsKey(vertLabel)) {
    		System.out.println("Already exists");
    		return;
    	}
    	vertlabels.put(vertLabel, new MyLinkedList<T>(vertLabel));
    	vertlabels.get(vertLabel).print();
    	// Implement me!
    } // end of addVertex()
	
    
    public void addEdge(T srcLabel, T tarLabel) {
        if(!(vertlabels.containsKey(srcLabel)&& vertlabels.containsKey(tarLabel)))
        {
        	System.out.println("edge doesnot exist");
        	return;
        }
       

        //if srclabel and tarlabel exist then throw exception
       // otherwise add edge between them
        	MyLinkedList<T> tmp = vertlabels.put(srcLabel, new MyLinkedList<T>(srcLabel));
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
    	   vertlabels.remove(vertLabel);
       }
    	// Implement me!
    } // end of removeVertex()
	
    
    public void removeEdge(T srcLabel, T tarLabel) {
        if(vertlabels.containsKey(srcLabel)&& vertlabels.get(srcLabel).search(tarLabel))
        {
        	vertlabels.get(srcLabel).remove(tarLabel);
        }
    	// Implement me!
    } // end of removeEdges()
	
    
    public void printVertices(PrintWriter os) {
        
    	
    	// Implement me!
    } // end of printVertices()
	
    
    public void printEdges(PrintWriter os) {
        // Implement me!
    } // end of printEdges()
    
    
    public int shortestPathDistance(T vertLabel1, T vertLabel2) {
    	// Implement me!
    	
        // if we reach this point, source and target are disconnected
        return disconnectedDist;    	
    } // end of shortestPathDistance()
    
} // end of class AdjList