


public class BinarySearchTree<E extends Comparable<E>> {
	class Node {
		E value;
		Node leftChild = null;
		Node rightChild = null;
		Node(E value) {
			this.value = value;
		}
		@Override
		public boolean equals(Object obj) {
			if ((obj instanceof BinarySearchTree.Node) == false)
				return false;
			@SuppressWarnings("unchecked")
			Node other = (BinarySearchTree<E>.Node)obj;
			return other.value.compareTo(value) == 0 &&
					other.leftChild == leftChild && other.rightChild == rightChild;
		}
	}
	
	protected Node root = null;
	
	protected void visit(Node n) {
		System.out.println(n.value);
	}
	
	public boolean contains(E val) {
		return contains(root, val);
	}
	
	protected boolean contains(Node n, E val) {
		if (n == null) return false;
		
		if (n.value.equals(val)) {
			return true;
		} else if (n.value.compareTo(val) > 0) {
			return contains(n.leftChild, val);
		} else {
			return contains(n.rightChild, val);
		}
	}
	
	public boolean add(E val) {
		if (root == null) {
			root = new Node(val);
			return true;
		}
		return add(root, val);
	}
	
	protected boolean add(Node n, E val) {
		if (n == null) {
			return false;
		}
		int cmp = val.compareTo(n.value);
		if (cmp == 0) {
			return false; // this ensures that the same value does not appear more than once
		} else if (cmp < 0) {
			if (n.leftChild == null) {
				n.leftChild = new Node(val);
				return true;
			} else {
				return add(n.leftChild, val);
			} 	
		} else {
			if (n.rightChild == null) {
				n.rightChild = new Node(val);
				return true;
			} else {
				return add(n.rightChild, val);
			} 	
		}
	}	
	
	public boolean remove(E val) {
		return remove(root, null, val);
	}
	
	protected boolean remove(Node n, Node parent, E val) {
		if (n == null) return false;

		if (val.compareTo(n.value) == -1) {
				return remove(n.leftChild, n, val);
		} else if (val.compareTo(n.value) == 1) {
				return remove(n.rightChild, n, val);
		} else {
			if (n.leftChild != null && n.rightChild != null){
				n.value = maxValue(n.leftChild);
				remove(n.leftChild, n, n.value);
			} else if (parent == null) {
				root = n.leftChild != null ? n.leftChild : n.rightChild;
			} else if (parent.leftChild == n){
				parent.leftChild = n.leftChild != null ? n.leftChild : n.rightChild;
			} else {
				parent.rightChild = n.leftChild != null ? n.leftChild : n.rightChild;
			}
			return true;
		}
	}

	protected E maxValue(Node n) {
		if (n.rightChild == null) {
			return n.value;
	    } else {
	       return maxValue(n.rightChild);
	    }
	}

	
	/*********************************************
	 * 
	 * IMPLEMENT THE METHODS BELOW!
	 *
	 *********************************************/
	
	
	// Method #1.
	public Node findNode(E val) {
		if (val == null) return null;
		
		Node node = root;
		
		do {
			if (node.value.equals(val)) return node;
			
			if (node.value.compareTo(val) > 0) {
				node = node.leftChild;
			} else {
				node = node.rightChild;
			}
		
		} while (node != null);
		
		return null;

	}
	
	// Method #2.
	protected int depth(E val) {
		if (val == null) return -1;
		
		Node node = root;
		int depth = 0;
		
		do {
			if (node.value.equals(val)) return depth;
			
			if (node.value.compareTo(val) > 0) {
				node = node.leftChild;
			} else {
				node = node.rightChild;
			}
			
			depth++;
		} while (node != null);
		
		return -1;

	}
	
	// Method #3.
	protected int height(E val) {
		if (val == null) return -1;
		
		if (this.contains(val))
			return getChildrenHeight(findNode(val));
		
		return -1;
	}
	
	protected int getChildrenHeight(Node node) {
		
		if (node.leftChild == null && node.rightChild == null) {
			return 0;
		} else if (node.leftChild != null && node.rightChild == null) {
			return 1 + getChildrenHeight(node.leftChild);
		} else if (node.rightChild != null && node.leftChild == null) {
			return 1 + getChildrenHeight(node.rightChild);
		} else {
			int leftHeight = getChildrenHeight(node.leftChild);
			int rightHeight = getChildrenHeight(node.rightChild);
			
			if (leftHeight > rightHeight) {
				return 1 + leftHeight;
			} else {
				return 1 + rightHeight;
			}
		}
		
	}
 

	// Method #4.
	protected boolean isBalanced(Node n) {
		if (n == null || !this.contains(n.value)) return false;
		
		int leftHeight = 0;
		int rightHeight = 0;
		
		if (n.leftChild != null) {
			leftHeight = getChildrenHeight(n.leftChild);
		} else {
			leftHeight = -1;
		}
		
		if (n.rightChild != null) {
			rightHeight = getChildrenHeight(n.rightChild);
		} else {
			rightHeight = -1;
		}
		
		if ((Math.abs(leftHeight - rightHeight)) <= 1) return true;
		
		return false;

	}
	
	// Method #5. .
	public boolean isBalanced() {
		
		if (root.leftChild == null && root.rightChild == null) {
			return true;
		} else if (root.leftChild != null && root.rightChild == null) {
			return isBalanced(root.leftChild);
		} else if (root.rightChild != null && root.leftChild == null) {
			return isBalanced(root.rightChild);
		} else {		
			return isBalanced(root.leftChild) && isBalanced(root.rightChild);
		}
		
	}

}
