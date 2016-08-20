import java.util.ArrayList;

public class MyLinkedList<T> {
	/** Reference to head node. */
	protected Node<T> mHead;

	/** Length of list. */
	protected int mLength;
	
	/** Path Cost for uniform cost search */
	public int pathCost;
	
	/** Vertice Head **/
	public Node<T> Vertex;

	public MyLinkedList() {
		mHead = null;
		mLength = 0;
	} // end of SimpleList()
	
	public MyLinkedList(T value) {
		//mHead = new Node<T>(value);
		//mLength++;
		Vertex = new Node<T>(value);
	}

	/**
	 * Add a new value to the start of the list.
	 * 
	 * @param newValue
	 *            Value to add to list.
	 */
	public void add(T newValue) {
		Node<T> newNode = new Node<T>(newValue);
		
		// If head is empty, then list is empty and head reference need to be
		// initialised.
		if (mHead == null) {
			mHead = newNode;
		}
		// otherwise, add node to the head of list.
		else {
			newNode.setNext(mHead);
			mHead = newNode;
			

		}
		
		mLength++;
		
	} // end of add()

	/**
	 * Add value (and corresponding node) at position 'index'. Indices start at
	 * 0.
	 * 
	 * @param index
	 *            Position in list to add new value to.
	 * @param newValue
	 *            Value to add to list.
	 * 
	 * @throws IndexOutOfBoundsException
	 *             In index are out of bounds.
	 */
	public void add(int index, T newValue) throws IndexOutOfBoundsException {
		if (index >= mLength || index < 0) {
			 
		}

		Node<T> newNode = new Node<T>(newValue);

		if (mHead == null) {
			mHead = newNode;
			
		}
		// list is not empty
		else {
			Node<T> currNode = mHead;
			for (int i = 0; i < index - 1; ++i) {
				currNode = currNode.getNext();
			}

			newNode.setNext(currNode.getNext());
			currNode.setNext(newNode);
		}

		mLength += 1;
	} // end of add()

	/**
	 * Returns the value stored in node at position 'index' of list.
	 * 
	 * @param index
	 *            Position in list to get new value for.
	 * @return Value of element at specified position in list.
	 * 
	 * @throws IndexOutOfBoundsException
	 *             In index are out of bounds.
	 */
	public T get(int index) throws IndexOutOfBoundsException {
		if (index >= mLength || index < 0) {
			throw new IndexOutOfBoundsException("Supplied index is invalid.");
		}

		Node<T> currNode = mHead;
		for (int i = 0; i < index; ++i) {
			currNode = currNode.getNext();
		}

		return currNode.getValue();
	} // end of get()

	/**
	 * Returns the value stored in node at position 'index' of list.
	 * 
	 * @param value
	 *            Value to search for.
	 * @return True if value is in list, otherwise false.
	 */
	public boolean search(T value) {
		Node<T> currNode = mHead;
		for (int i = 0; i < mLength; ++i) {
			if (currNode.getValue().equals(value)) {
				return true;
			}
			currNode = currNode.getNext();
		}

		return false;
	} // end of search()
	
	public int searchlocation(T value) {
		Node currNode = mHead;
		for (int i = 0; i < mLength; ++i) {
			if (currNode.getValue().equals(value)) {
				return i;
			}
			currNode = currNode.getNext();
		}

		return -1;
	} // end of search()
	/**
	 * Delete given value from list (delete first instance found).
	 * 
	 * @param <T>
	 * 
	 * @param value
	 *            Value to remove.
	 * @return True if deletion was successful, otherwise false.
	 */
	
	public void removevalue(T key)
	   {
	      if(mHead == null)
	         throw new RuntimeException("cannot delete");

	      if( mHead.mValue.equals(key) )
	      {
	    	  mHead = mHead.getNext();
	         return;
	      }

	      Node<T> cur  = mHead;
	      Node<T> prev = null;

	      while(cur != null && !cur.mValue.equals(key) )
	      {
	         prev = cur;
	         cur = cur.getNext();
	      }

	      if(cur == null)
	         throw new RuntimeException("cannot delete");

	      //delete cur node
	      prev.setNext(cur.getNext());
	   }
	
	
	public boolean remove(T value) {
		// YOUR IMPLEMENTATION

		if (mLength == 0) {
			return false;
		}

		Node<T> currNode = mHead;
		Node<T>	 prevNode = null;

		// check if value is head node
		if (currNode.getValue().equals(value)) {
			mHead = currNode.getNext();
			mLength--;
			return true;
		}

		prevNode = currNode;
		currNode = currNode.getNext();

		while (currNode != null) {
			if (currNode.getValue().equals(value)) {
				prevNode.setNext(currNode.getNext());
				currNode = null;
				mLength--;
				
				return true;
			}
			prevNode = currNode;
			currNode = currNode.getNext();
		}

		return false;
	} // end of delete()

