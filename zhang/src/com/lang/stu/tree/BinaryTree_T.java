package com.lang.stu.tree;

import java.util.Stack;

import com.lang.stu.linearlist.LinkedQueue;	 //链式队列
import com.lang.stu.stack.LinkedStack;	 //链式栈

//二叉树类
public class BinaryTree_T<E> {
	
	//根结点
	protected BinaryNode<E> root;	//二叉树的二叉链表结点类
	
	//构造空二叉树
	public BinaryTree_T(){
		root = null;
	}
	//构造指定根结点的二叉树
	public BinaryTree_T(BinaryNode<E> root){
		this.root = root;
	}

	//判断是否空二叉树
	public boolean isEmpty(){
		return this.root == null;
	}
	
	//返回二叉树的根结点
	public BinaryNode<E> getRoot(){
		return this.root;
	}
	
	// 二叉树的遍历：   先根次序遍历二叉树
	public void preOrder(){
		System.out.print("\n先根序列：  ");
		preOrder(root);
	}

	// 先根次序遍历以p结点为根的子二叉树
	private void preOrder(BinaryNode<E> p) {
		// 若二叉树不空
		if (p != null) {
			System.out.print(p.data + " ");	//访问当前结点
			preOrder(p.left); //按先根次序遍历当前结点的左子树
			preOrder(p.right); //按先根次序遍历当前结点的右子树
		}
	}
	
	//中根次序遍历二叉树
	public void inOrder(){
		System.out.print("\n中根序列：  ");
		inOrder(root);
	}

	// 中根次序遍历以p结点为根的子二叉树
	private void inOrder(BinaryNode<E> p) {
		if (p != null) {
			inOrder(p.left); // 中根次序遍历左子树
			System.out.print(p.data + " ");
			inOrder(p.right); // 中根次序遍历右子树
		}
	}
	//后根次序遍历二叉树
	public void postOrder(){
		System.out.print("\n后根序列：  ");
		postOrder(root);
	}

	// 后根次序遍历以p结点为根的子二叉树
	private void postOrder(BinaryNode<E> p) {
		if (p != null) {
			postOrder(p.left);
			postOrder(p.right);
			System.out.print(p.data + " ");
		}
	}
	//构造并遍历二叉树。
	//基于遍历的操作
	public int count(){ //求一棵二叉树中所有结点个数
		return count(root);
	}

	// 求以p结点为根的子树的结点个数
	public int count(BinaryNode<E> p){
		if(p != null)
			return 1 + count(p.left) + count(p.right);
		else 
			return 0;
	}
	
	// 求二叉树的深度
	public int depth(){
		return depth(root);
	}

	// 求以p结点为根的子树的深度，后根次序遍历
	public int depth(BinaryNode<E> p) {
		if (p != null) {
			int ld = depth(p.left);	 //求左子树的深度
			int rd = depth(p.right);
			return (ld >= rd) ? ld + 1 : rd + 1;
		}
		return 0;
	}
	
	// 查找值为value的结点
	public BinaryNode<E> search(E value) {
		return search(root, value);
	}
	
	// 在以p为根的子树中查找值为value的结点
	public BinaryNode<E> search(BinaryNode<E> p, E value) {
		// 先根次序遍历，返回查找到结点，若未找到返回null
		BinaryNode<E> find = null; // 记载找到结点
		if (p != null && value != null) {
			if (p.data.equals(value))
				find = p; // 查找成功
			else {
				find = search(p.left, value);  //在左子树中查找
				if (find == null)
					find = search(p.right, value);  //若左子树中未找到，则继续在右子树中查找
			}
		}
		return find;  //返回找到结点
	}
	 //返回指定结点 node 的父母结点
	public BinaryNode<E> getParent(BinaryNode<E> node) {
		//若空树、未找到或 node 为根或 node 为 null，返回null
		if(root == null || node == null || node == root)
			return null;
		return getParent(root, node);
	}
	//在以p为根的子树中查找并返回node结点的父母结点
	public BinaryNode<E> getParent(BinaryNode<E> p, BinaryNode<E> node){
		//在以p为根的子树中查找并返回node结点的父母结点
		BinaryNode<E> find = null; //记载找到结点
		if(p != null){
			if(p.left == node || p.right == node)
				find = p;	// 查找成功
			else { 
				find = getParent(p.left, node); //在左子树中查找
				if(find == null)
					find = getParent(p.right, node); //若左子树中未找到，则继续在右子树中查找
			}
		}
		return find; //返回找到的父母结点
	}
	
