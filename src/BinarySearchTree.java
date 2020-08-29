//import java.BinaryTree.Node;

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
			Node other = (BinarySearchTree.Node)obj;
			return other.value.compareTo(value) == 0 &&
					other.leftChild == leftChild && other.rightChild == rightChild;
		}
	}
	
	
	public BinarySearchTree() {
		//do nothing
	}
	
	public BinarySearchTree(E val) {
  		root = new Node(val);
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

	
	/*
	 * Implement the methods below.
	 */

	// Method #1.
	public Node findNode(E val) {
		
		if (val == null || !contains(val)) {
			return null;
		}
		
		return findNode(root, val);
	}
	
	private Node findNode(Node n, E val) {
		if (n.value.equals(val)) {
			return n;
		} else if (n.value.compareTo(val) > 0) {
			return findNode(n.leftChild, val);
		} else {
			return findNode(n.rightChild, val);
		}
	}
	
	// Method #2.
	
	private int depth = 0;
	
	protected int depth(E val) {
		
		if (val == null || !contains(val)) {
			return -1;
		}
		
		int output = findDepth(root, val);
		depth = 0;
		return output;
	}
	
	
	
	private int findDepth(Node n, E val) {

		if (n.value.equals(val)) {
			return depth;
		} else if (n.value.compareTo(val) > 0) {
			depth++;
			findDepth(n.leftChild, val);
		} else {
			depth++;
			findDepth(n.rightChild, val);
		}
		
		return depth;
	}
	
	// Method #3.
	protected int height(E val) {
		
		if (val == null || !contains(val)) {
			return -1;
		}
		
		Node n = findNode(val);
		
		return findHeight(n);
		
		
	}
	
	private int findHeight(Node n) {
		
		if (n==null) {
			return -1;
		}
		
		int lefth = findHeight(n.leftChild);
		int righth = findHeight(n.rightChild);
		
		if (lefth > righth) {
			return lefth + 1;
		} else {
			return righth + 1;
		}
		
	}
	
	// Method #4.
	protected boolean isBalanced(Node n) {
		
		if (n==null || !contains(n.value)) {
			return false;
		}
		
		int leftHeight = 0;
		int rightHeight = 0;
		
		if (n.leftChild != null) {
			leftHeight = height(n.leftChild.value);
		} else {
			leftHeight--;
		}
		
		if (n.rightChild != null) {
			rightHeight = height(n.rightChild.value);
		} else {
			rightHeight--;
		}
		
		//System.out.println(leftHeight);
		//System.out.println(rightHeight);
		
		if (Math.abs(leftHeight-rightHeight) > 1) {
			return false;
		} 
		
		return true;
	}
	
	// Method #5. 
	public boolean isBalanced() {
		
		Node n = root;
		return isBalancedHelper(n);

	}
	
	private boolean isBalancedHelper(Node n) {

		if (!isBalanced(n)) {
			return false;
		} else {
			if (n.leftChild != null) {
				isBalancedHelper(n.leftChild);
			} else if (n.rightChild != null) {
				isBalancedHelper(n.rightChild);
			}
		}
	
		return true;
	}
	
//	public static void main(String[] args) {
//		BinarySearchTree<Integer> bst = new BinarySearchTree<>(8);
////		bst.add(6);
////		bst.add(4);
////		bst.add(2);
////		bst.add(16);
////		bst.add(10);
////		bst.add(20);
////		bst.add(9);
////		bst.add(12);
////		bst.add(15);
////		bst.add(17);
////	    bst.add(18);
//		
//		
//		//System.out.println(bst.depth(null));
//		//System.out.println((bst.height(null)));
//		System.out.println(bst.isBalanced(null));
//		//System.out.println(bst.isBalanced());
//	
//	}
	
}