	/**
	 * Delete value (and corresponding node) at position 'index'. Indices start
	 * at 0.
	 * 
	 * @param index
	 *            Position in list to get new value for.
	 * @param dummy
	 *            Dummy variable, serves no use apart from distinguishing
	 *            overloaded methods.
	 * @return Value of node that was deleted.
	 */
	public T remove(int index, boolean dummy) throws IndexOutOfBoundsException {
		// YOUR IMPLEMENTATION
		if (index >= mLength || index < 0) {
			throw new IndexOutOfBoundsException("Supplied index is invalid.");
		}

		Node<T> currNode = mHead;
		Node<T> prevNode = null;

		T value;
		// deleting head
		if (index == 0) {
			value = (T) currNode.getValue();
			mHead = currNode.getNext();
		} else {
			for (int i = 0; i < index; ++i) {
				prevNode = currNode;
				currNode = currNode.getNext();
			}

			value = (T) currNode.getValue();
			prevNode.setNext(currNode.getNext());
			currNode = null;
		}

		mLength--;

		return value;
	} // end of delete()

	/**
	 * Returns the minimum value in the list.
	 * 
	 * @return Minimum value in the list.
	 * 
	 * @throws IndexOutOfBoundsException
	 *             In index are out of bounds.
	 */
	// public int min() throws IllegalStateException {
	// if (mLength == 0) {
	// throw new IllegalStateException("Min is not defined for an empty list.");
	// }
	//
	// // traverse list, looking for the minimum valued element
	// int minValue = mHead.getValue();
	// Node currNode = mHead.getNext();
	//
	// while (currNode != null) {
	// if (currNode.getValue() < minValue) {
	// minValue = currNode.getValue();
	// }
	// currNode = currNode.getNext();
	// }
	//
	// return minValue;
	// } // end of min()
	//
	//
	/**
	 * Returns the maximum value in the list.
	 * 
	 * @return Maximum value in the list.
	 * 
	 * @throws IndexOutOfBoundsException
	 *             In index are out of bounds.
	 */
	// public int max() throws IndexOutOfBoundsException {
	// if (mLength == 0) {
	// throw new IllegalStateException("Min is not defined for an empty list.");
	// }
	//
	// // traverse list, looking for the minimum valued element
	// int maxValue = mHead.getValue();
	// Node currNode = mHead.getNext();
	//
	// while (currNode != null) {
	// if (currNode.getValue() > maxValue) {
	// maxValue = currNode.getValue();
	// }
	// currNode = currNode.getNext();
	// }
	//
	// return maxValue;
	// } // end of max()
	//

	/**
	 * Print the list in head to tail.
	 */
	public void print() {
		System.out.println(toString());
	} // end of print()

	/**
	 * Print the list from tail to head.
	 */
	// public void reversePrint() {
	// // use a stack
	// Stack<Integer> stack = new Stack<Integer>();
	// Node currNode = mHead;
	// while (currNode != null) {
	// stack.add(currNode.getValue());
	// currNode = currNode.getNext();
	// }
	//
	//
	// while (!stack.empty()) {
	// System.out.print(stack.pop() + " ");
	// }
	//
	// System.out.println("");
	// } // end of reversePrint()
	//
	//
	/**
	 * @return String representation of the list.
	 */
	public String toString() {
		Node<T> currNode = mHead;

		StringBuffer str = new StringBuffer();

		while (currNode != null) {
			str.append(currNode.getValue() + " ");
			currNode = currNode.getNext();
		}

		return str.toString();
	} // end of getString();
	
	public T getVertex(){
		return Vertex.getValue();
	}
	
    public ArrayList<T> neighbours() {
        ArrayList<T> neighbours = new ArrayList<T>();
        Node<T> currNode = mHead;
        
        for(int i=0;i< mLength;i++){
        		neighbours.add(currNode.getValue());
        		currNode = currNode.getNext();
        	}
        	
        // Implement me!
        
        return neighbours;
    } // end of neighbours()

	/**
	 * Node type, inner private class.
	 */
	@SuppressWarnings("hiding")
	private class Node<T> {
		/** Stored value of node. */
		protected T mValue;
		/** Reference to next node. */
		protected Node<T> mNext;


		public Node(T value) {
			mValue = value;
			mNext = null;
		}

		public T getValue() {
			return mValue;
		}

		public Node<T> getNext() {
			return mNext;
		}

		@SuppressWarnings("unused")
		public void setValue(T value) {
			mValue = value;
		}

		public void setNext(Node<T> next) {
			mNext = next;
		}
		

	} // end of inner class Node

} // end of class SimpleList
