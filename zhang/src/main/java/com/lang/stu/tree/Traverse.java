//构造并遍历二叉树
package com.lang.stu.tree;

public class Traverse {

	// 构造指定二叉树
	public static BinaryTree<String> create() {
		// 二叉树的二叉链表结点类
		BinaryNode<String> child_f, child_d, child_b, child_c, child_a;

		child_d = new BinaryNode<String>("D", null, new BinaryNode("G"));
		child_b = new BinaryNode<String>("B", child_d, null);
		child_f = new BinaryNode<String>("F", new BinaryNode("H"), null);
		child_c = new BinaryNode<String>("C", new BinaryNode("E"), child_f);
		child_a = new BinaryNode("A", child_b, child_c);
		return new BinaryTree<String>(child_a);
	}

	public static void main(String[] args) {
		BinaryTree<String> bitree = create(); // 二叉树类
		bitree.preOrder();
		bitree.inOrder();
		bitree.postOrder();
	}
}


/*
程序运行结果如下：
先根序列：  A B D G C E F H 
中根序列：  D G B A E C H F 
后根序列：  G D B E H F C A 
*/
