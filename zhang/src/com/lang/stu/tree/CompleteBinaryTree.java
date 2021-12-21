//建立二叉链表表示的完全二叉树
package com.lang.stu.tree;

public class CompleteBinaryTree<E> extends BinaryTree<E> {

	 //构造空二叉树
	public CompleteBinaryTree(){
		super();
	}
	
	// 构造完全二叉树
	public CompleteBinaryTree(E[] levelorder) { // levelorder指定完全二叉树的层次序列
		this.root = create(levelorder, 0); // 创建以levelorder[0]为根的一棵二叉树
	}

	// 创建以levelorder[i]为根的一棵子树
	public BinaryNode<E> create(E[] levelorder, int i) {
		BinaryNode<E> p = null;
		if (i < levelorder.length) {
			p = new BinaryNode<>(levelorder[i]); // 建立结点p
			p.left = create(levelorder, 2 * i + 1); // 建立p的左子树
			p.right = create(levelorder, 2 * i + 2); // 建立p的右子树
		}

		return p;
	}
	
	public static void main(String[] args) {
		// 完全二叉树的层次遍历序列
		String[] levelorder = { "A", "B", "C", "D", "E", "F", "G", "H" };
		CompleteBinaryTree<String> cbtree = new CompleteBinaryTree<>(levelorder);

		cbtree.preOrder();
		cbtree.inOrder();
		cbtree.postOrder();

		System.out.println("\n结点个数：  " + cbtree.count());
		System.out.println("深度：  " + cbtree.depth());
	}
}
/*
先根序列：  A B D H E C F G 
中根序列：  H D B E A F C G 
后根序列：  H D E B F G C A 
结点个数：  8
深度：  4
*/
