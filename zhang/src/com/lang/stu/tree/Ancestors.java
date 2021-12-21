// 输出二叉树中指定结点的所有祖先结点。
package com.lang.stu.tree;

//  输出二叉树中指定结点的所有祖先结点。
public class Ancestors {

	public static void main(String[] args) {
		// 标明空子树的先根序列
		String[] preorder = { "A", "B", "D", null, "G", null, null, null, "C", "E", null, null, "F", "H" };

		BinaryTree<String> bitree = new BinaryTree<String>(preorder); // 二叉树类

		bitree.preOrder();

		String value = "H";
		// 二叉树的二叉链表结点类
		BinaryNode<String> find = bitree.search(value);
		if (find == null)
			System.out.println("\n未找到" + value);
		else {
			BinaryNode<String> parent = bitree.getParent(find);
			System.out.println("\n" + find.data + "的祖先结点是");
			while (parent != null) {
				System.out.print(parent.data + "  ");
				parent = bitree.getParent(parent);
			}
			System.out.println();
		}

	}
	
	/*
	程序运行结果如下：
	先根序列：  A B D G C E F H 
	未找到Z

	D的祖先结点是B  A 

	H的祖先结点是F  C  A  

	*/
}
