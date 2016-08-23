import java.util.ArrayList;
import java.util.LinkedList;

public class MyLinkedList<Object> {
	/** Reference to head node. */
	protected Node<Object> mHead;

	/** Length of list. */
	protected int mLength;
	
	public ArrayList<Object> path;
	public int minDistance;

	public MyLinkedList() {
		mHead = null;
		mLength = 0;
		minDistance = Integer.MAX_VALUE;
		path= new ArrayList<>();
	} // end of SimpleList()
	
	
	/**
	 * Add a new value to the start of the list.
	 * 
	 * @param newValue
	 *            Value to add to list.
	 */

	public void add(Object newValue) {
		Node<Object> newNode = new Node<Object>(newValue);
		
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
	public void add(int index, Object newValue) throws IndexOutOfBoundsException {
		if (index >= mLength || index < 0) {
			 
		}

		Node<Object> newNode = new Node<Object>(newValue);

		if (mHead == null) {
			mHead = newNode;
			
		}
		// list is not empty
		else {
			Node<Object> currNode = mHead;
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
	public Object get(int index) throws IndexOutOfBoundsException {
		if (index >= mLength || index < 0) {
			throw new IndexOutOfBoundsException("Supplied index is invalid.");
		}

		Node<Object> currNode = mHead;
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
	public boolean search(Object value) {
		Node<Object> currNode = mHead;
		for (int i = 0; i < mLength; ++i) {
			if (currNode.getValue().equals(value)) {
				return true;
			}
			currNode = currNode.getNext();
		}

		return false;
	} // end of search()
	
	public int searchlocation(Object value) {
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
	
	public void removevalue(Object key)
	   {
	      if(mHead == null)
	         throw new RuntimeException("cannot delete");

	      if( mHead.mValue.equals(key) )
	      {
	    	  mHead = mHead.getNext();
	         return;
	      }

	      Node<Object> cur  = mHead;
	      Node<Object> prev = null;

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
	
	
	public boolean remove(Object value) {
		// YOUR IMPLEMENTATION

		if (mLength == 0) {
			return false;
		}

		Node<Object> currNode = mHead;
		Node<Object> prevNode = null;

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
	public Object remove(int index, boolean dummy) throws IndexOutOfBoundsException {
		// YOUR IMPLEMENTATION
		if (index >= mLength || index < 0) {
			throw new IndexOutOfBoundsException("Supplied index is invalid.");
		}

		Node<Object> currNode = mHead;
		Node<Object> prevNode = null;

		Object value;
		// deleting head
		if (index == 0) {
			value = (Object) currNode.getValue();
			mHead = currNode.getNext();
		} else {
			for (int i = 0; i < index; ++i) {
				prevNode = currNode;
				currNode = currNode.getNext();
			}

			value = (Object) currNode.getValue();
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
		Node<Object> currNode = mHead;

		StringBuffer str = new StringBuffer();

		while (currNode != null) {
			str.append(currNode.getValue() + " ");
			currNode = currNode.getNext();
		}

		return str.toString();
	} // end of getString();
	
	
    public ArrayList<Object> neighbours() {
        ArrayList<Object> neighbours = new ArrayList<Object>();
        Node<Object> currNode = mHead;
        
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
	private class Node<Object> {
		/** Stored value of node. */
		protected Object mValue;
		/** Reference to next node. */
		protected Node<Object> mNext;


		public Node(Object value) {
			mValue = value;
			mNext = null;
		}

		public Object getValue() {
			return mValue;
		}

		public Node<Object> getNext() {
			return mNext;
		}

		@SuppressWarnings("unused")
		public void setValue(Object value) {
			mValue = value;
		}

		public void setNext(Node<Object> next) {
			mNext = next;
		}
		

	} // end of inner class Node

} // end of class SimpleList
