package com.lang.stu.tree;

//二叉树接口
public interface BinaryTTree<E> {

	boolean isEmpty(); // 判断是否空二叉树

	BinaryNode<E> getRoot(); // 返回二叉树的根结点

	BinaryNode<E> getParent(BinaryNode<E> node); // 返回node的父母结点

	BinaryNode<E> getLeftChild(BinaryNode<E> node); // 返回node的左孩子结点

	BinaryNode<E> getRightChild(BinaryNode<E> node); // 返回node的右孩子结点

	void preOrder(); // 先根次序遍历二叉树

	void inOrder(); // 中根次序遍历二叉树

	void postOrder(); // 后根次序遍历二叉树

	void levelOrder(); // 按层次遍历二叉树

	BinaryNode<E> search(E element); // 查找并返回元素为element的结点

	void insert(BinaryNode<E> p, E element, String child); // 插入element元素作为p结点的孩子，child指定左/右孩子

	void remove(BinaryNode<E> p, String child); // 删除p结点的左/右子树

	void clear(); // 清空
}
