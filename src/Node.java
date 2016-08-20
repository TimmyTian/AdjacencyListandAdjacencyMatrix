import java.util.ArrayList;





public class Node<T> {
	
	protected Node<T> mHead;

	/** Length of list. */
	protected int mLength;
	
	/** Path Cost for uniform cost search */
	public int pathCost;
	
	/** Vertice Head **/
	public Node<T> Vertex;

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
		 public ArrayList<T> neighbours() {
		        ArrayList<T> neighbours = new ArrayList<T>();
		        Node<T> currNode = mHead;
		        
		        for(int i=0;i< mLength;i++){
		        		neighbours.add(currNode.getValue());
		        		currNode = currNode.getNext();
		        	}
		        return neighbours;
		    } // end of neighbours()

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

	} 

