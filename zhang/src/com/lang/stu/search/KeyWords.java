package com.lang.stu.search;

public class KeyWords {

	private static String[] keywords = { "abstract", "boolean", "break", "byte", "case", "catch", "char", "class",
			"continue", "default", "do", "double", "else", "extends", "false", "final", "finally", "float", "for", "if",
			"implements", "import", "instanceof", "int", "interface", "long", "native", "new", "null", "package",
			"private", "protected", "public", "return", "short", "static", "super", "switch", "synchronized", "this",
			"throw", "throws", "transient", "true", "try", "void", "volatile", "while" }; // 关键字表
	
	// 索引表
	private static IndexItem index[] = {
			new IndexItem("a",0), new IndexItem("b",1),
	        new IndexItem("c",4), new IndexItem("d",9),
	        new IndexItem("e",12), new IndexItem("f",14),
	        new IndexItem("i",19), new IndexItem("l",25), 
	        new IndexItem("n",26), new IndexItem("p",29), 
	        new IndexItem("r",33),new IndexItem("s",34),
	        new IndexItem("t",39), new IndexItem("v",45), 
	        new IndexItem("w",46)
	};
	
	// 内部类， 私有成员
	private static class IndexItem implements Comparable<IndexItem> {
		String first; // 关键字的首字母
		int start; // 首字母相同的关键字块在主表中的起始下标

		public IndexItem(String first, int i) {
			this.first = first;
			start = i;
		}
		
		public IndexItem(String first) {
			this(first, -1);
		}

		public String toString() {
			return this.first;
		}
				
		//约定两个索引项比较大小的规则，实现Comparable接口
		public int compareTo(IndexItem item) {
			// 按首字母比较大小
			return this.first.compareTo(item.first);
		}
	}

	public static boolean isKeyword(String str) {
		if (str != null) {
			IndexItem item = new IndexItem(str.substring(0, 1));
			int pos = BSArray.binarySearch(index, item);
			int left = index[pos].start;
			int high = index[pos + 1].start - 1;
			int find = BSArray.binarySearch(keywords, str, left, high);
			return find >= 0;
		}
		return false;
	}

	public static void main(String[] args) {
		String str = "final";
		System.out.println(str + (isKeyword(str) ? "" : "不") + "是关键字");
		str = "length";
		System.out.println(str + (isKeyword(str) ? "" : "不") + "是关键字");
	}
	/*
	程序运行结果如下：
	l? d? f? finally? false? final? final是关键字
	l? long? length不是关键字

	*/
}