	// 构造二叉树
	public BinaryTree_T(E[] preorder){ //以标明空子树的先根序列构造一棵二叉树
		root = create(preorder);
	}
	private int i = 0;
	private BinaryNode<E> create(E[] preorder){
		//创建一棵子树，当前结点值是preorder[i]
		BinaryNode<E> p = null;	//返回所创建子树的根结点
		if(i < preorder.length){
			E elem = preorder[i];
			i++;
			if(elem != null){
				p = new BinaryNode<E>(elem); //建立p结点
				p.left = create(preorder);  //建立p的左子树
				p.right = create(preorder);  //建立p的右子树
			}
		}
		return p;
	}
	
	// 输出二叉树中指定结点的所有祖先结点
	// 二叉树的插入和删除操作
	public void insert(BinaryNode<E> p, E element, boolean leftChild){ //插入元素 element 作为 p 结点的孩子
		// 若 leftChild 为 true，插入结点作为左孩子，否则作为右孩子
		if(p != null){
			BinaryNode<E> q = new BinaryNode<E>(element);
			if (leftChild) {
				q.left = p.left; // p结点的原左孩子成为q结点的左孩子
				p.left = q; // q结点作为p结点的左孩子
			} else {
				q.right = p.right; // p结点的原右孩子成为q结点的右孩子
				p.right = q; // q结点作为p结点的右孩子
			}
		}
	}
	
	// 插入元素 element 作为 p 结点的左孩子
	public void insert(BinaryNode<E> p, E element) {
		insert(p, element, true);
	}
	
	// 删除p结点的左/右子树
	public void remove(BinaryNode<E> p, boolean leftChild) {
		// 若leftChild为true，删除左子树，否则删除右子树
		if (p != null) {
			if (leftChild)
				p.left = null;
			else
				p.right = null;
		}
	}

	// 删除 p 结点的左子树
	public void remove(BinaryNode<E> p) {
		remove(p, true);
	}
	
	//二叉树遍历的非递归算法
	//先根次序遍历二叉树的非递归算法：  根节点 -- 左孩子--右孩子
	public void preOrderTraverse(){
		System.out.print("先根次序遍历（非递归）：  ");
		// 栈：后进先出
		LinkedStack<BinaryNode<E>> stack = new LinkedStack<BinaryNode<E>>();   //创建一个空栈
		BinaryNode<E> p = this.root;
		
		while(p != null || !stack.isEmpty()) {   //p非空或栈非空时
			if(p != null){
				System.out.print(p.data + " ");	 //访问结点
				stack.push(p);	//p结点入栈
				p = p.left;		//进入左子树
			} else {	//p为空且栈非空时
				p = stack.pop();	//p指向出栈结点
				p = p.right;	//进入右子树
			}
		}
		System.out.println();
	}
	
	//中根次序遍历二叉树的非递归算法： 左孩子 -- 根节点--右孩子
	public void inOrderTravrese(){
		System.out.print("中根次序遍历（非递归）：  ");
		LinkedStack<BinaryNode<E>> stack = new LinkedStack<BinaryNode<E>>(); //创建一个空栈
		BinaryNode<E> p = this.root;
		while(p != null || !stack.isEmpty())	 //p非空或栈非空时
			if(p != null){
				stack.push(p);	//p结点入栈
				p = p.left;		 //进入左子树
			} else {	 //p为空且栈非空时
				p = stack.pop();	//p指向出栈结点
				System.out.print(p.data + " ");	//访问结点
				p = p.right;	  //进入右子树
			}
		System.out.println();
	}
	
