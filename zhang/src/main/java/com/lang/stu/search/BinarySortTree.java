// 二叉排序树的插入和查找操作

package com.lang.stu.search;

import com.lang.stu.tree.BinaryNode; // 二叉树的二叉链表结点类

public class BinarySortTree<E> { // 二叉排序树类

	protected BinaryNode<E> root; // 根结点

	public BinarySortTree() { // 构造空二叉排序树
		root = null;
	}

	public Boolean isEmpty() { // 判断是否空二叉树
		return this.root == null;
	}

	public void inOrder() { // 中根次序遍历二叉排序树
		System.out.print("\n中根次序遍历二叉排序树：  ");
		if (root != null)
			inOrder(root);
		System.out.println();
	}

	// 中根次序遍历以 p 结点为根的子二叉树
	private void inOrder(BinaryNode<E> p) {
		if (p != null) {
			inOrder(p.left);
			System.out.print(p.data + " ");
			inOrder(p.right);
		}
	}

	// 查找值为 value 的结点， 非递归算法
	public BinaryNode<E> search(E value) {
		// 若查找成功返回结点， 否则返回 null
		if (value == null || !(value instanceof Comparable))
			return null;
		Comparable cObj = (Comparable) value;
		BinaryNode<E> p = this.root;
		// 当前结点不是 空， 且value 不等于当前结点的值
		while (p != null && cObj.compareTo(p.data) != 0) {
			System.out.print(p.data + "? "); // 显示查找经过的结点值，可省略
			if (cObj.compareTo(p.data) < 0) // 若value 较小
				p = p.left; // 进入左子树
			else
				p = p.right; // 进入右子树
		}
		return p;
	}

	// 插入结点， 不插入关键字重复的结点
	public boolean insert(E value) { // 若插入成功返回 true
		// 不能插入空对象 或者不可比较大小的对象
		if (value == null || !(value instanceof Comparable))
			return false;

		if (root == null) {
			root = new BinaryNode<E>(value); // 建立根结点
			return true;
		}
		// 插入 value 到以 root 为根的二叉排序树中
		return insert(value, root);
	}

	// 将 value 插入到以 p 为根的子树中
	private boolean insert(E value, BinaryNode<E> p) {
		// 递归算法
		Comparable cObj = (Comparable) value;
		if (cObj.compareTo(p.data) == 0) // 不插入关键字重复的结点
			return false;
		if (cObj.compareTo(p.data) < 0)
			if (p.left == null) {
				p.left = new BinaryNode<E>(value); // 建立叶子结点作为 p 的左孩子
				return true;
			} else
				return insert(value, p.left); // 将 value 插入到 p 的左子树中
		else if (p.right == null) {
			p.right = new BinaryNode<E>(value); // 建立叶子结点作为p的右孩子
			return true;
		} else
			return insert(value, p.right); // 将value插入到p的右子树中
	}

	// 删除结点
	public E remove(E value) {
		// 若删除成功返回删除结点值， 否则返回 null
		if (root == null || value == null || !(value instanceof Comparable))
			return null;
		return remove(value, root, null); // 在以 root 为根的二叉排序树中删除value
	}

	// 在以 p 为根的子树中删除 value   递归算法
	private E remove(E value, BinaryNode<E> p, BinaryNode<E> parent) { 
		// parent 是 p 的父母结点， 递归算法
		Comparable cObj = (Comparable) value;
		if (p != null) {
			if (cObj.compareTo(p.data) < 0)
				return remove(value, p.left, p);
			else if (cObj.compareTo(p.data) > 0)
				return remove(value, p.right, p);
			// 找到待删除结点 p
			else if (p.left != null && p.right != null) { // p 是二度结点
				// 寻找p 结点的右孩子的左孩子的左孩子...; 将前面找到的左孩子的值
				// 赋值给p, 此时值为 value 的结点已经被删除了， 最后删除找到的左孩子的值
				// 寻找 p 在中根次序下的后继结点 innext
				BinaryNode<E> innext = p.right;
				while (innext.left != null)
					innext = innext.left;
				p.data = innext.data; // 以后继结点值替换
				return remove(p.data, p.right, p);
			} else { // p是1度和叶子结点
				// 删除根结点，即p==root  (即 value = p.data)
				if (parent == null) {
					if (p.left != null) {
						root = p.left;
					} else
						root = p.right;
					return p.data; // 返回删除结点p值
				}
				if (p == parent.left) // p是parent的左孩子
					if (p.left != null)
						parent.left = p.left; // p是parent的左孩子
					else
						parent.left = p.right;
				else if (p.left != null)
					parent.right = p.left;
				else
					parent.right = p.right;
				return p.data;
			}
		}
		return null;
	}

	public static void main(String args[]) {
		BinarySortTree<Integer> bstree = new BinarySortTree<>();
		System.out.println("插入：    ");
		int[] key = { 54, 18, 66, 87, 36, 12, 54, 81, 15, 76, 57, 6, 40, 99, 85, 99 };
		for (int i = 0; i < key.length; i++)
			if (bstree.insertNode(new Integer(key[i])))
				// 若插入对象成功
				System.out.print(key[i] + " ");
		bstree.inOrder();

		Integer element = new Integer(key[key.length - 1]);
		System.out.println("查找" + element + ", " + (bstree.search(element) != null ? "" : "不") + "成功 ");
		element = new Integer(50);

		System.out.println("查找" + element + ", " + (bstree.search(element) != null ? "" : "不") + "成功 ");
		int value = 66;

		System.out.print("删除" + value + ", " + (bstree.remove(new Integer(value)) != null ? "" : "不") + "成功 ");
		bstree.inOrder();

	}

	// 思考题答案
	public BinaryNode<E> searchNode(E value) {
		// 查找值为value的结点
		if (value == null || !(value instanceof Comparable)) {
			return null;
		}
		return searchNode(value, root);
	}

	// 查找值为value的结点，递归算法
	public BinaryNode<E> searchNode(E value, BinaryNode<E> p) {
		if (p != null) {
			Comparable cObj = (Comparable) value;
			// 若两个对象相等，查找成功
			if (cObj.compareTo(p.data) == 0)
				return p;
			System.out.println(p.data + "? ");
			if (cObj.compareTo(p.data) < 0)
				return searchNode(value, p.left);// 在左子树中查找
			else
				return searchNode(value, p.right);// 在右子树中查找
		}
		return null;
	}

	// 插入结点，非递归算法
	public boolean insertNode(E value) {
		if (value == null || !(value instanceof Comparable))
			return false;
		if (root == null)
			root = new BinaryNode<E>(value); // 建立根结点
		else {
			BinaryNode<E> p = this.root, parent = null;
			Comparable cObj = (Comparable) value;
			while (p != null) {
				parent = p;
				if (cObj.compareTo(p.data) == 0)
					return false; // 不插入重复关键字
				if (cObj.compareTo(p.data) < 0)
					p = p.left;
				else
					p = p.right;
			}
			// 建立叶子结点p
			p = new BinaryNode<E>(value);
			if (cObj.compareTo(parent.data) < 0)
				parent.left = p; // p作为parent的左孩子
			else
				parent.right = p; // p作为parent的右孩子
		}
		return true;
	}
}


/*
插入： 54 18 66 87 36 12 81 15 76 57 6 40 99 85 
中根次序遍历二叉排序树：  6 12 15 18 36 40 54 57 66 76 81 85 87 99 
54? 66? 87? 81? 查找85, 成功 
54? 18? 36? 40? 查找50, 不成功 
删除66, 成功 
中根次序遍历二叉排序树：  6 12 15 18 36 40 54 57 76 81 85 87 99 

*/
