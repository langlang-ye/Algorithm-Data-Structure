package com.lang.stu.tree;

// 二叉树的二叉链表结点类
public class BinaryNode<E> {

	public E data;	//数据元素
	public BinaryNode<E> left, right; 	// 分别指向左，右孩子结点
	
	// 构造节点， 指定元素和左，右孩子结点
	public BinaryNode(E data, BinaryNode<E> left, BinaryNode<E> right) {
		this.data = data;
		this.left = left;
		this.right = right;
	}
	
	// 构造有值的叶子结点
	public BinaryNode(E data) {
		this(data, null, null);
	}
	
	public BinaryNode(){
		this(null, null, null);
	}
	
	// 判断是否是叶子结点
	public boolean isLeaf(){
		return this.left == null && this.right == null;
	}
	
	@Override
	public String toString() {
		return this.data.toString();
	}
}
