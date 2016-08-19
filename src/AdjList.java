import java.io.*;
import java.util.*;
import java.util.Map.Entry;

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
	Map<T, Boolean> total = new HashMap<T, Boolean>();
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
    	//vertlabels.get(vertLabel).print();
    	
    	// Implement me!
    } // end of addVertex()
	
    
    public void addEdge(T srcLabel, T tarLabel) {
        if(vertlabels.containsKey(srcLabel) && vertlabels.get(srcLabel).search(tarLabel))
        {
        	//System.out.println("edge already exist");
        	return;
        }else if(vertlabels.containsKey(srcLabel) && vertlabels.get(srcLabel).search(srcLabel)){
        	return;
        }else if(vertlabels.containsKey(srcLabel) && !vertlabels.get(srcLabel).search(srcLabel)){
        	vertlabels.get(srcLabel).add(tarLabel);
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
    	
    	// Uniform Cost Search (UCS) path code. Use this for difficult level
    			// monster path code.
    			//
    			// This code displays the path as arraylist from Start (ie, monster location)
    			// to shortest distance player location.
    			
		ArrayList<T> shortestPathList = new ArrayList<T>();
		HashMap<T, Boolean> visited = new HashMap<T, Boolean>();

		if (vertLabel1 == vertLabel2)
			return 0;
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