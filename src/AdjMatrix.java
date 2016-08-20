import java.io.*;
import java.util.*;


/**
 * Adjacency matrix implementation for the FriendshipGraph interface.
 * 
 * Your task is to complete the implementation of this class.  You may add methods, but ensure your modified class compiles and runs.
 *
 * @author Jeffrey Chan, 2016.
 */
public class AdjMatrix <T extends Object> implements FriendshipGraph<T>
{
	private Map<T, Node<T>> Node;
	Node[][] adjmatrix;
	/**
	 * Contructs empty graph.
	 */
    public AdjMatrix() {
    	adjmatrix = new Node[Node.size()][Node.size()];
    	// Implement me!
    } // end of AdjMatrix()
    
    
    public void addVertex(T vertLabel) {
    	if(!Node.containsKey(vertLabel)){
        // Node.put(vertLabel,);                  //TO DO  check here
         //put((vertLabel, new Node[Node.size()][Node.size()]);
        	}
    
        // Implement me!
    } // end of addVertex()
	
    
    public void addEdge(T srcLabel, T tarLabel) {
    	Node distance;
    	if(srcLabel != tarLabel){
	        if(Node.containsKey(srcLabel) && !Node.get(srcLabel).search(tarLabel)){
	        	Node.get(srcLabel).add(tarLabel);
	        }
	        if(Node.containsKey(tarLabel) && !Node.get(tarLabel).search(srcLabel)){
	        	Node.get(tarLabel).add(srcLabel);
	        }
	       // Node[srcLabel][tarLabel]=distance;
	       // Node[tarLabel][srcLabel]=distance;
	    			}
    	// Implement me!
    } // end of addEdge()
	
    
    public String toString() {
    	int numberOfVertices = adjmatrix.length;
    	String result = "";

    	// For Adjacency Matrix
    	for (int j = 0, k = 0; j < numberOfVertices; j++) {
    	    result += j + ":";
    	    for (k = j; k < numberOfVertices; k++) {
    		if (adjmatrix[j][k] != null) {
    		    result += " (" + k + "," + adjmatrix[j][k] + ")";
    		}
    	    }
    	    }
		return toString();
    }

    public ArrayList<T> neighbours(T vertLabel) {
        ArrayList<T> neighbours = new ArrayList<T>();
        
        // Implement me!
        
        return neighbours;
    } // end of neighbours()
    
    
    public void removeVertex(T vertLabel) {
        // Implement me!
    } // end of removeVertex()
	
    
    public void removeEdge(T srcLabel, T tarLabel) {
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
    
} // end of class AdjMatrix