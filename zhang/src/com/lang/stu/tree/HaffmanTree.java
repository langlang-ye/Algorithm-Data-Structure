//构造哈夫曼树并获得哈夫曼编码
package com.lang.stu.tree;

//哈夫曼树的结点类
class HaffmanNode {
	int weight;
	int parent, left, right;

	public HaffmanNode(int weight) {
		this.weight = weight;
		this.parent = -1;
		this.left = -1;
		this.right = -1;
	}

	public HaffmanNode() {
		this(0);
	}

	public String toString() {
		return this.weight + ", " + this.parent + ", " + this.left + ", " + this.right;
	}
}

// 哈夫曼树
public class HaffmanTree {

	private int leafNum; // 叶子结点个数
	private HaffmanNode[] hnodes; // 哈夫曼树的结点数组

	// 构造指定权值集合的哈夫曼树
	public HaffmanTree(int[] weight) {
		int n = weight.length; // n个叶子结点
		this.leafNum = n;
		this.hnodes = new HaffmanNode[2 * n - 1]; // n个叶子结点的哈夫曼树共有2n-1个结点

		// 结点数组初始化有n个叶子结点
		for (int i = 0; i < n; i++)
			this.hnodes[i] = new HaffmanNode(weight[i]);

		// 构造 n-1 个 2度结点，每循环一次，构造一个2度结点
		for (int i = 0; i < n - 1; i++) {
			int min1, min2, x1, x2;
			min1 = min2 = Integer.MAX_VALUE; // 选择最小和次最小权值，初值为最大权值
			x1 = x2 = -1; // 记录两个无父母的最小权值结点下标
			// 查找两个无父母的最小权值结点
			for (int j = 0; j < n + i; j++) {
				// hnodes[j].parent == -1 确保对应的节点无父母
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
			hnodes[x2].parent = n + i;

			 // 将找出的两棵权值最小的子树合并为一棵子树
			this.hnodes[n + i] = new HaffmanNode();
			hnodes[n + i].weight = hnodes[x1].weight + hnodes[x2].weight;
			hnodes[n + i].left = x1;
			hnodes[n + i].right = x2;
		}
	}
	public String toString(){
		String str = "";
		for (int i = 0; i < this.hnodes.length ; i++)
			str += i + ", " + this.hnodes[i].toString() + "\n";
		return str;
	}
	
	// 返回当前哈夫曼树的哈夫曼编码
	public String[] haffmanCode() {
		String[] code = new String[this.leafNum];
		// 求 n 个叶子结点的哈夫曼编码
		for (int i = 0; i < this.leafNum; i++) {
			code[i] = "";
			int child = i;
			int parent = hnodes[child].parent;
			// 由叶结点向上直到根结点循环
			while (parent != -1) {
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
		int[] weight = { 2,3,5,7,11,13,17,19,23,29,31,37,41 }; // 指定权值集合
		HaffmanTree htree = new HaffmanTree(weight);
		System.out.println("哈夫曼树的结点数组:\n" + htree.toString());

		String[] code = htree.haffmanCode();
		System.out.println("哈夫曼编码:");
		for (int i = 0; i < code.length; i++)
			System.out.println(code[i]);
	}
}

/*

int[] weight={5,29,7,8,14,23,3,11};//严书
哈夫曼树的结点数组:
0, 5, 8, -1, -1
1, 29, 13, -1, -1
2, 7, 9, -1, -1
3, 8, 9, -1, -1
4, 14, 11, -1, -1
5, 23, 12, -1, -1
6, 3, 8, -1, -1
7, 11, 10, -1, -1
8, 8, 10, 6, 0
9, 15, 11, 2, 3
10, 19, 12, 8, 7
11, 29, 13, 4, 9
12, 42, 14, 10, 5
13, 58, 14, 1, 11
14, 100, -1, 12, 13

哈夫曼编码:
0001
10
1110
1111
110
01
0000
001

int[] weight = {2,4,5,7};                //指定权值集合
哈夫曼树的结点数组:
0, 2, 4, -1, -1
1, 4, 4, -1, -1
2, 5, 5, -1, -1
3, 7, 6, -1, -1
4, 6, 5, 0, 1
5, 11, 6, 2, 4
6, 18, -1, 3, 5

哈夫曼编码:
110
111
10
0


int[] weight = {7,5,2,4};//严书
哈夫曼树的结点数组:
0, 7, 6, -1, -1
1, 5, 5, -1, -1
2, 2, 4, -1, -1
3, 4, 4, -1, -1
4, 6, 5, 2, 3
5, 11, 6, 1, 4
6, 18, -1, 0, 5

哈夫曼编码:
0
10
110
111

int[] weight = {1, 3, 5, 7};
哈夫曼树的结点数组:
0, 1, 4, -1, -1
1, 3, 4, -1, -1
2, 5, 5, -1, -1
3, 7, 6, -1, -1
4, 4, 5, 0, 1
5, 9, 6, 4, 2
6, 16, -1, 3, 5

哈夫曼编码:
100
101
11
0

int[] weight = {2,3,5,7,11,13,17,19,23,29,31,37,41};//张乃孝
哈夫曼树的结点数组:
0, 2, 13, -1, -1
1, 3, 13, -1, -1
2, 5, 14, -1, -1
3, 7, 15, -1, -1
4, 11, 16, -1, -1
5, 13, 16, -1, -1
6, 17, 17, -1, -1
7, 19, 18, -1, -1
8, 23, 18, -1, -1
9, 29, 19, -1, -1
10, 31, 20, -1, -1
11, 37, 21, -1, -1
12, 41, 21, -1, -1
13, 5, 14, 0, 1
14, 10, 15, 2, 13
15, 17, 17, 3, 14
16, 24, 19, 4, 5
17, 34, 20, 6, 15
18, 42, 22, 7, 8
19, 53, 22, 16, 9
20, 65, 23, 10, 17
21, 78, 23, 11, 12
22, 95, 24, 18, 19
23, 143, 24, 20, 21
24, 238, -1, 22, 23

哈夫曼编码:
1011110
1011111
101110
10110
0100
0101
1010
000
001
011
100
110
111

*/

