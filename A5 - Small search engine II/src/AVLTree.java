class Node{
	private Node left,right;
	private int data;
	private String word;
	private int height;
	public Node(int p,String s){
		left = null;
		right = null;
		word = s;
		data = p;
		height = 0;
	}
	public int getData(){
		return data;
	}
	public String getName(){
		return word;
	}
	public Node getLeft(){
		return left;
	}
	public Node getRight(){
		return right;
	}
	public int getHeight(){
		return height;
	}
	public void setLeft(Node n){
		left = n;
	}
	public void setRight(Node n){
		right = n;
	}
	public void setData(int p){
		data = p;
	}
	public void setHeight(int i){
		height = i;
	}
	public AVLTree subtree(AVLTree t){
		AVLTree tree = new AVLTree();
		tree.setRoot(this);
		return tree;
	}
}
public class AVLTree{
	private Node root;
	public AVLTree(){
		root = null;
	}
	public AVLTree(String w,int p){
		root = new Node(p,w);
	}
	public int getRoot(){
		return root.getData();
	}
	public void setRoot(Node n){
		root = n;
	}
	public boolean isEmpty(){
		return (root == null);
	}
	public int height(Node n){
		if (n==null)
			return -1;
		else
			return n.getHeight();
	}
	public int max(int lhs,int rhs){
		if (lhs>rhs)
			return lhs;
		else
			return rhs;
	}
	public Node insert(Node n,int p,String s){
		if (n==null){
			n = new Node(p,s);
			return n;
		}	
		else{
			if (p<n.getData()){
				n.setLeft(insert(n.getLeft(),p,s)); 
				if (height(n.getLeft())-height(n.getRight())== 2){
					if (p<n.getLeft().getData())
						n = rotateWithLeftChild(n);
					else
						n = doubleRotateWithLeftChild(n);
				}
			}
			else{
				n.setRight(insert(n.getRight(),p,s));
				if (height(n.getRight()) - height(n.getLeft())==2){
					if (p<n.getRight().getData())
						n = doubleRotateWithRightChild(n);
					else
						n = rotateWithRightChild(n);
				}
			}
			n.setHeight(max(height(n.getLeft()),height(n.getLeft())));
			return n;
		}
	}
	private Node rotateWithLeftChild(Node n){
    	Node nl = n.getLeft();
    	n.setLeft(nl.getRight());
		nl.setRight(n);
		n.setHeight(max(height(n.getLeft()),height(n.getRight()))+1);
		nl.setHeight(max(height(nl.getLeft()),n.getHeight())+1);
		return nl;
	}
    private Node rotateWithRightChild(Node n){
		Node nr = n.getRight();
		n.setRight(nr.getLeft());
		nr.setLeft(n);
		n.setHeight(max(height(n.getLeft()),height(n.getRight()))+1);
		nr.setHeight(max(height(nr.getRight()),n.getHeight())+1);
		return nr;
	}
    private Node doubleRotateWithLeftChild(Node n){
		n.setLeft(rotateWithRightChild(n.getLeft()));
		return rotateWithLeftChild(n);
	}
    private Node doubleRotateWithRightChild(Node n){
		n.setRight(rotateWithLeftChild(n.getRight()));
		return rotateWithRightChild(n);
	}
	public boolean searchBool(Node r,int p){
		boolean found = false;
		while ((r != null) && !found){
			int rval = r.getData();
			if (p < rval)
            	r = r.getLeft();
			else if (p > rval)
				r = r.getRight();
			else{
				found = true;
				break;
			}
			found = searchBool(r,p);
		}
		return found;
	}
	public void insert(int p,String s){
		root = insert(root,p,s);
	}
	public Node search(Node r,int p){
		boolean found = false;
		int rval = r.getData();
		if (r == null){
			System.out.println("Doesnot contain this position!");
			return null;
		}
		else if (p < rval){
        	r = r.getLeft();
        	return search(r,p); 
        }	
		else if (p > rval){
			r = r.getRight();
			return search(r,p);
		}	
		else
			return r;
	}
	public Node searchNext(int p){
		if (searchBool(root,p)){
			Node position = search (root,p);
			if (position.getRight() != null){
				Node x = position.getRight();
				while (x.getLeft() != null){
					x = x.getLeft();
				}
				return x;
			}	
			else{
				Node lastLeft = null;
				Node current = root;
				while (current!=position){
					if (searchBool(current.getLeft(),p)){
						lastLeft = current;
						current = current.getLeft();
					}
					else
						current = current.getRight();
				}
				if (lastLeft==null){
					System.out.println("It is the largest position!");
					return null;
				}
				else{
					return lastLeft;
				}	
			}
		}
		else{
			System.out.println("Doesnot contain the position");
			return null;
		}
	}
	// public void print(){
	// 	if (isEmpty())
	// 		System.out.print("");
	// 	else{
	// 		if (root.getLeft()!= null)
	// 			root.getLeft().subtree(this).print();
	// 		System.out.print(root.getData()+" ");
	// 		if (root.getRight()!= null)
	// 			root.getRight().subtree(this).print();
	// 	}
	// }
	// public static void main(String[] args){
	// 	AVLTree tree = new AVLTree();
	// 	tree.insert(4,"Hello");
	// 	tree.insert(7,"Aditi");
	// 	tree.insert(11,"Singla");
	// 	tree.insert(8,"IIT Delhi");
	// 	tree.insert(2,"Heya");
	// 	tree.insert(14,"Yo");
	// 	tree.print(); 
	// 	System.out.println(tree.searchNext(7).getData());
	// 	System.out.println(tree.searchNext(2).getData());
	// 	if (tree.searchNext(14) != null)
	// 		System.out.println(tree.searchNext(14).getData());
		
	// }
}