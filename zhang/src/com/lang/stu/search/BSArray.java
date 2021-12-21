package com.lang.stu.search;

public class BSArray {

	// 输出数组元素
	public static void print(int[] table) {
		if (table != null)
			for (int i = 0; i < table.length; i++)
				System.out.print(" " + table[i]);
		System.out.println();
	}

	/*
	// 折半查找算法，数组元素已按升序排列           非递归算法
	public static int binarySearch(int[] table, int value) {
		// 若查找成功返回元素下标，否则返回-1
		if (table != null) { // 查找范围的下界和上界
			int low = 0, high = table.length - 1;
			
			if(table[low] == value) return low;
			if(table[high] == value) return high;
			
			while (low <= high) {  //边界有效
				int mid = (low + high) / 2;
				System.out.print(table[mid] + "? "); // 中间位置，当前比较元素位置
				if (table[mid] == value) {
					return mid; // 查找成功
				} else if (table[mid] > value) // 给定值小
					high = mid - 1; // 查找范围缩小到前半段
				else
					low = mid + 1; // 查找范围缩小到后半段
			}
		}
		return -1; // 查找不成功
	}
	*/

	// 折半查找算法，数组元素已按升序排列    非递归算法
	public static int binarySearch(Comparable[] table, Comparable cObj) {
		// 在table数组中查找cobj，若查找成功返回元素下标，否则返回-1
		return binarySearch(table, cObj, 0, table.length - 1);

	}

	//折半查找算法，数组元素已按升序排列
	public static int binarySearch(Comparable[] table, Comparable cObj, int low, int high) {
		// low、high指定查找范围的下界和上界
		if (table != null && cObj != null) {
			while (low <= high) { // 边界有效
				int mid = (low + high) / 2; // 中间位置，当前比较元素位置
				System.out.print(table[mid] + "? ");
				if (cObj.compareTo(table[mid]) == 0) // 对象比较大小
					return mid;
				else if (cObj.compareTo(table[mid]) < 0)
					high = mid - 1; // 查找范围缩小到前半段
				else
					low = mid + 1; // 查找范围缩小到后半段
			}
		}
		return -1; // 查找不成功
	}

	public static void main(String[] args) {
		int[] table = { 8, 17, 26, 32, 40, 72, 87, 99 }; // 已按升序排序
		System.out.print("\n已按升序排序的关键字序列: ");
		print(table);

		int key = 99;
		System.out.println("折半查找 " + key + ", " + ((binarySearch(table, key) == -1) ? "不" : "") + "成功");
		key = 25;
		System.out.println("折半查找 " + key + ", " + ((binarySearch(table, key) == -1) ? "不" : "") + "成功");
	}

	// 第8章习题
	// 递归算法
	public static int binarySearch(int[] table, int value) {
		if (table != null)
			return binarySearch(table, value, 0, table.length - 1);
		return -1;
	}

	private static int binarySearch(int[] table, int value, int low, int high) {
		if (low <= high) {
			int mid = (low + high) / 2;
			System.out.print(table[mid] + "? ");
			if (table[mid] == value)
				return mid;
			else if (table[mid] > value)
				return binarySearch(table, value, low, mid - 1);
			else
				return binarySearch(table, value, mid + 1, high);
		}
		return -1;
	}
}

/* 
程序运行结果如下：
已按升序排序的关键字序列:  8 17 26 32 40 72 87 99 
32? 72? 87? 99? 折半查找 99, 成功 
32? 17? 26? 折半查找 25, 不成功 

*/
