package com.lang.stu.tree;

//中序线索二叉树
public class ThreadBinaryTree<E> {

	private ThreadBinaryNode<E> root;

	// 构造空线索二叉树
	public ThreadBinaryTree() {
		root = null;
	}

	// 以标明空子树的先根序列构造一棵二叉树并进行中序线索化
	public ThreadBinaryTree(E[] preorder) {
		root = create(preorder);
		inorderThread(root); // 中序线索化二叉树
		System.out.print("中序线索化：\r\n" + instr);
	}

	private int i = 0;

	// 创建一棵子树，当前结点值是 preorder[i]
	private ThreadBinaryNode<E> create(E[] preorder) {
		// 返回所创建子树的根结点
		ThreadBinaryNode<E> p = null;
		if (i < preorder.length) {
			E elem = preorder[i];
			i++;
			if (elem != null) {
				p = new ThreadBinaryNode<>(elem); // 建立 p 节点
				p.left = create(preorder); // 建立p的左子树
				p.right = create(preorder); // 建立p的右子树
			}
		}
		return p;
	}

	private ThreadBinaryNode<E> front = null;
	private String instr = "";

	// 中序线索化以 p 结点为根的子树，p 的前驱结点是 front
	private void inorderThread(ThreadBinaryNode<E> p) {
		if (p != null) {
			inorderThread(p.left); // 中序线索化p的左子树
			if (p.left == null) { // 若p的左子树为空
				p.ltag = 1; // 设置左线索标记
				p.left = front; // 设置p.left为指向前驱front的线索
			}
			if (p.right == null) // 若p的右子树为空
				p.rtag = 1; // 设置右线索标记
			if (front != null && front.rtag == 1)
				front.right = p; // 设置前驱front.right为指向后继p的线索

			// 显示中间结果
			if (front != null)
				instr += "front=" + front.data + "\t";
			else
				instr += "front=null\t";
			instr += "p=" + p.data + "\r\n";

			front = p;
			inorderThread(p.right); // 中序线索化p的右子树
		}
	}

	// 返回 p 在中根次序下的后继结点
	public ThreadBinaryNode<E> inNext(ThreadBinaryNode<E> p) {
		if (p.rtag == 1) // 若右子树为空
			p = p.right; // p.right就是指向后继结点的线索
		else {
			p = p.right; // 进入右子树
			while (p.ltag == 0) // 找到最左边的后代结点
				p = p.left;
		}
		return p;
	}

	// 中根次序遍历 中序线索二叉树，非递归算法
	public void inOrder() {
		ThreadBinaryNode<E> p = root;
		if (p != null) {
			System.out.print("中根次序遍历：  ");
			while (p.ltag == 0) //左子树的左子树的左子树的....
				p = p.left; // 找到根的最左边的后代结点

			do {
				System.out.print(p.data + " ");
				p = inNext(p); // 返回p在中根次序下的后继结点
			} while (p != null);
			System.out.println();
		}
	}

	// 返回 p 在先根次序下的后继结点
	public ThreadBinaryNode<E> preNext(ThreadBinaryNode<E> p) {
		if (p.ltag == 0) // 若左子树非空
			p = p.left; // 左孩子就是p的后继结点
		else { // 否则，后继是右兄弟或某个中序祖先的右孩子
			if (p.rtag == 0) // 若左子树为空而右子树非空
				p = p.right; // 右孩子是p的后继结点
			else {
				while (p.rtag == 1 && p.right != null) // 叶子结点  || 节点的rtag=1, right是null 线索二叉树最后一个节点
					p = p.right; // 后继是其某个中序祖先的右孩子
				p = p.right;
			}
		}
		return p;
	}

	// 先根次序遍历中序线索二叉树 非递归算法
	public void preOrder() {
		ThreadBinaryNode<E> p = root;
		if (p != null) {
			System.out.print("先根次序遍历：  ");
			do {
				System.out.print(p.data + " ");
				p = preNext(p);
			} while (p != null);
			System.out.println();
		}
	}

	// 返回 p 在中根次序下的前驱结点
	public ThreadBinaryNode<E> inPrevious(ThreadBinaryNode<E> p) {
		if (p.ltag == 1) // 若左子树为空
			p = p.left; // p.left就是指向前驱结点的线索
		else { // 若左子树非空
			p = p.left; // 进入左子树
			while (p.rtag == 0) // 找到最右边的子孙结点 | 右子树的右子树的右子树...
				p = p.right;
		}
		return p;
	}
	
	// 中根次序遍历中序线索二叉树，非递归算法
	public void inOrder_previous() {
		ThreadBinaryNode<E> p = root;
		if (p != null) {
			System.out.print("中根次序遍历（反序）：  ");
			while (p.rtag == 0)
				p = p.right; // 找到根的最右边子孙结点 | 反序的
			do {
				System.out.print(p.data + " ");
				p = inPrevious(p); // 返回 p 的前驱结点
			} while (p != null);
			System.out.println();
		}
	}
	

	// 返回 p 在后根次序下的前驱结点
	public ThreadBinaryNode<E> postPrevious(ThreadBinaryNode<E> p) {
		if (p.rtag == 0) // 右子树非空
			p = p.right; // 右孩子是p的前驱结点
		else { // 否则，前驱是左兄弟或其某个中序祖先的左孩子
			while (p.ltag == 1 && p.left != null)
				p = p.left; // 寻找其某个中序祖先
			p = p.left; // 左孩子是p的前驱结点
		}
		return p;
	}

	// 后根次序遍历中序线索二叉树，非递归算法
	public void postOrder_previous() {
		ThreadBinaryNode<E> p = root;
		if (p != null) {
			System.out.print("后根次序遍历（反序）：  ");
			do {
				System.out.print(p.data + " ");
				p = postPrevious(p); // 返回p在后根次序下的前驱结点
			} while (p != null);
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		String[] preorder = { "A", "B", "D", null, null, "E", "G", null, null, null, "C", "F", null, "H", null, null,
				"K" };
		// 创建中序线索二叉树
		ThreadBinaryTree<String> tbtree = new ThreadBinaryTree<>(preorder);
		tbtree.preOrder(); // 先根次序遍历
		tbtree.inOrder(); // 中根次序遍历
		tbtree.inOrder_previous(); // 中根次序遍历（求前驱）
		tbtree.postOrder_previous(); // 后根次序遍历（求前驱）
	}
}
/*

中序线索化：
front=null	p=D
front=D	p=B
front=B	p=G
front=G	p=E
front=E	p=A
front=A	p=F
front=F	p=H
front=H	p=C
front=C	p=K
  
先根次序遍历：  A B D E G C F H K 
中根次序遍历：  D B G E A F H C K 
中根次序遍历（反序）：  K C H F A E G B D 
后根次序遍历（反序）：  A C K F H B E G D 


*/
/*
先根次序遍历：  A B D E G C F H K 
中根次序遍历：  D B G E A F H C K 
中根次序遍历（反序）：  K C H F A E G B D 
后根次序遍历（反序）：  A C K F H B E G D 
*/


