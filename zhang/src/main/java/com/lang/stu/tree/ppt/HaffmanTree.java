package com.lang.stu.tree.ppt;

//哈夫曼树
public class HaffmanTree {

	private int leafNum; // 叶子结点个数
	private HaffmanNode[] hnodes; // 哈夫曼树的结点数组

	// 构造指定权值集合的哈夫曼树
	public HaffmanTree(int[] weight) {
		int n = weight.length; // n个叶子结点
		this.leafNum = n;
		// n 个叶子结点的哈夫曼树共有 2n-1 个结点
		this.hnodes = new HaffmanNode[2 * n - 1];

		for (int i = 0; i < n; i++)
			this.hnodes[i] = new HaffmanNode(weight[i]);

		for (int i = 0; i < n - 1; i++) {
			// 构造 n-1 个 2度结点，每循环一次，构造一个2度结点
			int min1, min2, x1, x2;
			min1 = min2 = Integer.MAX_VALUE;
			// 选择最小和次最小权值，初值为最大权值

			x1 = x2 = -1; // 记录两个无父母的最小权值结点下标
			for (int j = 0; j < n + i; j++) { // 查找两个无父母的最小权值结点
				if (hnodes[j].weight < min1 && hnodes[j].parent == -1) {
					min2 = min1;
					x2 = x1;
					min1 = hnodes[j].weight; // min1 记下最小权值
					x1 = j; // x1 记下最小权值结点的下标
				} else if (hnodes[j].weight < min2 && hnodes[j].parent == -1) {
					min2 = hnodes[j].weight;
					x2 = j; // x2 记下次最小权值结点的下标
				}
			}
			hnodes[x1].parent = n + i;
			// 将找出的两棵权值最小的子树合并为一棵子树
			hnodes[x2].parent = n + i;

			this.hnodes[n + i] = new HaffmanNode();
			hnodes[n + i].weight = hnodes[x1].weight + hnodes[x2].weight;
			hnodes[n + i].left = x1;
			hnodes[n + i].right = x2;

		}
	}

	public String toString() {
		String str = "";
		for (int i = 0; i < this.hnodes.length && hnodes[i] != null; i++)
			str += i + ", " + this.hnodes[i].toString() + "\n";
		return str;
	}

	// 返回当前哈夫曼树的哈夫曼编码
	public String[] haffmanCode() {
		String[] code = new String[this.leafNum];
		for (int i = 0; i < this.leafNum; i++) { // 求n个叶子结点的哈夫曼编码
			code[i] = "";
			int child = i;
			int parent = hnodes[child].parent;
			while (parent != -1) { // 由叶结点向上直到根结点循环
				if (hnodes[parent].left == child)
					code[i] = "0" + code[i]; // 左孩子结点编码为0
				else
					code[i] = "1" + code[i]; // 右孩子结点编码为1
				child = parent;
				parent = hnodes[child].parent;
			}
		}
		return code;
	}

	public static void main(String[] args) {
		int[] weight = { 5, 29, 7, 8, 14, 23, 3, 11 }; // 指定权值集合
		HaffmanTree htree = new HaffmanTree(weight);
		System.out.println("哈夫曼树的结点数组:  \n" + htree.toString());
		String[] code = htree.haffmanCode();
		System.out.println("哈夫曼编码:");
		for (int i = 0; i < code.length; i++)
			System.out.println(code[i]);
		
		for (int i = 0; i < code.length; i++)
			System.out.println(weight[i] + "  对应的哈夫曼编码    " + code[i]);
	}

}
