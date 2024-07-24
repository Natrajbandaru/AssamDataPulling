package net.javaguides.springboot.controller;

import net.javaguides.springboot.controller.BinarySearchTree.Node;

public class AVLTree {

	public class Node {
		public int value;
		public Node left;
		public Node right;
		public int height;

		public Node(int value) {
			this.value=value;
		}
	}

	private Node rootNode;
	
	  public int height() {
		  return height(rootNode);
	}


	public int height(Node node) {
		if(node==null) {
			return -1;
		}
		return node.height;
	}

	public boolean isEmpty() {
		return rootNode==null;
	}
	public void  insert(int value) {
		rootNode = insertd(value, rootNode);
	}

	public Node insertd(int value,Node node) {

		if(node==null) {
			node=new Node(value);
			return node;
		}

		if(value<node.value) {
			node.left = insertd(value, node.left);
		}

		if(value>node.value) {
			node.right = insertd(value, node.right);

		}

		node.height = Math.max(height(node.left), height(node.right)) + 1;
		return rotate(node);
	}
	
	

	private Node rotate(Node node) {

		if(height(node.left)-height(node.right) > 1) {
			if(height(node.left.left) -  height(node.left.right) > 0) {				
				return rightRotate(node);
			}
			if(height(node.left.left)-height(node.left.right) < 0) {
				node.left=leftRotate(node.left);
				return rightRotate(node);
			}
		}
		
		if(height(node.left) - height(node.right) < -1) {
			if(height(node.right.left) -  height(node.right.right) < 0) {				
				return leftRotate(node);
			}
			if(height(node.right.left) - height(node.right.right) > 0) {
				  node.right = rightRotate(node.right);
			      return leftRotate(node);
			}
		}
		return node;
	}

	private Node rightRotate(Node p) {
		
		Node c=p.left;
		Node t=c.right;
		
		c.right=p;
		p.left=t;

		 p.height = Math.max(height(p.left), height(p.right) + 1);
		 c.height = Math.max(height(c.left), height(c.right) + 1);
		return c;
	}

	private Node leftRotate(Node c) {

		Node p=c.right;
		Node t= p.left;
		
		p.left=c;
		c.right=t;
		
	    p.height = Math.max(height(p.left), height(p.right) + 1);
	    c.height = Math.max(height(c.left), height(c.right) + 1);

		return p;
	}

	public void populate(int[] nums) {
		for(int i=0;i<nums.length;i++) {
			this.insert(nums[i]);
		}
	}

	public boolean balanced() {
		return balanced(rootNode);
	}

	private boolean balanced(Node node) {
		if(node==null) {
			return true;
		}
		return Math.abs(height(node.left)-height(node.right)) <= 1 && balanced(node.left) && balanced(node.right);
	}

	public void display() {
		display(this.rootNode,"Root Node : ");
	}

	private void display(Node node, String details) {

		if(node==null) {
			return;
		}

		System.out.println(details + node.value);
		display(node.left,"Left child of "+ node.value+" : ");
		display(node.right,"Right child of "+ node.value+" : ");
	}
	

	public static void main(String[] args) {

		AVLTree tree= new AVLTree();
		
		for(int j=0;j<=1000;j++) {
			tree.insert(j);
		}
		System.out.println(tree.height());

	}

}
