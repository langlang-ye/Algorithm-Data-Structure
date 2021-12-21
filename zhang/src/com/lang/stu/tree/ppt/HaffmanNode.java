package com.lang.stu.tree.ppt;

//哈夫曼树的结点类

public class HaffmanNode {

	int weight; // 权值
	int parent, left, right; // 父母结点和左右孩子下标

	public HaffmanNode(int weight) {
		this.weight = weight;
		this.parent = this.left = this.right = -1;
	}

	public HaffmanNode() {
		this(0);
	}

	public String toString(){
        return this.weight+", "+this.parent+", "+this.left+", "+this.right;
    }

}
