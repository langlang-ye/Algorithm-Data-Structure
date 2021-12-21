package com.lang.stu.tree;

//树接口
public interface TTree<E> {

	boolean isEmpty(); // 判断是否空树

	E getRoot(); // 返回根结点元素

	E getParent(E child); // 返回child的父母结点

	int getChildCount(E parent); // 返回parent的孩子结点数

	E getFirstChild(E parent); // 返回parent的一个孩子结点

	E getNextSibling(E element); // 返回element的下一个兄弟结点

	void traverse(); // 遍历树

	void insert(E parent, E element); // 插入element作为parent的孩子结点

	void remove(E parent); // 删除以parent为根结点的子树

	void clear(); // 清空
}
