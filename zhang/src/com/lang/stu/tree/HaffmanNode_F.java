//哈夫曼树的结点类
package com.lang.stu.tree;

public class HaffmanNode_F {

	int weight; // 权值
	int flag; // 标记
	int parent; // 双亲结点下标
	int left; // 左孩子下标
	int right; // 右孩子下标

	// 构造权值为 weight 的哈夫曼树 haffTree
	public HaffmanNode_F(int weight) {
		this.weight = weight;
		this.flag = 0;
		this.parent = -1;
		this.left = -1;
		this.right = -1;
	}

	public HaffmanNode_F() {
		this(0);
	}

	public String toString() {
		return this.weight + ", " + this.flag + ", " + this.parent + ", " + this.left + ", " + this.right;
	}

}