	//  copy right: langlang.ye
	// 后根次序遍历非递归实现： 左孩子 --右孩子-- 根节点
	public void postOrderTravrese() {
		System.out.print("后根次序遍历（非递归）：  ");
		Stack<BinaryNode> stack1 = new Stack<BinaryNode>(); // Stack: java.util 包里面的
		Stack<Integer> stack2 = new Stack<>();
		BinaryNode<E> node = this.root;
		int i = 1;
		while (node != null || !stack1.empty()) {
			while (node != null) {
				stack1.push(node);
				stack2.push(0);
				node = node.left;
			}

			while (!stack1.empty() && stack2.peek() == i) {
				stack2.pop();
				System.out.print(stack1.pop().data + " ");
			}
			if (!stack1.empty()) {
				stack2.pop();
				stack2.push(1);
				node = stack1.peek();
				node = node.right;
			}
		}
	}
	
	// 二叉树的层次遍历
	public void levelOrder(){//按层次遍历二叉树
		LinkedQueue<BinaryNode<E>> que = new LinkedQueue<BinaryNode<E>>(); //创建一个空队列
		BinaryNode<E> p = this.root;
		System.out.print("层次遍历：  ");
		while(p != null){
			System.out.print(p.data + " ");
			if(p.left != null)
				que.enqueue(p.left);
			if(p.right != null)
				que.enqueue(p.right);
			p = que.dequeue();
		}
		System.out.println();
	}
	
	public void leaf(){
		leaf(root);
	}
	
	private void leaf(BinaryNode<E> p){
		if(p != null){
			if(p.isLeaf())
				System.out.print(p.data + " ");
			leaf(p.left);
			leaf(p.right);
		}
	}
	//求一棵二叉树中所有叶子结点个数
	public int countLeaf(){
		return countLeaf(root);
	}
	//求以p结点为根的子树的叶子结点个数
	private int countLeaf(BinaryNode<E> p){
		if(p == null)
			return 0;
		if(p.isLeaf())
			return 1;
		return countLeaf(p.left) + countLeaf(p.right);
	}
	
	public BinaryTree_T(BinaryTree_T<E> bitree){
		this.root = copy(bitree.root);
	}
	private BinaryNode<E> copy(BinaryNode<E> p){
		BinaryNode<E> q = null;
		if(p != null){
			q = new BinaryNode<E>(p.data);
			q.left = copy(p.left);
			q.right = copy(p.right);
		}
		return q;
	}
	
	public boolean equals(Object obj){
		if(obj == this)
			return true;
		if(obj instanceof BinaryTree_T){
			BinaryTree_T<E> bitree = (BinaryTree_T<E>) obj;
			return equals(this.root, bitree.root);
		}
		return false;
	}
	
	//判断以p和q结点为根的两棵子树是否相等
	private boolean equals(BinaryNode<E> p, BinaryNode<E> q){
		// 递归方法
		if(p == null && q == null)
			return true;
		if(p != null && q != null)
			return (p.data.equals(q.data)) && equals(p.left.equals(q.left)) && equals(p.right.equals(q.right));
		return false;
	}
	
	//将首次出现的值为old结点值替换为value
	public boolean replace(E old, E value){
		BinaryNode<E> find = search(old);
		if(find != null)
			find.data = value;
		return find != null;
	}
	
	//将值为old的结点全部替换为value
	public void replaceAll(E old, E value){
		replaceAll(root, old, value);
	}

	 //在以p为根的子树中实现全部替换
	private void replaceAll(BinaryNode<E> p, E old, E value) {
		if (p != null) {
			if (p.data.equals(old))
				p.data = value;
			replaceAll(p.left, old, value);
			replaceAll(p.right, old, value);
		}
	}

}
