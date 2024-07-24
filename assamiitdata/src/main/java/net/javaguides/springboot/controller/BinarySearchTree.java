package net.javaguides.springboot.controller;


public class BinarySearchTree {

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
			System.out.println("EnteringNodeBefore  "+value);
			node=new Node(value);
			System.out.println("EnteringNodeAfter");
			return node;
		}

		if(value<node.value) {
			System.out.println("--leftBef1");
			node.left = insertd(value, node.left);
			System.out.println("--leftBef2");
		}

		if(value>node.value) {
			System.out.println("--RightBef1");
			node.right = insertd(value, node.right);
			System.out.println("--RightBef2");

		}

		node.height = Math.max(height(node.left), height(node.right)) + 1;
        System.out.println(node.height+"  ---nodeHeight");
		return node;
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

		BinarySearchTree tree= new BinarySearchTree();
		//int[] nums= {5,2,7,1,4,6,9,8,3,10};
		int[] nums= {5,4,1,2,8,6};
		tree.populate(nums);
		tree.display();

	}

}
